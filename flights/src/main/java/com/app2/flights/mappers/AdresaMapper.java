package com.app2.flights.mappers;

import org.springframework.stereotype.Component;

import com.app2.flights.dtos.AdresaDTO;
import com.app2.flights.model.data.Adresa;

@Component
public class AdresaMapper {

	public Adresa fromDTO(AdresaDTO dto) {
		Adresa a = new Adresa();
		a.setAdresa(dto.getAdresa());
		a.setId(dto.getId());
		a.setLat(dto.getLat());
		a.setLng(dto.getLng());
		return a;
	}
	public AdresaDTO toDTO(Adresa a) {
		AdresaDTO dto = new AdresaDTO();
		dto.setId(a.getId());
		dto.setAdresa(a.getAdresa());
		dto.setId(a.getId());
		dto.setLat(a.getLat());
		dto.setLng(a.getLng());
		return dto;
	}
}
