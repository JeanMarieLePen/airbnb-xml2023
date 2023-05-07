package com.xml.mainapp.mappers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml.mainapp.dtos.KorisnikDTO;
import com.xml.mainapp.dtos.RegisterDTO;
import com.xml.mainapp.model.users.Korisnik;

@Component
public class KorisnikMapper {

	@Autowired
	private AdresaMapper aMapper;
	
	public Korisnik fromDTO(KorisnikDTO kdto) {
		Korisnik k = new Korisnik();
		k.setAdresa(aMapper.fromDTO(kdto.getAdresa()));
		k.setEmail(kdto.getEmail());
		k.setIme(kdto.getIme());
		k.setPrezime(kdto.getPrezime());
		k.setKorIme(kdto.getKorIme());
		k.setId(kdto.getId());
		k.setLozinka(kdto.getLozinka());
		k.setTipKorisnika(kdto.getTipKorisnika());
		Collection<byte[]> tempSlike = new ArrayList<byte[]>();
		for(String s: kdto.getSlike()) {
			byte[] data = Base64.getDecoder().decode(s.split(",")[1]);
			tempSlike.add(data);
		}
		k.setSlike(tempSlike);
		return k;
	}
	
	public Korisnik fromDTOReg(RegisterDTO kdto) {
		Korisnik k = new Korisnik();
		k.setAdresa(aMapper.fromDTO(kdto.getAdresa()));
		k.setEmail(kdto.getEmail());
		k.setIme(kdto.getIme());
		k.setPrezime(kdto.getPrezime());
		k.setKorIme(kdto.getKorIme());
		k.setLozinka(kdto.getLozinka());
		k.setTipKorisnika(kdto.getTipKorisnika());
		Collection<byte[]> tempSlike = new ArrayList<byte[]>();
		for(String s: kdto.getSlike()) {
			byte[] data = Base64.getDecoder().decode(s.split(",")[1]);
			tempSlike.add(data);
		}
		k.setSlike(tempSlike);
		return k;
	}
	
	public KorisnikDTO toDTO(Korisnik k) {
		KorisnikDTO kdto = new KorisnikDTO();
		kdto.setAdresa(aMapper.toDTO(k.getAdresa()));
		kdto.setEmail(k.getEmail());
		kdto.setIme(k.getIme());
		kdto.setPrezime(k.getPrezime());
		kdto.setKorIme(k.getKorIme());
		kdto.setId(k.getId());
		kdto.setLozinka(k.getLozinka());
		kdto.setTipKorisnika(k.getTipKorisnika());
		kdto.setStatusNaloga(k.getStatusNaloga());
		if(k.getSlike() != null) {
			Collection<String> tempSlike = new ArrayList<String>();
			for(byte[] s : k.getSlike()) {
				String tempSlika = Base64.getEncoder().encodeToString(s);
				tempSlike.add(tempSlika);
			}
			kdto.setSlike(tempSlike);
		}else {
			kdto.setSlike(null);
		}
		return kdto;
	}
}
