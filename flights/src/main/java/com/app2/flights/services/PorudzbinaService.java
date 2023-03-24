package com.app2.flights.services;

import java.time.Duration;
import java.time.LocalDateTime;

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
import com.app2.flights.repositories.RegKorRep;

@Service
public class PorudzbinaService {
	
	@Autowired KorisnikRep kRep;
	@Autowired
	private LetRep letRep;
	@Autowired 
	private RegKorRep regKorRep;
	@Autowired
	private PorudzbinaRep porRep;
	@Autowired PorudzbinaMapper porMapper;
	
	public PorudzbinaDTO reservation(PorudzbinaDTO p) {
		// TODO Auto-generated method stub
		Let l = letRep.findById(p.getLet()).orElse(null);
		RegKor k = regKorRep.findById(p.getKupac()).orElse(null);
		if(l == null || k == null) {
			return null;
		}else {
			if(l.getKapacitet() - l.getListaPutnika().size() > 0) {
				Porudzbina nova = new Porudzbina();
				nova.setBrojKarata(p.getBrojKarata());
				nova.setKupac(k);
				nova.setLet(l);
				nova.setUkupnaCena(p.getUkupnaCena());
				porRep.save(nova);
				
				l.getListaPutnika().add(k);
				letRep.save(l);
				
				k.getPorudzbine().add(nova);
				regKorRep.save(k);
				
				return porMapper.toDTO(nova);
			}else {
				return null;
			}
			
		}
	}
	
	public PorudzbinaDTO cancelReservation(String idK, String idP) {
		RegKor rk = regKorRep.findById(idK).orElse(null);
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
				Duration duration = Duration.between(now, p.getLet().getDatumIVreme());
				long hours = duration.toHours();
				System.out.println("HOURS UNTIL FLIGHT :"+hours);
				if(hours > 24) {
					p.setStatus(StatusPorudzbine.OTKAZANA);
					porRep.save(p);
					return porMapper.toDTO(p);
				}
				return null;
			}
		}
	}

	public PorudzbinaDTO novaRez(PorudzbinaDTOnova p) {
		Let l = letRep.findById(p.getLet()).orElse(null);		
		RegKor k = regKorRep.findById(p.getKupac()).orElse(null);
		if(l == null || k == null) {
			return null;
		}else {
			if(l.getListaPutnika().size() +p.getBrojKarata()<=l.getKapacitet()) {
				System.out.println("NOVA PORUDZBINA");
				Porudzbina nova = new Porudzbina();
				nova.setBrojKarata(p.getBrojKarata());
				nova.setKupac(k);
				nova.setLet(l);
				nova.setUkupnaCena(p.getBrojKarata()*l.getCena());
				nova.setStatus(StatusPorudzbine.REZERVISANA);
				porRep.save(nova);
				
				l.getListaPutnika().add(k);
				letRep.save(l);
				
				k.getPorudzbine().add(nova);
				regKorRep.save(k);
				
				return porMapper.toDTO(nova);
			}else {
				return null;
			}
		}
	}

}
