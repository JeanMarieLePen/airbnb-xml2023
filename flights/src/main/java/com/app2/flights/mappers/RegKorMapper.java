package com.app2.flights.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app2.flights.dtos.PorudzbinaDTO;
import com.app2.flights.dtos.RegKorDTO;
import com.app2.flights.model.data.Porudzbina;
import com.app2.flights.model.user.RegKor;

@Component
public class RegKorMapper {
	
	@Autowired
	private AdresaMapper adresaMapper;
	@Autowired
	private PorudzbinaMapper porMapper;
	
	public RegKor fromDTO(RegKorDTO dto) {
		RegKor r = new RegKor();
		r.setAdresa(adresaMapper.fromDTO(dto.getAdresa()));
		r.setEmail(dto.getEmail());
		r.setId(dto.getId());
		r.setId(dto.getId());
		r.setIme(dto.getIme());
		r.setPassword(dto.getPassword());
		r.setPrezime(dto.getPrezime());
		r.setUsername(dto.getUsername());
		Collection<Porudzbina> tempList = new ArrayList<Porudzbina>();
		for(PorudzbinaDTO pdto : dto.getPorudzbine()) {
			Porudzbina temp = porMapper.fromDTO(pdto);
			tempList.add(temp);
		}
		r.setPorudzbine(tempList);
		return r;
	}
	public RegKorDTO toDTO(RegKor r) {
		RegKorDTO dto = new RegKorDTO();
		dto.setAdresa(adresaMapper.toDTO(r.getAdresa()));
		dto.setEmail(r.getEmail());
		dto.setId(r.getId());
		dto.setIme(r.getIme());
		dto.setPrezime(r.getPrezime());
		dto.setPassword(r.getPassword());
		dto.setStatus(r.getStatus());
		dto.setTip(r.getTip());
		dto.setId(r.getId());
		dto.setUsername(r.getUsername());
		Collection<PorudzbinaDTO> tempList = new ArrayList<PorudzbinaDTO>();
		for(Porudzbina p : r.getPorudzbine()) {
			PorudzbinaDTO temp = porMapper.toDTO(p);
			tempList.add(temp);
		}
		dto.setPorudzbine(tempList);
		return dto;
	}
}
