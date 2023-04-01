package com.app2.flights.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.app2.flights.dtos.LoginDTO;
import com.app2.flights.dtos.LoginResponseDTO;
import com.app2.flights.dtos.RegisterDTO;
import com.app2.flights.services.GenerateJwt;
import com.app2.flights.services.LoginService;
import com.app2.flights.services.MyUserDetailsService;


@RestController
@RequestMapping("/login2")
public class LoginCtrl {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private GenerateJwt generateJwt;
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/test")
	public String test() {
		return "TEST APP 2";
	}
	
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
	
	
}
