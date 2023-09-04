package com.xml.mainapp.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xml.mainapp.MetrikeMetode;
import com.xml.mainapp.dtos.KorisnikDTO;
import com.xml.mainapp.dtos.UpdateProfileDTO;
import com.xml.mainapp.dtos.UpdateProfileDTO2;
import com.xml.mainapp.dtos.data.OcenaSmestajaDTO;
import com.xml.mainapp.dtos.data.RezervacijaDTO;
import com.xml.mainapp.dtos.user.HostDTO;
import com.xml.mainapp.dtos.user.OcenaHostBasicDTO;
import com.xml.mainapp.services.KorisnikService;

import jakarta.ws.rs.Consumes;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/korisnik")
public class KorisnikCtrl {
	@Autowired MetrikeMetode met;
	@Autowired
	private KorisnikService korisnikService;
	
	@PreAuthorize("hasAnyAuthority('GUEST', 'HOST')")
	@GetMapping("/{id}")
	public ResponseEntity<KorisnikDTO> getProfileById(@PathVariable(name="id") String id) throws JsonProcessingException{
		KorisnikDTO retVal = this.korisnikService.getUserById(osisajId(id));
		ResponseEntity<KorisnikDTO> resp = 	new ResponseEntity<KorisnikDTO>(HttpStatus.NO_CONTENT);
		if(retVal == null) {
			resp = new ResponseEntity<KorisnikDTO>(HttpStatus.NO_CONTENT);
		}else {
			resp = new ResponseEntity<KorisnikDTO>(retVal, HttpStatus.OK);
		}
		met.incrementTotalResponse((Object)resp, "/getUser", "GET", resp.getStatusCode().value());
		return resp;
	}
	@PreAuthorize("hasAnyAuthority('GUEST', 'HOST')")
	@GetMapping("/getHostById/{id}")
	public ResponseEntity<KorisnikDTO> getHostById(@PathVariable(name="id") String id) throws JsonProcessingException{
		KorisnikDTO retVal = this.korisnikService.getUserById(osisajId(id));
		ResponseEntity<KorisnikDTO> resp = 	new ResponseEntity<KorisnikDTO>(HttpStatus.NO_CONTENT);
		if(retVal == null) {
			resp =  new ResponseEntity<KorisnikDTO>(HttpStatus.NO_CONTENT);
		}else {
			resp = new ResponseEntity<KorisnikDTO>(retVal, HttpStatus.OK);
		}
		met.incrementTotalResponse((Object)resp, "/getHostById", "GET", resp.getStatusCode().value());
		return resp;
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	@Transactional
	@PutMapping("/update")
//	@Consumes(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
//	@Consumes("application/json")
//	@Consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<KorisnikDTO> editProfileById(@RequestBody @Validated UpdateProfileDTO udto) throws JsonProcessingException{
		KorisnikDTO retVal = this.korisnikService.updateProfileById(udto.getGuestDTO().getId(), udto);
		ResponseEntity<KorisnikDTO> resp = 	new ResponseEntity<KorisnikDTO>(HttpStatus.NO_CONTENT);

		if(retVal == null) {
			resp = new ResponseEntity<KorisnikDTO>(HttpStatus.NO_CONTENT);
		}else {
			resp = new ResponseEntity<KorisnikDTO>(retVal, HttpStatus.OK);
		}
		met.incrementTotalResponse((Object)resp, "/update", "PUT", resp.getStatusCode().value());
		return resp;
	}
	@Transactional
	@PutMapping("/update2")
//	@Consumes("application/json")
	@Consumes(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
//	@Consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<KorisnikDTO> editHostProfileById(@RequestBody @Validated UpdateProfileDTO2 udto) throws JsonProcessingException{
		KorisnikDTO retVal = this.korisnikService.updateProfileById(udto.getHostDTO().getId(), udto);
		ResponseEntity<KorisnikDTO> resp = 	new ResponseEntity<KorisnikDTO>(HttpStatus.NO_CONTENT);

		if(retVal == null) {
			resp = new ResponseEntity<KorisnikDTO>(HttpStatus.NO_CONTENT);
		}else {
			resp = new ResponseEntity<KorisnikDTO>(retVal, HttpStatus.OK);
		}
		met.incrementTotalResponse((Object)resp, "/update2", "PUT", resp.getStatusCode().value());
		return resp;
		
	}
	
	@PreAuthorize("hasAuthority('GUEST')")
	@Transactional
	@GetMapping("/getAllReservationsByUser/{id}")
	public ResponseEntity<Collection<RezervacijaDTO>> getAllByUser(@PathVariable(value = "id") String id) throws JsonProcessingException{
		Collection<RezervacijaDTO> retList = this.korisnikService.getAllReservationByUserId(osisajId(id));
		ResponseEntity<Collection<RezervacijaDTO>> resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		if(retList == null) {
			resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			resp = new ResponseEntity<Collection<RezervacijaDTO>>(retList, HttpStatus.OK);
		}
		met.incrementTotalResponse((Object)resp, "/getAllReservationsByUser", "GET", resp.getStatusCode().value());
		return resp;
	}
	
	
	@PreAuthorize("hasAnyAuthority('GUEST', 'HOST')")
	@Transactional
	@DeleteMapping("/deleteAccount/{id}")
	public ResponseEntity<?> deleteAcc(@PathVariable(value = "id") String id) throws JsonProcessingException{
		KorisnikDTO retVal = this.korisnikService.deleteAcc(osisajId(id));
		ResponseEntity<?> resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		if(retVal == null) {
			resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			resp = new ResponseEntity<KorisnikDTO>(retVal, HttpStatus.OK);
		}
		met.incrementTotalResponse((Object)resp, "/deleteAccount", "DELETE", resp.getStatusCode().value());
		return resp;
	}
	
	@Transactional
	@PostMapping("/giveRatingToHost/{userId}/{hostId}")
	public ResponseEntity<?> giveRatingToHost(@PathVariable(value = "userId") String userId, @PathVariable(value = "hostId") String hostId, @RequestBody OcenaHostBasicDTO ocena) throws JsonProcessingException{
		OcenaHostBasicDTO retVal = this.korisnikService.giveRatingToHost(osisajId(userId), osisajId(hostId), ocena);
		ResponseEntity<?> resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);

		if(retVal == null) {
			resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			resp = new ResponseEntity<OcenaHostBasicDTO>(retVal, HttpStatus.OK);
		}
		met.incrementTotalResponse((Object)resp, "/giveRatingToHost", "POST", resp.getStatusCode().value());
		return resp;
	}
	
	
	private String osisajId(String id) {
		if(id.startsWith("\"")){
			return id.substring(1, id.length() - 1);
		}else 
			return id;
	}
}
