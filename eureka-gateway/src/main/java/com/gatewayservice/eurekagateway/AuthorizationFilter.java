package com.gatewayservice.eurekagateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import com.xml2023.flights.FlightsGrpc;
import com.xml2023.flights.FlightsGrpc.FlightsBlockingStub;
import com.xml2023.flights.Gateway.TokenValidanRequest;
import com.xml2023.flights.Gateway.TokenValidanResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationFilter implements GlobalFilter, Ordered{
	
//	@Autowired
//	private RedisHashComponent redisHashComponent;
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		
		ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest(); 
		String putanja = request.getURI().getPath();
//		/regkor/reservationWithToken
		if(putanja.equals("/regkor/reservationWithToken")) {
			List<String> apiKeyHeader = exchange.getRequest().getHeaders().get("gatewayApiKey");
			log.info("API KEY: {}", apiKeyHeader);
			Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
			String routeId = route != null ? route.getId() : null;
//			if(routeId.equals("FLIGHTS-"))
			if(routeId == null || CollectionUtils.isEmpty(apiKeyHeader) || !isAuthorized(routeId, apiKeyHeader.get(0))) {
				System.out.println("WRONG API KEY");
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "YOU CANT CONSUME THIS SERVICE. WRONG API KEY.");
			}else {
				return chain.filter(exchange);
			}
		}
		return chain.filter(exchange);
		
	}
	
	//provera da li prosledjeni gatewayApiKey odgovara nekom korisniku
	private boolean isAuthorized(String routeId, String apiKey) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7976).usePlaintext().build();
		TokenValidanRequest rqst = TokenValidanRequest.newBuilder().setApiToken(apiKey).build();
		FlightsBlockingStub bs = FlightsGrpc.newBlockingStub(channel);
		TokenValidanResponse response = bs.validateToken(rqst);
		return response.getResult();
//		Object apiKeyObj = redisHashComponent.hGet(ApiKeys.RECORD_KEY, apiKey);
//		if(apiKeyObj != null) {
//			ApiKey key = redisHashComponent.objectMapper(apiKeyObj, ApiKey.class);
//			return key.getServices().contains(routeId);
//		}else {
//			return false;
//		}
	}
	
	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}