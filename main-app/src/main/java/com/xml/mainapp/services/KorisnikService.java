package com.xml.mainapp.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.xml.mainapp.dtos.KorisnikDTO;
import com.xml.mainapp.dtos.UpdateProfileDTO;
import com.xml.mainapp.dtos.data.OcenaSmestajaDTO;
import com.xml.mainapp.dtos.data.RezervacijaDTO;
import com.xml.mainapp.dtos.data.SmestajDTO;
import com.xml.mainapp.dtos.user.HostDTO;
import com.xml.mainapp.mappers.AdresaMapper;
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
import com.xml.mainapp.model.users.Guest;
import com.xml.mainapp.model.users.Host;
import com.xml.mainapp.model.users.Korisnik;
import com.xml.mainapp.model.users.TipKorisnika;
import com.xml.mainapp.repositories.AdresaRep;
import com.xml.mainapp.repositories.GuestRepository;
import com.xml.mainapp.repositories.HostRepository;
import com.xml.mainapp.repositories.KorisnikRep;
import com.xml.mainapp.repositories.SmestajRep;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRep korisnikRep;
	@Autowired
	private AdresaMapper aMapper;
	@Autowired 
	private KorisnikMapper korisnikMapper;
	@Autowired 
	private AdresaRep adrRep;
	@Autowired
	private GuestRepository guestRep;
	@Autowired
	private RezervacijaMapper rezMapper;
	@Autowired
	private SmestajRep smestajRep;
	@Autowired
	private OcenaSmestajMapper osMapper;
	@Autowired
	private HostRepository hostRep;
	@Autowired
	private HostMapper hostMapper;
	@Autowired
	private SmestajBasicMapper smestajBasicMapper;
	
//	@Cacheable(key="#id", value = "Korisnik")
	public KorisnikDTO getUserById(String id) {
		// TODO Auto-generated method stub
		Korisnik k = this.korisnikRep.findById(id).orElse(null);
		
		if(k == null) {
			return null;
		}else {
			KorisnikDTO retVal = this.korisnikMapper.toDTO(k);
			if(k.getTipKorisnika().equals(TipKorisnika.HOST)) {
				Host h = this.hostRep.findById(id).orElse(null);
				retVal.setIstaknuti(h.isIstaknuti());
				retVal.setRezAutomatski(h.isRezAutomatski());
				Collection<SmestajDTO> smestajList = new ArrayList<SmestajDTO>();
				if(h.getSmestajList() != null) {
					for(Smestaj s : h.getSmestajList()) {
						smestajList.add(smestajBasicMapper.toDTO(s));
					}
				}
				retVal.setSmestajList(smestajList);
			}
			return retVal;
		}
	}

	public HostDTO findHostById(String id) {
		// TODO Auto-generated method stub
		Host h = this.hostRep.findById(id).orElse(null);
		if(h == null) {
			return null;
		}
		HostDTO retVal = this.hostMapper.toDTO(h);
		return retVal;
	}
//	@Transactional
//	@CachePut(value="Korisnik", key="#id")
	public KorisnikDTO updateProfileById(String id, UpdateProfileDTO udto) {
		// TODO Auto-generated method stub
		Korisnik k = this.korisnikRep.findById(id).orElse(null);
		if(k == null || (korisnikRep.findByEmail(udto.getKorisnikDTO().getEmail()) != null && !korisnikRep.findByEmail(udto.getKorisnikDTO().getEmail()).getId().equals(id)) || (korisnikRep.findByKorIme(udto.getKorisnikDTO().getKorIme()) != null) && 
				!korisnikRep.findByKorIme(udto.getKorisnikDTO().getKorIme()).getId().equals(id)) {
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
			//adresa i ostalo
			Adresa a = adrRep.findById(udto.getKorisnikDTO().getAdresa().getId()).orElse(null);
			if(a == null) {
				return null;
			}
			
			a.setAdresa(udto.getKorisnikDTO().getAdresa().getAdresa());
			a.setLat(udto.getKorisnikDTO().getAdresa().getLat());
			a.setLng(udto.getKorisnikDTO().getAdresa().getLng());
			adrRep.save(a);
			k.setAdresa(a);
			k.setIme(udto.getKorisnikDTO().getIme());
			k.setPrezime(udto.getKorisnikDTO().getPrezime());
			k.setKorIme(udto.getKorisnikDTO().getKorIme());
			k.setEmail(udto.getKorisnikDTO().getEmail());
//			k.setAdresa(aMapper.fromDTO(udto.getKorisnikDTO().getAdresa()));
			if(k.getSlike() != null) {
				Collection<byte[]> tempList = new ArrayList<byte[]>();
				for(String s : udto.getKorisnikDTO().getSlike()) {
					byte[] data = Base64.getDecoder().decode(s.split(",")[1]);
					tempList.add(data);
				}
				k.setSlike(tempList);
			}
			korisnikRep.save(k);
			if(k.getTipKorisnika().equals(TipKorisnika.HOST)) {
				Host h = hostRep.findById(k.getId()).orElse(null);
				if(h!=null) {
					h.setRezAutomatski(udto.getKorisnikDTO().isRezAutomatski());
				}
				hostRep.save(h);
			}
			if(k.getTipKorisnika().equals(TipKorisnika.GUEST)) {
				Guest g = guestRep.findById(k.getId()).orElse(null);
				if(g!= null) {
					
				}
			}
			KorisnikDTO retVal = this.korisnikMapper.toDTO(k);
			return retVal;
		}
	}

	public Collection<RezervacijaDTO> getAllReservationByUserId(String id) {
		// TODO Auto-generated method stub
		Guest g = guestRep.findById(id).orElse(null);
		if(g == null)
			return null;
		Collection<RezervacijaDTO> retList = new ArrayList<RezervacijaDTO>();
		if(retList != null) {
			for(Rezervacija r : g.getRezervacije()) {
				retList.add(rezMapper.toDTO(r));
			}
		}
		return retList;
	}

	public OcenaSmestajaDTO giveRatingToSmestaj(String userId, String smestajId, OcenaSmestajaDTO ocena) {
		// TODO Auto-generated method stub
		Guest g = this.guestRep.findById(userId).orElse(null);
		Smestaj s = this.smestajRep.findById(smestajId).orElse(null);
		OcenaSmestaj os = new OcenaSmestaj();
		os.setGost(g);
		os.setSmestaj(s);
		os.setDatum(ocena.getDatum());
		os.setOcena(ocena.getOcena());
		s.getListaOcena().add(os);
		smestajRep.save(s);
		return osMapper.toDTO(os);
	}

	public boolean canGiveRating(String userId, String smestajId) {
		// TODO Auto-generated method stub
		Guest g = this.guestRep.findById(userId).orElse(null);
		if(g == null) {
			return false;
		}
		Smestaj s = this.smestajRep.findById(smestajId).orElse(null);
		if(s == null) {
			return false;
		}
//		s.getListaOcena().stream().map(OcenaSmestaj::getGost).filter(g::equals);
		boolean retVal = false;
		
		retVal = s.getRezervacije().stream().map(Rezervacija::getGost).filter(g::equals).findFirst().isPresent() && s.getListaOcena().stream().filter(p -> p.getGost().getId().equals(userId)).findFirst().isPresent();
		return !retVal;
	}

	public KorisnikDTO deleteAcc(String id) {
		// TODO Auto-generated method stub
		Korisnik k = this.korisnikRep.findById(id).orElse(null);
		if(k == null) {
			return null;
		}
		
		if(k.getTipKorisnika().equals(TipKorisnika.GUEST)) {
			Guest g = guestRep.findById(id).orElse(null);
			if(g.getRezervacije().stream().anyMatch(r -> r.getStatus().equals(StatusRezervacije.REZERVISANA))){
				return null;
			}else if(g.getRezervacije().stream().anyMatch(r -> r.getStatus().equals(StatusRezervacije.PENDING))) {
				return null;
			}else {
				this.korisnikRep.delete(k);
				this.guestRep.delete(g);
				return korisnikMapper.toDTO(k);
			}
		}
		
		if(k.getTipKorisnika().equals(TipKorisnika.HOST)) {
			Host h = hostRep.findById(id).orElse(null);
			if(h.getSmestajList().stream().anyMatch(s -> s.getRezervacije().stream().anyMatch(r -> r.getStatus().equals(StatusRezervacije.REZERVISANA)))) {
				return null;
			}
			else if(h.getSmestajList().stream().anyMatch(s -> s.getRezervacije().stream().anyMatch(r -> r.getStatus().equals(StatusRezervacije.PENDING)))) {
				return null;
			}else {
				this.korisnikRep.delete(k);
				this.hostRep.delete(h);
				return korisnikMapper.toDTO(k);
			}
		}
		return null;
	}
	
	
	
}
