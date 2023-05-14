package com.xml.mainapp.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.xml.mainapp.dtos.KorisnikDTO;
import com.xml.mainapp.dtos.LoginDTO;
import com.xml.mainapp.dtos.RegisterDTO;
import com.xml.mainapp.mappers.KorisnikMapper;
import com.xml.mainapp.model.data.Adresa;
import com.xml.mainapp.model.data.OcenaSmestaj;
import com.xml.mainapp.model.data.Rezervacija;
import com.xml.mainapp.model.data.Smestaj;
import com.xml.mainapp.model.users.Guest;
import com.xml.mainapp.model.users.Host;
import com.xml.mainapp.model.users.Korisnik;
import com.xml.mainapp.model.users.OcenaHost;
import com.xml.mainapp.model.users.StatusNaloga;
import com.xml.mainapp.model.users.TipKorisnika;
import com.xml.mainapp.repositories.AdresaRep;
import com.xml.mainapp.repositories.GuestRepository;
import com.xml.mainapp.repositories.HostRepository;
import com.xml.mainapp.repositories.KorisnikRep;
import com.xml.mainapp.security.MessageQueueConfig;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class LoginService {

	@Autowired
	private KorisnikRep korisnikRep;
	@Autowired
	private KorisnikMapper korMapper;
	
	
	@Autowired
	private AdresaRep adrRep;
	
	@Autowired
	private RabbitTemplate template;
	
	@Autowired 
	private GuestRepository guestRep;
	@Autowired 
	private HostRepository hostRep;
	
	public LoginDTO login(LoginDTO logDTO) {
		Korisnik k = this.korisnikRep.findByEmail(logDTO.getEmail());
		if(k != null) {
			return logDTO;
		}
		return null;
	}
	
	@CircuitBreaker(name="emailService", fallbackMethod = "emailServiceFallback")
	public RegisterDTO registerNewUser(RegisterDTO regDTO) {
		// TODO Auto-generated method stub
		Korisnik k = this.korisnikRep.findByEmail(regDTO.getEmail());
		if(k!= null) {
			return null;
		}
		Korisnik newUser = this.korMapper.fromDTOReg(regDTO);
		Adresa a = new Adresa();
		a.setAdresa(regDTO.getAdresa().getAdresa());
		a.setLat(regDTO.getAdresa().getLat());
		a.setLng(regDTO.getAdresa().getLng());
		adrRep.save(a);
		newUser.setAdresa(a);
		if(newUser != null) {
			BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
			newUser.setTipKorisnika(regDTO.getTipKorisnika());
			newUser.setStatusNaloga(StatusNaloga.NA_CEKANJU);
			newUser.setLozinka(new String(enc.encode(regDTO.getLozinka())));
//			newUser.setAdresa(a);
			korisnikRep.save(newUser);
			
			if(newUser.getTipKorisnika().equals(TipKorisnika.HOST)) {
				Host h = new Host();
				h.setId(newUser.getId());
				h.setRezAutomatski(false);
				h.setIstaknuti(false);
				h.setOcene(new ArrayList<OcenaHost>());
				h.setSmestajList(new ArrayList<Smestaj>());
				hostRep.save(h);
			}
			if(newUser.getTipKorisnika().equals(TipKorisnika.GUEST)) {
				Guest g = new Guest();
				g.setId(newUser.getId());
				g.setBrojOtkazivanja(0);
				g.setRezervacije(new ArrayList<Rezervacija>());
				g.setOceneSmestaja(new ArrayList<OcenaSmestaj>());
				g.setOceneVlasnika(new ArrayList<OcenaHost>());
				guestRep.save(g);
			}
			
			this.template.convertAndSend(MessageQueueConfig.EXCHANGE, MessageQueueConfig.ROUTING_KEY, regDTO);
			System.out.println("POSLATO NA QUEUE");
			
//			this.emailService.sendActivationEmail(newUser);
//			korisnikRep.saveAndFlush(newUser);
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
		
		if(k.getStatusNaloga().equals(StatusNaloga.NA_CEKANJU)) {
			k.setStatusNaloga(StatusNaloga.AKTIVAN);
		}try {
			this.korisnikRep.save(k);
		}catch(Exception e) {
			throw new DataIntegrityViolationException("Greska pri cuvanju!");
		}
		return "<html><p>NALOG USPESNO AKTIVIRAN. ULOGUJTE SE</p></html>";
	}
	
}
