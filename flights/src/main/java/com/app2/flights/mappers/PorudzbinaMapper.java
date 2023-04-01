package com.app2.flights.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app2.flights.dtos.PorudzbinaDTO;
import com.app2.flights.dtos.RegKorDTO;
import com.app2.flights.model.data.Porudzbina;
import com.app2.flights.repositories.LetRep;
import com.app2.flights.repositories.RegKorRep;

@Component
public class PorudzbinaMapper {

	@Autowired
	private RegKorMapper regKorMapper;
	@Autowired
	private LetMapper letMapper;
	@Autowired RegKorRep regRep;
	@Autowired LetRep letRep;
	public Porudzbina fromDTO(PorudzbinaDTO dto) {
		Porudzbina p = new Porudzbina();
		p.setBrojKarata(dto.getBrojKarata());
		p.setId(dto.getId());
		p.setKupac(regRep.findById(dto.getKupac()).orElse(null));
		p.setLet(letRep.findById(dto.getLet()).orElse(null));
		p.setUkupnaCena(dto.getUkupnaCena());
		p.setStatus(dto.getStatus());
		return p;
	}
	

	
	public PorudzbinaDTO toDTO(Porudzbina p) {
		PorudzbinaDTO dto = new PorudzbinaDTO();
		dto.setBrojKarata(p.getBrojKarata());
		dto.setId(p.getId());
		dto.setKupac(p.getKupac().getId());
		dto.setLet(p.getLet().getId());
		dto.setUkupnaCena(p.getUkupnaCena());
		dto.setStatus(p.getStatus());
		dto.setLetBroj(p.getLet().getBrojLeta());
		dto.setStatus(p.getStatus());
		return dto;
	}

}
