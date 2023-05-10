package com.example.reservationservice.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reservationservice.dtos.RezervacijaDTO;
import com.example.reservationservice.mappers.HostMapper;
import com.example.reservationservice.mappers.RezervacijaMapper;
import com.example.reservationservice.model.Guest;
import com.example.reservationservice.model.Host;
import com.example.reservationservice.model.Korisnik;
import com.example.reservationservice.model.Rezervacija;
import com.example.reservationservice.model.Smestaj;
import com.example.reservationservice.model.StatusRezervacije;
import com.example.reservationservice.model.Termin;
import com.example.reservationservice.repositories.GuestRepository;
import com.example.reservationservice.repositories.HostRepository;
import com.example.reservationservice.repositories.KorisnikRep;
import com.example.reservationservice.repositories.RezervacijaRep;
import com.example.reservationservice.repositories.SmestajRep;
import com.example.reservationservice.repositories.TerminRepository;

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
	private HostRepository hostRep;
	
	@Autowired 
	private HostMapper hostMapper;
	@Autowired
	private GuestRepository guestRep;
	@Autowired
	private TerminRepository terminRep;
	
	public RezervacijaDTO makeReservation(String userId, String smestajId, RezervacijaDTO r) {
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
		terminRep.save(t);
		
		s.getNedostupni().add(t);
		smestajRep.save(s);
		
		Rezervacija rez = rezMapper.fromDTO(r);
		rez.setGost(guestRep.findById(userId).orElse(null));
		rez.setSmestaj(s);
		//ZAVISNO OD TOGA DA LI JE VLASNIK SMESTAJA ODABRAO DA SE REZERVACIJE MODERIRAJU AUTOMATSKI ILI RUCNO
		if(s.getVlasnik().isRezAutomatski()) {
			rez.setStatus(StatusRezervacije.REZERVISANA);
		}else {
			rez.setStatus(StatusRezervacije.PENDING);
		}
		rezervacijaRep.save(rez);
		//dodavanje u listu rezervacija
		s.getRezervacije().add(rez);
		//cuvanje smestaja
		smestajRep.save(s);
		Guest g = guestRep.findById(userId).orElse(null);
		if(g != null) {
			g.getRezervacije().add(rez);
			guestRep.save(g);
		}
		//cuvanje rezervacije
		//rezervacijaRep.saveAndFlush(rez);
		return rezMapper.toDTO(rez);
	}
	
	public RezervacijaDTO cancelReservation(String userId, String rezervacijaId) {
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
		smestajRep.save(s);
		r.setStatus(StatusRezervacije.OTKAZANA);
		g.setBrojOtkazivanja(g.getBrojOtkazivanja() + 1);
		rezervacijaRep.save(r);
		guestRep.save(g);
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
		rezervacijaRep.save(r);
		return rezMapper.toDTO(r);
	}
	
	public Collection<RezervacijaDTO> getAllReservationsByHostId(String id) {
		// TODO Auto-generated method stub
		Host h = hostRep.findById(id).orElse(null);
		if(h == null)
			return null;
		Collection<RezervacijaDTO> retList = new ArrayList<RezervacijaDTO>();
		if(h.getSmestajList()!= null) {
			for(Smestaj s: h.getSmestajList()) {
				if(s.getRezervacije() != null) {
					for(Rezervacija r : s.getRezervacije()) {
						retList.add(rezMapper.toDTO(r));
					}
				}
			}
		}
		return retList;
	}
	
	public RezervacijaDTO approveReservation(String reservationId, String ownerId) {
		Rezervacija r = this.rezervacijaRep.findById(reservationId).orElse(null);
		if(r == null) {
			return null;
		}
		Host h = this.hostRep.findById(ownerId).orElse(null);
		if(h == null) {
			return null;
		}
		if(r.getSmestaj().getVlasnik().getId().equals(ownerId)) {
			r.setStatus(StatusRezervacije.REZERVISANA);
		}
		rezervacijaRep.save(r);
		return rezMapper.toDTO(r);
	}
	
	public RezervacijaDTO rejectReservation(String reservationId, String ownerId) {
		Rezervacija r = this.rezervacijaRep.findById(reservationId).orElse(null);
		if(r == null) {
			return null;
		}
		Host h = this.hostRep.findById(ownerId).orElse(null);
		if(h == null) {
			return null;
		}
		if(r.getSmestaj().getVlasnik().getId().equals(ownerId)) {
			r.setStatus(StatusRezervacije.ODBIJENA);
		}
		rezervacijaRep.save(r);
		return rezMapper.toDTO(r);
	}

	public Collection<RezervacijaDTO> getAllReservationByUserGuestId(String id) {
		// TODO Auto-generated method stub
		Guest g = guestRep.findById(id).orElse(null);
		if(g == null)
			return null;
		Collection<RezervacijaDTO> retList = new ArrayList<RezervacijaDTO>();
		if(g.getRezervacije() != null) {
			for(Rezervacija r : g.getRezervacije()) {
				retList.add(rezMapper.toDTO(r));
			}
		}
		return retList;
	}

}
