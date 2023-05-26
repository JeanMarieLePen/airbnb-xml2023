package com.app2.flights.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app2.flights.model.data.APIToken;
import com.app2.flights.model.user.RegKor;
import com.app2.flights.repositories.KorisnikRep;
import com.xml2023.flights.FlightsGrpc.FlightsImplBase;
import com.xml2023.flights.FlightsOuterClass.TokenValidanRequest;
import com.xml2023.flights.FlightsOuterClass.TokenValidanResponse;

import io.grpc.stub.StreamObserver;

@Service
public class FlightsServiceGrpc extends FlightsImplBase{

	@Autowired
	private KorisnikRep korRep;
	
	@Override
	public void validateToken(TokenValidanRequest request, StreamObserver<TokenValidanResponse> responseObserver) {
		String token = request.getApiToken();
		RegKor rk = this.korRep.findByApiTokenToken(token).orElse(null);
		
		TokenValidanResponse.Builder response = TokenValidanResponse.newBuilder();
		
		
		if(rk != null) {
			//korisnik postoji; token postoji; treba proveriti validnost tokena
			APIToken tmp = rk.getApiToken();
			if(tmp.isTrajni()) {
				response.setResult(true);
				response.setTekst("TRAJNI TOKEN; KORISNIK IMA PRAVO PRISTUPA MIKROSERVISU");
			}else {
				if(tmp.getVremeVazenja().isBefore(LocalDateTime.now())) {
					response.setResult(false);
					response.setTekst("TOKEN POSTOJI ALI JE ISTEKAO; KORISNIK TREBA DA OBNOVNI TOKEN");
				}else {
					response.setResult(true);
					response.setTekst("TOKEN POSTOJI I NIJE ISTEKAO; KORISNIK IMA PRAVO PRISTUPA MIKROSERVISU");
				}
			}
			responseObserver.onNext(response.build());
			
			responseObserver.onCompleted();
		}else {
			//korisnik ili token ne postoje;
			response.setTekst("NE POSTOJI KORISNIK SA TIM TOKENOM");
			response.setResult(false);
			responseObserver.onNext(response.build());
			responseObserver.onCompleted();
			
		}
	}

	
}
