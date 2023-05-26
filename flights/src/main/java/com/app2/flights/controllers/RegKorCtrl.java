package com.app2.flights.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app2.flights.dtos.KorisnikDTO;
import com.app2.flights.dtos.LetDTO;
import com.app2.flights.dtos.PorudzbinaDTO;
import com.app2.flights.dtos.PorudzbinaDTOnova;
import com.app2.flights.dtos.RegKorDTO;
import com.app2.flights.dtos.TokenCreateDTO;
import com.app2.flights.dtos.TokenPrikazDTO;
import com.app2.flights.dtos.UpdateProfileDTO;
import com.app2.flights.model.data.Let;
import com.app2.flights.services.KorisnikService;
import com.app2.flights.services.LetService;
import com.app2.flights.services.PorudzbinaService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/regkor")
public class RegKorCtrl {

	@Autowired
	private PorudzbinaService porudzbinaService;
	@Autowired
	private KorisnikService korisnikService;
	@GetMapping("/test")
	private String test() {
		return "TEST";
	}
	
	@PostMapping("/generateToken/{id}")
	public ResponseEntity<?> generateToken(@Validated @RequestBody TokenCreateDTO t, @PathVariable(value = "id") String idKorisnika){
		TokenPrikazDTO retVal = this.korisnikService.generateToken(t, idKorisnika.substring(1, idKorisnika.length() - 1));
		if(retVal == null) {
			return new ResponseEntity<TokenCreateDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<TokenPrikazDTO>(retVal, HttpStatus.OK);
		}
	}
	@GetMapping("/checkToken/{id}")
	public ResponseEntity<?> checkToken(@PathVariable(value = "id") String idKorisnika){
		TokenPrikazDTO retVal = this.korisnikService.checkToken(idKorisnika.substring(1, idKorisnika.length() - 1));
		if(retVal == null) {
			return new ResponseEntity<TokenPrikazDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<TokenPrikazDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@PostMapping("/reservationWithToken")
	public ResponseEntity<PorudzbinaDTO> reservation2(@Validated @RequestBody PorudzbinaDTOnova p){
		p.setKupac(p.getKupac().substring(1, p.getKupac().length() - 1));
		//System.out.println(p.toString());

		PorudzbinaDTO retVal = porudzbinaService.novaRez(p); //porudzbinaService.reservation(p);
		if(retVal == null) {
			return new ResponseEntity<PorudzbinaDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<PorudzbinaDTO>(retVal, HttpStatus.OK);
		}
	}
	@PostMapping("/reservation")
	public ResponseEntity<PorudzbinaDTO> reservation(@Validated @RequestBody PorudzbinaDTOnova p){
		p.setKupac(p.getKupac().substring(1, p.getKupac().length() - 1));
		//System.out.println(p.toString());

		PorudzbinaDTO retVal = porudzbinaService.novaRez(p); //porudzbinaService.reservation(p);
		if(retVal == null) {
			return new ResponseEntity<PorudzbinaDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<PorudzbinaDTO>(retVal, HttpStatus.OK);
		}
	}
	@PutMapping("/cancelReservation/{idK}/{idP}")
	public ResponseEntity<PorudzbinaDTO> cancel(@PathVariable(value = "idK") String idK, @PathVariable(value = "idP") String idP){
		PorudzbinaDTO retVal = porudzbinaService.cancelReservation(idK.substring(1, idK.length() - 1), idP);
		if(retVal == null) {
			return new ResponseEntity<PorudzbinaDTO>(HttpStatus.NO_CONTENT); 
		}else {
			return new ResponseEntity<PorudzbinaDTO>(retVal, HttpStatus.OK);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<KorisnikDTO> getProfile(@PathVariable(value = "id") String id){
		KorisnikDTO retVal = korisnikService.getProfile(id.substring(1, id.length() - 1));
		if(retVal == null) {
			return new ResponseEntity<KorisnikDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<KorisnikDTO>(retVal, HttpStatus.OK);
		}
	}
	@Transactional
	@PutMapping("/update")
	public ResponseEntity<KorisnikDTO> editProfileById(@RequestBody UpdateProfileDTO udto){
		KorisnikDTO retVal = this.korisnikService.updateProfileById(udto.getKorisnikDTO().getId(), udto);
		if(retVal == null) {
			return new ResponseEntity<KorisnikDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<KorisnikDTO>(retVal, HttpStatus.OK);
		}
	}
	@GetMapping("/getPorudzbineByUser/{id}")
	public ResponseEntity<List<PorudzbinaDTO>> getAllByUser(@PathVariable(value = "id") String id){
		System.out.println("KOR_ID : "+id);
		List<PorudzbinaDTO> retVal = korisnikService.getPorudzbineByUser(id.substring(1, id.length() - 1));
		if(retVal == null) {
			return new ResponseEntity<List<PorudzbinaDTO>>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<PorudzbinaDTO>>(retVal, HttpStatus.OK);
		}
	}
	@DeleteMapping("/deleteAccount/{id}")
	public ResponseEntity<?> deleteAcc(@PathVariable(value = "id") String id){
		KorisnikDTO retVal = this.korisnikService.deleteAcc(id.substring(1, id.length() - 1));
		if(retVal == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<KorisnikDTO>(retVal, HttpStatus.OK);
		}
	}
}
