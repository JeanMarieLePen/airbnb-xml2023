package com.xml2023.smestajmicroservice.mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml2023.mainapp.HostBasicDTO;
import com.xml2023.mainapp.KorisnikGrpcGrpc;
import com.xml2023.mainapp.KorisnikGrpcGrpc.KorisnikGrpcBlockingStub;
import com.xml2023.mainapp.getHostRequest;
import com.xml2023.mainapp.getHostResponse;
import com.xml2023.smestajmicroservice.dtos.SmestajPretragaDTO;
import com.xml2023.smestajmicroservice.model.data.OcenaSmestaj;
import com.xml2023.smestajmicroservice.model.data.Smestaj;
import com.xml2023.smestajmicroservice.repositories.OcenaSmestajRep;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class SmestajPretragaMapper {
	@Autowired 	private AdresaMapper aMapper;
	@Autowired OcenaSmestajRep oRep;
	public SmestajPretragaDTO toDTO(Smestaj s, LocalDateTime pocetak, LocalDateTime kraj) {
		
		System.out.println("Pocetak konverzije entiteta: " + s.getId());
		SmestajPretragaDTO dto = new SmestajPretragaDTO();
		System.out.println("Objekat: " + s.toString());
		dto.setAdresa(aMapper.toDTO(s.getAdresa()));
		dto.setId(s.getId());
		dto.setMaxGosti(s.getMaxGosti());
		dto.setMinGosti(s.getMinGosti());
		dto.setHostId(s.getVlasnik());

		dto.setHostIstaknuti(getHost(s.getVlasnik()).getIstaknuti());
		if(s.getPogodnosti()!= null) {
			dto.setPogodnosti(s.getPogodnosti());
		}
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
		System.out.println("KONVERZIJA ENTITETA ID-a: " + s.getId() + ": USPESNA");
		return dto;
		
	} 
	
	public HostBasicDTO getHost(String hostId) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("reglog", 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub bs = KorisnikGrpcGrpc.newBlockingStub(channel);
		getHostRequest rqst = getHostRequest.newBuilder().setHostId(hostId).build();
		getHostResponse rspns = bs.getHost(rqst);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		return rspns.getHost();
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
		if(pocetak==null || kraj==null) {
			if(isWeekend2(LocalDateTime.now())) {
				return s.getCenovnik().getCenaVikend();
			}
			if(isInTheSummer(LocalDateTime.now())) {
				return s.getCenovnik().getCenaLeto();
			}
			return s.getCenovnik().getCena();
		}
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
	private static boolean isWeekend2(LocalDateTime localDate) {
        // get Day of week for the passed LocalDate
        return (localDate.get(ChronoField.DAY_OF_WEEK) == 6) 
                || (localDate.get(ChronoField.DAY_OF_WEEK) == 7);
	}	
	private static boolean isInTheSummer(LocalDateTime localDate) {
        Month month = localDate.getMonth();
        if (month == Month.JUNE || month == Month.JULY || month == Month.AUGUST) {
            return true;
        }else {
        	return false;
        }
	}
}
