package com.xml2023.smestajmicroservice.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml2023.smestajmicroservice.dtos.PogodnostBasicDTO;
import com.xml2023.smestajmicroservice.dtos.PogodnostDTO;
import com.xml2023.smestajmicroservice.model.data.Pogodnost;
import com.xml2023.smestajmicroservice.model.data.Smestaj;


@Component
public class PogodnostMapper {

	@Autowired
	private SmestajBasicMapper sMapper;
	
	public Pogodnost fromDTO(PogodnostBasicDTO dto) {
		Pogodnost p = new Pogodnost();
		p.setNaziv(dto.getNaziv());
//		p.setListaSmestaja(null);
		return p;
	}
	
	public Pogodnost fromDTO(PogodnostDTO dto) {
		Pogodnost p = new Pogodnost();
		p.setNaziv(dto.getNaziv());
//		p.setListaSmestaja(null);
		return p;
	}
	public PogodnostDTO toDTO(Pogodnost p) {
		PogodnostDTO dto = new PogodnostDTO();
		dto.setId(p.getId());
		dto.setNaziv(p.getNaziv());
		Collection<String> tempSmestaj = new ArrayList<String>();
		if(p.getListaSmestaja() != null) {
			for(Smestaj s: p.getListaSmestaja()) {
				tempSmestaj.add(s.getId());
				tempSmestaj.add(s.getId());
			}
		}
		dto.setListaSmestajaIds(tempSmestaj);
		return dto;
	}
}
