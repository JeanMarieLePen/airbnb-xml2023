package com.example.reservationservice.services;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ClusterInfo;
import org.springframework.stereotype.Service;

import com.example.reservationservice.dtos.RezervacijaDTO;
import com.example.reservationservice.mappers.RezervacijaMapper;

import com.example.reservationservice.model.Rezervacija;
import com.example.reservationservice.model.StatusRezervacije;
import com.example.reservationservice.model.Termin;
import com.example.reservationservice.repositories.RezervacijaRep;
import com.google.protobuf.Timestamp;
import com.xml2023.mainapp.GreetingServiceGrpc;
import com.xml2023.mainapp.GreetingServiceGrpc.GreetingServiceBlockingStub;
import com.xml2023.mainapp.HostBasicDTO;
import com.xml2023.mainapp.KorisnikGrpcGrpc;
import com.xml2023.mainapp.KorisnikGrpcGrpc.KorisnikGrpcBlockingStub;
import com.xml2023.mainapp.KorisnikRequest;
import com.xml2023.mainapp.KorisnikResponse;
import com.xml2023.mainapp.SmestajDTO;
import com.xml2023.mainapp.SmestajGrpcGrpc;
import com.xml2023.mainapp.SmestajGrpcGrpc.SmestajGrpcBlockingStub;
import com.xml2023.mainapp.SmestajIdsForHostRequest;
import com.xml2023.mainapp.SmestajIdsForHostResponse;
import com.xml2023.mainapp.TerminDTO;
import com.xml2023.mainapp.getHostRequest;
import com.xml2023.mainapp.getHostResponse;
import com.xml2023.mainapp.getListaSmestajaByUserIdRequest;
import com.xml2023.mainapp.getListaSmestajaByUserIdResponse;
import com.xml2023.mainapp.getSmestajByIdRequest;
import com.xml2023.mainapp.getSmestajByIdResponse;
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
	
	public RezervacijaDTO makeReservation(String userId, String smestajId, RezervacijaDTO r) {
//		
//		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7979).usePlaintext().build();
//		GreetingServiceBlockingStub greetServBlockStub = GreetingServiceGrpc.newBlockingStub(channel);
//		KorisnikRequest zahtev = KorisnikRequest.newBuilder().setId(userId).build();
//		KorisnikResponse odgovor = greetServBlockStub.greeting(zahtev);
//		
		//proveri ostale rez za smestaj, da li je slobodan
		Collection<Rezervacija> rezRezervisane = rezervacijaRep.findBySmestajAndStatus(smestajId, StatusRezervacije.REZERVISANA).orElse(new ArrayList<Rezervacija>());
		//Collection<Rezervacija> rezPending=rezervacijaRep.findBySmestajIdAndStatus(smestajId, StatusRezervacije.PENDING).orElse(new ArrayList<Rezervacija>());
		Termin t= new Termin(r.getOdDatum(), r.getDoDatum());
		if(rezRezervisane.size()>0) {
			for(Rezervacija rez: rezRezervisane) {
				Boolean preklapanje= t.preklapanjeSa(new Termin(rez.getOdDatum(),rez.getDoDatum()));
				if(preklapanje) return null;
			}
		}
		//GRPC: provera smestaja, upitaj za nedostupne termine i cenovnik izabranog smestaja
		SmestajDTO smestaj= getSmestaj(smestajId);
		if(smestaj.getNedostupniCount()>0) {
			for(TerminDTO ter : smestaj.getNedostupniList()) {
				Boolean preklop = t.preklapanjeSa(new Termin(convertFromTimeStamp(ter.getPocetak()), convertFromTimeStamp(ter.getKraj())));
				if(preklop) return null;
			}
		}
		//TODO sta sa cenom?
		float cena= cenaServ.ukupnaCena(smestaj, r.getOdDatum(), r.getDoDatum());
		Rezervacija rez = rezMapper.fromDTO(r, smestajId, userId);

		//ZAVISNO OD TOGA DA LI JE VLASNIK SMESTAJA ODABRAO DA SE REZERVACIJE MODERIRAJU AUTOMATSKI ILI RUCNO
		HostBasicDTO host= getHostBasic(smestaj.getVlasnik());		

		if(host.getRezAutomatski()) {
			rez.setStatus(StatusRezervacije.REZERVISANA);
		}else {
			rez.setStatus(StatusRezervacije.PENDING);
		}
		rezervacijaRep.save(rez);
		return rezMapper.toDTO(rez);
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
		r.setStatus(StatusRezervacije.OTKAZANA);
		guestOtkazaoRez(userId);
		rezervacijaRep.save(r);
		return rezMapper.toDTO(r);
	}


	//TODO: treba li ovo uopste?
//	public RezervacijaDTO editReservation(RezervacijaDTO edited) {
//		Rezervacija r = rezervacijaRep.findById(edited.getId()).orElse(null);
//		if(r==null) {
//			return null;
//		}
//		r.setBrojGostiju(edited.getBrojGostiju());
//		r.setDoDatum(edited.getDoDatum());
//		r.setOdDatum(edited.getOdDatum());
//		if(r.getSmestaj().getVlasnik().isRezAutomatski()) {
//			r.setStatus(StatusRezervacije.REZERVISANA);
//		}else {
//			r.setStatus(StatusRezervacije.PENDING);
//		}
//		rezervacijaRep.save(r);
//		return rezMapper.toDTO(r);
//	}
	
	public Collection<RezervacijaDTO> getAllReservationsByHostId(String id) {
		// TODO Auto-generated method stub
		Collection<RezervacijaDTO> retList = new ArrayList<RezervacijaDTO>();
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7977).usePlaintext().build();
		SmestajGrpcBlockingStub smestajBlockingStub = SmestajGrpcGrpc.newBlockingStub(channel);
		SmestajIdsForHostRequest rqst = SmestajIdsForHostRequest.newBuilder().setUserId(id).build();
		SmestajIdsForHostResponse rspns = smestajBlockingStub.getSmestajIdsForHost(rqst);
		
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
		r.setStatus(StatusRezervacije.REZERVISANA);

		rezervacijaRep.save(r);
		//otkazivanje pending rez za smestaj;
		Collection<Rezervacija> pendingRez= rezervacijaRep.findBySmestajAndStatus(r.getSmestaj(), StatusRezervacije.PENDING).orElse(new ArrayList<Rezervacija>());
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
		return rezMapper.toDTO(r);
	}

	public Collection<RezervacijaDTO> getAllReservationByUserGuestId(String id) {
		Collection<Rezervacija> retList = new ArrayList<Rezervacija>();
		retList=rezervacijaRep.findAllByGost(id).orElse(new ArrayList<Rezervacija>());
		return retList.stream().map(x->rezMapper.toDTO(x)).collect(Collectors.toList());
	}
	public SmestajDTO getSmestaj(String id) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7977).usePlaintext().build();
		SmestajGrpcBlockingStub smestajServBlockStub=SmestajGrpcGrpc.newBlockingStub(channel);
		getSmestajByIdRequest req= getSmestajByIdRequest.newBuilder().setSmestajId(id).build();
		getSmestajByIdResponse resp= smestajServBlockStub.getSmestajById(req);
		return resp.getOdgovor();
	}
	public HostBasicDTO getHostBasic(String id) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub korServBlockStub= KorisnikGrpcGrpc.newBlockingStub(channel);
		getHostRequest req= getHostRequest.newBuilder().setHostId(id).build();
		getHostResponse res= korServBlockStub.getHost(req);
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
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub korServBlockStub= KorisnikGrpcGrpc.newBlockingStub(channel);
		rezOtkazanaRequest req= rezOtkazanaRequest.newBuilder().setGuestId(userId).build();
		rezOtkazanaResponse res= korServBlockStub.rezOtkazana(req);
		Boolean uspeh= res.getPenalDodat();
		//todo
	}
}
