package com.app2.flights.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app2.flights.dtos.KorisnikDTO;
import com.app2.flights.dtos.LetDTO;
import com.app2.flights.dtos.PorudzbinaDTO;
import com.app2.flights.dtos.RegKorDTO;
import com.app2.flights.dtos.UpdateProfileDTO;
import com.app2.flights.mappers.AdresaMapper;
import com.app2.flights.mappers.KorisnikMapper;
import com.app2.flights.mappers.PorudzbinaMapper;
import com.app2.flights.model.data.Adresa;
import com.app2.flights.model.data.Porudzbina;
import com.app2.flights.model.data.StatusPorudzbine;
import com.app2.flights.model.user.Korisnik;
import com.app2.flights.model.user.RegKor;
import com.app2.flights.model.user.StatusNaloga;
import com.app2.flights.repositories.AdresaRep;
import com.app2.flights.repositories.KorisnikRep;
import com.app2.flights.repositories.RegKorRep;


@Service
public class KorisnikService {

	@Autowired
	private KorisnikRep korRep;
	@Autowired
	private KorisnikMapper korMapper;
	@Autowired
	private AdresaMapper adrMapper;
	@Autowired
	private AdresaRep adrRep;
	@Autowired RegKorRep regRep;
	@Autowired PorudzbinaMapper porMap;
	
	public KorisnikDTO getProfile(String id) {
		Korisnik k = korRep.findById(id).orElse(null);
		if(k==null) {
			return null;
		}else {
			return korMapper.toDTO(k);
		}
	}


	@Transactional
	@CachePut(value="Korisnik", key="#id")
	public KorisnikDTO updateProfileById(String id, UpdateProfileDTO udto) {
		// TODO Auto-generated method stub
		Korisnik k = this.korRep.findById(id).orElse(null);
		//izmena u regKor tabeli
		RegKor rk= regRep.findByEmail(k.getEmail());
		Adresa a = this.adrRep.findById(k.getAdresa().getId()).orElse(null);
		if(k == null) {
			return null;
		}else {
			//izmena sifre ako je menjao
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			if(udto.getNovaSifraDTO() != null && !udto.getNovaSifraDTO().getNovaSifra().equals("")) {
				if(BCrypt.checkpw(udto.getNovaSifraDTO().getStaraSifra(), k.getPassword())) {
					String pw = bc.encode(udto.getNovaSifraDTO().getNovaSifra());
					k.setPassword(pw);
					rk.setPassword(pw);
				}else {
					return null;
				}
			}
			//adresa i ostalo
			a.setAdresa(udto.getKorisnikDTO().getAdresa().getAdresa());
			adrRep.save(a);
			k.setAdresa(adrMapper.fromDTO(udto.getKorisnikDTO().getAdresa()));
			k.setIme(udto.getKorisnikDTO().getIme());
			k.setPrezime(udto.getKorisnikDTO().getPrezime());
			rk.setIme(udto.getKorisnikDTO().getIme());
			rk.setPrezime(udto.getKorisnikDTO().getPrezime());
			
			if(udto.getKorisnikDTO().getSlike() != null) {
				Collection<byte[]> tempList = new ArrayList<byte[]>();
				for(String s : udto.getKorisnikDTO().getSlike()) {
					byte[] data = Base64.getDecoder().decode(s.split(",")[1]);
					tempList.add(data);
				}
				k.setSlike(tempList);
				rk.setSlike(tempList);

			}
			
			
			korRep.save(k);
			regRep.save(rk);
			
			KorisnikDTO retVal = this.korMapper.toDTO(k);
			return retVal;
		}
	}


	public List<PorudzbinaDTO> getPorudzbineByUser(String id) {
		RegKor k= regRep.findById(id).orElse(null);
		if(k==null) return null;
		
		List<PorudzbinaDTO> lista= k.getPorudzbine().stream().map(x->porMap.toDTO(x)).collect(Collectors.toList());
		return lista;
	}
	public List<PorudzbinaDTO> getPorudzbineByUserAndStatus(String id, StatusPorudzbine sp) {
		RegKor k= regRep.findById(id).orElse(null);
		if(k.equals(null)) return null;
		
		List<PorudzbinaDTO> lista= k.getPorudzbine().stream().filter(x->x.getStatus().equals(sp))
				.map(x->porMap.toDTO(x)).collect(Collectors.toList());
		return lista;
	}


	public KorisnikDTO deleteAcc(String id) {
		// TODO Auto-generated method stub
		//RegKor ne postoji
		RegKor rk = this.regRep.findById(id).orElse(null);
		if(rk == null) {
			return null;
		}
		//korisnik ne postoji
		Korisnik k = this.regRep.findById(id).orElse(null);
		if(k == null) {
			return null;
		}

		//ima aktivne rezervacije, ne moze da se obrise nalog
		if(this.hasActiveOrders(rk)) {
			return null;
		}
		//ako prodje sve provere, nalog se brise logicki
		rk.setStatus(StatusNaloga.OBRISAN);
		k.setStatus(StatusNaloga.OBRISAN);
		regRep.save(rk);
		korRep.save(k);

		KorisnikDTO retVal = korMapper.toDTO(k);
		return retVal;
	}
	public boolean hasActiveOrders(RegKor rk) {
		boolean retVal = rk.getPorudzbine().stream().map(Porudzbina::getStatus).filter(StatusPorudzbine.REZERVISANA::equals).findFirst().isPresent();
		return retVal;
	}
}
