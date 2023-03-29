package com.app2.flights.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app2.flights.dtos.LetDTO;
import com.app2.flights.dtos.LetDTOSimple;
import com.app2.flights.mappers.AdresaMapper;
import com.app2.flights.mappers.LetMapper;
import com.app2.flights.model.data.Adresa;
import com.app2.flights.model.data.Let;
import com.app2.flights.model.data.Porudzbina;
import com.app2.flights.model.data.StatusPorudzbine;
import com.app2.flights.repositories.LetRep;
import com.app2.flights.repositories.PorudzbinaRep;
import com.app2.flights.repositories.RegKorRep;

@Service
public class LetService {

	@Autowired
	private LetRep letRep;
	@Autowired 
	private LetMapper letMapper;
	@Autowired 
	private AdresaMapper adresaMapper;
	@Autowired PorudzbinaRep pRep;
	
	public LetDTO addNew(LetDTO letDTO) {
		// TODO Auto-generated method stub
		//this.letRep.save(letMapper.fromDTO(letDTO));
		this.letRep.save(new Let(letDTO));
//		Collection<Let> letovi = letRep.findAll();
//		System.out.println("BROJ LETOVA U BAZI: " + letovi.size());
		return letDTO;
	}

	public LetDTOSimple getLetById(String id) {
		// TODO Auto-generated method stub
		Let l = letRep.findById(id).orElse(null);
		if(l==null) {
			return null;
		}else {
			return letMapper.toDTOSimple(l);
		}
	}

	public LetDTO editLet(LetDTO ldto) {
		// TODO Auto-generated method stub
		Let l = letRep.findById(ldto.getId()).orElse(null);
		if(l == null) {
			return null;
		}else {
			l.setBrojLeta(ldto.getBrojLeta());
			l.setCena(ldto.getCena());
			l.setDatumIVreme(ldto.getDatumIVreme());
			l.setKapacitet(ldto.getKapacitet());
//			l.setListaPutnika(ldto.getListaPutnika());
			
			
			//Zakomentarisane dve linije jer sam menjao dto da bude string
			//l.setLokDo(adresaMapper.fromDTO(ldto.getLokDo()));
			//l.setLokOd(adresaMapper.fromDTO(ldto.getLokOd()));
			
			l.setLokOd(new Adresa(ldto.getLokOd()));
			l.setLokDo(new Adresa(ldto.getLokDo()));
			
		//	l.setLokDoAddress(adresaMapper.fromDTO(ldto.getLokDo()));
			
			
			letRep.save(l);
			return letMapper.toDTO(l);
		}
	}
	
	public LetDTO removeLet(String id) {
		Let l = letRep.findById(id).orElse(null);
		if(l == null) {
			return null;
		}else {
			LocalDateTime now = LocalDateTime.now();
			Duration duration = Duration.between(now, l.getDatumIVreme());
			long days = duration.toDays();
			if(l.getListaPutnika().size() == 0 && days > 3) {
				letRep.deleteById(id);
				return letMapper.toDTO(l);
			}else {
				return null;
			}
		}
	}
	
	public List<LetDTOSimple> findLetovi(String lokOd,String lokDo,LocalDateTime datumIVreme,int brojPutnika)
	{	
		//List<Let> letovi = letRep.findBylokOdIdAndlokDoIdAnddatumIVremeGreaterThanEqualAndkapacitetGreaterThanEqual(lokOd, lokDo, datumIVreme, brojPutnika);
		
		List<Let> letovi =new ArrayList<Let>();
		return letovi.stream()
				.map(letMapper::toDTOSimple)
				.collect(Collectors.toList());
	}
	
	public int brojSlobodnihMesta(String id) {
		
		Let l= letRep.findById(id).orElse(null);
		if (l==null) return -1;
		int kapacitet=l.getKapacitet();
		int slobMesta=0;
		int zauzeto=0;

		List<Porudzbina> allRez=pRep.findAllByLet(id);
		for(Porudzbina por : allRez) {
			if(por.getStatus().equals(StatusPorudzbine.REZERVISANA))
				zauzeto+=por.getBrojKarata();
			}
		
		slobMesta=kapacitet-zauzeto;
		System.out.println("Kap - Zauz = brSlob  : "+kapacitet+" - "+zauzeto+" = "+slobMesta);
		return slobMesta;
	}
		
}
