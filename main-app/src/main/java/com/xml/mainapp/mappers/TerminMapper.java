package com.xml.mainapp.mappers;

import org.springframework.stereotype.Component;

import com.xml.mainapp.dtos.data.TerminDTO;
import com.xml.mainapp.model.data.Termin;
@Component
public class TerminMapper {

	
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
