package com.xml2023.smestajmicroservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.xml2023.smestajmicroservice.services.SmestajExistsServiceImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SmestajMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmestajMicroserviceApplication.class, args);
	}
	
	@GetMapping("/smestajservice")
	public String get() { 
		return "SMESTAJ SERVICE PODIGNUT";
	}
	
	@Bean int port() {
		return 7977;
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean 
	public SmestajExistsServiceImpl smestajExistsService() {
		return new SmestajExistsServiceImpl();
	}
	
	@Bean
    CommandLineRunner runner(){
        return args -> {
//        	GrpcServer server = new GrpcServer(this.port());
//    		server.start();
//    		server.blockUntilShutdown();
        	Server server = ServerBuilder.forPort(this.port())
     	        .addService(this.smestajExistsService())
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
