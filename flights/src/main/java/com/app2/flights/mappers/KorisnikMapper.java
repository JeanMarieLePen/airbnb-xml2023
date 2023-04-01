package com.app2.flights.mappers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app2.flights.dtos.KorisnikDTO;
import com.app2.flights.dtos.RegisterDTO;
import com.app2.flights.model.user.Korisnik;

@Component
public class KorisnikMapper {

	@Autowired
	private AdresaMapper adresaMapper;
	public Korisnik fromDTO(KorisnikDTO dto) {
		Korisnik k = new Korisnik();
		k.setAdresa(adresaMapper.fromDTO(dto.getAdresa()));
		k.setEmail(dto.getEmail());
		k.setId(dto.getId());
		k.setIme(dto.getIme());
		k.setPassword(dto.getPassword());
		k.setPrezime(dto.getPrezime());
		k.setUsername(dto.getUsername());
//		k.setStatus(dto.getStatus());
//		k.setTip(dto.getTip());
		return k;
	}
	
	public Korisnik fromDTOReg(RegisterDTO dto) {
		Korisnik k = new Korisnik();
		k.setAdresa(adresaMapper.fromDTO(dto.getAdresa()));
		k.setEmail(dto.getEmail());
		k.setIme(dto.getIme());
		k.setPassword(dto.getPassword());
		k.setPrezime(dto.getPrezime());
		k.setUsername(dto.getUsername());
		Collection<byte[]> tempSlike = new ArrayList<byte[]>();
		for(String s: dto.getSlike()) {
			byte[] data = Base64.getDecoder().decode(s.split(",")[1]);
			tempSlike.add(data);
		}
		k.setSlike(tempSlike);
//		k.setStatus(dto.getStatus());
//		k.setTip(dto.getTip());
		return k;
	}
	public KorisnikDTO toDTO(Korisnik k) {
		KorisnikDTO dto = new KorisnikDTO();
		dto.setAdresa(adresaMapper.toDTO(k.getAdresa()));
		dto.setEmail(k.getEmail());
		dto.setId(k.getId());
		dto.setIme(k.getIme());
		dto.setPrezime(k.getPrezime());
		dto.setPassword(k.getPassword());
		dto.setStatus(k.getStatus());
		dto.setTip(k.getTip());
		Collection<String> tempSlike = new ArrayList<String>();
		if(k.getSlike() != null) {
			for(byte[] tmp : k.getSlike()) {
				String tempSlika = Base64.getEncoder().encodeToString(tmp);
				tempSlike.add(tempSlika);
			}
		}
		dto.setSlike(tempSlike);
		dto.setUsername(k.getUsername());
		return dto;
	}
}
