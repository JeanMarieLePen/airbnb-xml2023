package com.example.reservationservice.services;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ClusterInfo;
import org.springframework.stereotype.Service;

import com.example.reservationservice.dtos.RezervacijaDTO;
import com.example.reservationservice.exceptions.AccommodationUnavailableException;
import com.example.reservationservice.exceptions.Neo4jDatabaseException;
import com.example.reservationservice.exceptions.PriceCalculationException;
import com.example.reservationservice.exceptions.ReservationCreationException;
import com.example.reservationservice.exceptions.ReservationTerminException;
import com.example.reservationservice.mappers.RezervacijaMapper;

import com.example.reservationservice.model.Rezervacija;
import com.example.reservationservice.model.StatusRezervacije;
import com.example.reservationservice.model.Termin;
import com.example.reservationservice.neo4j.model.Korisnik;
//import com.example.reservationservice.neo4j.model.OcenaSmestaj;
import com.example.reservationservice.neo4j.model.Smestaj;
import com.example.reservationservice.repositories.Neo4JKorisnikRep;
import com.example.reservationservice.repositories.Neo4JSmestajRep;
import com.example.reservationservice.repositories.RezervacijaRep;
import com.google.protobuf.Timestamp;
import com.xml2023.mainapp.AnySmestajBelongToHostRequest;
import com.xml2023.mainapp.AnySmestajBelongToHostResponse;
import com.xml2023.mainapp.DobioStatusIstaknutogRequest;
import com.xml2023.mainapp.DobioStatusIstaknutogResponse;
import com.xml2023.mainapp.GreetingServiceGrpc;
import com.xml2023.mainapp.GreetingServiceGrpc.GreetingServiceBlockingStub;
import com.xml2023.mainapp.HostBasicDTO;
import com.xml2023.mainapp.KorisnikGrpcGrpc;
import com.xml2023.mainapp.KorisnikGrpcGrpc.KorisnikGrpcBlockingStub;
import com.xml2023.mainapp.KorisnikRequest;
import com.xml2023.mainapp.KorisnikResponse;
import com.xml2023.mainapp.NekoOtkazaoRequest;
import com.xml2023.mainapp.NekoOtkazaoResponse;
import com.xml2023.mainapp.NekoRezervisaoRequest;
import com.xml2023.mainapp.NekoRezervisaoResponse;
import com.xml2023.mainapp.NovaRezervacijaNotifikacijaRequest;
import com.xml2023.mainapp.NovaRezervacijaNotifikacijaResponse;
import com.xml2023.mainapp.OcenaSmestajDTO;
import com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaRequest;
import com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaResponse;
import com.xml2023.mainapp.SmestajDTO;
import com.xml2023.mainapp.SmestajGrpcGrpc;
import com.xml2023.mainapp.SmestajGrpcGrpc.SmestajGrpcBlockingStub;
import com.xml2023.mainapp.SmestajIdsForHostRequest;
import com.xml2023.mainapp.SmestajIdsForHostResponse;
import com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest;
import com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse;
import com.xml2023.mainapp.TerminDTO;
import com.xml2023.mainapp.TerminOslobodiRequest;
import com.xml2023.mainapp.TerminOslobodiResponse;
import com.xml2023.mainapp.TerminZauzmiRequest;
import com.xml2023.mainapp.TerminZauzmiResponse;
import com.xml2023.mainapp.getHostRequest;
import com.xml2023.mainapp.getHostResponse;
import com.xml2023.mainapp.getListaSmestajaByUserIdRequest;
import com.xml2023.mainapp.getListaSmestajaByUserIdResponse;
import com.xml2023.mainapp.getOceneBySmestajIdRequest;
import com.xml2023.mainapp.getOceneBySmestajIdResponse;
import com.xml2023.mainapp.getSmestajByIdRequest;
import com.xml2023.mainapp.getSmestajByIdResponse;
import com.xml2023.mainapp.reservationApprovedNotificationRequest;
import com.xml2023.mainapp.reservationApprovedNotificationResponse;
import com.xml2023.mainapp.rezOtkazanaHostRequest;
import com.xml2023.mainapp.rezOtkazanaHostResponse;
import com.xml2023.mainapp.rezOtkazanaRequest;
import com.xml2023.mainapp.rezOtkazanaResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class RezervacijaService {	
	@Autowired
	private RezervacijaRep rezervacijaRep;

	@Autowired 
	private RezervacijaMapper rezMapper;
	@Autowired CenaService cenaServ;
	
	@Autowired
	private Neo4JKorisnikRep neo4jKorisnikRep;
	@Autowired
	private Neo4JSmestajRep neo4jSmestajRep;
	
	private Runnable undoReservationCreation;
    private Runnable undoTerminBooking;
    private Runnable undoNeo4jStep;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(RezervacijaService.class);
	
    private String reservationHost= "reservation";
	private String smestajHost = "smestaj";
	private String reglogHost= "reglog";
    
    //PROBAJ JOS NEKI KORAK DA RAZLOZIS AKO MISLIS DA TREBA; OD MENE OVOLIKO ZA SAD
	public RezervacijaDTO makeReservationSaga(String userId, String smestajId, RezervacijaDTO r) throws Exception{
		System.out.println("MAKE RESERVATION SAGA START");
		try {
			//korak 1: Dostupnost
			checkAvailability(smestajId, r.getOdDatum(), r.getOdDatum());
			LOGGER.info("Step 1: Provera dostupnosti zavrsena");
			
			//korak 2: Racunanje cene
			float cena = calculatePrice(smestajId, r.getOdDatum(), r.getDoDatum());
			LOGGER.info("Step 2: Racunanje cene zavrseno");
			
			//korak 3: Kreiranje rezervacije
			Rezervacija rez = createReservationStep(userId, smestajId, r, cena);
			LOGGER.info("Step 3: Kreiranje rezervacije na osnovu prosledjenog DTO-a zavrseno");
			
			this.undoReservationCreation = () -> {
				try {
					rezervacijaRep.delete(rez);
				}catch(Exception e) {
					LOGGER.error("Greska revertu rezervacije", e);
				}
			};			
			
			
			//zauzimanje termina za rezervaciju
			boolean terminZauzet = makeReservationStep(smestajId, rez, r.getOdDatum(), r.getDoDatum());
			LOGGER.info("Step 4: Zauzimanje termina zavrseno");
			
			 this.undoTerminBooking = () -> {
				try {
					
					//mi ne koristimo terminId za oslobadjanje termina ali ako je neophodno, mozemo i tako
					//u prevodu  terminId ne sluzi nicemu ovde
					this.oslobodiTermin(rez);
				}catch (Exception e) {
					LOGGER.error("Greska pri revertu termina", e);
				}
			};
			
			
			//upisivanje u neo4j bazu
			this.neo4jStep(userId, smestajId, rez);
			LOGGER.info("Step 5: Cuvanje u Neo4j bazu zavrseno");
			
			this.undoNeo4jStep = () -> {
	            try {
	            	Korisnik kor = this.neo4jKorisnikRep.findById(userId).orElse(null);
	        		Smestaj sme = this.neo4jSmestajRep.findById(smestajId).orElse(null);
	        		if(kor != null) {
	        			kor.getRezervisani().remove(sme);
	        		}
	        		this.neo4jKorisnikRep.save(kor);
	            } catch (Exception e) {
	            	LOGGER.error("Greska pri revertu upisa u neo4j bazu", e);
	            }
	        };
			
			//cuvanje rezervacije
			rezervacijaRep.save(rez);
			LOGGER.info("Step 6: Uspesno cuvanje u bazu");
			//povratna vrednost
			return rezMapper.toDTO(rez);
		}catch (AccommodationUnavailableException e) {
			//nema hendlovanja jer se ne desava neka promena nad bazom ili nesto slicno
			LOGGER.error("Step 1 failed: Greska u proveri dostupnosti", e);
		}catch (PriceCalculationException e) {
			//nema hendlovanja jer se ne desava neka promena nad bazom ili nesto slicno
			LOGGER.error("Step 2 failed: Greska pri racunanju cene", e);
		}catch(ReservationCreationException e) {
			LOGGER.error("Step 3 failed: Greska pri kreiranju rezervacije na osnovu prosledjeno DTO-a", e);
			this.undoReservationCreation.run();
			
		}catch(ReservationTerminException e){
			LOGGER.error("Step 4 failed: Greska pri zauzimanju termina", e);
			this.undoReservationCreation.run();
			this.undoTerminBooking.run();
			
		}catch(Neo4jDatabaseException e) {
			LOGGER.error("Step 5 failed: Greska pri pokusaju upisa u neo4j bazu", e);
			this.undoReservationCreation.run();
			this.undoTerminBooking.run();
			this.undoNeo4jStep.run();
			
		}
		return null;
	}
	
	public boolean oslobodiTermin(Rezervacija r) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(smestajHost, 7977).usePlaintext().build();
		SmestajGrpcBlockingStub smestajBlockingStub = SmestajGrpcGrpc.newBlockingStub(channel);
		TerminOslobodiRequest rqst = TerminOslobodiRequest.newBuilder().setSmestajId(r.getSmestaj()).setTermin(mapTermin(r.getOdDatum(), r.getDoDatum())).build();
		TerminOslobodiResponse rspns = smestajBlockingStub.oslobodiTermin(rqst);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		if(rspns.getOslobodjen()) {
			r.setStatus(StatusRezervacije.OTKAZANA);
		}
		return true;
	}
	
	public void checkAvailability(String smestajId, LocalDateTime odDatum, LocalDateTime doDatum) throws AccommodationUnavailableException {
		Collection<Rezervacija> rezRezervisane = rezervacijaRep.findAllBySmestajAndStatus(smestajId, StatusRezervacije.REZERVISANA).orElse(new ArrayList<Rezervacija>());
		//Collection<Rezervacija> rezPending=rezervacijaRep.findBySmestajIdAndStatus(smestajId, StatusRezervacije.PENDING).orElse(new ArrayList<Rezervacija>());
		Termin t= new Termin(odDatum, doDatum);
		if(rezRezervisane.size()>0) {
			for(Rezervacija rez: rezRezervisane) {
				Boolean preklapanje= t.preklapanjeSa(new Termin(rez.getOdDatum(),rez.getDoDatum()));
				if(preklapanje) {
					throw new AccommodationUnavailableException("Smestaj nije dostupan za taj opseg datuma.");
				}
			}
		}
		//GRPC: provera smestaja, upitaj za nedostupne termine i cenovnik izabranog smestaja
		try {
			SmestajDTO smestaj= getSmestaj(smestajId);
			if(smestaj.getNedostupniCount()>0) {
				for(TerminDTO ter : smestaj.getNedostupniList()) {
					Boolean preklop = t.preklapanjeSa(new Termin(convertFromTimeStamp(ter.getPocetak()), convertFromTimeStamp(ter.getKraj())));
					if(preklop) {
						throw new AccommodationUnavailableException("Smestaj nije dostupan za taj opseg datuma.");
					}
				}
			}
		}catch(Exception e) {
			throw new AccommodationUnavailableException("Smestaj Service nije dostupan.");
		}
		
	}
//	public void getSmestajStep();
	
	public float calculatePrice(String smestajId , LocalDateTime odDatum, LocalDateTime doDatum) throws PriceCalculationException{
		try {
			SmestajDTO smestaj = getSmestaj(smestajId);
			float cena = cenaServ.ukupnaCena(smestaj, odDatum, doDatum);
			return cena;
		}catch(Exception e) {
			throw new PriceCalculationException("Smestaj service nije dostupan.");
		}
	}
	
	private Rezervacija createReservationStep(String userId, String smestajId, RezervacijaDTO r, float cena) throws ReservationCreationException{
		Rezervacija rez = rezMapper.fromDTO(r, smestajId, userId);
		if(rez == null) {
			throw new ReservationCreationException("Greska pri formiranju entiteta rezervacije.");
		}
		rez.setCena(cena);
		rez.setStatus(StatusRezervacije.PENDING);
		
		try {
			rezervacijaRep.save(rez);
		}catch(Exception e) {
			throw new ReservationCreationException("Greska pri formiranju entiteta rezervacije.");
		}
		
//		this.rezervacijaRep.save(rez);
		return rez;
	}
	
	private boolean makeReservationStep(String smestajId, Rezervacija r, LocalDateTime odDatum, LocalDateTime doDatum) {
		boolean automatski = false;
		try {
			SmestajDTO smestaj = getSmestaj(smestajId);
			String hostId = smestaj.getVlasnik();
			HostBasicDTO owner= getHostBasic(hostId);
			System.out.println("REZERVACIJE SE OBRADJUJU AUTOMATSKI: " + owner.getRezAutomatski());
			automatski = owner.getRezAutomatski();
		}catch(Exception e) {
			System.out.println("CATCH BLOCK ZA makeReservationStep");			
			throw new Neo4jDatabaseException("SmestajService je nedostupan. Revert");
		}
		
		
		
		if(automatski == true) {
			System.out.println("USAO U TRUE LOOP");
			ManagedChannel channel = ManagedChannelBuilder.forAddress(smestajHost, 7977).usePlaintext().build();
			SmestajGrpcBlockingStub smestajBlockingStub = SmestajGrpcGrpc.newBlockingStub(channel);
			TerminZauzmiRequest rqst = TerminZauzmiRequest.newBuilder().setSmestajId(smestajId).setTermin(mapTermin(r.getOdDatum(), r.getDoDatum())).build();
			TerminZauzmiResponse rspns = smestajBlockingStub.zauzmiTermin(rqst);
			channel.shutdown();

			boolean terminated = false;
			while (!terminated) {
			  try {
			    // Wait for the channel to terminate gracefully
			    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
			  } catch (InterruptedException e) {
			    // Handle the exception if necessary
			    e.printStackTrace();
			  }
			}
			if(rspns.getZauzet()) {
				r.setStatus(StatusRezervacije.REZERVISANA);			
			}else {
				throw new ReservationTerminException("Greska pri zauzimanju termina");
			}
			return true;
		}else {
			System.out.println("USAO U FALSE LOOP");
			r.setStatus(StatusRezervacije.PENDING);
			return true;
		}
	}
	
//	private String makeReservationStep(String smestajId, Rezervacija r, LocalDateTime odDatum, LocalDateTime doDatum) throws ReservationTerminException {
//		SmestajDTO smestaj = getSmestaj(smestajId);
//		ManagedChannel channel = ManagedChannelBuilder.forAddress(smestajHost, 7977).usePlaintext().build();
//		SmestajGrpcBlockingStub smestajBlockingStub = SmestajGrpcGrpc.newBlockingStub(channel);
//		TerminZauzmiRequest rqst = TerminZauzmiRequest.newBuilder().setSmestajId(smestajId).setTermin(mapTermin(r.getOdDatum(), r.getDoDatum())).build();
//		TerminZauzmiResponse rspns = smestajBlockingStub.zauzmiTermin(rqst);
//		channel.shutdown();
//
//		boolean terminated = false;
//		while (!terminated) {
//		  try {
//		    // Wait for the channel to terminate gracefully
//		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
//		  } catch (InterruptedException e) {
//		    // Handle the exception if necessary
//		    e.printStackTrace();
//		  }
//		}
//		if(rspns.getZauzet()) {
//			r.setStatus(setStatusRez(smestaj.getVlasnik()));			
//		}else {
//			throw new ReservationTerminException("Greska pri zauzimanju termina");
//		}
//		return rspns.getTerminId();
//	}
	
	private void neo4jStep(String userId, String smestajId, Rezervacija rez) throws Neo4jDatabaseException{
		SmestajDTO smestaj = getSmestaj(smestajId);
		String idVlasnika = smestaj.getVlasnik();
		//utvrdi da li je Host zavredio status istaknutog 
		boolean isIstaknuti = determineIfIstaknuti(idVlasnika);
		//ako jeste, izmeni mu status u true, ako nije izmeni u false
		boolean statusPromenjen = izmeniStatusHosta(idVlasnika, isIstaknuti);
		System.out.println("USPESNO PROMENJEN STATUS HOSTA U: " + isIstaknuti);
		
		//U GRAF BAZU SE UPISUJE REZERVISANI SMESTAJ OBOSTRANO;
		System.out.println("SLEDI POKUSAJ CITANJA IZ NEO4j baze");
		try {
			Korisnik kor = this.neo4jKorisnikRep.findById(userId).orElse(null);
			Smestaj sme = this.neo4jSmestajRep.findById(smestajId).orElse(null);
		}catch(Exception e) {
			System.out.println("CATCH BLOK ZA NEO4J");
			throw new Neo4jDatabaseException("Neo4jDatabase je nedostupna. Revert");
		}
		Korisnik kor = this.neo4jKorisnikRep.findById(userId).orElse(null);
		Smestaj sme = this.neo4jSmestajRep.findById(smestajId).orElse(null);
		System.out.println("PROSAO POKUSAJ CITANJA IZ NEO4j baze");
		if(kor == null || sme == null) {
			throw new Neo4jDatabaseException("Neo4jDatabase je nedostupna. Revert");
		}
		if(kor != null) {
			kor.getRezervisani().add(sme);
		}
		this.neo4jKorisnikRep.save(kor);
		
		if(novaRezervacijaNotificationEnabled(idVlasnika)) {
			newReservationNotify(rez);
		}
	}
	private StatusRezervacije setStatusRez(String idVlasnik) {
		HostBasicDTO host= getHostBasic(idVlasnik);
		if(host.getRezAutomatski()) {
			return StatusRezervacije.REZERVISANA;
		}else {
			return StatusRezervacije.PENDING;
		}
	}
	
//	public RezervacijaDTO makeReservation(String userId, String smestajId, RezervacijaDTO r) {
//		//proveri ostale rez za smestaj, da li je slobodan
//		Collection<Rezervacija> rezRezervisane = rezervacijaRep.findAllBySmestajAndStatus(smestajId, StatusRezervacije.REZERVISANA).orElse(new ArrayList<Rezervacija>());
//		//Collection<Rezervacija> rezPending=rezervacijaRep.findBySmestajIdAndStatus(smestajId, StatusRezervacije.PENDING).orElse(new ArrayList<Rezervacija>());
//		Termin t= new Termin(r.getOdDatum(), r.getDoDatum());
//		if(rezRezervisane.size()>0) {
//			for(Rezervacija rez: rezRezervisane) {
//				Boolean preklapanje= t.preklapanjeSa(new Termin(rez.getOdDatum(),rez.getDoDatum()));
//				if(preklapanje) return null;
//			}
//		}
//		//GRPC: provera smestaja, upitaj za nedostupne termine i cenovnik izabranog smestaja
//		SmestajDTO smestaj= getSmestaj(smestajId);
//		if(smestaj.getNedostupniCount()>0) {
//			for(TerminDTO ter : smestaj.getNedostupniList()) {
//				Boolean preklop = t.preklapanjeSa(new Termin(convertFromTimeStamp(ter.getPocetak()), convertFromTimeStamp(ter.getKraj())));
//				if(preklop) return null;
//			}
//		}
//		//TODO sta sa cenom?
//		float cena= cenaServ.ukupnaCena(smestaj, r.getOdDatum(), r.getDoDatum());
//		Rezervacija rez = rezMapper.fromDTO(r, smestajId, userId);
//		rez.setCena(cena);
//		//ZAVISNO OD TOGA DA LI JE VLASNIK SMESTAJA ODABRAO DA SE REZERVACIJE MODERIRAJU AUTOMATSKI ILI RUCNO
//		HostBasicDTO host= getHostBasic(smestaj.getVlasnik());		
//		
//		if(host.getRezAutomatski()) {
//			
//			ManagedChannel channel = ManagedChannelBuilder.forAddress(smestajHost, 7977).usePlaintext().build();
//			SmestajGrpcBlockingStub smestajBlockingStub = SmestajGrpcGrpc.newBlockingStub(channel);
//			TerminZauzmiRequest rqst = TerminZauzmiRequest.newBuilder().setSmestajId(smestajId).setTermin(mapTermin(r.getOdDatum(), r.getDoDatum())).build();
//			TerminZauzmiResponse rspns = smestajBlockingStub.zauzmiTermin(rqst);
//			channel.shutdown();
//
//			boolean terminated = false;
//			while (!terminated) {
//			  try {
//			    // Wait for the channel to terminate gracefully
//			    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
//			  } catch (InterruptedException e) {
//			    // Handle the exception if necessary
//			    e.printStackTrace();
//			  }
//			}
//			if(rspns.getZauzet()) {
//				rez.setStatus(StatusRezervacije.REZERVISANA);
//				
//			}else {
//				return null;
//			}
//		}else {
//			rez.setStatus(StatusRezervacije.PENDING);
//		}
//		rezervacijaRep.save(rez);
//		
//		//utvrdi da li je Host zavredio status istaknutog 
//		boolean isIstaknuti = determineIfIstaknuti(smestaj.getVlasnik());
//		//ako jeste, izmeni mu status u true, ako nije izmeni u false
//		boolean statusPromenjen = izmeniStatusHosta(smestaj.getVlasnik(), isIstaknuti);
//		System.out.println("USPESNO PROMENJEN STATUS HOSTA U: " + isIstaknuti);
//		
//		//U GRAF BAZU SE UPISUJE REZERVISANI SMESTAJ OBOSTRANO;
//		Korisnik kor = this.neo4jKorisnikRep.findById(userId).orElse(null);
//		Smestaj sme = this.neo4jSmestajRep.findById(smestajId).orElse(null);
//		if(kor != null) {
//			kor.getRezervisani().add(sme);
//		}
//		this.neo4jKorisnikRep.save(kor);
//		
//		if(novaRezervacijaNotificationEnabled(smestaj.getVlasnik())) {
//			newReservationNotify(rez);
//		}
//		return rezMapper.toDTO(rez);
//	}
	
	
//	public List<OcenaSmestaj> getOceneSmestaja(String id){
//		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7977).usePlaintext().build();
//		SmestajGrpcBlockingStub bs = SmestajGrpcGrpc.newBlockingStub(channel);
//		getOceneBySmestajIdRequest rqst = getOceneBySmestajIdRequest.newBuilder().setSmestajId(id).build();
//		getOceneBySmestajIdResponse rspns = bs.getOceneSmestaja(rqst);
//		List<OcenaSmestaj> retList = new ArrayList<OcenaSmestaj>();
//		if(!retList.isEmpty()) {
//			for(OcenaSmestajDTO dto : rspns.getOceneList()) {
//				retList.add(mapDTOtoOcenaSmestaj(dto));
//			}
//		}
//		return retList;
//		
//	}
//	
//	public OcenaSmestaj mapDTOtoOcenaSmestaj(OcenaSmestajDTO dto) {
//		OcenaSmestaj retVal = new OcenaSmestaj();
//		retVal.setDatumIVreme(convertFromTimeStamp(dto.getDatumIVreme()));
//		retVal.setId(dto.getId());
//		retVal.setKorisnikDavalac(dto.getIdKorisnika());
//		return retVal;
//	}
	public boolean izmeniStatusHosta(String idHosta, boolean istaknuti) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(reglogHost, 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub korBlockingStub = KorisnikGrpcGrpc.newBlockingStub(channel);
		DobioStatusIstaknutogRequest rqst = DobioStatusIstaknutogRequest.newBuilder().setIdKorisnika(idHosta).setStatus(istaknuti).build();
		DobioStatusIstaknutogResponse rspns = korBlockingStub.istaknutiHost(rqst);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		return rspns.getResult();
	}
	public boolean promenaStatusaHostaNotificationEnabled(String idVlasnika) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(reglogHost, 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub bs = KorisnikGrpcGrpc.newBlockingStub(channel);
		StatusIstaknutogNotifikacijaRequest rqst = StatusIstaknutogNotifikacijaRequest.newBuilder().setIdKorisnika(idVlasnika).build();
		StatusIstaknutogNotifikacijaResponse rspns = bs.istaknutiNotStatus(rqst);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		return rspns.getStanje();
	}
	public boolean novaRezervacijaNotificationEnabled(String idVlasnika) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(reglogHost, 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub bs = KorisnikGrpcGrpc.newBlockingStub(channel);
		NovaRezervacijaNotifikacijaRequest rqst = NovaRezervacijaNotifikacijaRequest.newBuilder().setIdKorisnika(idVlasnika).build();
		NovaRezervacijaNotifikacijaResponse rspns = bs.novaRezNotStatus(rqst);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		return rspns.getStanje();
	}
	
	public void newReservationNotify(Rezervacija r) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(reglogHost, 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub bs = KorisnikGrpcGrpc.newBlockingStub(channel);
		NekoRezervisaoRequest rqst = NekoRezervisaoRequest.newBuilder().setIdSmestaja(r.getSmestaj()).setIdKorisnika(r.getGost()).build();
		NekoRezervisaoResponse rspns = bs.newRezNotify(rqst);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		if(rspns.getResult()) {
			System.out.println("USPESNO POSLATO OBAVESTENJE HOSTU");
		}
	}
	public TerminDTO mapTermin(LocalDateTime localDateTime, LocalDateTime localDateTime2) {
		TerminDTO.Builder t = TerminDTO.newBuilder();
		t.setPocetak(convertToTimeStamp(localDateTime));
		t.setKraj(convertToTimeStamp(localDateTime2));
		return t.build();
	}
	
	
	public RezervacijaDTO cancelHost(String userId, String rezervacijaId) {
		Rezervacija r = rezervacijaRep.findById(rezervacijaId).orElse(null);
		if(r==null) {
			return null;
		}
		LocalDateTime temp = LocalDateTime.now();
		Duration duration = Duration.between(temp, r.getOdDatum());
		long days = duration.toDays();
		if(days <= 1) {
			return null;
		}
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(smestajHost, 7977).usePlaintext().build();
		SmestajGrpcBlockingStub smestajBlockingStub = SmestajGrpcGrpc.newBlockingStub(channel);
		TerminOslobodiRequest rqst = TerminOslobodiRequest.newBuilder().setSmestajId(r.getSmestaj()).setTermin(mapTermin(r.getOdDatum(), r.getDoDatum())).build();
		TerminOslobodiResponse rspns = smestajBlockingStub.oslobodiTermin(rqst);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		if(rspns.getOslobodjen()) {
			r.setStatus(StatusRezervacije.OTKAZANA);
		}else {
			return null;
		}
		hostOtkazaoRez(userId, rezervacijaId);
		rezervacijaRep.save(r);
		//da bih dobavio id vlasnika
		SmestajDTO smestaj= getSmestaj(r.getSmestaj());
//		
//		//utvrdi da li je Host zavredio status istaknutog 
//		boolean isIstaknuti = determineIfIstaknuti(smestaj.getVlasnik());
//		//ako jeste, izmeni mu status u true, ako nije izmeni u false
//		boolean statusPromenjen = izmeniStatusHosta(smestaj.getVlasnik(), isIstaknuti);
//		System.out.println("USPESNO PROMENJEN STATUS HOSTA U: " + isIstaknuti);
		return rezMapper.toDTO(r);
	}
	
	public RezervacijaDTO cancelReservation(String userId, String rezervacijaId) {
		Rezervacija r = rezervacijaRep.findById(rezervacijaId).orElse(null);
		if(r==null) {
			return null;
		}
		LocalDateTime temp = LocalDateTime.now();
		Duration duration = Duration.between(temp, r.getOdDatum());
		long days = duration.toDays();
		if(days <= 1) {
			return null;
		}
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(smestajHost, 7977).usePlaintext().build();
		SmestajGrpcBlockingStub smestajBlockingStub = SmestajGrpcGrpc.newBlockingStub(channel);
		TerminOslobodiRequest rqst = TerminOslobodiRequest.newBuilder().setSmestajId(r.getSmestaj()).setTermin(mapTermin(r.getOdDatum(), r.getDoDatum())).build();
		TerminOslobodiResponse rspns = smestajBlockingStub.oslobodiTermin(rqst);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		if(rspns.getOslobodjen()) {
			r.setStatus(StatusRezervacije.OTKAZANA);
		}else {
			return null;
		}
		guestOtkazaoRez(userId);
		rezervacijaRep.save(r);
		//da bih dobavio id vlasnika
		SmestajDTO smestaj= getSmestaj(r.getSmestaj());
		if(otkazanaRezervacijaNotificationEnabled(smestaj.getVlasnik())) {
			newCancelationNotify(r);
		}
		
		//utvrdi da li je Host zavredio status istaknutog 
		boolean isIstaknuti = determineIfIstaknuti(smestaj.getVlasnik());
		//ako jeste, izmeni mu status u true, ako nije izmeni u false
		boolean statusPromenjen = izmeniStatusHosta(smestaj.getVlasnik(), isIstaknuti);
		System.out.println("USPESNO PROMENJEN STATUS HOSTA U: " + isIstaknuti);
		
		return rezMapper.toDTO(r);
	}
	public boolean otkazanaRezervacijaNotificationEnabled(String idVlasnika) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(reglogHost, 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub bs = KorisnikGrpcGrpc.newBlockingStub(channel);
		OtkazanaRezervacijaNotifikacijaRequest rqst = OtkazanaRezervacijaNotifikacijaRequest.newBuilder().setIdKorisnika(idVlasnika).build();
		OtkazanaRezervacijaNotifikacijaResponse rspns = bs.otkazanaRezNotStatus(rqst);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		return rspns.getStanje();
	}
	public void newCancelationNotify(Rezervacija r) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(reglogHost, 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub bs = KorisnikGrpcGrpc.newBlockingStub(channel);
		NekoOtkazaoRequest rqst = NekoOtkazaoRequest.newBuilder().setIdKorisnika(r.getGost()).setIdSmestaja(r.getSmestaj()).build();
		NekoOtkazaoResponse rspns = bs.newQuitNotify(rqst);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		if(rspns.getResult()) {
			System.out.println("USPESNO POSLATO OBAVESTENJE HOSTU");
		}
	}
	
	public Collection<RezervacijaDTO> getAllReservationsByHostId(String id) {
		// TODO Auto-generated method stub
		
		System.out.println("GRPC KOMUNIKACIJA IZMEDJU RESERVATION i SMESTAJ SERVICE-a");
		
		Collection<RezervacijaDTO> retList = new ArrayList<RezervacijaDTO>();
		ManagedChannel channel = ManagedChannelBuilder.forAddress(smestajHost, 7977).usePlaintext().build();
		SmestajGrpcBlockingStub smestajBlockingStub = SmestajGrpcGrpc.newBlockingStub(channel);
		SmestajIdsForHostRequest rqst = SmestajIdsForHostRequest.newBuilder().setUserId(id).build();
		SmestajIdsForHostResponse rspns = smestajBlockingStub.getSmestajIdsForHost(rqst);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		System.out.println("KRAJ GRPC KOMUNIKACIJE IZMEDJU RESERVATION i SMESTAJ SERVICE-a");
		Collection<Rezervacija> rezervacije = this.rezervacijaRep.findAll();
		if(!rezervacije.isEmpty()) {
			for(String s : rspns.getSmestajIdsList()) {
				for(Rezervacija r : rezervacije) {
					if(r.getSmestaj().equals(s)) {
						retList.add(rezMapper.toDTO(r));
					}
				}
			}
		}
		return retList;
	}
	
	public RezervacijaDTO approveReservation(String reservationId, String ownerId) {
		Rezervacija r = this.rezervacijaRep.findById(reservationId).orElse(null);
		if(r == null) {
			return null;
		}
		//TODO proveri da li uopste postoji smestaj
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(smestajHost, 7977).usePlaintext().build();
		SmestajGrpcBlockingStub smestajBlockingStub = SmestajGrpcGrpc.newBlockingStub(channel);
		TerminZauzmiRequest rqst = TerminZauzmiRequest.newBuilder().setSmestajId(r.getSmestaj()).setTermin(mapTermin(r.getOdDatum(), r.getDoDatum())).build();
		TerminZauzmiResponse rspns = smestajBlockingStub.zauzmiTermin(rqst);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		if(rspns.getZauzet()) {
			r.setStatus(StatusRezervacije.REZERVISANA);
			reservationApprovedNotificationRequest req = reservationApprovedNotificationRequest.newBuilder().setIdRezervacije(r.getId()).build();
			ManagedChannel channel2 = ManagedChannelBuilder.forAddress(reglogHost, 7979).usePlaintext().build();
			KorisnikGrpcBlockingStub blockStub = KorisnikGrpcGrpc.newBlockingStub(channel2);
			reservationApprovedNotificationResponse res = blockStub.rezObavestenje(req);
			channel.shutdown();

			terminated = false;
			while (!terminated) {
			  try {
			    // Wait for the channel to terminate gracefully
			    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
			  } catch (InterruptedException e) {
			    // Handle the exception if necessary
			    e.printStackTrace();
			  }
			}
			//ako nije uspesno preneo obavestenje da je obavljena rezervacija, prekini izvrsavanje
			if(!res.getIsporuceno()) {
				return null;
			}
		}else {
			return null;
		}
		rezervacijaRep.save(r);
		
		
		//otkazivanje pending rez za smestaj;
		Collection<Rezervacija> pendingRez= rezervacijaRep.findAllBySmestajAndStatus(r.getSmestaj(), StatusRezervacije.PENDING).orElse(new ArrayList<Rezervacija>());
		if(pendingRez.size()>0) {
			Termin t= new Termin(r.getOdDatum(), r.getDoDatum());
			for(Rezervacija p: pendingRez) {
				Boolean preklop= t.preklapanjeSa(new Termin(p.getOdDatum(),p.getDoDatum()));
				if(preklop) {
					p.setStatus(StatusRezervacije.ODBIJENA);
					//save one
				}
			}
		}
		rezervacijaRep.saveAll(pendingRez);
		return rezMapper.toDTO(r);
	}
	
	public RezervacijaDTO rejectReservation(String reservationId, String ownerId) {
		Rezervacija r = this.rezervacijaRep.findById(reservationId).orElse(null);
		if(r == null) {
			return null;
		}
		//proveri postoji li smestaj
		r.setStatus(StatusRezervacije.ODBIJENA);
		rezervacijaRep.save(r);
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(reglogHost, 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub korServBlockStub= KorisnikGrpcGrpc.newBlockingStub(channel);
		rezOtkazanaHostRequest req = rezOtkazanaHostRequest.newBuilder().setHostId(ownerId).setResId(reservationId).build();
		rezOtkazanaHostResponse res= korServBlockStub.rezOtkazanaHost(req);
		channel.shutdown();
		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		return rezMapper.toDTO(r);
	}

	public Collection<RezervacijaDTO> getAllReservationByUserGuestId(String id) {
		Collection<Rezervacija> retList = new ArrayList<Rezervacija>();
		retList=rezervacijaRep.findAllByGost(id).orElse(new ArrayList<Rezervacija>());
		return retList.stream().map(x->rezMapper.toDTO(x)).collect(Collectors.toList());
	}
	public SmestajDTO getSmestaj(String id) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(smestajHost, 7977).usePlaintext().build();
		SmestajGrpcBlockingStub smestajServBlockStub=SmestajGrpcGrpc.newBlockingStub(channel);
		getSmestajByIdRequest req= getSmestajByIdRequest.newBuilder().setSmestajId(id).build();
		getSmestajByIdResponse resp= smestajServBlockStub.getSmestajById(req);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		return resp.getOdgovor();
	}
	public HostBasicDTO getHostBasic(String id) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(reglogHost, 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub korServBlockStub= KorisnikGrpcGrpc.newBlockingStub(channel);
		getHostRequest req= getHostRequest.newBuilder().setHostId(id).build();
		getHostResponse res= korServBlockStub.getHost(req);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		return res.getHost();

	}
	public Timestamp convertToTimeStamp(LocalDateTime ldt) {
		return Timestamp.newBuilder().setSeconds(ldt.toEpochSecond(ZoneOffset.UTC))
				.setNanos(ldt.getNano()).build();
	}
	public LocalDateTime convertFromTimeStamp(Timestamp t) {
		return Instant.ofEpochSecond(t.getSeconds(),t.getNanos()).atZone(ZoneId.of("Europe/Belgrade")).toLocalDateTime();
	}
	private void guestOtkazaoRez(String userId) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(reglogHost, 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub korServBlockStub= KorisnikGrpcGrpc.newBlockingStub(channel);
		rezOtkazanaRequest req= rezOtkazanaRequest.newBuilder().setGuestId(userId).build();
		rezOtkazanaResponse res= korServBlockStub.rezOtkazana(req);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		Boolean uspeh= res.getPenalDodat();
		//todo
	}
	private void hostOtkazaoRez(String hostId, String resId) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(reglogHost, 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub korServBlockStub= KorisnikGrpcGrpc.newBlockingStub(channel);
		rezOtkazanaHostRequest req = rezOtkazanaHostRequest.newBuilder().setHostId(hostId).setResId(resId).build();
		rezOtkazanaHostResponse res= korServBlockStub.rezOtkazanaHost(req);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
	}

	public boolean canGiveRating(String userId, String smestajId) {
		//smestaj i rezervisane
		System.out.println("Gost id : "+userId);
		Collection<Rezervacija> rez= rezervacijaRep.findAllByGost(userId).orElse(new ArrayList<Rezervacija>());
		System.out.println("Rezervacije gosta : "+rez.size());
		if(rez.size()>0) {
			//isfiltrirane zavrsene za smestaj za korisnika
			Collection<Rezervacija> userRez= rez.stream().filter(x->x.getSmestaj().equals(smestajId) && x.getStatus().equals(StatusRezervacije.REZERVISANA)).collect(Collectors.toList());
			if(userRez.size()>0) {
				System.out.println("Korisnik moze oceniti!");
				return true;
			}
		}
		System.out.println("Korisnik NE moze oceniti!");
		return false;
	}

	public boolean canGiveRatingHost(String userId, String hostId) {
		Collection<Rezervacija> rez= rezervacijaRep.findAllByGost(userId).orElse(new ArrayList<Rezervacija>());
		System.out.println("CAN GIVE RATING HOST");
		System.out.println("Gost id : "+userId);
		System.out.println("HOST ID: " + hostId);
		System.out.println("BROJ REZERVACIJA: " + rez.size());
		Collection<String> smestajsId= new ArrayList<String>();
		boolean canGiveRating=false;
		if(rez.size()>0) {
			smestajsId=rez.stream().map(x->x.getSmestaj()).distinct().collect(Collectors.toList());
			canGiveRating=SmestajsBelongToHost(hostId, smestajsId);
		}
		return canGiveRating;
	}
	
	private boolean SmestajsBelongToHost(String hostId ,Collection<String> smestajsId ) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(smestajHost, 7977).usePlaintext().build();
		SmestajGrpcBlockingStub smBlockStub= SmestajGrpcGrpc.newBlockingStub(channel);
		AnySmestajBelongToHostRequest req= AnySmestajBelongToHostRequest.newBuilder().setHostId(hostId).addAllSmestajIds(smestajsId).build();
		AnySmestajBelongToHostResponse resp= smBlockStub.belongsToHost(req);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		return resp.getBelongs();
	}
	
	public List<SmestajDTO> getAllSmestajByHost(String id) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(smestajHost, 7977).usePlaintext().build();
		SmestajGrpcBlockingStub smestajServBlockStub=SmestajGrpcGrpc.newBlockingStub(channel);
		getListaSmestajaByUserIdRequest req= getListaSmestajaByUserIdRequest.newBuilder().setId(id).build();
		getListaSmestajaByUserIdResponse resp= smestajServBlockStub.getListaSmestajaByUserId(req);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		return resp.getListaSmestajaList();
	}
	private boolean determineIfIstaknuti(String hostId) {
		boolean isIstaknuti = false;
		
		//dobavi listu rezervacija HOST-a i izvuci ID-ove razlicitih smestaja koji su rezervisani
		List<String> idsRezervisanihSmestaja = this.getAllReservationsByHostId(hostId).stream().map(RezervacijaDTO::getSmestaj).distinct().toList();
		if(idsRezervisanihSmestaja.size() < 5) {
			return isIstaknuti;
		}
		
		//nepotrebne liste, napravljene cisto radi lakse provere
		List<String> idsOtkazanihRezervacija = this.getAllReservationsByHostId(hostId).stream().filter(r -> r.getStatus().equals(StatusRezervacije.OTKAZANA)).map(RezervacijaDTO::getId).toList();
		List<String> idsSvihRezervacija = this.getAllReservationsByHostId(hostId).stream().map(RezervacijaDTO::getId).toList();
		//ako je broj otkazanih rezervacija veci od 5 posto ukupnog broja rezervacija
		if(idsOtkazanihRezervacija.size() > 0.05 * idsSvihRezervacija.size()) {
			return isIstaknuti;
		}
		//ako je prosecna ocena manja od 4.7
		if(this.getHostBasic(hostId).getProsecnaOcena() < 4.7) {
			return isIstaknuti;
		}
		
		List<RezervacijaDTO> tempLista = this.getAllReservationsByHostId(hostId).stream().toList();
		int brDana = 0;
		for(RezervacijaDTO rdto : tempLista) {
			Duration duration = Duration.between(rdto.getOdDatum(), rdto.getDoDatum());
			int days = (int) duration.toDays();
			brDana = brDana + days;
		}
		if(brDana < 50) {
			return isIstaknuti;
		}
		
		isIstaknuti = true;
		return isIstaknuti;
	}


	public RezervacijaDTO getRezervacijaById(String id) {
		// TODO Auto-generated method stub
		Rezervacija r = this.rezervacijaRep.findById(id).orElse(null);
		
		SmestajDTO smestaj= getSmestaj(r.getSmestaj());
		
		if(r == null) {
			return null;
		}else {
			RezervacijaDTO retVal = this.rezMapper.toDTO(r);
			retVal.setAdresa(smestaj.getAdresa().getAdresa());
			return retVal;
		}
	}
}
