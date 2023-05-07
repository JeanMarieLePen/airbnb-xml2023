package com.example.reservationservice.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.reservationservice.dtos.OcenaSmestajaDTO;
import com.example.reservationservice.model.OcenaSmestaj;


@Component
public class OcenaSmestajMapper {

	@Autowired
	public SmestajBasicMapper sbMapper;
	@Autowired
	public GuestMapper guestMapper;
	
	public OcenaSmestaj fromDTO(OcenaSmestajaDTO dto) {
		OcenaSmestaj os = new OcenaSmestaj();
		os.setSmestaj(sbMapper.fromBasicDTO(dto.getSmestaj()));
		return os;
	}
	
	public OcenaSmestajaDTO toDTO(OcenaSmestaj os) {
		OcenaSmestajaDTO dto = new OcenaSmestajaDTO();
		dto.setDatum(os.getDatum());
		dto.setId(os.getId());
		dto.setOcena(os.getOcena());
//		dto.setSmestaj(sbMapper.toDTO(os.getSmestaj()));
		dto.setGost(guestMapper.toDTO(os.getGost()));
		return dto;
	}
}
