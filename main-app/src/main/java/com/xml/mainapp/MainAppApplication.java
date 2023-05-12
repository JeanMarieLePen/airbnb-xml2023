package com.xml.mainapp;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.github.cloudyrock.spring.v5.EnableMongock;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableCaching
@EnableMongock
public class MainAppApplication {

	public static void main(String[] args) throws IOException, Exception {
		SpringApplication.run(MainAppApplication.class, args);
		GrpcServer server = new GrpcServer(7979);
		server.start();
		server.blockUntilShutdown();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
