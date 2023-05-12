package com.xml.mainapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.xml.mainapp.dtos.KorisnikDTO;
import com.xml.mainapp.model.users.Korisnik;
import com.xml.mainapp.repositories.KorisnikRep;
import com.xml2023.mainapp.GreetingServiceGrpc.GreetingServiceImplBase;
import com.xml2023.mainapp.KorisnikRequest;
import com.xml2023.mainapp.KorisnikResponse;

import io.grpc.stub.StreamObserver;

@Service
public class GreetingServiceImpl extends GreetingServiceImplBase{

	@Autowired
	private KorisnikRep korRep;
	@Override
	public void greeting(KorisnikRequest request, StreamObserver<KorisnikResponse> responseObserver) {
		// TODO Auto-generated method stub
		System.out.println("INSIDE GREETING[GET KORISNIK]");
		String idKorisnika = request.getId();
		System.out.println("TRAZI SE KORISNIK SA ID-OM: " + idKorisnika);
		Korisnik k = this.korRep.findById(idKorisnika).orElse(null);
		
		KorisnikResponse.Builder response = KorisnikResponse.newBuilder();
		if(k == null) {
			response.setResponseCode(104).setResponsemessage("INVALID ID");
			
		}else {
			response.setResponseCode(200).setResponsemessage(k.getId());
		}
		
		responseObserver.onNext(response.build());
		
		responseObserver.onCompleted();
//		super.greeting(request, responseObserver);
	}

	
}
