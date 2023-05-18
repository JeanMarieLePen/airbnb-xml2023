package com.example.reservationservice.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.xml2023.mainapp.SmestajDTO;


@Service
public class CenaService {

	public float ukupnaCena(SmestajDTO s, LocalDateTime pocetak, LocalDateTime kraj) {
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
