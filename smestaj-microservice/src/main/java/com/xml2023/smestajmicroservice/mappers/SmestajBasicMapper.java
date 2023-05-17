package com.xml2023.smestajmicroservice.mappers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml2023.smestajmicroservice.dtos.SmestajBasicDTO;
import com.xml2023.smestajmicroservice.dtos.SmestajDTO;
import com.xml2023.smestajmicroservice.dtos.TerminDTO;
import com.xml2023.smestajmicroservice.model.data.Adresa;
import com.xml2023.smestajmicroservice.model.data.Cenovnik;
import com.xml2023.smestajmicroservice.model.data.Smestaj;
import com.xml2023.smestajmicroservice.model.data.Termin;


@Component
public class SmestajBasicMapper {

	@Autowired
	private AdresaMapper aMapper;
	@Autowired
	private CenovnikMapper cenovnikMapper;
//	@Autowired
//	private OcenaSmestajMapper ocenaSmestajMapper;
	@Autowired
	private TerminMapper terminMapper;
	public Smestaj fromBasicDTO(SmestajBasicDTO dto) {
		Smestaj s = new Smestaj();
		Adresa adr = aMapper.fromDTO(dto.getAdresa());
		s.setAdresa(adr);
		
		Cenovnik cenovnik = cenovnikMapper.fromDTO(dto.getCenovnik());
		s.setCenovnik(cenovnik);
		s.setMinGosti(dto.getMinGosti());
		s.setMaxGosti(dto.getMaxGosti());
		s.setPogodnosti(dto.getPogodnosti());
		return s;
	}
	
	public Smestaj fromDTO(SmestajDTO dto) {
		Smestaj s = new Smestaj();
		Adresa adr = aMapper.fromDTO(dto.getAdresa());
		s.setAdresa(adr);
		Cenovnik cenovnik = cenovnikMapper.fromDTO(dto.getCenovnik());
		s.setCenovnik(cenovnik);
		s.setMinGosti(dto.getMinGosti());
		s.setMaxGosti(dto.getMaxGosti());
		
		Collection<Termin> tempTermini = new ArrayList<Termin>();
		for(TerminDTO tdto : dto.getNedostupni()) {
			Termin t = terminMapper.fromDTO(tdto);
			tempTermini.add(t);
		}
		s.setNedostupni(tempTermini);
		
		Collection<byte[]> tempSlike = new ArrayList<byte[]>();
		for(String tmp: dto.getSlike()) {
			byte[] data = Base64.getDecoder().decode(tmp.split(",")[1]);
			tempSlike.add(data);
		}
		s.setSlike(tempSlike);
		
		return s;
	}
	
	public Smestaj editSmestaj(Smestaj s, SmestajDTO dto) {
		s.setAdresa(aMapper.fromDTO(dto.getAdresa()));
		s.setCenovnik(cenovnikMapper.fromDTO(dto.getCenovnik()));
		s.setMaxGosti(s.getMaxGosti());
		s.setMinGosti(s.getMinGosti());
		Collection<Termin> tempTermini = new ArrayList<Termin>();
		for(TerminDTO tdto : dto.getNedostupni()) {
			Termin t = terminMapper.fromDTO(tdto);
			tempTermini.add(t);
		}
		s.setNedostupni(tempTermini);
		s.setPogodnosti(dto.getPogodnosti());
		Collection<byte[]> tempSlike = new ArrayList<byte[]>();
		for(String tmp: dto.getSlike()) {
			byte[] data = Base64.getDecoder().decode(tmp.split(",")[1]);
			tempSlike.add(data);
		}
		s.setSlike(tempSlike);
		return s;
	}
	
	public SmestajDTO toDTO(Smestaj s) {
		SmestajDTO dto = new SmestajDTO();
		dto.setAdresa(aMapper.toDTO(s.getAdresa()));
		dto.setCenovnik(cenovnikMapper.toDTO(s.getCenovnik()));
		dto.setId(s.getId());
		dto.setMaxGosti(s.getMaxGosti());
		dto.setMinGosti(s.getMinGosti());
//		Collection<OcenaSmestajaDTO> tempLista = new ArrayList<OcenaSmestajaDTO>();
//		if(s.getListaOcena() != null) {
//			for(OcenaSmestaj o: s.getListaOcena()) {
//				tempLista.add(ocenaSmestajMapper.toDTO(o));
//			}
//		}
//		dto.setListaOcena(tempLista);
		dto.setVlasnikId(s.getVlasnik());
		
		
		Collection<TerminDTO> tempTermini = new ArrayList<TerminDTO>();
		if(s.getNedostupni() != null) {
			for(Termin t : s.getNedostupni()) {
				tempTermini.add(terminMapper.toDTO(t));
			}
		}
		dto.setNedostupni(tempTermini);
		
		Collection<String> tempSlike = new ArrayList<String>();
		if(s.getSlike() != null) {
			for(byte[] tmp : s.getSlike()) {
				String tempSlika = Base64.getEncoder().encodeToString(tmp);
				tempSlike.add(tempSlika);
			}
		}
		dto.setSlike(tempSlike);
		dto.setPogodnosti(s.getPogodnosti());
		
		
		return dto;
		
	}
}
