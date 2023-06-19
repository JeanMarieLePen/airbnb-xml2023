package com.xml.mainapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.xml.mainapp.dtos.KorisnikDTO;
import com.xml.mainapp.dtos.LoginDTO;
import com.xml.mainapp.dtos.LoginResponseDTO;
import com.xml.mainapp.dtos.RegisterDTO;
import com.xml.mainapp.model.users.Korisnik;
import com.xml.mainapp.services.GenerateJwt;
import com.xml.mainapp.services.LoginService;
import com.xml.mainapp.services.MyUserDetailsService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/login")
public class LoginCtrl {
	
//	@Value("${server.port}") private int serverPort;
	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LoginService loginService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private GenerateJwt generateJwt;
	
	@GetMapping("/test")
	public String testEndPoint() {
		String port = environment.getProperty("local.server.port");
		System.out.println("USLUZENO NA PORTU: " + port);
		return "SUCCESS NA PORTU" + port;
	}
	
	@PreAuthorize("hasAnyAuthority('REGISTROVANI_KORISNIK')")
	@GetMapping("/roletest")
	public String roleTestEndpoint() {
		System.out.println("DEBUG STEP");
		return "SUCCESS";
	}
	
	
	//ENDPOINT KOJI PRI LOGINU KORISNIKA PROVERAVA DA LI SU KREDENCIJALI ISPRAVNI; UKOLIKO JESU, GENERISE JWT I VRACA GA KAO DEO ODGOVORA
	//UKOLIKO AUTENTIFIKACIJA NE PRODJE, VRACA GRESKU NETACNA SIFRA ILI EMAIL ADRESA
	@PostMapping("/authenticate")
	public ResponseEntity<LoginResponseDTO> authenticateUser(@RequestBody LoginDTO logDTO) throws Exception{
		
		try {
			System.out.println("EMAIL KORISNIKA KOJI POKUSAVA LOGIN: " + logDTO.getEmail() + " PASSWORD KORISNIKA: " + logDTO.getPassword());
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logDTO.getEmail(), logDTO.getPassword()));
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<LoginResponseDTO>(HttpStatus.NO_CONTENT);
		}
		
		//AKO USPESNO PRODJE AUTENTIFIKACIJU TREBA IZGENERISATI TOKEN KOJI CE DALJE
		//SLATI UZ SVAKI ZAHTEV
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(logDTO.getEmail());
		final String jwt = generateJwt.generateToken(userDetails);
		System.out.println("TOKEN KOJI SE SALJE U ODGOVORU NA ZAHTEV ZA LOGIN: " + jwt);
		return ResponseEntity.ok(new LoginResponseDTO(jwt));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerNewUser(@Validated @RequestBody RegisterDTO regDTO){
		RegisterDTO retVal = this.loginService.registerNewUser(regDTO);
		if(retVal != null) {
			return ResponseEntity.ok(retVal);
		}else {
			return (ResponseEntity<?>) ResponseEntity.noContent();
		}
	}
	
	@GetMapping("/activateAccount")
	public String activateAccount(@RequestParam(name="id") String id, @RequestParam(name="secret") String pw, @RequestParam(name="exptime") String exptime) {
		return this.loginService.activateAccount(id, pw, exptime);
	}
	
	
	//RADI CIRCUITBREAKER
	//RADI RATELIMITER
//	@CircuitBreaker(name="reservationService", fallbackMethod = "reservationServiceFallback")
	@GetMapping("/resilience")
	@Retry(name = "reservationService", fallbackMethod = "reservationServiceFallback")
//	@RateLimiter(name = "reservationService")
	public String callResilience() {
		int cnt = 1;
		System.out.println("RETRY METODA ZA RESERVATION-SERVICE. POKUSAJ: " + cnt);
		cnt = cnt + 1;
		return restTemplate.getForObject("http://localhost:5000" + "/test", String.class);
	}
		
	
	//mora da vraca isti tip podataka kao metoda anotirana sa CircuitBreaker
	public String reservationServiceFallback(Exception e) {
		System.out.println("FALLBACK METHOD ZA RESERVATION-SERVICE");
		return "FALLBACK METHOD ZA RESERVATION-SERVICE";
	}
	
}