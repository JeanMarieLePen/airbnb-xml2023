package com.example.reservationservice.mappers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.reservationservice.dtos.OcenaSmestajaDTO;
import com.example.reservationservice.dtos.PogodnostBasicDTO;
import com.example.reservationservice.dtos.PogodnostDTO;
import com.example.reservationservice.dtos.SmestajBasicDTO;
import com.example.reservationservice.dtos.SmestajDTO;
import com.example.reservationservice.dtos.TerminDTO;
import com.example.reservationservice.model.OcenaSmestaj;
import com.example.reservationservice.model.Pogodnost;
import com.example.reservationservice.model.Smestaj;
import com.example.reservationservice.model.Termin;
import com.example.reservationservice.repositories.HostRepository;
import com.example.reservationservice.repositories.PogodnostRepository;
import com.example.reservationservice.repositories.TerminRepository;

@Component
public class SmestajBasicMapper {

	@Autowired
	private AdresaMapper aMapper;
	@Autowired
	private CenovnikMapper cenovnikMapper;
	@Autowired
	private PogodnostMapper pogodnostMapper;
	@Autowired
	private OcenaSmestajMapper ocenaSmestajMapper;
	@Autowired
	private HostMapper hMapper;
	@Autowired
	private HostRepository hRep;
	@Autowired
	private TerminMapper terminMapper;
	@Autowired
	private PogodnostRepository pogRep;
	@Autowired
	private TerminRepository terRep;
	
	public Smestaj fromBasicDTO(SmestajBasicDTO dto) {
		Smestaj s = new Smestaj();
		s.setAdresa(aMapper.fromDTO(dto.getAdresa()));
		s.setCenovnik(cenovnikMapper.fromDTO(dto.getCenovnik()));
		s.setId(dto.getId());
		s.setMinGosti(dto.getMinGosti());
		s.setMaxGosti(dto.getMaxGosti());
		Collection<Pogodnost> tempPogodnosti = new ArrayList<Pogodnost>();
		for(PogodnostBasicDTO pdto : dto.getPogodnosti()) {
			Pogodnost tmp = pogodnostMapper.fromDTO(pdto);
			tempPogodnosti.add(tmp);
		}
		s.setPogodnosti(tempPogodnosti);
		
		return s;
	}
	
	public Smestaj fromDTO(SmestajDTO dto) {
		Smestaj s = new Smestaj();
		s.setAdresa(aMapper.fromDTO(dto.getAdresa()));
		s.setCenovnik(cenovnikMapper.fromDTO(dto.getCenovnik()));
//		s.setId(dto.getId());
		s.setMinGosti(dto.getMinGosti());
		s.setMaxGosti(dto.getMaxGosti());
//		s.setVlasnik(hMapper.fromDTO(dto.getVlasnik()));
		
//		Collection<Pogodnost> tempPogodnosti = new ArrayList<Pogodnost>();
//		for(PogodnostDTO pdto : dto.getPogodnosti()) {
//			Pogodnost tmp = pogodnostMapper.fromDTO(pdto);
//			tempPogodnosti.add(tmp);
//		}
//		s.setPogodnosti(tempPogodnosti);
		
		Collection<Termin> tempTermini = new ArrayList<Termin>();
		for(TerminDTO tdto : dto.getNedostupni()) {
			Termin t = terminMapper.fromDTO(tdto);
			t.setSmestaj(s);
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
		s.setMinGosti(dto.getMinGosti());
		s.setMaxGosti(dto.getMaxGosti());
		
		
		
		Collection<Pogodnost> tempPogodnosti = new ArrayList<Pogodnost>();
		for(PogodnostDTO pdto : dto.getPogodnosti()) {
			Pogodnost tmp = pogodnostMapper.fromDTO(pdto);
			tempPogodnosti.add(tmp);
		}
		s.setPogodnosti(tempPogodnosti);
		
		
		s.getNedostupni().removeAll(s.getNedostupni());
		for(TerminDTO tdto : dto.getNedostupni()) {
			Termin t = terminMapper.fromDTO(tdto);
			t.setSmestaj(s);
			s.getNedostupni().add(t);
		}
		
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
		Collection<OcenaSmestajaDTO> tempLista = new ArrayList<OcenaSmestajaDTO>();
		if(s.getListaOcena() != null) {
			for(OcenaSmestaj o: s.getListaOcena()) {
				tempLista.add(ocenaSmestajMapper.toDTO(o));
			}
		}
		dto.setVlasnikId(s.getVlasnik().getId());
		dto.setListaOcena(tempLista);
		
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
		
		Collection<PogodnostDTO> tempPogodnosti = new ArrayList<PogodnostDTO>();
		if(s.getPogodnosti() != null) {
			for(Pogodnost p : s.getPogodnosti()) {
				tempPogodnosti.add(pogodnostMapper.toDTO(p));
			}
		}
		dto.setPogodnosti(tempPogodnosti);
		
		
		return dto;
		
	}
}
