package com.xml2023.smestajmicroservice.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml2023.smestajmicroservice.dtos.TerminDTO;
import com.xml2023.smestajmicroservice.model.data.Termin;
import com.xml2023.smestajmicroservice.repositories.SmestajRep;
import com.xml2023.smestajmicroservice.repositories.TerminRepository;

@Component
public class TerminMapper {

	@Autowired
	private TerminRepository terminRep;
	@Autowired
	private SmestajBasicMapper smestajMapper;
	@Autowired
	private SmestajRep smestajRep;
	
	public Termin fromDTO(TerminDTO tdto) {
		Termin t = new Termin();
		t.setPocetak(tdto.getPocetak());
		t.setKraj(tdto.getKraj());
//		t.setSmestaj(tdto.getSmestaj());
//		t.setSmestaj(smestajMapper.fromDTO(tdto.getSmestaj()));
		return t;
	}
	public TerminDTO toDTO(Termin t) {
		TerminDTO tdto = new TerminDTO();
		tdto.setKraj(t.getKraj());
		tdto.setPocetak(t.getPocetak());
//		tdto.setSmestaj(smestajMapper.toDTO(t.getSmestaj()));
		tdto.setId(t.getId());
		return tdto;
	}

}