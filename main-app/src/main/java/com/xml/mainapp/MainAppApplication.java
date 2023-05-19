package com.xml.mainapp;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.github.cloudyrock.spring.v5.EnableMongock;
import com.xml.mainapp.repositories.KorisnikRep;
import com.xml.mainapp.services.GreetingServiceImpl;
import com.xml.mainapp.services.KorisnikGrpcImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableCaching
@EnableMongock
public class MainAppApplication {

	public static void main(String[] args) throws IOException, Exception {
		SpringApplication.run(MainAppApplication.class, args);
		
	}
	
	@Bean int port() {
		return 7979;
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean 
	public GreetingServiceImpl greetingService() {
		return new GreetingServiceImpl();
	}
	@Bean
	public KorisnikGrpcImpl korisnikGrpcImplService() {
		return new KorisnikGrpcImpl();
	}
	
	@Bean
    CommandLineRunner runner(){
        return args -> {
//        	GrpcServer server = new GrpcServer(this.port());
//    		server.start();
//    		server.blockUntilShutdown();
        	Server server = ServerBuilder.forPort(this.port())
//     	        .addService(this.greetingService())
     	        .addService(this.korisnikGrpcImplService())
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
