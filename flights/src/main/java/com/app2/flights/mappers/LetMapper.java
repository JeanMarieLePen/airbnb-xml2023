package com.app2.flights.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app2.flights.dtos.AdresaDTO;
import com.app2.flights.dtos.LetDTO;
import com.app2.flights.dtos.LetDTOSimple;
import com.app2.flights.dtos.PorudzbinaDTO;
import com.app2.flights.dtos.RegKorDTO;
import com.app2.flights.model.data.Adresa;
import com.app2.flights.model.data.Let;
import com.app2.flights.model.data.Porudzbina;
import com.app2.flights.model.user.RegKor;
import com.app2.flights.repositories.LetRep;
import com.app2.flights.repositories.PorudzbinaRep;
import com.app2.flights.services.LetService;

@Component
public class LetMapper {

	@Autowired
	private PorudzbinaRep porRep;
	
	@Autowired RegKorMapper regKorMapper;
	@Autowired LetRep lRep;
	@Autowired LetService lServ;
	@Autowired PorudzbinaMapper porMap;
	public Let fromDTO(LetDTO dto) {
		Let l = new Let();
		l.setBrojLeta(dto.getBrojLeta());
		l.setCena(dto.getCena());
		l.setDatumIVreme(dto.getDatumIVreme());
		l.setId(dto.getId());
		l.setKapacitet(dto.getKapacitet());
		l.setLokOd(new Adresa(dto.getLokOd()));
		l.setLokDo(new Adresa(dto.getLokDo()));
		
		
		
		if(dto.getListaPorudzbina()!= null) {
			Collection<String> tempList = new ArrayList<String>();
			for(PorudzbinaDTO rdto: dto.getListaPorudzbina()) {
				String temp = rdto.getId();
				tempList.add(temp);
			}
			l.setListaPorudzbina(tempList);
		}
		
		return l;
	}
	
	public LetDTO toDTO(Let l) {
		LetDTO dto = new LetDTO();
		dto.setBrojLeta(l.getBrojLeta());
		dto.setCena(l.getCena());
		dto.setDatumIVreme(l.getDatumIVreme());
		dto.setId(l.getId());
		dto.setKapacitet(l.getKapacitet());
		//dto.setLokOd(adrMapper.toDTO(l.getLokOd()));
		//dto.setLokDo(adrMapper.toDTO(l.getLokDo()));
		
		dto.setLokOd(new AdresaDTO(l.getLokOd()));
		dto.setLokDo(new AdresaDTO(l.getLokDo()));
		
		Collection<PorudzbinaDTO> tempList = new ArrayList<PorudzbinaDTO>();
		for(String r : l.getListaPorudzbina()) {
			Porudzbina tmp = porRep.findById(r).orElse(null);
			if(tmp != null) {
				PorudzbinaDTO temp = porMap.toDTO(tmp);
				tempList.add(temp);
			}else {
				return null;
			}
		}
		dto.setListaPorudzbina(tempList);
		return dto;
	}
	
	public LetDTOSimple toDTOSimple(Let l) {
		
		int brojPutnika=0;
		Let l1= lRep.findById(l.getId()).orElse(null);
		if(l1== null) return null;
		//broj slob mesta
		int slobMesta= lServ.brojSlobodnihMesta(l.getId());
	LetDTOSimple dto= new LetDTOSimple(l.getId(), l.getLokOd().getAdresa(), l.getLokDo().getAdresa(),
			l.getBrojLeta(),l.getKapacitet(), l.getCena(), l.getDatumIVreme(), slobMesta);
	return dto;
	}
}
