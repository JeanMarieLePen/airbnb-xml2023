package com.xml.mainapp.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.protobuf.Timestamp;
import com.xml.mainapp.dtos.AdresaDTO;
import com.xml.mainapp.dtos.KorisnikDTO;
import com.xml.mainapp.dtos.UpdateProfileDTO;
import com.xml.mainapp.dtos.UpdateProfileDTO2;
import com.xml.mainapp.dtos.data.CenovnikDTO;
import com.xml.mainapp.dtos.data.OcenaSmestajaDTO;
import com.xml.mainapp.dtos.data.RezervacijaDTO;
import com.xml.mainapp.dtos.data.SmestajDTO;
import com.xml.mainapp.dtos.user.GuestDTO;
import com.xml.mainapp.dtos.user.HostDTO;
import com.xml.mainapp.dtos.user.OcenaHostBasicDTO;
import com.xml.mainapp.mappers.AdresaMapper;
import com.xml.mainapp.mappers.GuestMapper;
import com.xml.mainapp.mappers.HostMapper;
import com.xml.mainapp.mappers.KorisnikMapper;
import com.xml.mainapp.mappers.OcenaSmestajMapper;
import com.xml.mainapp.mappers.RezervacijaMapper;
import com.xml.mainapp.mappers.SmestajBasicMapper;
import com.xml.mainapp.model.data.Adresa;
import com.xml.mainapp.model.data.OcenaSmestaj;
import com.xml.mainapp.model.data.Rezervacija;
import com.xml.mainapp.model.data.Smestaj;
import com.xml.mainapp.model.data.StatusRezervacije;
import com.xml.mainapp.model.data.Termin;
import com.xml.mainapp.model.users.Guest;
import com.xml.mainapp.model.users.Host;
import com.xml.mainapp.model.users.Korisnik;
import com.xml.mainapp.model.users.OcenaHost;
import com.xml.mainapp.model.users.TipKorisnika;
import com.xml.mainapp.repositories.KorisnikRep;
import com.xml.mainapp.repositories.OcenaHostRep;
import com.xml2023.mainapp.ActiveResExistsRequest;
import com.xml2023.mainapp.ActiveResExistsResponse;
import com.xml2023.mainapp.DeleteSmestajsForHostRequest;
import com.xml2023.mainapp.DeleteSmestajsForHostResponse;
import com.xml2023.mainapp.DobioStatusIstaknutogRequest;
import com.xml2023.mainapp.DobioStatusIstaknutogResponse;
import com.xml2023.mainapp.KorisnikGrpcGrpc;
import com.xml2023.mainapp.KorisnikGrpcGrpc.KorisnikGrpcBlockingStub;
import com.xml2023.mainapp.NekoOcenioHostaRequest;
import com.xml2023.mainapp.NekoOcenioHostaResponse;
import com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest;
import com.xml2023.mainapp.NovaOcenaHostaNotifikacijaResponse;
import com.xml2023.mainapp.RezervacijaGrpcGrpc;
import com.xml2023.mainapp.RezervacijaGrpcGrpc.RezervacijaGrpcBlockingStub;
import com.xml2023.mainapp.SlikaDTO;
import com.xml2023.mainapp.SmestajGrpcGrpc;
import com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest;
import com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse;
import com.xml2023.mainapp.getListaRezervacijaByUserIdRequest;
import com.xml2023.mainapp.getListaRezervacijaByUserIdResponse;
import com.xml2023.mainapp.getListaSmestajaByUserIdRequest;
import com.xml2023.mainapp.getListaSmestajaByUserIdResponse;
import com.xml2023.mainapp.SmestajGrpcGrpc.SmestajGrpcBlockingStub;
import com.xml2023.mainapp.TerminDTO;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRep korisnikRep;
	@Autowired 
	private KorisnikMapper korisnikMapper;
	@Autowired
	private HostMapper hostMapper;
	@Autowired
	private GuestMapper guestMapper;
	@Autowired OcenaHostRep oRep;
	
//	@Cacheable(key="#id", value = "Korisnik")
	public KorisnikDTO getUserById(String id) {
		// TODO Auto-generated method stub
		Korisnik k = this.korisnikRep.findById(id).orElse(null);
		
		if(k == null) {
			return null;
		}else {
			if(k.getTipKorisnika().equals(TipKorisnika.GUEST)) {
				Guest tmp = this.korisnikRep.findGuestById(id);
				GuestDTO retVal =guestMapper.toDTO(tmp, null); 
				retVal.setObradjenaRezervacijaNotifikacija(tmp.isObradjenaRezervacijaNotifikacija());
				return retVal;
			}
			//mora da se premapira
			if(k.getTipKorisnika().equals(TipKorisnika.HOST)) {
				Host tmp = this.korisnikRep.findHostById(id);
				HostDTO retVal = hostMapper.toDTO(tmp, null);
				
				//nepotrebno, al ajde
				retVal.setIstaknuti(tmp.isIstaknuti());
				retVal.setRezAutomatski(tmp.isRezAutomatski());
				retVal.setCanceledNotification(tmp.isCanceledNotification());
				retVal.setNewNotification(tmp.isNewNotification());
				retVal.setRatedAccomodationNotification(tmp.isRatedAccomodationNotification());
				retVal.setRatedHostNotification(tmp.isRatedHostNotification());
				retVal.setStatusNotification(tmp.isStatusNotification());
				
				
				
				Collection<com.xml2023.mainapp.SmestajDTO> smestajList = getAllSmestajForHost(tmp.getId());
				
				Collection<SmestajDTO> tempList = new ArrayList<SmestajDTO>();
				if(!smestajList.isEmpty()) {
					for(com.xml2023.mainapp.SmestajDTO sdto : smestajList) {
						tempList.add(mapToDTO(sdto));
					}
				}
				retVal.setSmestajList(tempList);
				
				Collection<OcenaHost> ocene=oRep.findAllByVlasnik(id).orElse(new ArrayList<OcenaHost>());
				retVal.setOcene(ocene.stream().map(x->new OcenaHostBasicDTO(x)).collect(Collectors.toList()));
				float prosek= retVal.getOcene().stream().mapToInt(x->x.getOcena()).sum();
				retVal.setProsecnaOcena(prosek/retVal.getOcene().size());
				
				return retVal;
			}	
		}
		return null;
	}

	private SmestajDTO mapToDTO(com.xml2023.mainapp.SmestajDTO sdto) {
		// TODO Auto-generated method stub
		SmestajDTO retVal = new SmestajDTO();
		AdresaDTO adr = new AdresaDTO();
		adr.setAdresa(sdto.getAdresa().getAdresa());
		adr.setLat(sdto.getAdresa().getLat());
		adr.setLng(sdto.getAdresa().getLng());
		retVal.setAdresa(adr);
		
		CenovnikDTO cen = new CenovnikDTO();
		cen.setCena(sdto.getCenovnik().getCena());
		cen.setCenaLeto(sdto.getCenovnik().getCenaLeto());
		cen.setCenaPraznik(sdto.getCenovnik().getCenaPraznik());
		cen.setCenaVikend(sdto.getCenovnik().getCenaVikend());
		cen.setPoSmestaju(sdto.getCenovnik().getPoSmestaju());
		retVal.setCenovnik(cen);
		
		retVal.setId(sdto.getId());
		retVal.setMaxGosti(sdto.getMaxGost());
		retVal.setMinGosti(sdto.getMinGosti());
		
		Collection<String> slike = new ArrayList<String>();
		for(SlikaDTO s : sdto.getSlikaList()) {
			slike.add(s.getSlika());
		}
		retVal.setSlike(slike);
		Collection<String> pogodnosti = new ArrayList<String>();
		for(String p : sdto.getPogodnostiList()) {
			pogodnosti.add(p);
		}
		retVal.setPogodnosti(pogodnosti);
		
		Collection<com.xml.mainapp.dtos.data.TerminDTO> nedostupni = new ArrayList<com.xml.mainapp.dtos.data.TerminDTO>();
		for(TerminDTO t : sdto.getNedostupniList()) {
			com.xml.mainapp.dtos.data.TerminDTO tt = new com.xml.mainapp.dtos.data.TerminDTO();
			tt.setPocetak(convertFromTimeStamp(t.getPocetak()));
			tt.setKraj(convertFromTimeStamp(t.getKraj()));
			nedostupni.add(tt);
		}
		retVal.setNedostupni(nedostupni);
		
		retVal.setVlasnikId(sdto.getVlasnik());
		return retVal;
	}
	
	public Timestamp convertToTimeStamp(LocalDateTime ldt) {
		return Timestamp.newBuilder().setSeconds(ldt.toEpochSecond(ZoneOffset.UTC))
				.setNanos(ldt.getNano()).build();
	}
	public LocalDateTime convertFromTimeStamp(Timestamp t) {
		return Instant.ofEpochSecond(t.getSeconds(),t.getNanos()).atZone(ZoneId.of("Europe/Belgrade")).toLocalDateTime();
	}

	public HostDTO findHostById(String id) {
		System.out.println("TRAZENI HOST : "+id);
		Host h = this.korisnikRep.findHostById(id);
		if(h == null) {
			return null;
		}
		
		HostDTO retVal = this.hostMapper.toDTO(h, getAllSmestajForHost(id));
		Collection<SmestajDTO> smestajList = new ArrayList<SmestajDTO>();
		for(com.xml2023.mainapp.SmestajDTO sdto : getAllSmestajForHost(id)) {
			smestajList.add(mapToDTO(sdto));
		}
		retVal.setSmestajList(smestajList);
		Collection<OcenaHost> ocene=oRep.findAllByVlasnik(id).orElse(new ArrayList<OcenaHost>());
		retVal.setOcene(ocene.stream().map(x->new OcenaHostBasicDTO(x)).collect(Collectors.toList()));
		return retVal;
	}
	
	public KorisnikDTO updateProfileById(String id, UpdateProfileDTO2 udto) {
		Host k = this.korisnikRep.findHostById(id);
		if(k == null || (korisnikRep.findByEmail(udto.getHostDTO().getEmail()) != null && !korisnikRep.findByEmail(udto.getHostDTO().getEmail()).getId().equals(id)) || (korisnikRep.findByKorIme(udto.getHostDTO().getKorIme()) != null) && 
				!korisnikRep.findByKorIme(udto.getHostDTO().getKorIme()).getId().equals(id)) {
			return null;
		}
//		if(k == null || (korisnikRep.findByEmail(udto.getKorisnikDTO().getEmail()) != null) || (korisnikRep.findByKorIme(udto.getKorisnikDTO().getKorIme()) != null)) {
//			return null;
//		}
		else {
			//izmena sifre ako je menjao
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			if(udto.getNovaSifraDTO() != null && !udto.getNovaSifraDTO().getNovaSifra().equals("")) {
				if(BCrypt.checkpw(udto.getNovaSifraDTO().getStaraSifra(), k.getLozinka())) {
					String pw = bc.encode(udto.getNovaSifraDTO().getNovaSifra());
					k.setLozinka(pw);
				}else {
					return null;
				}
			}
			
			k.getAdresa().setAdresa(udto.getHostDTO().getAdresa().getAdresa());
			k.getAdresa().setLat(udto.getHostDTO().getAdresa().getLat());
			k.getAdresa().setLng(udto.getHostDTO().getAdresa().getLng());
			
			
			k.setIme(udto.getHostDTO().getIme());
			k.setPrezime(udto.getHostDTO().getPrezime());
			k.setKorIme(udto.getHostDTO().getKorIme());
			k.setEmail(udto.getHostDTO().getEmail());
			k.setCanceledNotification(udto.getHostDTO().isCanceledNotification());
			k.setNewNotification(udto.getHostDTO().isNewNotification());
			k.setRatedAccomodationNotification(udto.getHostDTO().isRatedAccomodationNotification());
			k.setRatedHostNotification(udto.getHostDTO().isRatedHostNotification());
			k.setStatusNotification(udto.getHostDTO().isStatusNotification());

			if(k.getSlike() != null) {
				Collection<byte[]> tempList = new ArrayList<byte[]>();
				for(String s : udto.getHostDTO().getSlike()) {
					byte[] data = Base64.getDecoder().decode(s.split(",")[1]);
					tempList.add(data);
				}
				k.setSlike(tempList);
			}
			korisnikRep.save(k);
			if(k.getTipKorisnika().equals(TipKorisnika.HOST)) {
				Host h = this.korisnikRep.findHostById(id);
				if(h!=null) {
					h.setRezAutomatski(udto.getHostDTO().isRezAutomatski());
				}
				korisnikRep.save(h);
			}
			if(k.getTipKorisnika().equals(TipKorisnika.GUEST)) {
				Guest g = this.korisnikRep.findGuestById(k.getId());
				if(g!= null) {
					
				}
			}
			KorisnikDTO retVal = this.korisnikMapper.toDTO(k);
			return retVal;
		}
	}
	
	
//	@Transactional
//	@CachePut(value="Korisnik", key="#id")
	public KorisnikDTO updateProfileById(String id, UpdateProfileDTO udto) {
		Guest k = this.korisnikRep.findGuestById(id);
		if(k == null || (korisnikRep.findByEmail(udto.getGuestDTO().getEmail()) != null && !korisnikRep.findByEmail(udto.getGuestDTO().getEmail()).getId().equals(id)) || (korisnikRep.findByKorIme(udto.getGuestDTO().getKorIme()) != null) && 
				!korisnikRep.findByKorIme(udto.getGuestDTO().getKorIme()).getId().equals(id)) {
			return null;
		}
		else {
			//izmena sifre ako je menjao
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			if(udto.getNovaSifraDTO() != null && !udto.getNovaSifraDTO().getNovaSifra().equals("")) {
				if(BCrypt.checkpw(udto.getNovaSifraDTO().getStaraSifra(), k.getLozinka())) {
					String pw = bc.encode(udto.getNovaSifraDTO().getNovaSifra());
					k.setLozinka(pw);
				}else {
					return null;
				}
			}
			k.getAdresa().setAdresa(udto.getGuestDTO().getAdresa().getAdresa());
			k.getAdresa().setLat(udto.getGuestDTO().getAdresa().getLat());
			k.getAdresa().setLng(udto.getGuestDTO().getAdresa().getLng());
			k.setIme(udto.getGuestDTO().getIme());
			k.setPrezime(udto.getGuestDTO().getPrezime());
			k.setKorIme(udto.getGuestDTO().getKorIme());
			k.setEmail(udto.getGuestDTO().getEmail());
			if(k.getSlike() != null) {
				Collection<byte[]> tempList = new ArrayList<byte[]>();
				for(String s : udto.getGuestDTO().getSlike()) {
					byte[] data = Base64.getDecoder().decode(s.split(",")[1]);
					tempList.add(data);
				}
				k.setSlike(tempList);
			}
			k.setObradjenaRezervacijaNotifikacija(udto.getGuestDTO().isObradjenaRezervacijaNotifikacija());
			korisnikRep.save(k);
			if(k.getTipKorisnika().equals(TipKorisnika.GUEST)) {
				Guest g = this.korisnikRep.findGuestById(k.getId());
				
				if(g!= null) {
					
				}
			}
			KorisnikDTO retVal = this.korisnikMapper.toDTO(k);
			return retVal;
		}
	}
	
	  public Collection<RezervacijaDTO> getAllReservationByUserId(String id) { 
		  Guest g = this.korisnikRep.findGuestById(id);
		  if(g == null) return null; 
		  Collection<RezervacijaDTO> retList = new ArrayList<RezervacijaDTO>();
//		  if(retList != null) { for(Rezervacija r : g.getRezervacije()) { 
//			  retList.add(rezMapper.toDTO(r)); 
//			  } 
//		  } 
		  return retList; 
	  }
	 

	public OcenaSmestajaDTO giveRatingToSmestaj(String userId, String smestajId, OcenaSmestajaDTO ocena) {
		return null;
	}

	public KorisnikDTO deleteAcc(String id) {
		// TODO Auto-generated method stub
		Korisnik k = this.korisnikRep.findById(id).orElse(null);
		if(k == null) {
			return null;
		}
		
		if(k.getTipKorisnika().equals(TipKorisnika.GUEST)) {
			Guest g = this.korisnikRep.findGuestById(id);
			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7978).usePlaintext().build();
			RezervacijaGrpcBlockingStub blockStub= RezervacijaGrpcGrpc.newBlockingStub(channel);
			ActiveResExistsRequest req=ActiveResExistsRequest.newBuilder().setUserId(g.getId()).setTip("gost").build();
			ActiveResExistsResponse res=blockStub.reservationsForUserExists(req);
			
			if(res.getExists()==1) {
				System.out.println("Grpc rezulatat : Za korisnika postoje aktivne rezervacije!");
				return null;
			}
			this.korisnikRep.delete(k);
			return korisnikMapper.toDTO(k);
		}
		
		if(k.getTipKorisnika().equals(TipKorisnika.HOST)) {
			Host h =  this.korisnikRep.findHostById(id);
			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7978).usePlaintext().build();
			RezervacijaGrpcBlockingStub blockStub= RezervacijaGrpcGrpc.newBlockingStub(channel);
			ActiveResExistsRequest req=ActiveResExistsRequest.newBuilder().setUserId(h.getId()).setTip("host").build();
			ActiveResExistsResponse res=blockStub.reservationsForUserExists(req);
			
			if(res.getExists()==1) {
				System.out.println("Grpc rezulatat : Za smestaje hosta postoje aktivne rezervacije!");
				return null;
			}
			
			//TODO: posalji zahtev smestaju za brisanje korisnikovih smestaja
			ManagedChannel channelSmestaj = ManagedChannelBuilder.forAddress("localhost", 7977).usePlaintext().build();
			SmestajGrpcBlockingStub blockStubSm= SmestajGrpcGrpc.newBlockingStub(channelSmestaj);
			DeleteSmestajsForHostRequest delReq= DeleteSmestajsForHostRequest.newBuilder().setHostId(id).build();
			DeleteSmestajsForHostResponse delRes= blockStubSm.deketeSnestajsForHost(delReq);
			if(delRes.getSuccess()==0) {
				System.out.println("Grpc rezulatat : Neuspelo brisnaje smestaja za hosta");
				return null;
			}			
			this.korisnikRep.delete(k);
			return korisnikMapper.toDTO(k);
		}
		return null;
	}	
	private Collection<com.xml2023.mainapp.SmestajDTO>getAllSmestajForHost(String hostId) {
		ManagedChannel channelSmestaj = ManagedChannelBuilder.forAddress("localhost", 7977).usePlaintext().build();
		SmestajGrpcBlockingStub blockStubSm= SmestajGrpcGrpc.newBlockingStub(channelSmestaj);
		getListaSmestajaByUserIdRequest reqLista= getListaSmestajaByUserIdRequest.newBuilder().setId(hostId).build();
		getListaSmestajaByUserIdResponse resLista= blockStubSm.getListaSmestajaByUserId(reqLista);
		return resLista.getListaSmestajaList();
	}
	
	private Collection<com.xml2023.mainapp.RezervacijaDTO> getAllRezervacijaForGuest(String guestId){
		ManagedChannel channelRez = ManagedChannelBuilder.forAddress("localhost", 7977).usePlaintext().build();
		RezervacijaGrpcBlockingStub blockStubRez= RezervacijaGrpcGrpc.newBlockingStub(channelRez);
		getListaRezervacijaByUserIdRequest reqRez= getListaRezervacijaByUserIdRequest.newBuilder().setId(guestId).build();
		getListaRezervacijaByUserIdResponse resRez= blockStubRez.getListaRezervacijaByUserId(reqRez);
		if(resRez.getListaRezervacijaList()==null) {
			return new ArrayList<com.xml2023.mainapp.RezervacijaDTO>();
		}else return resRez.getListaRezervacijaList();
	}

	public OcenaHostBasicDTO giveRatingToHost(String substring, String hostId, OcenaHostBasicDTO ocena) {
		
		OcenaHost o= oRep.findByGostAndVlasnik(substring,hostId).orElse(null); 
		if(o==null) {
			o=new OcenaHost(  hostId, substring, ocena.getOcena(), LocalDate.now());
		}else {
			o.setDatum(LocalDate.now());
			o.setOcena(ocena.getOcena());
		}
		oRep.save(o);
		
		boolean statusIzmenjen = izmeniStatusHosta(hostId, determineIfIstaknuti(hostId));
		if(statusIzmenjen) {
			System.out.println("USPESNO IZMENJEN STATUS HOSTA");
		}
		return new OcenaHostBasicDTO(o);
	}
	
	private boolean determineIfIstaknuti(String hostId) {
		List<OcenaHost> listaOcena = this.oRep.findAllByVlasnik(hostId).orElse(null).stream().toList();
		double prosek = 0;
		if(listaOcena != null) {
			if(listaOcena.isEmpty() == false) {
				int suma = listaOcena.stream().mapToInt(OcenaHost::getOcena).sum();
				int brOcena = listaOcena.size();
				prosek = suma / brOcena;
			}
		}
		if(prosek > 4.7) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean izmeniStatusHosta(String idHosta, boolean istaknuti) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub korBlockingStub = KorisnikGrpcGrpc.newBlockingStub(channel);
		DobioStatusIstaknutogRequest rqst = DobioStatusIstaknutogRequest.newBuilder().setIdKorisnika(idHosta).setStatus(istaknuti).build();
		DobioStatusIstaknutogResponse rspns = korBlockingStub.istaknutiHost(rqst);
		return rspns.getResult();
	}
	public boolean novaOcenaHostaNotificationEnabled(String idVlasnika) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub bs = KorisnikGrpcGrpc.newBlockingStub(channel);
		NovaOcenaHostaNotifikacijaRequest rqst = NovaOcenaHostaNotifikacijaRequest.newBuilder().setIdKorisnika(idVlasnika).build();
		NovaOcenaHostaNotifikacijaResponse rspns = bs.novaOcenaHostaNotStatus(rqst);
		return rspns.getStanje();
	}
	
	public void newHostOcenaNotify(OcenaHost oh) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub bs = KorisnikGrpcGrpc.newBlockingStub(channel);
		NekoOcenioHostaRequest rqst = NekoOcenioHostaRequest.newBuilder().setIdKorisnika(oh.getGost()).setOcena(oh.getOcena()).build();
		NekoOcenioHostaResponse rspns = bs.newRankHost(rqst);
		if(rspns.getResult()) {
			System.out.println("USPESNO POSLATO OBAVESTENJE O NOVOJ OCENI HOSTA");
		}
	}
	
}
