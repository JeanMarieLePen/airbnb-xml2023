package com.xml.mainapp.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.mainapp.dtos.data.PogodnostDTO;
import com.xml.mainapp.dtos.data.SmestajDTO;
import com.xml.mainapp.mappers.PogodnostMapper;
import com.xml.mainapp.mappers.SmestajBasicMapper;
import com.xml.mainapp.model.data.Pogodnost;
import com.xml.mainapp.model.data.Rezervacija;
import com.xml.mainapp.model.data.Smestaj;
import com.xml.mainapp.model.data.StatusRezervacije;
import com.xml.mainapp.model.data.Termin;
import com.xml.mainapp.model.users.Host;
import com.xml.mainapp.repositories.HostRepository;
import com.xml.mainapp.repositories.PogodnostRepository;
import com.xml.mainapp.repositories.RezervacijaRep;
import com.xml.mainapp.repositories.SmestajRep;

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
	
	public SmestajDTO createNew(SmestajDTO s) {
		Smestaj tmp = smestajMapper.fromDTO(s);
		Host vlasnik = hostRep.findById(s.getVlasnikId()).orElse(null);
		tmp.setVlasnik(vlasnik);
		
		Collection<Pogodnost> svePogodnosti = pogRep.findAll();
		Collection<Pogodnost> setPogodnosti = new ArrayList<Pogodnost>();
		for(Pogodnost p: svePogodnosti) {
			for(PogodnostDTO tmpPgd : s.getPogodnosti()) {
				if(tmpPgd.getNaziv().equals(p.getNaziv())) {
					p.getListaSmestaja().add(tmp);
					setPogodnosti.add(p);
				}
			}
		}
		tmp.setPogodnosti(setPogodnosti);
//		tmp.setVlasnik(hostRep.findById(s.getVlasnikId()).orElse(null));
		this.smestajRep.saveAndFlush(tmp);
		return s;
	}

	public SmestajDTO editSmestaj(SmestajDTO smestaj, Long smestajId, Long vlasnikId) {
		// TODO Auto-generated method stub
		Smestaj s = smestajRep.findById(smestajId).orElse(null);
		//Da li smestaj postoji?
		if(s==null) {
			return null;
		}
		//Da li je vlasnikId vlasnik tog smestaja?
		if(s.getVlasnik().getId() != vlasnikId) {
			return null;
		}
		
		s = smestajMapper.editSmestaj(s, smestaj);
		smestajRep.saveAndFlush(s);
		
		//cisto da proverim maper u dbgeru
		return smestajMapper.toDTO(s);
	}

	public SmestajDTO removeSmestaj(Long smestajId, Long vlasnikId) {
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

	public SmestajDTO getByIdSmestaja(Long smestajId) {
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
}
