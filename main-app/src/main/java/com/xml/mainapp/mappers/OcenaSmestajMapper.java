package com.xml.mainapp.mappers;

import org.springframework.stereotype.Component;

import com.xml.mainapp.dtos.data.OcenaSmestajaDTO;
import com.xml.mainapp.model.data.OcenaSmestaj;


@Component
public class OcenaSmestajMapper {
	
	public OcenaSmestaj fromDTO(OcenaSmestajaDTO dto) {
		OcenaSmestaj os = new OcenaSmestaj();
		return os;
	}
	
	public OcenaSmestajaDTO toDTO(OcenaSmestaj os) {
		OcenaSmestajaDTO dto = new OcenaSmestajaDTO();
		dto.setDatum(os.getDatum());
		dto.setId(os.getId());
		dto.setOcena(os.getOcena());
		return dto;
	}
}
