package com.gatewayservice.eurekagateway;

import org.springframework.stereotype.Service;

import com.xml2023.flights.FlightsGrpc;
import com.xml2023.flights.FlightsGrpc.FlightsImplBase;
import com.xml2023.flights.Gateway.TokenValidanRequest;
import com.xml2023.flights.Gateway.TokenValidanResponse;

import io.grpc.stub.StreamObserver;

@Service
public class TempServiceImpl extends FlightsImplBase{

	@Override
	public void validateToken(TokenValidanRequest request, StreamObserver<TokenValidanResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.validateToken(request, responseObserver);
	}
	
}
