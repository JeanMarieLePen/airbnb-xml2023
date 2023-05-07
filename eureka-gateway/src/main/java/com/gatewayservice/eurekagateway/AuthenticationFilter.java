package com.gatewayservice.eurekagateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.google.common.net.HttpHeaders;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{

	@Autowired
	private RouteValidator validator;
	@Autowired
	private JwtUtil jwtUtil;
	
	public static class Config{
		
	}
	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		// TODO Auto-generated method stub
		return ((exchange, chain)->{
			System.out.println("FILTER GATEWAY");
			if(validator.isSecured.test(exchange.getRequest())) {
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("ZAHTEV NEMA AUTORIZACIONI TOKEN");
				}
			}
			
			String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			if(authHeader != null && authHeader.startsWith("Bearer ")) {
				authHeader = authHeader.substring(7);
			}
			try {
				jwtUtil.validateToken(authHeader);
			}catch(Exception e) {
				System.out.println("TOKEN NEISPRAVAN");
				throw new RuntimeException("NEAUTORIZOVAN PRISTUP APLIKACIJI");
			}
			return chain.filter(exchange);
		});
	}
}
