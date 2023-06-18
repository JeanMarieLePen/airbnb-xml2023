package com.xml.mainapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.mainapp.controllers.CommunicationListener;
import com.xml.mainapp.dtos.NotificationDTO;
import com.xml.mainapp.dtos.NotificationHostDTO;
import com.xml.mainapp.dtos.NotificationHostDTO.TipNotifikacije;
import com.xml.mainapp.model.users.Guest;
import com.xml.mainapp.model.users.Host;
import com.xml.mainapp.model.users.Korisnik;
import com.xml.mainapp.repositories.KorisnikRep;
import com.xml2023.mainapp.DobioStatusIstaknutogRequest;
import com.xml2023.mainapp.DobioStatusIstaknutogResponse;
import com.xml2023.mainapp.HostBasicDTO;
import com.xml2023.mainapp.KorisnikGrpcGrpc.KorisnikGrpcImplBase;
import com.xml2023.mainapp.NekoOcenioHostaRequest;
import com.xml2023.mainapp.NekoOcenioHostaResponse;
import com.xml2023.mainapp.NekoOcenioSmestajRequest;
import com.xml2023.mainapp.NekoOcenioSmestajResponse;
import com.xml2023.mainapp.NekoOtkazaoRequest;
import com.xml2023.mainapp.NekoOtkazaoResponse;
import com.xml2023.mainapp.NekoRezervisaoRequest;
import com.xml2023.mainapp.NekoRezervisaoResponse;
import com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest;
import com.xml2023.mainapp.NovaOcenaHostaNotifikacijaResponse;
import com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaRequest;
import com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaResponse;
import com.xml2023.mainapp.NovaRezervacijaNotifikacijaRequest;
import com.xml2023.mainapp.NovaRezervacijaNotifikacijaResponse;
import com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaRequest;
import com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaResponse;
import com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest;
import com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse;
import com.xml2023.mainapp.getHostRequest;
import com.xml2023.mainapp.getHostResponse;
import com.xml2023.mainapp.reservationApprovedNotificationRequest;
import com.xml2023.mainapp.reservationApprovedNotificationResponse;
import com.xml2023.mainapp.rezOtkazanaHostRequest;
import com.xml2023.mainapp.rezOtkazanaHostResponse;
import com.xml2023.mainapp.rezOtkazanaRequest;
import com.xml2023.mainapp.rezOtkazanaResponse;
import com.xml2023.mainapp.rezOtkazanaResponseOrBuilder;

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
		Host h = this.korRep.findHostById(hostId);
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
		Guest g = this.korRep.findGuestById(id);
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
	public void rezOtkazanaHost(rezOtkazanaHostRequest request,
			StreamObserver<rezOtkazanaHostResponse> responseObserver) {
		// TODO Auto-generated method stub
		String id = request.getHostId();
		NotificationDTO notifikacija = new NotificationDTO();
		notifikacija.setIdRezervacije(request.getResId());
		notifikacija.setTekst("Otkazana je rezervacija sa ID-om: " + notifikacija.getIdRezervacije());
		cmnListener.sendNotification(notifikacija);
		rezOtkazanaHostResponse.Builder response = rezOtkazanaHostResponse.newBuilder();
		response.setPenalDodat(true);
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void rezObavestenje(reservationApprovedNotificationRequest request,
			StreamObserver<reservationApprovedNotificationResponse> responseObserver) {
			String id = request.getIdRezervacije();
			NotificationDTO notifikacija = new NotificationDTO();
			notifikacija.setIdRezervacije(id);
			notifikacija.setTekst("Odobrena je rezervacija sa ID-om: " + id);
			cmnListener.sendNotification(notifikacija);
			reservationApprovedNotificationResponse.Builder response = reservationApprovedNotificationResponse.newBuilder();
			response.setIsporuceno(true);
			responseObserver.onNext(response.build());
			responseObserver.onCompleted();		
	}

	@Override
	public void newRezNotify(NekoRezervisaoRequest request, StreamObserver<NekoRezervisaoResponse> responseObserver) {
		String idKorisnika = request.getIdKorisnika();
		String idSmestaja = request.getIdSmestaja();
		NotificationHostDTO notifikacija = new NotificationHostDTO();
		notifikacija.setIdKorisnika(idKorisnika);
		notifikacija.setIdSmestaja(idSmestaja);
		notifikacija.setTipNotifikacije(TipNotifikacije.NOVA_REZERVACIJA);
		notifikacija.setTekst("Nova rezervacija smestaja ID-a " + idSmestaja);
		cmnListener.sendNotificationToHost(notifikacija);
		NekoRezervisaoResponse.Builder response = NekoRezervisaoResponse.newBuilder();
		response.setResult(true);
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();		
	}

	@Override
	public void newQuitNotify(NekoOtkazaoRequest request, StreamObserver<NekoOtkazaoResponse> responseObserver) {
		String idKorisnika = request.getIdKorisnika();
		String idSmestaja = request.getIdSmestaja();
		NotificationHostDTO notifikacija = new NotificationHostDTO();
		notifikacija.setIdKorisnika(idKorisnika);
		notifikacija.setIdSmestaja(idSmestaja);
		notifikacija.setTipNotifikacije(TipNotifikacije.OTKAZANA_REZERVACIJA);
		notifikacija.setTekst("Otkazana rezervacija smestaja ID-a " + idSmestaja);
		cmnListener.sendNotificationToHost(notifikacija);
		NekoOtkazaoResponse.Builder response = NekoOtkazaoResponse.newBuilder();
		response.setResult(true);
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();		
	}

	@Override
	public void newRankSmestaj(NekoOcenioSmestajRequest request,
			StreamObserver<NekoOcenioSmestajResponse> responseObserver) {
		String idKorisnika = request.getIdKorisnika();
		String idSmestaja = request.getIdSmestaja();
		int ocena = request.getOcena();
		NotificationHostDTO notifikacija = new NotificationHostDTO();
		notifikacija.setIdKorisnika(idKorisnika);
		notifikacija.setIdSmestaja(idSmestaja);
		notifikacija.setTipNotifikacije(TipNotifikacije.OCENJEN_SMESTAJ);
		notifikacija.setTekst("Ocenjen smestaj ID-a " + idSmestaja + "ocenom " + ocena);
		notifikacija.setOcena(ocena);
		cmnListener.sendNotificationToHost(notifikacija);
		NekoOcenioSmestajResponse.Builder response = NekoOcenioSmestajResponse.newBuilder();
		response.setResult(true);
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();	
	}

	@Override
	public void newRankHost(NekoOcenioHostaRequest request, StreamObserver<NekoOcenioHostaResponse> responseObserver) {
		String idKorisnika = request.getIdKorisnika();
		int ocena = request.getOcena();
		NotificationHostDTO notifikacija = new NotificationHostDTO();
		notifikacija.setIdKorisnika(idKorisnika);
		notifikacija.setOcena(ocena);
		notifikacija.setTipNotifikacije(TipNotifikacije.OCENJEN_HOST);
		notifikacija.setTekst("Korisnik ID-a " + idKorisnika + "vas je ocenio ocenom " + ocena);
		cmnListener.sendNotificationToHost(notifikacija);
		NekoOcenioHostaResponse.Builder response = NekoOcenioHostaResponse.newBuilder();
		response.setResult(true);
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();	
	}

	@Override
	public void istaknutiHost(DobioStatusIstaknutogRequest request,
			StreamObserver<DobioStatusIstaknutogResponse> responseObserver) {
		String idKorisnika = request.getIdKorisnika();
		Host h = this.korRep.findHostById(idKorisnika);
		if(h != null) {
			if(h.isStatusNotification()) {
				NotificationHostDTO notifikacija = new NotificationHostDTO();
				notifikacija.setIstaknuti(request.getStatus());
				notifikacija.setTipNotifikacije(TipNotifikacije.STATUS_ISTAKNUTOG);
				notifikacija.setTekst("status istaknutog hosta: " + request.getStatus());
				cmnListener.sendNotificationToHost(notifikacija);
			}
		}
		DobioStatusIstaknutogResponse.Builder response = DobioStatusIstaknutogResponse.newBuilder().setResult(true);
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();	
	}

	@Override
	public void novaRezNotStatus(NovaRezervacijaNotifikacijaRequest request,
			StreamObserver<NovaRezervacijaNotifikacijaResponse> responseObserver) {
		String idKorisnika = request.getIdKorisnika();
		Host k = this.korRep.findHostById(idKorisnika);
		NovaRezervacijaNotifikacijaResponse response = NovaRezervacijaNotifikacijaResponse.newBuilder().setStanje(k.isNewNotification()).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();	
	}

	@Override
	public void otkazanaRezNotStatus(OtkazanaRezervacijaNotifikacijaRequest request,
			StreamObserver<OtkazanaRezervacijaNotifikacijaResponse> responseObserver) {
		String idKorisnika = request.getIdKorisnika();
		Host k = this.korRep.findHostById(idKorisnika);
		OtkazanaRezervacijaNotifikacijaResponse response = OtkazanaRezervacijaNotifikacijaResponse.newBuilder().setStanje(k.isCanceledNotification()).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();	
	}

	@Override
	public void novaOcenaHostaNotStatus(NovaOcenaHostaNotifikacijaRequest request,
			StreamObserver<NovaOcenaHostaNotifikacijaResponse> responseObserver) {
		String idKorisnika = request.getIdKorisnika();
		Host k = this.korRep.findHostById(idKorisnika);
		NovaOcenaHostaNotifikacijaResponse response = NovaOcenaHostaNotifikacijaResponse.newBuilder().setStanje(k.isRatedHostNotification()).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();	
	}

	@Override
	public void novaOcenaSmestajaNotStatus(NovaOcenaSmestajaNotifikacijaRequest request,
			StreamObserver<NovaOcenaSmestajaNotifikacijaResponse> responseObserver) {
		String idKorisnika = request.getIdKorisnika();
		Host k = this.korRep.findHostById(idKorisnika);
		NovaOcenaSmestajaNotifikacijaResponse response = NovaOcenaSmestajaNotifikacijaResponse.newBuilder().setStanje(k.isRatedAccomodationNotification()).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();	
	}

	@Override
	public void istaknutiNotStatus(StatusIstaknutogNotifikacijaRequest request,
			StreamObserver<StatusIstaknutogNotifikacijaResponse> responseObserver) {
		String idKorisnika = request.getIdKorisnika();
		Host k = this.korRep.findHostById(idKorisnika);
		StatusIstaknutogNotifikacijaResponse response = StatusIstaknutogNotifikacijaResponse.newBuilder().setStanje(k.isStatusNotification()).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();	
	}
}
