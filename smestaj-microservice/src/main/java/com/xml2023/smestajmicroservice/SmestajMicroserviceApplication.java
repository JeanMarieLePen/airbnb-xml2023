package com.xml2023.smestajmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
