package com.app2.flights.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app2.flights.dtos.PorudzbinaDTO;
import com.app2.flights.dtos.PorudzbinaDTOnova;
import com.app2.flights.mappers.PorudzbinaMapper;
import com.app2.flights.model.data.Let;
import com.app2.flights.model.data.Porudzbina;
import com.app2.flights.model.data.StatusPorudzbine;
import com.app2.flights.model.user.Korisnik;
import com.app2.flights.model.user.RegKor;
import com.app2.flights.repositories.KorisnikRep;
import com.app2.flights.repositories.LetRep;
import com.app2.flights.repositories.PorudzbinaRep;

@Service
public class PorudzbinaService {
	
	@Autowired 
	private KorisnikRep kRep;
	@Autowired
	private LetRep letRep;
	@Autowired
	private PorudzbinaRep porRep;
	@Autowired 
	private PorudzbinaMapper porMapper;
	
	public PorudzbinaDTO reservation(PorudzbinaDTO p) {
		// TODO Auto-generated method stub
		Let l = letRep.findById(p.getLet()).orElse(null);
		RegKor k = (RegKor) kRep.findById(p.getKupac()).orElse(null);
		if(l == null || k == null) {
			return null;
		}else {
			if(l.getKapacitet() - brZauzetihMesta(l) > 0) {
				Porudzbina nova = new Porudzbina();
				nova.setBrojKarata(p.getBrojKarata());
				nova.setKupac(k.getId());
				nova.setLet(l.getId());
				nova.setUkupnaCena(p.getUkupnaCena());
				
				l.getListaPorudzbina().add(nova.getId());
				letRep.save(l);
				
				k.getPorudzbine().add(nova.getId());
				kRep.save(k);
				return porMapper.toDTO(nova);
			}else {
				return null;
			}
			
		}
	}
	
	public int brZauzetihMesta(Let l) {
		int retVal = 0;
		List<Porudzbina> lista = new ArrayList<Porudzbina>();
		retVal = porRep.findAll().stream().filter(p -> p.getLet().equals(l.getId())).map(x -> x.getBrojKarata()).collect(Collectors.summingInt(Integer::intValue));
		return retVal;
	}
	
	public PorudzbinaDTO cancelReservation(String idK, String idP) {
		RegKor rk = (RegKor) kRep.findById(idK).orElse(null);
		Porudzbina p = porRep.findById(idP).orElse(null);
		if(rk == null || p == null) {
			System.out.println("RESERVATION NOT FOUND");
			return null;
		}else {
			if(porRep.findById(idP) == null) {
				return null;
			}else {
				System.out.println("RESERVATION OTKAZIVANJE...");

				LocalDateTime now = LocalDateTime.now();
				Let tmp = letRep.findById(p.getLet()).orElse(null);
				if(tmp != null) {
					Duration duration = Duration.between(now, tmp.getDatumIVreme());
					long hours = duration.toHours();
					System.out.println("HOURS UNTIL FLIGHT :"+hours);
					if(hours > 24) {
						p.setStatus(StatusPorudzbine.OTKAZANA);
						porRep.save(p);
						return porMapper.toDTO(p);
					}
				}else {
					return null;
				}
				
				return null;
			}
		}
	}

	public PorudzbinaDTO novaRez(PorudzbinaDTOnova p) {
		Let l = letRep.findById(p.getLet()).orElse(null);		
		RegKor k = (RegKor) kRep.findById(p.getKupac()).orElse(null);
		if(l == null || k == null) {
			return null;
		}else {
			if(brZauzetihMesta(l) + p.getBrojKarata() <= l.getKapacitet()) {
				System.out.println("NOVA PORUDZBINA");
				Porudzbina nova = new Porudzbina();
				nova.setBrojKarata(p.getBrojKarata());
				nova.setKupac(k.getId());
				nova.setLet(l.getId());
				nova.setUkupnaCena(p.getBrojKarata() * l.getCena());
				nova.setStatus(StatusPorudzbine.REZERVISANA);
				porRep.save(nova);
				
				l.getListaPorudzbina().add(nova.getId());
				letRep.save(l);
				
				k.getPorudzbine().add(nova.getId());
				kRep.save(k);
				
				return porMapper.toDTO(nova);
			}else {
				return null;
			}
		}
	}

}
