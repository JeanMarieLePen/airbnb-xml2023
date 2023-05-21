package com.xml.mainapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.mainapp.controllers.CommunicationListener;
import com.xml.mainapp.dtos.NotificationDTO;
import com.xml.mainapp.model.users.Guest;
import com.xml.mainapp.model.users.Host;
import com.xml.mainapp.repositories.KorisnikRep;
import com.xml2023.mainapp.HostBasicDTO;
import com.xml2023.mainapp.KorisnikGrpcGrpc.KorisnikGrpcImplBase;
import com.xml2023.mainapp.getHostRequest;
import com.xml2023.mainapp.getHostResponse;
import com.xml2023.mainapp.reservationApprovedNotificationRequest;
import com.xml2023.mainapp.reservationApprovedNotificationResponse;
import com.xml2023.mainapp.rezOtkazanaRequest;
import com.xml2023.mainapp.rezOtkazanaResponse;

import io.grpc.stub.StreamObserver;

@Service
public class KorisnikGrpcImpl extends KorisnikGrpcImplBase{

	@Autowired
	private KorisnikRep korRep;
	
	@Autowired
	private CommunicationListener cmnListener;
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
		String id = request.getGuestId();
		Guest g = (Guest) this.korRep.findById(id).orElse(null);
		boolean penal = false;
		if(g != null) {
			g.setBrojOtkazivanja(g.getBrojOtkazivanja() + 1);
			penal = true;
		}
		korRep.save(g);
		rezOtkazanaResponse.Builder response = rezOtkazanaResponse.newBuilder();
		response.setPenalDodat(penal);
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void rezObavestenje(reservationApprovedNotificationRequest request,
			StreamObserver<reservationApprovedNotificationResponse> responseObserver) {
			String id = request.getIdRezervacije();
			NotificationDTO notifikacija = new NotificationDTO();
			notifikacija.setIdRezervacije(id);
			cmnListener.sendNotification(notifikacija);
			reservationApprovedNotificationResponse.Builder response = reservationApprovedNotificationResponse.newBuilder();
			response.setIsporuceno(true);
			responseObserver.onNext(response.build());
			responseObserver.onCompleted();		
	}
	

}
