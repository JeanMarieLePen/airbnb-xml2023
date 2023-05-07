package com.example.reservationservice.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.reservationservice.dtos.RezervacijaDTO;
import com.example.reservationservice.model.Rezervacija;


@Component
public class RezervacijaMapper {
	
	@Autowired
	private SmestajBasicMapper smestajMapper;

	
	public Rezervacija fromDTO(RezervacijaDTO dto) {
		Rezervacija r = new Rezervacija();
		r.setBrojGostiju(dto.getBrojGostiju());
		r.setDoDatum(dto.getDoDatum());
		r.setOdDatum(dto.getOdDatum());
		return r;
	}
	
	public RezervacijaDTO toDTO(Rezervacija r) {
		RezervacijaDTO dto = new RezervacijaDTO();
		dto.setBrojGostiju(r.getBrojGostiju());
		dto.setDoDatum(r.getDoDatum());
		dto.setOdDatum(r.getOdDatum());
		dto.setId(r.getId());
		dto.setSmestaj(smestajMapper.toDTO(r.getSmestaj()));
		dto.setStatus(r.getStatus());
		return dto;
	}
}
