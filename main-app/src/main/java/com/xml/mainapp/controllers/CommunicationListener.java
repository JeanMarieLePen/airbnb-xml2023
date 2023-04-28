package com.xml.mainapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.xml.mainapp.dtos.NotificationDTO;

@Component
public class CommunicationListener {

	@Autowired
	SimpMessagingTemplate template;
	
	public void sendNotification(NotificationDTO notification) {
		template.convertAndSend("/topic/notifications", notification);
		System.out.println("NOTIFIKACIJA POSLATA NA PRIKAZ. ID SMESTAJA: " + notification.getIdSmestaja());
	}
}
