package com.xml.mainapp.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.mainapp.dtos.data.RezervacijaDTO;
import com.xml.mainapp.mappers.RezervacijaMapper;
import com.xml.mainapp.model.data.Rezervacija;
import com.xml.mainapp.model.data.Smestaj;
import com.xml.mainapp.model.data.StatusRezervacije;
import com.xml.mainapp.model.data.Termin;
import com.xml.mainapp.model.users.Guest;
import com.xml.mainapp.model.users.Host;
import com.xml.mainapp.model.users.Korisnik;
import com.xml.mainapp.repositories.GuestRepository;
import com.xml.mainapp.repositories.KorisnikRep;
import com.xml.mainapp.repositories.RezervacijaRep;
import com.xml.mainapp.repositories.SmestajRep;

@Service
public class RezervacijaService {
	
	@Autowired
	private KorisnikRep korRep;
	
	@Autowired
	private RezervacijaRep rezervacijaRep;

	@Autowired 
	private RezervacijaMapper rezMapper;
	@Autowired
	private SmestajRep smestajRep;
	
	@Autowired
	private GuestRepository guestRep;
	
	public RezervacijaDTO makeReservation(Long userId, Long smestajId, RezervacijaDTO r) {
		// TODO Auto-generated method stub
		Korisnik k = korRep.findById(userId).orElse(null);
		if(k==null) {
			return null;
		}
		Smestaj s = smestajRep.findById(smestajId).orElse(null);
		if(s==null) {
			return null;
		}
		Termin t = new Termin(r.getOdDatum(), r.getDoDatum());
		t.setSmestaj(s);
		s.getNedostupni().add(t);
		
		Rezervacija rez = rezMapper.fromDTO(r);
		rez.setGost(guestRep.findById(userId).orElse(null));
		rez.setSmestaj(s);
		//ZAVISNO OD TOGA DA LI JE VLASNIK SMESTAJA ODABRAO DA SE REZERVACIJE MODERIRAJU AUTOMATSKI ILI RUCNO
		if(s.getVlasnik().isRezAutomatski()) {
			rez.setStatus(StatusRezervacije.REZERVISANA);
		}else {
			rez.setStatus(StatusRezervacije.PENDING);
		}
		//dodavanje u listu rezervacija
		s.getRezervacije().add(rez);
		//cuvanje smestaja
		smestajRep.saveAndFlush(s);
		
		//cuvanje rezervacije
		//rezervacijaRep.saveAndFlush(rez);
		return rezMapper.toDTO(rez);
	}
	
	public RezervacijaDTO cancelReservation(Long userId, Long rezervacijaId) {
		Guest g = guestRep.findById(userId).orElse(null);
		Rezervacija r = rezervacijaRep.findById(rezervacijaId).orElse(null);
		if(r==null) {
			return null;
		}
		LocalDateTime temp = LocalDateTime.now();
		Duration duration = Duration.between(temp, r.getOdDatum());
		long days = duration.toDays();
		if(days <= 1) {
			return null;
		}
		
		Smestaj s = r.getSmestaj();
		for(Termin t:s.getNedostupni()) {
			//potrebno osloboditi datum otkazane rezervacije
			if(t.getPocetak().isEqual(r.getOdDatum())) {
				s.getNedostupni().remove(t);
				break;
			}
		}
		
		r.setStatus(StatusRezervacije.OTKAZANA);
		g.setBrojOtkazivanja(g.getBrojOtkazivanja() + 1);
		rezervacijaRep.saveAndFlush(r);
		guestRep.saveAndFlush(g);
		return rezMapper.toDTO(r);
	}
	
	public RezervacijaDTO editReservation(RezervacijaDTO edited) {
		Rezervacija r = rezervacijaRep.findById(edited.getId()).orElse(null);
		if(r==null) {
			return null;
		}
		r.setBrojGostiju(edited.getBrojGostiju());
		r.setDoDatum(edited.getDoDatum());
		r.setOdDatum(edited.getOdDatum());
		if(r.getSmestaj().getVlasnik().isRezAutomatski()) {
			r.setStatus(StatusRezervacije.REZERVISANA);
		}else {
			r.setStatus(StatusRezervacije.PENDING);
		}
		rezervacijaRep.saveAndFlush(r);
		return rezMapper.toDTO(r);
	}

}
