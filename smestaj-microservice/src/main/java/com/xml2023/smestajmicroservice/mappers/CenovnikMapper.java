package com.xml2023.smestajmicroservice.mappers;

import org.springframework.stereotype.Component;

import com.xml2023.smestajmicroservice.dtos.CenovnikDTO;
import com.xml2023.smestajmicroservice.model.data.Cenovnik;


@Component
public class CenovnikMapper {

	public Cenovnik fromDTO(CenovnikDTO dto) {
		Cenovnik c = new Cenovnik();
		c.setCena(dto.getCena());
		c.setCenaLeto(dto.getCenaLeto());
		c.setCenaPraznik(dto.getCenaPraznik());
		c.setCenaVikend(dto.getCenaVikend());
//		c.setId(dto.getId());
		c.setPoSmestaju(dto.isPoSmestaju());
		return c;
	}
	public CenovnikDTO toDTO(Cenovnik c) {
		CenovnikDTO cdto = new CenovnikDTO();
		cdto.setCena(c.getCena());
		cdto.setCenaLeto(c.getCenaLeto());
		cdto.setCenaPraznik(c.getCenaPraznik());
		cdto.setCenaVikend(c.getCenaVikend());
		cdto.setId(c.getId());
		cdto.setPoSmestaju(c.isPoSmestaju());
		return cdto;
	}
}
