package com.xml.mainapp.services;

import java.time.LocalDateTime;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.xml.mainapp.model.users.Korisnik;
import com.xml.mainapp.repositories.KorisnikRep;

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
	private static String host="http://localhost:8082";
	
	@Async
	public void sendActivationEmail(Korisnik k) {
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
		korRep.saveAndFlush(k);
		body = "Postovani  " + k.getIme() + " " + k.getPrezime()
		+ ",\nDa biste aktivirali svoj nalog, potrebno je da kliknete na sledeci link: "
		+ host+"/login/activateAccount" + "?id=" + k.getId() + "&secret="
		+ k.getActivationLink() + "&exptime=" + exptime;
		
		mail.setText(body); 
		javaMailSender.send(mail);
		
	}
}
