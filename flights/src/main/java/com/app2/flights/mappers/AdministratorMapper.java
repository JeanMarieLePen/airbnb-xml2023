package com.app2.flights.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app2.flights.dtos.AdministratorDTO;
import com.app2.flights.dtos.LetDTO;
import com.app2.flights.dtos.PorudzbinaDTO;
import com.app2.flights.model.data.Let;
import com.app2.flights.model.data.Porudzbina;
import com.app2.flights.model.user.Administrator;
import com.app2.flights.repositories.LetRep;
import com.app2.flights.repositories.PorudzbinaRep;

@Component
public class AdministratorMapper {
	@Autowired
	private AdresaMapper adresaMapper;
	@Autowired
	private PorudzbinaMapper porMapper;
	@Autowired
	private LetMapper letMapper;
	@Autowired
	private PorudzbinaRep porRep;
	@Autowired
	private LetRep letRep;
	public Administrator fromDTO(AdministratorDTO dto) {
		Administrator a = new Administrator();
		a.setAdresa(adresaMapper.fromDTO(dto.getAdresa()));
		a.setEmail(dto.getEmail());
		a.setId(dto.getId());
		a.setIme(dto.getIme());
		a.setPassword(dto.getPassword());
		a.setPrezime(dto.getPrezime());
		a.setUsername(dto.getUsername());
		
		Collection<String> tempList = new ArrayList<String>();
		for(PorudzbinaDTO pdto : dto.getPorudzbine()) {
			String temp = pdto.getId();
			tempList.add(temp);
		}
		a.setPorudzbine(tempList);
		
		Collection<String> tempList2 = new ArrayList<String>();
		for(LetDTO ldto : dto.getLetovi()) {
			String temp = ldto.getId();
			tempList2.add(temp);
		}
		a.setLetovi(tempList2);
		return a;
	}
	
	public AdministratorDTO toDTO(Administrator a) {
		AdministratorDTO dto = new AdministratorDTO();
		dto.setAdresa(adresaMapper.toDTO(a.getAdresa()));
		dto.setEmail(a.getEmail());
		dto.setId(a.getId());
		dto.setIme(a.getIme());
		dto.setPassword(a.getPassword());
		dto.setPrezime(a.getPrezime());
		dto.setUsername(a.getUsername());
		
		Collection<PorudzbinaDTO> tempList = new ArrayList<PorudzbinaDTO>();
		for(String s : a.getPorudzbine()) {
//			PorudzbinaDTO temp = porMapper.toDTO(p);
			Porudzbina tmp = porRep.findById(s).orElse(null);
			if(tmp != null) {
				PorudzbinaDTO temp = porMapper.toDTO(tmp);
				tempList.add(temp);
			}else {
				return null;
			}
		}
		dto.setPorudzbine(tempList);
		
		Collection<LetDTO> tempList2 = new ArrayList<LetDTO>();
		for(String l : a.getLetovi()) {
			Let tmp = letRep.findById(l).orElse(null);
			if(tmp != null) {
				LetDTO temp = letMapper.toDTO(tmp);
				tempList2.add(temp);
			}else {
				return null;
			}
		}
		dto.setLetovi(tempList2);
		return dto;
	}
}
