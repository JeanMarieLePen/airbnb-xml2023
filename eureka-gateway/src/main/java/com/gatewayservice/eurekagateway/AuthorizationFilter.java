//package com.gatewayservice.eurekagateway;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.cloud.gateway.route.Route;
//import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.server.ResponseStatusException;
//import org.springframework.web.server.ServerWebExchange;
//
//import lombok.extern.slf4j.Slf4j;
//import reactor.core.publisher.Mono;
//
//@Component
//@Slf4j
//public class AuthorizationFilter implements GlobalFilter, Ordered{
//	
//	@Autowired
//	private RedisHashComponent redisHashComponent;
//	
//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//		// TODO Auto-generated method stub
//		List<String> apiKeyHeader = exchange.getRequest().getHeaders().get("gatewayApiKey");
//		log.info("API KEY: {}", apiKeyHeader);
//		Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
//		String routeId = route != null ? route.getId() : null;
//		if(routeId == null || CollectionUtils.isEmpty(apiKeyHeader) || !isAuthorized(routeId, apiKeyHeader.get(0))) {
//			System.out.println("WRONG API KEY");
//			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "YOU CANT CONSUME THIS SERVICE. WRONG API KEY.");
//		}else {
//			return chain.filter(exchange);
//		}
//	}
//	
//	//u redis db-u se proverava da li prosledjeni key odgovara mikroservisu
//	private boolean isAuthorized(String routeId, String apiKey) {
//		Object apiKeyObj = redisHashComponent.hGet(ApiKeys.RECORD_KEY, apiKey);
//		if(apiKeyObj != null) {
//			ApiKey key = redisHashComponent.objectMapper(apiKeyObj, ApiKey.class);
//			return key.getServices().contains(routeId);
//		}else {
//			return false;
//		}
//	}
//	
//	@Override
//	public int getOrder() {
//		return Ordered.LOWEST_PRECEDENCE;
//	}
//}
