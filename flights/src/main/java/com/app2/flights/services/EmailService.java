package com.app2.flights.services;

import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app2.flights.model.user.Korisnik;
import com.app2.flights.model.user.RegKor;
import com.app2.flights.repositories.KorisnikRep;


@Service
public class EmailService {

	@Autowired
	private Environment env;
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private QRService qrService;
	@Autowired 
	private KorisnikRep korRep;
	
	private SimpleMailMessage mail;
	private static String host="http://localhost:8084";
//	private static String host="http://flights:8084";
	@Async
	public void sendActivationEmail(RegKor k) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(k.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Aktivacioni email");
		String body = null;
		String exptime = LocalDateTime.now().toString();
		
		String randomString = RandomStringUtils.randomAlphanumeric(40);
//		randomString += ";";
//		randomString += exptime;
		k.setActivationLink(randomString);
		korRep.save(k);
		body = "Postovani  " + k.getIme() + " " + k.getPrezime()
		+ ",\nDa biste aktivirali svoj nalog, potrebno je da kliknete na sledeci link: "
		+ host+"/login2/activateAccount" + "?id=" + k.getId() + "&secret="
		+ k.getActivationLink() + "&exptime=" + exptime;
		
		mail.setText(body); 
		javaMailSender.send(mail);
		
	}
}
