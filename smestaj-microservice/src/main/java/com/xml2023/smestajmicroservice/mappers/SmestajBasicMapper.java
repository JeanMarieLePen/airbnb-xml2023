package com.xml2023.smestajmicroservice.mappers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml2023.smestajmicroservice.dtos.OcenaSmestajaDTO;
import com.xml2023.smestajmicroservice.dtos.PogodnostBasicDTO;
import com.xml2023.smestajmicroservice.dtos.PogodnostDTO;
import com.xml2023.smestajmicroservice.dtos.SmestajBasicDTO;
import com.xml2023.smestajmicroservice.dtos.SmestajDTO;
import com.xml2023.smestajmicroservice.dtos.TerminDTO;
import com.xml2023.smestajmicroservice.model.data.Adresa;
import com.xml2023.smestajmicroservice.model.data.Cenovnik;
import com.xml2023.smestajmicroservice.model.data.OcenaSmestaj;
import com.xml2023.smestajmicroservice.model.data.Pogodnost;
import com.xml2023.smestajmicroservice.model.data.Rezervacija;
import com.xml2023.smestajmicroservice.model.data.Smestaj;
import com.xml2023.smestajmicroservice.model.data.Termin;
import com.xml2023.smestajmicroservice.repositories.AdresaRepository;
import com.xml2023.smestajmicroservice.repositories.CenovnikRepository;
import com.xml2023.smestajmicroservice.repositories.HostRepository;
import com.xml2023.smestajmicroservice.repositories.PogodnostRepository;
import com.xml2023.smestajmicroservice.repositories.SmestajRep;
import com.xml2023.smestajmicroservice.repositories.TerminRepository;


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
	
	@Autowired
	private CenovnikRepository cenRep;
	
	@Autowired
	private AdresaRepository adrRep;
	@Autowired
	private SmestajRep smestajRep;
	public Smestaj fromBasicDTO(SmestajBasicDTO dto) {
		Smestaj s = new Smestaj();
		Adresa adr = aMapper.fromDTO(dto.getAdresa());
		adrRep.save(adr);
		s.setAdresa(adr);
		
		Cenovnik cenovnik = cenovnikMapper.fromDTO(dto.getCenovnik());
		s.setCenovnik(cenovnik);
		s.setMinGosti(dto.getMinGosti());
		s.setMaxGosti(dto.getMaxGosti());
		Collection<Pogodnost> tempPogodnosti = new ArrayList<Pogodnost>();
		for(PogodnostBasicDTO pdto : dto.getPogodnosti()) {
			Pogodnost tmp = pogRep.findById(pdto.getId()).orElse(null);
			if(tmp!=null) {
				tempPogodnosti.add(tmp);
			}
		}
		s.setPogodnosti(tempPogodnosti);
		
		return s;
	}
	
	public Smestaj fromDTO(SmestajDTO dto) {
		Smestaj s = new Smestaj();
		Adresa adr = aMapper.fromDTO(dto.getAdresa());
		adrRep.save(adr);
		s.setAdresa(adr);
		Cenovnik cenovnik = cenovnikMapper.fromDTO(dto.getCenovnik());
		cenRep.save(cenovnik);
		s.setCenovnik(cenovnik);
		s.setMinGosti(dto.getMinGosti());
		s.setMaxGosti(dto.getMaxGosti());
		s.setVlasnik(hRep.findById(dto.getVlasnikId().substring(1, dto.getVlasnikId().length() - 1)).orElse(null));
		
		s.setRezervacije(new ArrayList<Rezervacija>());
		
		smestajRep.save(s);
		Collection<Termin> tempTermini = new ArrayList<Termin>();
		for(TerminDTO tdto : dto.getNedostupni()) {
			Termin t = terminMapper.fromDTO(tdto);
			terRep.save(t);
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
		
		//ADRESA I CENOVNIK NEMAJU DVOSTRANU REFERENCU
		adrRep.delete(s.getAdresa());
		cenRep.delete(s.getCenovnik());
		if(s.getNedostupni() != null) {
			terRep.deleteAll(s.getNedostupni());
		}
		
		for(Pogodnost tempPogodnost : s.getPogodnosti()) {
			Pogodnost pgd = pogRep.findById(tempPogodnost.getId()).orElse(null);
			if(pgd != null) {
//				pgd.getListaSmestaja().remove(s);
				for(Smestaj sTemp : pgd.getListaSmestaja()){
					if(sTemp.getId().equals(s.getId())) {
						pgd.getListaSmestaja().remove(sTemp);
						break;
					}
				}
				pogRep.save(pgd);
				
			}
		}
		
		
		s.setAdresa(null);
		s.setCenovnik(null);
//		s.setListaOcena(null);
		s.setNedostupni(new ArrayList<Termin>());
		s.setPogodnosti(new ArrayList<Pogodnost>());
		s.setRezervacije(new ArrayList<Rezervacija>());
		s.setSlike(new ArrayList<byte[]>());
		
		smestajRep.save(s);
		Adresa a = aMapper.fromDTO(dto.getAdresa());
		adrRep.save(a);
		s.setAdresa(a);
		
		Cenovnik c = cenovnikMapper.fromDTO(dto.getCenovnik());
		cenRep.save(c);
		s.setCenovnik(c);
		
		s.setMinGosti(dto.getMinGosti());
		s.setMaxGosti(dto.getMaxGosti());
		
		Collection<Pogodnost> svePogodnosti = pogRep.findAll();
		Collection<Pogodnost> setPogodnosti = new ArrayList<Pogodnost>();
		for(Pogodnost p: svePogodnosti) {
			for(PogodnostDTO tmpPgd : dto.getPogodnosti()) {
				if(tmpPgd.getNaziv().equals(p.getNaziv())) {
					p.getListaSmestaja().add(s);
					pogRep.save(p);
					setPogodnosti.add(p);
				}
			}
		}
		s.setPogodnosti(setPogodnosti);
//		Collection<Pogodnost> tempPogodnosti = new ArrayList<Pogodnost>();
//		for(PogodnostDTO pdto : dto.getPogodnosti()) {
//			Pogodnost tmp = pogodnostMapper.fromDTO(pdto);
//			tmp.setId(pdto.getId());
//			tmp.getListaSmestaja().add(s);
//			pogRep.save(tmp);
//			tempPogodnosti.add(tmp);
//		}
//		s.setPogodnosti(tempPogodnosti);
		
		
		if(s.getNedostupni() != null) {
			s.getNedostupni().removeAll(s.getNedostupni());
		}
		for(TerminDTO tdto : dto.getNedostupni()) {
			Termin t = new Termin();
			t.setKraj(tdto.getKraj());
			t.setPocetak(tdto.getPocetak());
			t.setSmestaj(s);
			terRep.save(t);
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
