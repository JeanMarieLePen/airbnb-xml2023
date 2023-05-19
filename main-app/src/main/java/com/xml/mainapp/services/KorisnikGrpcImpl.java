package com.xml.mainapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.mainapp.model.users.Host;
import com.xml.mainapp.repositories.KorisnikRep;
import com.xml2023.mainapp.HostBasicDTO;
import com.xml2023.mainapp.KorisnikGrpcGrpc.KorisnikGrpcImplBase;
import com.xml2023.mainapp.getHostRequest;
import com.xml2023.mainapp.getHostResponse;
import com.xml2023.mainapp.rezOtkazanaRequest;
import com.xml2023.mainapp.rezOtkazanaResponse;

import io.grpc.stub.StreamObserver;

@Service
public class KorisnikGrpcImpl extends KorisnikGrpcImplBase{

	@Autowired
	private KorisnikRep korRep;
	@Override
	public void getHost(getHostRequest request, StreamObserver<getHostResponse> responseObserver) {
		// TODO Auto-generated method stub
		System.out.println("INSIDE getHost");
		String hostId = request.getHostId();
		Host h = (Host) this.korRep.findById(hostId).orElse(null);
		getHostResponse.Builder response = getHostResponse.newBuilder();
		response.setHost(mapHost(h));
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}
	
	public HostBasicDTO mapHost(Host h) {
		HostBasicDTO.Builder retVal = HostBasicDTO.newBuilder();
		retVal.setIstaknuti(h.isIstaknuti());
		retVal.setRezAutomatski(h.isRezAutomatski());
		retVal.setProsecnaOcena(0);
		return retVal.build();
	}

	@Override
	public void rezOtkazana(rezOtkazanaRequest request, StreamObserver<rezOtkazanaResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.rezOtkazana(request, responseObserver);
	}
	

}
