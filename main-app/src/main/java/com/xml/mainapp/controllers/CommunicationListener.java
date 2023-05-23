package com.xml.mainapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.xml.mainapp.dtos.NotificationDTO;
import com.xml.mainapp.dtos.NotificationHostDTO;

@Component
public class CommunicationListener {

	@Autowired
	SimpMessagingTemplate template;
	
	public void sendNotification(NotificationDTO notification) {
		System.out.println("NOTIFIKACIJA STIGLA;");
		template.convertAndSend("/queue/notifications", notification);
		System.out.println("NOTIFIKACIJA POSLATA NA PRIKAZ. ID SMESTAJA: " + notification.getIdRezervacije());
	}
	
	public void sendNotificationToHost(NotificationHostDTO notification) {
		System.out.println("NOTIFIKACIJA ZA HOSTA STIGLA;");
		template.convertAndSend("/queue/notificationsHost", notification);
		System.out.println("NOTIFIKACIJA POSLATA NA PRIKAZ HOSTU");
	}
}
