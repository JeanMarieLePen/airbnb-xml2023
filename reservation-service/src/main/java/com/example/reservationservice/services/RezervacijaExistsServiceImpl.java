package com.example.reservationservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reservationservice.model.Rezervacija;
import com.example.reservationservice.model.StatusRezervacije;
import com.example.reservationservice.repositories.RezervacijaRep;
import com.xml2023.mainapp.ActiveResExistsRequest;
import com.xml2023.mainapp.ActiveResExistsResponse;
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
		// TODO Auto-generated method stub
		super.getRezervacijaById(request, responseObserver);
	}
	@Override
	public void getListaRezervacijaByUserId(getListaRezervacijaByUserIdRequest request,
			StreamObserver<getListaRezervacijaByUserIdResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.getListaRezervacijaByUserId(request, responseObserver);
	}
	
	
	
}
