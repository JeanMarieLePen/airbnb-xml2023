package com.xml.mainapp.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml.mainapp.dtos.data.PogodnostBasicDTO;
import com.xml.mainapp.dtos.data.PogodnostDTO;
import com.xml.mainapp.dtos.data.SmestajDTO;
import com.xml.mainapp.model.data.Pogodnost;
import com.xml.mainapp.model.data.Smestaj;

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
		Collection<Long> tempSmestaj = new ArrayList<Long>();
		if(p.getListaSmestaja() != null) {
			for(Smestaj s: p.getListaSmestaja()) {
				tempSmestaj.add(s.getId());
			}
		}
		dto.setListaSmestajaIds(tempSmestaj);
		return dto;
	}
}
