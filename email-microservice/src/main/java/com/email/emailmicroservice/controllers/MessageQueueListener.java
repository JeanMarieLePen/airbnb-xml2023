package com.email.emailmicroservice.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.email.emailmicroservice.MetrikeMetode;
import com.email.emailmicroservice.config.MessageQueueConfig;
import com.email.emailmicroservice.dtos.RegisterDTO;
import com.email.emailmicroservice.services.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class MessageQueueListener {
	@Autowired 
	MetrikeMetode met;

	@Autowired
	private EmailService emailService;
	@RabbitListener(queues = MessageQueueConfig.QUEUE)
	public void listener(RegisterDTO dto) throws JsonProcessingException {
		System.out.println("EMAIL SERVIS: KORISNIK PRIHVACEN");
		met.incrementTotalResponse((Object)dto);
		this.emailService.sendEmailToUser(dto);
	}
	
//	@RabbitListener(queues = MessageQueueConfig.QUEUE2)
//	public void listener2(RegisterDTO dto) {
//		System.out.println("EMAIL SERVIS ZA APLIKACIJU FLIGHTS");
//		this.emailService.sendEmailToUser2(dto);
//	}
	
}
