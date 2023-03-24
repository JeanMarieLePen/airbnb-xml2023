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

@Component
public class AdministratorMapper {
	@Autowired
	private AdresaMapper adresaMapper;
	@Autowired
	private PorudzbinaMapper porMapper;
	@Autowired
	private LetMapper letMapper;
	public Administrator fromDTO(AdministratorDTO dto) {
		Administrator a = new Administrator();
		a.setAdresa(adresaMapper.fromDTO(dto.getAdresa()));
		a.setEmail(dto.getEmail());
		a.setId(dto.getId());
		a.setIme(dto.getIme());
		a.setPassword(dto.getPassword());
		a.setPrezime(dto.getPrezime());
		a.setUsername(dto.getUsername());
		
		Collection<Porudzbina> tempList = new ArrayList<Porudzbina>();
		for(PorudzbinaDTO pdto : dto.getPorudzbine()) {
			Porudzbina temp = porMapper.fromDTO(pdto);
			tempList.add(temp);
		}
		a.setPorudzbine(tempList);
		
		Collection<Let> tempList2 = new ArrayList<Let>();
		for(LetDTO ldto : dto.getLetovi()) {
			Let temp = letMapper.fromDTO(ldto);
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
		for(Porudzbina p : a.getPorudzbine()) {
			PorudzbinaDTO temp = porMapper.toDTO(p);
			tempList.add(temp);
		}
		dto.setPorudzbine(tempList);
		
		Collection<LetDTO> tempList2 = new ArrayList<LetDTO>();
		for(Let l : a.getLetovi()) {
			LetDTO temp = letMapper.toDTO(l);
			tempList2.add(temp);
		}
		dto.setLetovi(tempList2);
		return dto;
	}
}
