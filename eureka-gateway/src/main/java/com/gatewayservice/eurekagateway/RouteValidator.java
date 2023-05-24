package com.gatewayservice.eurekagateway;

import java.util.List;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

	//endpointi za koje nije neophodan API key
	public static final List<String> apiEndPoints = List.of(
		"/login/authenticate",
		"/login/register",
		"/login/activateAccount",
		"/login2/authenticate",
		"/login2/register",
		"/login2/activateAccount"
//		"/smestaj/getAllPogodnosti"
	);
			
	public Predicate<ServerHttpRequest> isSecured = 
			request -> apiEndPoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
			
}
