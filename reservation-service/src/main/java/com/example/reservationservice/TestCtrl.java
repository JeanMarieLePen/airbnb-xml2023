package com.example.reservationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class TestCtrl {
	
	@Autowired
	private RestTemplate template;
	
	@GetMapping
	public String service() {
		return "Reservation-service je pozvan";
	}
}
