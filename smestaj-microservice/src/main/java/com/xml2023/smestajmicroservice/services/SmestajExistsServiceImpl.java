package com.xml2023.smestajmicroservice.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.protobuf.Timestamp;
import com.xml2023.mainapp.CenovnikDTO;
import com.xml2023.mainapp.DeleteSmestajsForHostRequest;
import com.xml2023.mainapp.DeleteSmestajsForHostResponse;
import com.xml2023.mainapp.SlikaDTO;
import com.xml2023.mainapp.SmestajDTO;
import com.xml2023.mainapp.SmestajExistsRequest;
import com.xml2023.mainapp.SmestajExistsResponse;
import com.xml2023.mainapp.SmestajGrpcGrpc.SmestajGrpcImplBase;
import com.xml2023.mainapp.SmestajIdsForHostRequest;
import com.xml2023.mainapp.SmestajIdsForHostResponse;
import com.xml2023.mainapp.TerminDTO;
import com.xml2023.mainapp.getListaSmestajaByUserIdRequest;
import com.xml2023.mainapp.getListaSmestajaByUserIdResponse;
import com.xml2023.mainapp.getSmestajByIdRequest;
import com.xml2023.mainapp.getSmestajByIdResponse;
import com.xml2023.smestajmicroservice.model.data.Cenovnik;
import com.xml2023.smestajmicroservice.model.data.Smestaj;
import com.xml2023.smestajmicroservice.model.data.Termin;
import com.xml2023.smestajmicroservice.repositories.SmestajRep;

import io.grpc.stub.StreamObserver;

@Service
public class SmestajExistsServiceImpl extends SmestajGrpcImplBase{
	@Autowired SmestajRep sRep;
	
	@Override
	public void exists(SmestajExistsRequest request, StreamObserver<SmestajExistsResponse> responseObserver) {
		String id= request.getId();
		Smestaj r= sRep.findById(id).orElse(null);
		SmestajExistsResponse.Builder response = SmestajExistsResponse.newBuilder();
		if(r == null) {
			response.setResponseCode(104).setResponsemessage("INVALID ID");
			
		}else {
			response.setResponseCode(200).setResponsemessage(r.getId());
		}
		
		responseObserver.onNext(response.build());
		
		responseObserver.onCompleted();
//		super.greeting(request, responseObserver);
	
	}

	@Override
	public void getSmestajIdsForHost(SmestajIdsForHostRequest request,
			StreamObserver<SmestajIdsForHostResponse> responseObserver) {
		String hostId=request.getUserId();
		List<String> ids= sRep.findAll().stream().filter(x->x.getVlasnik().equals(hostId)).map(x->x.getId()).collect(Collectors.toList());

		SmestajIdsForHostResponse.Builder res= SmestajIdsForHostResponse.newBuilder();
		if(ids.size()>0) {
			res.addAllSmestajIds(ids);
		}else 	res.setSmestajIds(0, "0");

		responseObserver.onNext(res.build());
		
		responseObserver.onCompleted();
		
	}

	@Override
	public void deketeSnestajsForHost(DeleteSmestajsForHostRequest request,
			StreamObserver<DeleteSmestajsForHostResponse> responseObserver) {
		String hostId=request.getHostId();
		List<String> smestajiIds=sRep.findAll().stream().filter(x->x.getVlasnik().equals(hostId)).map(x->x.getId()).collect(Collectors.toList());
		sRep.deleteAllById(smestajiIds);
		List<String> smestajiIdsCheck=sRep.findAll().stream().filter(x->x.getVlasnik().equals(hostId)).map(x->x.getId()).collect(Collectors.toList());
		
		DeleteSmestajsForHostResponse.Builder res= DeleteSmestajsForHostResponse.newBuilder();
		if(smestajiIdsCheck.size()>0) {
			res.setSuccess(0);
		}else res.setSuccess(1);
		responseObserver.onNext(res.build());
		responseObserver.onCompleted();
	}

	@Override
	public void getSmestajById(getSmestajByIdRequest request, StreamObserver<getSmestajByIdResponse> responseObserver) {
		// TODO Auto-generated method stub
		String smestajId = request.getSmestajId();
		Smestaj s = sRep.findById(smestajId).orElse(null);
		getSmestajByIdResponse.Builder response = getSmestajByIdResponse.newBuilder();
		
		if(s != null) {
			response.setOdgovor(mapSmestaj(s));
		}
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void getListaSmestajaByUserId(getListaSmestajaByUserIdRequest request,
			StreamObserver<getListaSmestajaByUserIdResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.getListaSmestajaByUserId(request, responseObserver);
	}
	
	public SmestajDTO mapSmestaj(Smestaj s) {
		SmestajDTO.Builder retVal = SmestajDTO.newBuilder();
		retVal.setId(s.getId());
		retVal.setVlasnik(s.getVlasnik());
		retVal.setMinGosti(s.getMinGosti());
		retVal.setMaxGost(s.getMaxGosti());
		//cenovnik
		retVal.setCenovnik(mapCenovnik(s.getCenovnik()));
		//mapiranje termina
		for(Termin t : s.getNedostupni()) {
			retVal.addNedostupni(mapTermin(t));
		}
		//pogodnosti
		for(String str : s.getPogodnosti()) {
			retVal.addPogodnosti(str);
		}
		//slike
		for(byte[] slika : s.getSlike()) {
			String tempSlika = Base64.getEncoder().encodeToString(slika);
			retVal.addSlika(mapSlika(tempSlika));
		}
		return retVal.build();
	}
	
	public SlikaDTO mapSlika(String tempSlika) {
		SlikaDTO.Builder retVal = SlikaDTO.newBuilder();
		retVal.setSlika(tempSlika.toString());
		return retVal.build();
	}
	public CenovnikDTO mapCenovnik(Cenovnik c) {
		CenovnikDTO.Builder retVal = CenovnikDTO.newBuilder();
		retVal.setCena(c.getCena());
		retVal.setCenaLeto(c.getCenaLeto());
		retVal.setCenaVikend(c.getCenaVikend());
		retVal.setCenaPraznik(c.getCenaPraznik());
		retVal.setPoSmestaju(c.isPoSmestaju());
		return retVal.build();
	}
	public TerminDTO mapTermin(Termin t) {
		TerminDTO.Builder retVal = TerminDTO.newBuilder();
		retVal.setPocetak(convertToTimeStamp(t.getPocetak()));
		retVal.setKraj(convertToTimeStamp(t.getKraj()));
		return retVal.build();
	}
	
	public Timestamp convertToTimeStamp(LocalDateTime ldt) {
		return Timestamp.newBuilder().setSeconds(ldt.toEpochSecond(ZoneOffset.UTC))
				.setNanos(ldt.getNano()).build();
	}
}
