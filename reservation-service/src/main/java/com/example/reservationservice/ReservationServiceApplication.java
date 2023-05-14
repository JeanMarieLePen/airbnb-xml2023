package com.example.reservationservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.reservationservice.services.RezervacijaExistsServiceImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ReservationServiceApplication {

	@GetMapping("/reservation")
	public String get() { 
		return "RESERVATION";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}

	@Bean int port() {
		return 7978;
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean 
	public RezervacijaExistsServiceImpl rezervacijaExistsService() {
		return new RezervacijaExistsServiceImpl();
	}
	
	@Bean
    CommandLineRunner runner(){
        return args -> {
//        	GrpcServer server = new GrpcServer(this.port());
//    		server.start();
//    		server.blockUntilShutdown();
        	Server server = ServerBuilder.forPort(this.port())
     	        .addService(this.rezervacijaExistsService())
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
