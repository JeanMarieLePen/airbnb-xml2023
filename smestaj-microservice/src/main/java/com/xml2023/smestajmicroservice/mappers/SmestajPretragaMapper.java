package com.xml2023.smestajmicroservice.mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml2023.smestajmicroservice.dtos.SmestajPretragaDTO;
import com.xml2023.smestajmicroservice.model.data.OcenaSmestaj;
import com.xml2023.smestajmicroservice.model.data.Smestaj;
import com.xml2023.smestajmicroservice.repositories.OcenaSmestajRep;

@Component
public class SmestajPretragaMapper {
	@Autowired 	private AdresaMapper aMapper;
	@Autowired OcenaSmestajRep oRep;
	public SmestajPretragaDTO toDTO(Smestaj s, LocalDateTime pocetak, LocalDateTime kraj) {
		
		SmestajPretragaDTO dto = new SmestajPretragaDTO();
		
		dto.setAdresa(aMapper.toDTO(s.getAdresa()));
		dto.setId(s.getId());
		dto.setMaxGosti(s.getMaxGosti());
		dto.setMinGosti(s.getMinGosti());
		dto.setHostId(s.getVlasnik());

		Collection<String> tempSlike = new ArrayList<String>();
		if(s.getSlike() != null) {
			for(byte[] tmp : s.getSlike()) {
				String tempSlika = Base64.getEncoder().encodeToString(tmp);
				tempSlike.add(tempSlika);
			}
		}		dto.setSlike(tempSlike);
		dto.setProsecnaOcena(getProsecnaOcena(s.getId()));
		dto.setUkCena(ukupnaCena(s, pocetak, kraj));
		dto.setPoSmestaju(s.getCenovnik().isPoSmestaju());
		return dto;
		
	} 
	
	public float getProsecnaOcena(String id) {
		Collection<OcenaSmestaj> ocene= oRep.findAllBySmestaj(id);;
		float uk=0;
		for(OcenaSmestaj o : ocene) {
			uk+=o.getOcena();
		}
		return uk/ocene.size();	
	}
	//https://www.baeldung.com/java-between-dates
	//TODO provera praznika, leta i neradnih dana
	public float ukupnaCena(Smestaj s, LocalDateTime pocetak, LocalDateTime kraj) {
		if(pocetak==null || kraj==null) return 0;
		float uk=0;
		LocalDate pocDate=pocetak.toLocalDate();
		LocalDate krajDate=kraj.toLocalDate();
		
	    List<LocalDate> dani= pocDate.datesUntil(krajDate).collect(Collectors.toList());
	    dani.add(krajDate);
		System.out.println("Pocetni : kranji : broj dana = "+pocetak.toLocalDate()+" : "+" : "+kraj.toLocalDate()+" : "+dani.size());
		for(LocalDate dan : dani) {
			if(isWeekend(dan)) {
				uk+= s.getCenovnik().getCenaVikend();
			}else {
				uk+=s.getCenovnik().getCena();
			}
		}
		return uk;
	}
	private static boolean isWeekend(LocalDate localDate) {
        // get Day of week for the passed LocalDate
        return (localDate.get(ChronoField.DAY_OF_WEEK) == 6) 
                || (localDate.get(ChronoField.DAY_OF_WEEK) == 7);
	}	
}
