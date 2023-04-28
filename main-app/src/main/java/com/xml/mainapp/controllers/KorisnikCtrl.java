package com.xml.mainapp.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.mainapp.dtos.KorisnikDTO;
import com.xml.mainapp.dtos.UpdateProfileDTO;
import com.xml.mainapp.dtos.data.OcenaSmestajaDTO;
import com.xml.mainapp.dtos.data.RezervacijaDTO;
import com.xml.mainapp.services.KorisnikService;
import com.xml.mainapp.services.RezervacijaService;

import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/korisnik")
public class KorisnikCtrl {
	
	@Autowired
	private KorisnikService korisnikService;
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@PreAuthorize("hasAnyAuthority('GUEST', 'HOST')")
	@GetMapping("/{id}")
	public ResponseEntity<KorisnikDTO> getProfileById(@PathVariable(name="id") Long id){
		KorisnikDTO retVal = this.korisnikService.getUserById(id);
		if(retVal == null) {
			return new ResponseEntity<KorisnikDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<KorisnikDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	@PreAuthorize("hasAnyAuthority('GUEST', 'HOST')")
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
	
	@PreAuthorize("hasAuthority('GUEST')")
	@Transactional
	@PostMapping("/makeReservation/{userId}/{smestajId}")
	public ResponseEntity<RezervacijaDTO> makeReservation(@PathVariable(value = "userId") Long userId, @PathVariable(value = "smestajId") Long smestajId, @RequestBody RezervacijaDTO r){
		RezervacijaDTO retVal = this.rezervacijaService.makeReservation(userId, smestajId, r);
		if(retVal == null) {
			return new ResponseEntity<RezervacijaDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAuthority('GUEST')")
	@Transactional
	@PutMapping("/editReservation")
	public ResponseEntity<RezervacijaDTO> editReservation(@RequestBody RezervacijaDTO dto){
		RezervacijaDTO retVal = this.rezervacijaService.editReservation(dto);
		if(retVal == null) {
			return new ResponseEntity<RezervacijaDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAuthority('GUEST')")
	@Transactional
	@PutMapping("/cancelReservation/{userId}/{rezervacijaId}")
	public ResponseEntity<RezervacijaDTO> cancelReservation(@PathVariable(value = "userId") Long userId, @PathVariable(value = "rezervacijaId") Long rezervacijaId){
		RezervacijaDTO retVal = this.rezervacijaService.cancelReservation(userId, rezervacijaId);
		if(retVal == null) {
			return new ResponseEntity<RezervacijaDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAuthority('GUEST')")
	@Transactional
	@GetMapping("/getAllReservationsByUser/{id}")
	public ResponseEntity<Collection<RezervacijaDTO>> getAllByUser(@PathVariable(value = "id") Long id){
		Collection<RezervacijaDTO> retList = this.korisnikService.getAllReservationByUserId(id);
		if(retList == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Collection<RezervacijaDTO>>(retList, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAuthority('GUEST')")
	@Transactional
	@PostMapping("/giveRatingToSmestaj/{userId}/{smestajId}")
	public ResponseEntity<?> giveRatingToSmestaj(@PathVariable(value = "userId") Long userId, @PathVariable(value = "smestajId") Long smestajId, @RequestBody OcenaSmestajaDTO ocena){
		OcenaSmestajaDTO retVal = this.korisnikService.giveRatingToSmestaj(userId, smestajId, ocena);
		if(retVal == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<OcenaSmestajaDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAuthority('GUEST')")
	@Transactional
	@GetMapping("/canGiveRating/{userId}/{smestajId}")
	public ResponseEntity<?> canGiveRating(@PathVariable(value = "userId") Long userId, @PathVariable(value = "smestajId") Long smestajId){
		boolean retVal = this.korisnikService.canGiveRating(userId, smestajId);
		return new ResponseEntity<Boolean>(retVal, HttpStatus.OK);
	}
}
