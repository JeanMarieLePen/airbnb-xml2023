package com.xml2023.smestajmicroservice.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.xml2023.smestajmicroservice.dtos.PogodnostDTO;
import com.xml2023.smestajmicroservice.dtos.PretragaDTO;
import com.xml2023.smestajmicroservice.dtos.SmestajDTO;
import com.xml2023.smestajmicroservice.dtos.SmestajPretragaDTO;
import com.xml2023.smestajmicroservice.mappers.PogodnostMapper;
import com.xml2023.smestajmicroservice.mappers.SmestajBasicMapper;
import com.xml2023.smestajmicroservice.model.data.Host;
import com.xml2023.smestajmicroservice.model.data.OcenaSmestaj;
import com.xml2023.smestajmicroservice.model.data.Pogodnost;
import com.xml2023.smestajmicroservice.model.data.Rezervacija;
import com.xml2023.smestajmicroservice.model.data.Smestaj;
import com.xml2023.smestajmicroservice.model.data.StatusRezervacije;
import com.xml2023.smestajmicroservice.model.data.Termin;
import com.xml2023.smestajmicroservice.repositories.HostRepository;
import com.xml2023.smestajmicroservice.repositories.PogodnostRepository;
import com.xml2023.smestajmicroservice.repositories.RezervacijaRep;
import com.xml2023.smestajmicroservice.repositories.SmestajRep;


@Service
public class SmestajService {

	@Autowired
	private SmestajBasicMapper smestajMapper;
	@Autowired
	private SmestajRep smestajRep;
	@Autowired
	private RezervacijaRep rezRep;
	@Autowired
	private HostRepository hostRep;
	@Autowired
	private PogodnostRepository pogRep;
	@Autowired
	private PogodnostMapper pogMap;
	@Autowired MongoTemplate monTempl;

	public SmestajDTO createNew(SmestajDTO s) {
		//u maperu se definisu i cuvaju svi objekti koji su ugnjezdeni u objekat smestaj
		Smestaj tmp = smestajMapper.fromDTO(s);
		
//		Host vlasnik = hostRep.findById(s.getVlasnikId().substring(1, s.getVlasnikId().length() - 1)).orElse(null);
//		tmp.setVlasnik(vlasnik);
//		this.smestajRep.save(tmp);
		Collection<Pogodnost> svePogodnosti = pogRep.findAll();
		Collection<Pogodnost> setPogodnosti = new ArrayList<Pogodnost>();
		for(Pogodnost p: svePogodnosti) {
			for(PogodnostDTO tmpPgd : s.getPogodnosti()) {
				if(tmpPgd.getNaziv().equals(p.getNaziv())) {
					p.getListaSmestaja().add(tmp);
					pogRep.save(p);
					setPogodnosti.add(p);
				}
			}
		}
		tmp.setListaOcena(new ArrayList<OcenaSmestaj>());
		tmp.setPogodnosti(setPogodnosti);
		this.smestajRep.save(tmp);
		Host vlasnik = hostRep.findById(s.getVlasnikId().substring(1, s.getVlasnikId().length() - 1)).orElse(null);
		vlasnik.getSmestajList().add(tmp);
		this.hostRep.save(vlasnik);
		return s;
	}

	public SmestajDTO editSmestaj(SmestajDTO smestaj, String smestajId, String vlasnikId) {
		// TODO Auto-generated method stub
		Smestaj s = smestajRep.findById(smestajId).orElse(null);
		//Da li smestaj postoji?
		if(s==null) {
			return null;
		}
		//Da li je vlasnikId vlasnik tog smestaja?
		if(!s.getVlasnik().getId().equals(vlasnikId)) {
			return null;
		}
		
		s = smestajMapper.editSmestaj(s, smestaj);
		smestajRep.save(s);
		
		//cisto da proverim maper u dbgeru
		return smestajMapper.toDTO(s);
	}

	public SmestajDTO removeSmestaj(String smestajId, String vlasnikId) {
		// TODO Auto-generated method stub
		Smestaj s = smestajRep.findById(smestajId).orElse(null);
		//da li postoji smestaj sa tim id-om?
		if(s == null) {
			return null;
		}
		//da li smestaj pripada vlasniku sa id-om vlasnikId 
		if(s.getVlasnik().getId() != vlasnikId) {
			return null;
		}
		//da li ima trenutno aktivnih rezervacija prema tom smestaju
		Collection<Rezervacija> listaRezervacija = rezRep.findBySmestajIdAndStatus(vlasnikId, StatusRezervacije.REZERVISANA).orElse(null);
		if(listaRezervacija == null) {
			return null;
		}
		smestajRep.delete(s);
		return smestajMapper.toDTO(s);
	}

	public SmestajDTO getByIdSmestaja(String smestajId) {
		// TODO Auto-generated method stub
		Smestaj s = smestajRep.findById(smestajId).orElse(null);
		if(s == null)
			return null;
		return smestajMapper.toDTO(s);
	}

	public Collection<SmestajDTO> getAll() {
		// TODO Auto-generated method stub
		Collection<Smestaj> smestajList = smestajRep.findAll();
		Collection<SmestajDTO> retList = new ArrayList<SmestajDTO>();
		for(Smestaj s: smestajList) {
			retList.add(smestajMapper.toDTO(s));
		}
		return retList;
	}

	public Collection<PogodnostDTO> getAllPogodnosti() {
		// TODO Auto-generated method stub
		Collection<Pogodnost> tempList = this.pogRep.findAll();
		Collection<PogodnostDTO> retList = new ArrayList<PogodnostDTO>();
		for(Pogodnost p : tempList) {
			retList.add(pogMap.toDTO(p));
		}
		return retList;
	}
	public float getProsecnaOcena(Smestaj s) {
		Collection<OcenaSmestaj> ocene= s.getListaOcena();
		float uk=0;
		for(OcenaSmestaj o : ocene) {
			uk+=o.getOcena();
		}
		return uk/ocene.size();	
	}
	
}
