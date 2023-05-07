package com.xml.mainapp.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.mainapp.dtos.data.RezervacijaDTO;
import com.xml.mainapp.dtos.user.HostDTO;
import com.xml.mainapp.mappers.HostMapper;
import com.xml.mainapp.mappers.RezervacijaMapper;
import com.xml.mainapp.model.data.Rezervacija;
import com.xml.mainapp.model.data.Smestaj;
import com.xml.mainapp.model.data.StatusRezervacije;
import com.xml.mainapp.model.users.Host;
import com.xml.mainapp.repositories.HostRepository;
import com.xml.mainapp.repositories.RezervacijaRep;

@Service
public class HostService {

	@Autowired
	private HostRepository hostRep;
	@Autowired
	private HostMapper hostMapper;
	@Autowired
	private RezervacijaMapper rezMap;
	@Autowired
	private RezervacijaRep rezRep;
	
	public HostDTO findHostById(Long id) {
		// TODO Auto-generated method stub
		Host h = this.hostRep.findById(id).orElse(null);
		if(h == null) {
			return null;
		}
		HostDTO retVal = this.hostMapper.toDTO(h);
		return retVal;
	}

	public Collection<RezervacijaDTO> getAllReservationsByHostId(Long id) {
		// TODO Auto-generated method stub
		Host h = hostRep.findById(id).orElse(null);
		if(h == null)
			return null;
		Collection<RezervacijaDTO> retList = new ArrayList<RezervacijaDTO>();
		if(h.getSmestajList()!= null) {
			for(Smestaj s: h.getSmestajList()) {
				if(s.getRezervacije() != null) {
					for(Rezervacija r : s.getRezervacije()) {
						retList.add(rezMap.toDTO(r));
					}
				}
			}
		}
		return retList;
	}
	
	public RezervacijaDTO approveReservation(Long reservationId, Long ownerId) {
		Rezervacija r = this.rezRep.findById(reservationId).orElse(null);
		if(r == null) {
			return null;
		}
		Host h = this.hostRep.findById(ownerId).orElse(null);
		if(h == null) {
			return null;
		}
		if(r.getSmestaj().getVlasnik().getId() == ownerId) {
			r.setStatus(StatusRezervacije.REZERVISANA);
		}
		rezRep.save(r);
		return rezMap.toDTO(r);
	}
	
	public RezervacijaDTO rejectReservation(Long reservationId, Long ownerId) {
		Rezervacija r = this.rezRep.findById(reservationId).orElse(null);
		if(r == null) {
			return null;
		}
		Host h = this.hostRep.findById(ownerId).orElse(null);
		if(h == null) {
			return null;
		}
		if(r.getSmestaj().getVlasnik().getId() == ownerId) {
			r.setStatus(StatusRezervacije.ODBIJENA);
		}
		rezRep.save(r);
		return rezMap.toDTO(r);
	}
	
	
}
