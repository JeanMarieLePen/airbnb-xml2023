package com.xml2023.smestajmicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml2023.mainapp.RezervacijaExistsResponse;
import com.xml2023.mainapp.SmestajExistsRequest;
import com.xml2023.mainapp.SmestajExistsResponse;
import com.xml2023.mainapp.SmestajGrpcGrpc.SmestajGrpcImplBase;
import com.xml2023.smestajmicroservice.model.data.Smestaj;
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
	
}
