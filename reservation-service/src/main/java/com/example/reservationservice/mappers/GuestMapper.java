package com.example.reservationservice.mappers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.reservationservice.dtos.GuestDTO;
import com.example.reservationservice.dtos.OcenaHostDTO;
import com.example.reservationservice.dtos.OcenaSmestajaDTO;
import com.example.reservationservice.dtos.RezervacijaDTO;
import com.example.reservationservice.model.Guest;
import com.example.reservationservice.model.OcenaHost;
import com.example.reservationservice.model.OcenaSmestaj;
import com.example.reservationservice.model.Rezervacija;


@Component
public class GuestMapper {

	@Autowired
	private AdresaMapper aMapper;
	@Autowired
	private OcenaSmestajMapper osMapper;
	@Autowired
	private OcenaHostMapper ohMapper;
	@Autowired
	private RezervacijaMapper rMapper;
	
	public Guest fromDTO(GuestDTO dto) {
		Guest g = new Guest();
		g.setAdresa(aMapper.fromDTO(dto.getAdresa()));
		g.setEmail(dto.getEmail());
		g.setIme(dto.getIme());
		g.setPrezime(dto.getPrezime());
		g.setKorIme(dto.getKorIme());
		g.setId(dto.getId());
		g.setLozinka(dto.getLozinka());
		g.setTipKorisnika(dto.getTipKorisnika());
		Collection<byte[]> tempSlike = new ArrayList<byte[]>();
		for(String s: dto.getSlike()) {
			byte[] data = Base64.getDecoder().decode(s.split(",")[1]);
			tempSlike.add(data);
		}
		g.setSlike(tempSlike);
		g.setBrojOtkazivanja(dto.getBrojOtkazivanja());
		Collection<OcenaSmestaj> tempOcene = new ArrayList<OcenaSmestaj>();
		for(OcenaSmestajaDTO odto : dto.getOceneSmestaja()) {
			OcenaSmestaj tmp = osMapper.fromDTO(odto);
			tempOcene.add(tmp);
		}
		g.setOceneSmestaja(tempOcene);
		
		return g;
	}
	
	public GuestDTO toDTO(Guest g) {
		GuestDTO dto = new GuestDTO();
		dto.setAdresa(aMapper.toDTO(g.getAdresa()));
		dto.setBrojOtkazivanja(g.getBrojOtkazivanja());
		dto.setEmail(g.getEmail());
		dto.setId(g.getId());
		dto.setIme(g.getIme());
		dto.setPrezime(g.getPrezime());
		dto.setKorIme(g.getKorIme());
		dto.setTipKorisnika(g.getTipKorisnika());
		dto.setStatusNaloga(g.getStatusNaloga());
		
		//ocene smestaja, kopirati ako zatreba negde
		Collection<OcenaSmestajaDTO> tempOceneSmestaja = new ArrayList<OcenaSmestajaDTO>();
		if(g.getOceneSmestaja()!=null) {
			for(OcenaSmestaj o: g.getOceneSmestaja()) {
				tempOceneSmestaja.add(osMapper.toDTO(o));
			}
		}
		dto.setOceneSmestaja(tempOceneSmestaja);
		
		//ocene hosta, kopirati ako zatreba negde
		Collection<OcenaHostDTO> tempOceneHost = new ArrayList<OcenaHostDTO>();
		if(g.getOceneVlasnika()!=null) {
			for(OcenaHost o: g.getOceneVlasnika()) {
				tempOceneHost.add(ohMapper.toDTO(o));
			}
		}
		dto.setOceneVlasnika(tempOceneHost);
		
		//rezervacije, kopirati ako zatreba negde
		Collection<RezervacijaDTO> tempRezervacije = new ArrayList<RezervacijaDTO>();
		if(g.getRezervacije()!=null) {
			for(Rezervacija r:g.getRezervacije()) {
				tempRezervacije.add(rMapper.toDTO(r));
			}
		}
		dto.setRezervacije(tempRezervacije);
		
		Collection<String> tempSlike = new ArrayList<String>();
		for(byte[] s : g.getSlike()) {
			String tempSlika = Base64.getEncoder().encodeToString(s);
			tempSlike.add(tempSlika);
		}
		dto.setSlike(tempSlike);
		
		return dto;
	}
}
