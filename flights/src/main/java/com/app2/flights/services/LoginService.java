package com.app2.flights.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app2.flights.dtos.LoginDTO;
import com.app2.flights.dtos.RegisterDTO;
import com.app2.flights.mappers.KorisnikMapper;
import com.app2.flights.model.user.Korisnik;
import com.app2.flights.model.user.RegKor;
import com.app2.flights.model.user.StatusNaloga;
import com.app2.flights.model.user.TipKorisnika;
import com.app2.flights.repositories.KorisnikRep;
import com.app2.flights.security.MessageQueueConfig;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class LoginService {

	@Autowired
	private KorisnikRep korisnikRep;
	@Autowired
	private KorisnikMapper korMapper;
	
	@Autowired
	private RabbitTemplate template;
	@Autowired
	private EmailService emailService;
	
	public LoginDTO login(LoginDTO logDTO) {
		Korisnik k = this.korisnikRep.findByEmail(logDTO.getEmail());
		if(k != null) {
			return logDTO;
		}
		return null;
	}
	
//	@CircuitBreaker(name="emailService", fallbackMethod = "emailServiceFallback")
	public RegisterDTO registerNewUser(RegisterDTO regDTO) {
		// TODO Auto-generated method stub
		Korisnik k = this.korisnikRep.findByEmail(regDTO.getEmail());
		if(k!= null) {
			return null;
		}
		Korisnik newUser = this.korMapper.fromDTOReg(regDTO);
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		newUser.setTip(TipKorisnika.REG_KOR);
		newUser.setStatus(StatusNaloga.NA_CEKANJU);
		newUser.setPassword(new String(enc.encode(regDTO.getPassword())));
		if(newUser != null) {
			RegKor regKor = new RegKor(newUser);
			this.korisnikRep.save(regKor);
			this.emailService.sendActivationEmail(regKor);
			korisnikRep.save(regKor);
			return regDTO;
		}
		return null;
	}
	//mora da vraca isti tip podataka kao metoda anotirana sa CircuitBreaker
	public RegisterDTO emailServiceFallback(Exception e) {
		System.out.println("FALLBACK METHOD");
		return null;
	}

	public String activateAccount(String id, String pw, String exptime) {
		// TODO Auto-generated method stub
		
//		Long tempId = Long.parseLong(id);
		String tempId = id;
		LocalDateTime temp = LocalDateTime.now();
		LocalDateTime exp = LocalDateTime.parse(exptime);
		Duration duration = Duration.between(exp, temp);
		long minutes = duration.toMinutes();
		Korisnik k = korisnikRep.findById(tempId).orElse(null);
		if(!k.getActivationLink().equals(pw)) {
			return "<html><h4>Link za aktivaciju je neispravan. Ponovo kliknite na link iz mejla.</h4></html>";
		}
		if(minutes > 3) {
			return "<html><p>Aktivacioni link je istekao(period vazenja 30 minuta). Nije moguce aktiviranje naloga.</p></html>";
		}
		
		if(k.getStatus().equals(StatusNaloga.NA_CEKANJU)) {
			k.setStatus(StatusNaloga.AKTIVAN);
		}try {
			this.korisnikRep.save(k);
		}catch(Exception e) {
			throw new DataIntegrityViolationException("Greska pri cuvanju!");
		}
		return "<html><p>NALOG USPESNO AKTIVIRAN. ULOGUJTE SE</p></html>";
	}
	
}
