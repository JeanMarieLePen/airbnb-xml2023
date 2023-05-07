package com.example.reservationservice.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.reservationservice.dtos.OcenaHostDTO;
import com.example.reservationservice.model.OcenaHost;


@Component
public class OcenaHostMapper {

	@Autowired
	private GuestMapper guestMapper;
	@Autowired
	private HostMapper hostMapper;
	
	public OcenaHost fromDTO(OcenaHostDTO dto) {
		OcenaHost o = new OcenaHost();
		o.setDatum(dto.getDatum());
		o.setOcena(dto.getOcena());
		o.setGost(guestMapper.fromDTO(dto.getGost()));
		o.setVlasnik(hostMapper.fromDTO(dto.getVlasnik()));
		return o;
	}
	
	public OcenaHostDTO toDTO(OcenaHost o) {
		OcenaHostDTO dto = new OcenaHostDTO();
		dto.setDatum(o.getDatum());
		dto.setGost(guestMapper.toDTO(o.getGost()));
		dto.setId(o.getId());
		dto.setVlasnik(hostMapper.toDTO(o.getVlasnik()));
		return dto;
	}
}
