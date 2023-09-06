package com.xml2023.smestajmicroservice.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.Timestamp;
import com.xml2023.mainapp.ActiveReservationsRequest;
import com.xml2023.mainapp.ActiveReservationsResponse;
import com.xml2023.mainapp.RezervacijaDTO;
import com.xml2023.mainapp.RezervacijaGrpcGrpc;
import com.xml2023.mainapp.RezervacijaGrpcGrpc.RezervacijaGrpcBlockingStub;
import com.xml2023.smestajmicroservice.MetrikeMetode;
import com.xml2023.smestajmicroservice.dtos.PretragaDTO;
import com.xml2023.smestajmicroservice.dtos.SmestajPretragaDTO;
import com.xml2023.smestajmicroservice.mappers.SmestajPretragaMapper;
import com.xml2023.smestajmicroservice.model.data.OcenaSmestaj;
import com.xml2023.smestajmicroservice.model.data.Smestaj;
import com.xml2023.smestajmicroservice.model.data.Termin;
import com.xml2023.smestajmicroservice.repositories.OcenaSmestajRep;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class PretragaService {
	@Autowired 
	private MongoTemplate monTempl;
	@Autowired 
	private SmestajPretragaMapper smMap;
	@Autowired OcenaSmestajRep oRep;
	@Autowired MetrikeMetode met;
	private String rezService="reservation";
	
	public Collection<SmestajPretragaDTO> pretraga(PretragaDTO dto){
		Query query = new Query();
		if(dto.getAdresa()!=null && !dto.getAdresa().getAdresa().trim().equals("")) {
			//Criteria adresa= Criteria.where("adresa.adresa").regex(dto.getAdresa().getAdresa().toLowerCase());
			//query.addCriteria(adresa);
		}
		if(dto.getBrGosti()>0) {
			Criteria minGosti= Criteria.where("minGosti").lte(dto.getBrGosti());
			Criteria maxGosti= Criteria.where("maxGosti").gte(dto.getBrGosti());
			query.addCriteria(minGosti);
			query.addCriteria(maxGosti);
		}
		//query
		List<Smestaj> pronadjeni=monTempl.find(query, Smestaj.class);
		System.out.println("Pronadjenih u bazi :"+pronadjeni.size());
		
		System.out.println("FILTERI ZAKOMENTARISANI");
		//filter datumi rez i nedostupni datumi
		if(dto.getPocetak()!=null && dto.getKraj()!=null) {
			pronadjeni=pronadjeni.stream().filter(x-> isSlobodan(x, dto.getPocetak(), dto.getKraj())).collect(Collectors.toList());
		}
		
//		List<SmestajPretragaDTO> dtos = pronadjeni.stream().map(x-> smMap.toDTO(x, dto.getPocetak(), dto.getKraj())).collect(Collectors.toList());
		
		List<SmestajPretragaDTO> dtos = new ArrayList<>();
		System.out.println("POCETAK KONVERZIJE");
		for (Smestaj obj : pronadjeni) {
			System.out.println("ITERACIJA KONVERZIJE");
			dtos.add(smMap.toDTO(obj, dto.getPocetak(), dto.getKraj()));
		}
		
		//filter za cene
		if(dto.getMinCena()>-1) {
			dtos=dtos.stream().filter(x-> x.getUkCena()>=dto.getMinCena()).collect(Collectors.toList());
		}
		if(dto.getMaxCena()>-1) {
			dtos=dtos.stream().filter(x-> x.getUkCena()<=dto.getMaxCena()).collect(Collectors.toList());
		}
		//filter adrese
		if(dto.getAdresa()!=null && !dto.getAdresa().getAdresa().trim().equals("")) {
			String trazenaAdresa=dto.getAdresa().getAdresa().toLowerCase().trim();
			dtos=dtos.stream().filter(x->x.getAdresa().getAdresa().toLowerCase().contains(trazenaAdresa)).collect(Collectors.toList());
		}
		System.out.println("DTOS lista[broj elemenata]:  " + dtos.size());
		if(dtos.isEmpty()) {
			System.out.println("LISTA PRAZNA");
			return new ArrayList<SmestajPretragaDTO>();
		}
			
		else {
			System.out.println("LISTA NIJE PRAZNA");
			return dtos;
		}
	}
	
	public Timestamp convertToTimeStamp(LocalDateTime ldt) {
		return Timestamp.newBuilder().setSeconds(ldt.toEpochSecond(ZoneOffset.UTC))
				.setNanos(ldt.getNano()).build();
	}
	public LocalDateTime convertFromTimeStamp(Timestamp t) {
		return Instant.ofEpochSecond(t.getSeconds(),t.getNanos()).atZone(ZoneId.of("Europe/Belgrade")).toLocalDateTime();
	}
	//provera dostupnosti preko rezervacija i nedosupnih termina
	public boolean isSlobodan(Smestaj s, LocalDateTime pocetak, LocalDateTime kraj) {
		
		System.out.println("POCETAK GRPC KOMUNIKACIJE IZMEDJU SMESTAJ I RESERVATION");
//		List<Rezervacija> rezervacijeAktIPreklop= s.getRezervacije().stream().filter(x->x.getStatus().equals(StatusRezervacije.REZERVISANA) && 
//				preklop(x.getOdDatum(),x.getDoDatum(),pocetak,kraj)).collect(Collectors.toList());
		ManagedChannel channel = ManagedChannelBuilder.forAddress("reservation", 7978).usePlaintext().build();
		RezervacijaGrpcBlockingStub rezServBlockStub = RezervacijaGrpcGrpc.newBlockingStub(channel);
		ActiveReservationsRequest.Builder req = ActiveReservationsRequest.newBuilder();
		req=req.setSmestajId(s.getId()).setPocetak(convertToTimeStamp(pocetak)).setKraj(convertToTimeStamp(kraj));
		int bSize=req.build().getSerializedSize();
		try {
			met.grpcRequestExport(bSize, "pretraga");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ActiveReservationsResponse rezervacijaAkt = rezServBlockStub.getActiveReservations(req.build());
		
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
		bSize=rezervacijaAkt.getSerializedSize();
		try {
			met.grpcResponseExport(bSize, "pretraga");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<RezervacijaDTO> rezervacijeAktIPreklop = rezervacijaAkt.getListaRezervacijaList().stream().filter(x -> 
			this.preklop(convertFromTimeStamp(x.getOdDatum()), convertFromTimeStamp(x.getDoDatum()), pocetak, kraj)).collect(Collectors.toList());
		
		
		List<Termin> preklopSaNedostupnima= s.getNedostupni().stream().filter(x->preklop(x.getPocetak(),x.getKraj(),pocetak,kraj)).collect(Collectors.toList());
		System.out.println("KRAJ GRPC KOMUNIKACIJE IZMEDJU SMESTAJ I RESERVATION");
		if(rezervacijeAktIPreklop.size()>0 || preklopSaNedostupnima.size()>0) return false;
		else return true;
	}
	
	private boolean preklop(LocalDateTime p1, LocalDateTime k1, LocalDateTime p2, LocalDateTime k2) {
		if(p1==null || k1==null || p2==null || k2==null) return false;
		boolean p1UnutarT2= p1.plusSeconds(1).isAfter(p2) && p1.plusSeconds(1).isBefore(k2);
		boolean k1UnutarT2= k1.plusSeconds(1).isAfter(p2) && k1.plusSeconds(1).isBefore(k2);
		return p1UnutarT2 || k1UnutarT2;
	}
}
