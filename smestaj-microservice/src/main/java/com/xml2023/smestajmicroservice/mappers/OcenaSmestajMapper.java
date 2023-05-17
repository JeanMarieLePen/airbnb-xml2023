package com.xml2023.smestajmicroservice.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml2023.smestajmicroservice.dtos.OcenaSmestajaDTO;
import com.xml2023.smestajmicroservice.model.data.OcenaSmestaj;


@Component
public class OcenaSmestajMapper {

	@Autowired
	public SmestajBasicMapper sbMapper;
	
	public OcenaSmestaj fromDTO(OcenaSmestajaDTO dto) {
//		OcenaSmestaj os = new OcenaSmestaj();
//		os.setSmestaj(dto.getS);
//		os.setSmestaj(sbMapper.fromBasicDTO(dto.getSmestaj()));
//		return os;
		return null;
	}
	
	public OcenaSmestajaDTO toDTO(OcenaSmestaj os) {
//		OcenaSmestajaDTO dto = new OcenaSmestajaDTO();
//		dto.setDatum(os.getDatum());
//		dto.setId(os.getId());
//		dto.setOcena(os.getOcena());
////		dto.setSmestaj(sbMapper.toDTO(os.getSmestaj()));
//		dto.setGost(guestMapper.toDTO(os.getGost()));
//		return dto;
		return null;
	}
}
