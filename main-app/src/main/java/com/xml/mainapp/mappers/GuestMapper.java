package com.xml.mainapp.mappers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml.mainapp.dtos.data.OcenaSmestajaDTO;
import com.xml.mainapp.dtos.user.GuestDTO;
import com.xml.mainapp.model.data.OcenaSmestaj;
import com.xml.mainapp.model.users.Guest;

@Component
public class GuestMapper {

	@Autowired
	private AdresaMapper aMapper;
	@Autowired
	private OcenaSmestajMapper osMapper;
	
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
		g.setObradjenaRezervacijaNotifikacija(dto.isObradjenaRezervacijaNotifikacija());
		//g.setOceneSmestaja(tempOcene);
		
		return g;
	}
	
	public GuestDTO toDTO(Guest g, Collection<com.xml2023.mainapp.RezervacijaDTO> allRez) {
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
		
		Collection<String> tempSlike = new ArrayList<String>();
		for(byte[] s : g.getSlike()) {
			String tempSlika = Base64.getEncoder().encodeToString(s);
			tempSlike.add(tempSlika);
		}
		dto.setSlike(tempSlike);
		
		dto.setObradjenaRezervacijaNotifikacija(g.isObradjenaRezervacijaNotifikacija());
		return dto;
	}
}
