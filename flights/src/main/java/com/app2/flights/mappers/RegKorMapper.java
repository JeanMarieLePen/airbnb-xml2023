package com.app2.flights.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app2.flights.dtos.PorudzbinaDTO;
import com.app2.flights.dtos.RegKorDTO;
import com.app2.flights.model.data.Porudzbina;
import com.app2.flights.model.user.RegKor;
import com.app2.flights.repositories.PorudzbinaRep;

@Component
public class RegKorMapper {
	
	@Autowired
	private AdresaMapper adresaMapper;
	@Autowired
	private PorudzbinaMapper porMapper;
	@Autowired
	private PorudzbinaRep porRep;
	
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
		Collection<String> tempList = new ArrayList<String>();
		for(PorudzbinaDTO pdto : dto.getPorudzbine()) {
			tempList.add(pdto.getId());
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
		for(String p : r.getPorudzbine()) {
			Porudzbina tmp = porRep.findById(p).orElse(null);
			if(tmp != null) {
				PorudzbinaDTO temp = porMapper.toDTO(tmp);
				tempList.add(temp);
			}else {
				return null;
			}
		}
		dto.setPorudzbine(tempList);
		return dto;
	}
}
