package com.example.reservationservice.services;

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
		Boolean b;
		String userId= request.getUserId();
		String tip= request.getTip();
		ActiveResExistsResponse.Builder res= ActiveResExistsResponse.newBuilder();

		if(tip.equals("gost"));
		{
			b= rRep.findAll().stream().anyMatch(x->x.getGost().getId().equals(userId) && x.getStatus().equals(StatusRezervacije.REZERVISANA));
		}//else {
			
		//}
		if(b) {
			res.setExists(1);
		}
		else res.setExists(0);
		responseObserver.onNext(res.build());
		
		responseObserver.onCompleted();
		
	}
	
}
