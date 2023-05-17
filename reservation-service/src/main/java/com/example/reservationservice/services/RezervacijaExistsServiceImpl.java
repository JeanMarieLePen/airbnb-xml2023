package com.example.reservationservice.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reservationservice.model.Rezervacija;
import com.example.reservationservice.model.StatusRezervacije;
import com.example.reservationservice.repositories.RezervacijaRep;
import com.example.reservationservice.repositories.SmestajRep;
import com.google.protobuf.Timestamp;
import com.xml2023.mainapp.ActiveResExistsForSmestajRequest;
import com.xml2023.mainapp.ActiveResExistsForSmestajResponse;
import com.xml2023.mainapp.ActiveResExistsRequest;
import com.xml2023.mainapp.ActiveResExistsResponse;
import com.xml2023.mainapp.ActiveReservationsRequest;
import com.xml2023.mainapp.ActiveReservationsResponse;
import com.xml2023.mainapp.RezervacijaDTO;
import com.xml2023.mainapp.RezervacijaExistsRequest;
import com.xml2023.mainapp.RezervacijaExistsResponse;
import com.xml2023.mainapp.RezervacijaGrpcGrpc.RezervacijaGrpcImplBase;
import com.xml2023.mainapp.SmestajGrpcGrpc;
import com.xml2023.mainapp.SmestajGrpcGrpc.SmestajGrpcBlockingStub;
import com.xml2023.mainapp.SmestajIdsForHostRequest;
import com.xml2023.mainapp.SmestajIdsForHostResponse;
import com.xml2023.mainapp.getListaRezervacijaByUserIdRequest;
import com.xml2023.mainapp.getListaRezervacijaByUserIdResponse;
import com.xml2023.mainapp.getRezervacijaByIdRequest;
import com.xml2023.mainapp.getRezervacijaByIdResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

@Service
public class RezervacijaExistsServiceImpl extends RezervacijaGrpcImplBase {

	@Autowired RezervacijaRep rRep;
	@Override
	public void exists(RezervacijaExistsRequest request, StreamObserver<RezervacijaExistsResponse> responseObserver) {
		String idRez= request.getId();
		Rezervacija r= rRep.findById(idRez).orElse(null);
		RezervacijaExistsResponse.Builder response = RezervacijaExistsResponse.newBuilder();
		if(r == null) {
			response.setResponseCode(104).setResponsemessage("INVALID ID");
			
		}else {
			response.setResponseCode(200).setResponsemessage(r.getId());
		}
		
		responseObserver.onNext(response.build());
		
		responseObserver.onCompleted();
		//super.greeting(request, responseObserver);
	}
	@Override
	public void reservationsForUserExists(ActiveResExistsRequest request,
			StreamObserver<ActiveResExistsResponse> responseObserver) {
		Boolean b=false;
		String userId= request.getUserId();
		String tip= request.getTip();
		ActiveResExistsResponse.Builder res= ActiveResExistsResponse.newBuilder();

		if(tip.equals("gost"))
		{
			b= rRep.findAll().stream().anyMatch(x->x.getGost().getId().equals(userId) && x.getStatus().equals(StatusRezervacije.REZERVISANA));
		}else {
			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7977).usePlaintext().build();
			SmestajGrpcBlockingStub blockStub= SmestajGrpcGrpc.newBlockingStub(channel);
			SmestajIdsForHostRequest req= SmestajIdsForHostRequest.newBuilder().setUserId(userId).build();
			SmestajIdsForHostResponse resIds= blockStub.getSmestajIdsForHost(req);
			ArrayList<String> ids=new ArrayList<String>();
			for(int i= 0 ; i<=resIds.getSmestajIdsCount();i++) {
				ids.add(resIds.getSmestajIds(i));
			}
			if (ids.size()==1 && ids.get(0).equals("0"))
				b=false;
			else {
				for(String id : ids) {
					Boolean postojeRez= rRep.findAll().stream().anyMatch(x->x.getSmestaj().getId().equals(id) && 
							(x.getStatus().equals(StatusRezervacije.REZERVISANA) ||x.getStatus().equals(StatusRezervacije.PENDING) ));
					if(postojeRez) {
						b=true;
						break;
					}
				}
			}
		}
		if(b) {
			res.setExists(1);
		}
		else res.setExists(0);
		responseObserver.onNext(res.build());
		
		responseObserver.onCompleted();
		
	}
	
	
	@Override
	public void getRezervacijaById(getRezervacijaByIdRequest request,
			StreamObserver<getRezervacijaByIdResponse> responseObserver) {
//		String rezervacijaId = request.getRezervacijaId();
//		Rezervacija r = rRep.findById(rezervacijaId).orElse(null);
//		getRezervacijaByIdResponse.Builder response = getRezervacijaByIdResponse.newBuilder();
//		RezervacijaDTO.Builder retVal = RezervacijaDTO.newBuilder();
//		retVal.setBrojGostiju(r.getBrojGostiju());
//		retVal.setDoDatum(convertToTimeStamp(r.getDoDatum()));
//		retVal.setOdDatum(convertToTimeStamp(r.getOdDatum()));
//		retVal.setGost(r.getGost());
//		retVal.setId(r.getId());
//		retVal.setSmestaj(r.getSmestaj());
//		retVal.setStatus(pickStatus(r.getStatus()));
//		response.setOdgovor(retVal);
//		responseObserver.onNext(response.build());
//		responseObserver.onCompleted();
		
	}
	@Override
	public void getListaRezervacijaByUserId(getListaRezervacijaByUserIdRequest request,
			StreamObserver<getListaRezervacijaByUserIdResponse> responseObserver) {
		// TODO Auto-generated method stub
//		String userId = request.getId();
//		List<Rezervacija> userReservation = this.rRep.findAll().stream().filter(r -> r.getGost().equals(userId)).toList();
//		getListaRezervacijaByUserIdResponse.Builder response = getListaRezervacijaByUserIdResponse.newBuilder();
//		
//		if(userReservation != null) {
//			for(Rezervacija r : userReservation) {
//				RezervacijaDTO.Builder temp = RezervacijaDTO.newBuilder();
//				temp.setBrojGostiju(r.getBrojGostiju());
//				temp.setDoDatum(convertToTimeStamp(r.getOdDatum()));
//				temp.setOdDatum(convertToTimeStamp(r.getOdDatum()));
//				temp.setGost(r.getGost());
//				temp.setId(r.getId());
//				temp.setSmestaj(r.getSmestaj());
//				temp.setStatusValue(r.getStatus().getStatus());
//				response.addListaRezervacija(temp);
//			}
//		}else {
//			response.addAllListaRezervacija(new ArrayList<RezervacijaDTO>());
//		}
//		responseObserver.onNext(response.build());
//		responseObserver.onCompleted();
	}
	
	@Override
	public void resExistsForSmestaj(ActiveResExistsForSmestajRequest request,
			StreamObserver<ActiveResExistsForSmestajResponse> responseObserver) {
		String smestajId = request.getUserId();
		//lista koja sadrzi sve rezervacije smestaja sa ID-om smestajId koje su u statusu REZERVISANA
		List<Rezervacija> userAll = rRep.findAll().stream().filter(r -> r.getSmestaj().equals(smestajId) && r.getStatus().equals(StatusRezervacije.REZERVISANA)).toList();
		ActiveResExistsForSmestajResponse.Builder response = ActiveResExistsForSmestajResponse.newBuilder();
		if(userAll.isEmpty()) {
			response.setExists(false);
		}else {
			response.setExists(true);
		}
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}
	@Override
	public void getActiveReservations(ActiveReservationsRequest request,
			StreamObserver<ActiveReservationsResponse> responseObserver) {
		String smestajId = request.getSmestajId();
		LocalDateTime pocetak = Instant.ofEpochSecond(request.getPocetak().getSeconds(), request.getPocetak().getNanos()).atZone(ZoneId.of("Europe/Belgrade")).toLocalDateTime();
		LocalDateTime kraj = Instant.ofEpochSecond(request.getKraj().getSeconds(), request.getKraj().getNanos()).atZone(ZoneId.of("Europe/Belgrade")).toLocalDateTime();
		List<Rezervacija> listaRezervacija = rRep.findAll().stream().filter(r -> r.getSmestaj().equals(smestajId) && r.getStatus().equals(StatusRezervacije.REZERVISANA)).toList();
		
		//Konverzija u list<RezervacijaDTO> iz protobufa
		ActiveReservationsResponse.Builder res = ActiveReservationsResponse.newBuilder();
		
		List<RezervacijaDTO> retList = new ArrayList<RezervacijaDTO>();
		for(Rezervacija r : listaRezervacija) {
			RezervacijaDTO.Builder temp = RezervacijaDTO.newBuilder();
//			temp.setBrojGostiju(r.getBrojGostiju());
//			temp.setDoDatum(convertToTimeStamp(r.getOdDatum()));
//			temp.setOdDatum(convertToTimeStamp(r.getOdDatum()));
//			temp.setGost(r.getGost());
//			temp.setId(r.getId());
//			temp.setSmestaj(r.getSmestaj());
//			temp.setStatusValue(r.getStatus().getStatus());
//			retList.add(temp.build());
		}
		res.addAllListaRezervacija(retList);
		responseObserver.onNext(res.build());
		
		responseObserver.onCompleted();
	}
	
	public Timestamp convertToTimeStamp(LocalDateTime ldt) {
		return Timestamp.newBuilder().setSeconds(ldt.toEpochSecond(ZoneOffset.UTC))
				.setNanos(ldt.getNano()).build();
	}
	
	private com.xml2023.mainapp.RezervacijaDTO.StatusRezervacije pickStatus(StatusRezervacije status) {
		if(status.equals(StatusRezervacije.ODBIJENA)) {
			return RezervacijaDTO.StatusRezervacije.ODBIJENA;
		}else if(status.equals(StatusRezervacije.OTKAZANA)) {
			return RezervacijaDTO.StatusRezervacije.OTKAZANA;
		}else if(status.equals(StatusRezervacije.PENDING)) {
			return RezervacijaDTO.StatusRezervacije.PENDING;
		}else if(status.equals(StatusRezervacije.REZERVISANA)) {
			return RezervacijaDTO.StatusRezervacije.REZERVISANA;
		}
		return null;
	}
}
