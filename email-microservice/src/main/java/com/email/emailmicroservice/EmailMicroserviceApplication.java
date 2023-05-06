package com.email.emailmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient 
@RestController
@RequestMapping("/email")
public class EmailMicroserviceApplication {

	
	@GetMapping("/test")
	public String test() {
		return "TEST";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EmailMicroserviceApplication.class, args);
	}

}
