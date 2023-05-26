package com.app2.flights.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app2.flights.dtos.KorisnikDTO;
import com.app2.flights.dtos.PorudzbinaDTO;
import com.app2.flights.dtos.TokenCreateDTO;
import com.app2.flights.dtos.TokenPrikazDTO;
import com.app2.flights.dtos.UpdateProfileDTO;
import com.app2.flights.mappers.AdresaMapper;
import com.app2.flights.mappers.KorisnikMapper;
import com.app2.flights.mappers.PorudzbinaMapper;
import com.app2.flights.model.data.APIToken;
import com.app2.flights.model.data.StatusPorudzbine;
import com.app2.flights.model.user.Korisnik;
import com.app2.flights.model.user.RegKor;
import com.app2.flights.model.user.StatusNaloga;
import com.app2.flights.repositories.KorisnikRep;
import com.app2.flights.repositories.PorudzbinaRep;


@Service
public class KorisnikService {

	@Autowired
	private KorisnikRep korRep;
	@Autowired
	private KorisnikMapper korMapper;
	@Autowired
	private AdresaMapper adrMapper;
	@Autowired
	private PorudzbinaRep porRep;
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
		if(k == null) {
			return null;
		}else {
			//izmena sifre ako je menjao
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			if(udto.getNovaSifraDTO() != null && !udto.getNovaSifraDTO().getNovaSifra().equals("")) {
				if(BCrypt.checkpw(udto.getNovaSifraDTO().getStaraSifra(), k.getPassword())) {
					String pw = bc.encode(udto.getNovaSifraDTO().getNovaSifra());
					k.setPassword(pw);
				}else {
					return null;
				}
			}
			//adresa i ostalo
			
			k.setAdresa(adrMapper.fromDTO(udto.getKorisnikDTO().getAdresa()));
			k.setIme(udto.getKorisnikDTO().getIme());
			k.setPrezime(udto.getKorisnikDTO().getPrezime());
			if(udto.getKorisnikDTO().getSlike() != null) {
				Collection<byte[]> tempList = new ArrayList<byte[]>();
				for(String s : udto.getKorisnikDTO().getSlike()) {
					byte[] data = Base64.getDecoder().decode(s.split(",")[1]);
					tempList.add(data);
				}
				k.setSlike(tempList);
			}
			korRep.save(k);
			
			KorisnikDTO retVal = this.korMapper.toDTO(k);
			return retVal;
		}
	}


	public List<PorudzbinaDTO> getPorudzbineByUser(String id) {
		RegKor k= (RegKor) korRep.findById(id).orElse(null);
		if(k==null) 
			return null;
		
		List<PorudzbinaDTO> lista = new ArrayList<PorudzbinaDTO>();
		for(String s : k.getPorudzbine()) {
			List<PorudzbinaDTO> lista2 = new ArrayList<PorudzbinaDTO>();
			lista2 = porRep.findAll().stream().filter(p -> p.getId().equals(s)).map(x -> porMap.toDTO(x)).toList();
			lista.addAll(lista2);
		}
//		lista =  porRep.findAll().stream().filter(p -> p.getId().equals(k.getPorudzbine())).map( x -> porMap.toDTO(x)).toList();
//		List<PorudzbinaDTO> lista= k.getPorudzbine().stream().map(x->porMap.toDTO(x)).collect(Collectors.toList());
		return lista;
	}
	public List<PorudzbinaDTO> getPorudzbineByUserAndStatus(String id, StatusPorudzbine sp) {
		RegKor k= (RegKor) korRep.findById(id).orElse(null);
		if(k.equals(null)) 
			return null;
		List<PorudzbinaDTO> lista = new ArrayList<PorudzbinaDTO>();
		lista = porRep.findAll().stream().filter(p -> p.getStatus().equals(sp) && p.getKupac().equals(id)).map(x -> porMap.toDTO(x)).toList();
//		List<PorudzbinaDTO> lista= k.getPorudzbine().stream().filter(x->x.getStatus().equals(sp))
//				.map(x->porMap.toDTO(x)).collect(Collectors.toList());
		return lista;
	}


	public KorisnikDTO deleteAcc(String id) {
		//korisnik ne postoji
		RegKor k = (RegKor) this.korRep.findById(id).orElse(null);
		if(k == null) {
			return null;
		}

		//ima aktivne rezervacije, ne moze da se obrise nalog
		if(this.hasActiveOrders(k)) {
			return null;
		}
		//ako prodje sve provere, nalog se brise logicki
		k.setStatus(StatusNaloga.OBRISAN);
		korRep.save(k);

		KorisnikDTO retVal = korMapper.toDTO(k);
		return retVal;
	}
	public boolean hasActiveOrders(RegKor rk) {
		boolean retVal = porRep.findAll().stream().filter(p -> p.getStatus().equals(StatusPorudzbine.REZERVISANA)).findAny().isPresent();
		return retVal;
	}


	public TokenPrikazDTO generateToken(TokenCreateDTO t, String idKorisnika) {
		// TODO Auto-generated method stub
		RegKor k = (RegKor) this.korRep.findById(idKorisnika).orElse(null);
		if(k != null) {
			APIToken token = new APIToken();
			if(t.isTrajni()) {
				token.setTrajni(true);
				token.setBrojSatiVazenja(-3);
				token.setVremeGenerisanja(LocalDateTime.now());
				//godinu dana umesto trajno
				token.setVremeVazenja(LocalDateTime.now().plusYears(1));
				String tekstTokena = RandomStringUtils.randomAlphanumeric(40);
				token.setToken(tekstTokena);
			}else {
				token.setTrajni(false);
				token.setVremeGenerisanja(LocalDateTime.now());
				token.setVremeVazenja(LocalDateTime.now().plusHours(t.getBrojSatiVazenja()));
				token.setBrojSatiVazenja(t.getBrojSatiVazenja());
				String tekstTokena = RandomStringUtils.randomAlphanumeric(40);
				token.setToken(tekstTokena);
			}
			k.setApiToken(token);
			this.korRep.save(k);
			TokenPrikazDTO retVal = new TokenPrikazDTO();
			retVal.setTokenTekst(k.getApiToken().getToken());
			retVal.setTrajni(k.getApiToken().isTrajni());
			retVal.setVremeVazenja(k.getApiToken().getVremeVazenja());
			return retVal;
		}
		return null;
	}


	public TokenPrikazDTO checkToken(String idKorisnika) {
		RegKor k = (RegKor) this.korRep.findById(idKorisnika).orElse(null);
		if(k != null) {
			if(k.getApiToken() == null) {
				return null;
			}
			if(k.getApiToken().getVremeVazenja().isBefore(LocalDateTime.now())) {
				return null;
			}
			TokenPrikazDTO retVal = new TokenPrikazDTO();
			retVal.setTrajni(k.getApiToken().isTrajni());
			retVal.setVremeVazenja(k.getApiToken().getVremeVazenja());
			retVal.setTokenTekst(k.getApiToken().getToken());
			return retVal;
		}
		return null;
	}
}
