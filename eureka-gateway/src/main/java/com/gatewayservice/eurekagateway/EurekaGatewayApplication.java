package com.gatewayservice.eurekagateway;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.resolver.DefaultAddressResolverGroup;
import jakarta.annotation.PostConstruct;
import reactor.netty.http.client.HttpClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@RestController
public class EurekaGatewayApplication {

	@GetMapping("/test")
	public String test() {
		return "TEST GATEWAY";
	}
	
//	@Autowired
//	private RedisHashComponent redisHashComponent;
	
	
//	@PostConstruct
//	//konfigurisu se API Keys spram redis baze
//	//postconstruct anotacija -> u vreme app startup-a izvrsava se konfiguracija sa redis bazom
//	public void initKeysToRedis() {
//		List<ApiKey> apiKeys = new ArrayList<>();
//		apiKeys.add(new ApiKey("cgConPZBPgwLNoLTSLj4NtXp2vNi7WKx", Stream.of(ApiKeys.MAIN_APP_KEY, ApiKeys.FLIGHTS_APP_KEY).collect(Collectors.toList())));
//		apiKeys.add(new ApiKey("jc5RD98p3tCUHfd8zflHjfRQF4ENoBBn", Stream.of(ApiKeys.FLIGHTS_APP_KEY).collect(Collectors.toList())));
//		List<Object> lists = redisHashComponent.hValues(ApiKeys.RECORD_KEY);
//		if(lists.isEmpty()) {
//			apiKeys.forEach(k->redisHashComponent.hSet(ApiKeys.RECORD_KEY, k.getKey(), k));
//		}
//	}
//	
//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes()
////				.route(ApiKeys.MAIN_APP_KEY, r->r.path("/korisnik/**").filters(f->f.stripPrefix(2)).uri("lb://MAIN-APP-SERVICE"))
////				.route(ApiKeys.MAIN_APP_KEY, r->r.path("/login/**").filters(f->f.stripPrefix(2)).uri("lb://MAIN-APP-SERVICE"))
////				.route(ApiKeys.FLIGHTS_APP_KEY, r->r.path("/login2/authenticate/").filters(f->f.stripPrefix(2)).uri("lb://FLIGHTS-SERVICE"))
//				.route(ApiKeys.MAIN_APP_KEY, r->r.path("/korisnik/**").uri("lb://MAIN-APP-SERVICE"))
//				.route(ApiKeys.MAIN_APP_KEY, r->r.path("/login/**").uri("lb://MAIN-APP-SERVICE"))
//				.route(ApiKeys.FLIGHTS_APP_KEY, r->r.path("/login2/**").uri("lb://FLIGHTS-SERVICE"))
//				.route(ApiKeys.FLIGHTS_APP_KEY, r->r.path("/korisnik2/**").uri("lb://FLIGHTS-SERVICE"))
////				.route(ApiKeys.FLIGHTS_APP_KEY, r->r.path("/korisnik2/**").filters(f->f.stripPrefix(2)).uri("lb://FLIGHTS-SERVICE"))
//				.build();
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaGatewayApplication.class, args);
	}

}
