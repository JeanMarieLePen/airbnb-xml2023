package com.gatewayservice.eurekagateway;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml2023.flights.FlightsGrpc;
import com.xml2023.flights.FlightsGrpc.FlightsImplBase;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableHystrix
@RestController
@EnableScheduling
public class EurekaGatewayApplication {

	@GetMapping("/test")
	public String test() {
		return "TEST GATEWAY";
	}
	
//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) 
//	{
//		AuthorizationFilter orderedGatewayFilter =
//	      new AuthorizationFilter();
//	    return routeLocatorBuilder.routes()
//	         .route( p -> p.path("/regkor/reservation").uri("http://localhost:8081"))
//	        .build().ad;
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaGatewayApplication.class, args);
	}
	
	@Bean int port() {
		return 7975;
	}
	@Bean
	public FlightsImplBase flightsImplBaseService() {
		return new TempServiceImpl();
	}
	@Bean
	CommandLineRunner runner() {
		return args -> {
        	Server server = ServerBuilder.forPort(this.port())
        		.addService(this.flightsImplBaseService())
     	        .build();
        	server.start();
    	    System.out.println("gRPC server started, listening on port " + this.port());
    	    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
    	    	System.out.println("Shutting down gRPC server");
		    	stop(server);
		    }));
        };
	}
	public void stop(Server server) {
	    if (server != null) {
	      server.shutdown();
	    }
	  }
}
