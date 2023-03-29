package com.app2.flights.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app2.flights.dtos.AdresaDTO;
import com.app2.flights.dtos.LetDTO;
import com.app2.flights.dtos.LetDTOSimple;
import com.app2.flights.dtos.RegKorDTO;
import com.app2.flights.model.data.Adresa;
import com.app2.flights.model.data.Let;
import com.app2.flights.model.user.RegKor;
import com.app2.flights.repositories.LetRep;
import com.app2.flights.services.LetService;

@Component
public class LetMapper {

	@Autowired
	private AdresaMapper adrMapper;
	
	@Autowired RegKorMapper regKorMapper;
	@Autowired LetRep lRep;
	@Autowired LetService lServ;
	
	public Let fromDTO(LetDTO dto) {
		Let l = new Let();
		l.setBrojLeta(dto.getBrojLeta());
		l.setCena(dto.getCena());
		l.setDatumIVreme(dto.getDatumIVreme());
		l.setId(dto.getId());
		l.setKapacitet(dto.getKapacitet());
		//l.setLokOd(adrMapper.fromDTO(dto.getLokOd()));
		//l.setLokDo(adrMapper.fromDTO(dto.getLokDo()));
		
		l.setLokOd(new Adresa(dto.getLokOd()));
		l.setLokDo(new Adresa(dto.getLokDo()));
		
		
		
		if(dto.getListaPutnika()!= null) {
			Collection<RegKor> tempList = new ArrayList<RegKor>();
			for(RegKorDTO rdto: dto.getListaPutnika()) {
				RegKor temp = regKorMapper.fromDTO(rdto);
				tempList.add(temp);
			}
			l.setListaPutnika(tempList);
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
		
		Collection<RegKorDTO> tempList = new ArrayList<RegKorDTO>();
		for(RegKor r : l.getListaPutnika()) {
			RegKorDTO temp = regKorMapper.toDTO(r);
			tempList.add(temp);
		}
		dto.setListaPutnika(tempList);
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
