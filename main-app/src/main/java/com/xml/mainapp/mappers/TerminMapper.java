package com.xml.mainapp.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml.mainapp.dtos.data.TerminDTO;
import com.xml.mainapp.model.data.Termin;
import com.xml.mainapp.repositories.SmestajRep;

@Component
public class TerminMapper {

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
		return tdto;
	}

}
