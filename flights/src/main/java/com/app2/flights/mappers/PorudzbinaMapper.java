package com.app2.flights.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app2.flights.dtos.PorudzbinaDTO;
import com.app2.flights.dtos.RegKorDTO;
import com.app2.flights.model.data.Let;
import com.app2.flights.model.data.Porudzbina;
import com.app2.flights.repositories.LetRep;

@Component
public class PorudzbinaMapper {
	@Autowired LetRep letRep;
	public Porudzbina fromDTO(PorudzbinaDTO dto) {
		Porudzbina p = new Porudzbina();
		p.setBrojKarata(dto.getBrojKarata());
		p.setId(dto.getId());
		p.setKupac(dto.getKupac());
		p.setLet(dto.getLet());
		p.setUkupnaCena(dto.getUkupnaCena());
		p.setStatus(dto.getStatus());
		return p;
	}
	
	public PorudzbinaDTO toDTO(Porudzbina p) {
		PorudzbinaDTO dto = new PorudzbinaDTO();
		dto.setBrojKarata(p.getBrojKarata());
		dto.setId(p.getId());
		
		dto.setKupac(p.getKupac());
		dto.setLet(p.getLet());
		dto.setUkupnaCena(p.getUkupnaCena());
		dto.setStatus(p.getStatus());
		Let l = letRep.findById(p.getLet()).orElse(null);
		if(l != null) {
			dto.setLetBroj(l.getBrojLeta());
		}else {
			return null;
		}
		dto.setStatus(p.getStatus());
		return dto;
	}

}
