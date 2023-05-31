package com.xml2023.smestajmicroservice.mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml2023.smestajmicroservice.dtos.OcenaSmestajaDTO;
import com.xml2023.smestajmicroservice.model.data.OcenaSmestaj;


@Component
public class OcenaSmestajMapper {

	@Autowired
	public SmestajBasicMapper sbMapper;
	
	public OcenaSmestaj fromDTO(OcenaSmestajaDTO dto) {
		OcenaSmestaj os = new OcenaSmestaj();
		os.setDatum(LocalDateTime.now());
		os.setId(dto.getId());
		os.setGost(dto.getGost());
		os.setSmestaj(dto.getSmestaj());
		os.setOcena(dto.getOcena());
		return os;
	}
	
	public OcenaSmestajaDTO toDTO(OcenaSmestaj os) {
		OcenaSmestajaDTO dto = new OcenaSmestajaDTO();
		dto.setDatum(os.getDatum());
		dto.setId(os.getId());
		dto.setOcena(os.getOcena());
		dto.setSmestaj(os.getSmestaj());
		dto.setGost(os.getGost());
		return dto;
	}
}
