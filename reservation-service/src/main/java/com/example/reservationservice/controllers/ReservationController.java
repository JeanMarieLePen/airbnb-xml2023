package com.example.reservationservice.controllers;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservationservice.MetrikeMetode;
import com.example.reservationservice.dtos.RezervacijaDTO;
import com.example.reservationservice.services.RezervacijaService;
import com.fasterxml.jackson.core.JsonProcessingException;


@RestController
@RequestMapping("/rezervacija")
public class ReservationController {
	@Autowired MetrikeMetode met;

	@Autowired
	private RezervacijaService rezervacijaService;
	
	@PostMapping("/makeReservation/{userId}/{smestajId}")
	public ResponseEntity<RezervacijaDTO> makeReservation(@PathVariable(value = "userId") String userId, @PathVariable(value = "smestajId") 
	String smestajId, @RequestBody RezervacijaDTO r) throws JsonProcessingException{
		RezervacijaDTO retVal;
		ResponseEntity<RezervacijaDTO> resp= new ResponseEntity<RezervacijaDTO>(HttpStatus.NO_CONTENT);
		try {
			retVal = this.rezervacijaService.makeReservationSaga(userId.substring(1, userId.length() - 1), smestajId, r);
			if(retVal == null) {
				resp = new ResponseEntity<RezervacijaDTO>(HttpStatus.NO_CONTENT);
			}else {
				resp = new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp=new ResponseEntity<RezervacijaDTO>(HttpStatus.NO_CONTENT);
			met.incrementTotalResponse((Object)resp, "/makeReservation", "POST", resp.getStatusCode().value());
			return resp;
		}
		met.incrementTotalResponse((Object)resp, "/makeReservation", "POST", resp.getStatusCode().value());
		return resp;
	}
	
//	@PutMapping("/editReservation")
//	public ResponseEntity<RezervacijaDTO> editReservation(@RequestBody RezervacijaDTO dto){
//		RezervacijaDTO retVal = this.rezervacijaService.editReservation(dto);
//		if(retVal == null) {
//			return new ResponseEntity<RezervacijaDTO>(HttpStatus.NO_CONTENT);
//		}else {
//			return new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
//		}
//	}
	
	@PutMapping("/cancelReservation/{userId}/{rezervacijaId}")
	public ResponseEntity<RezervacijaDTO> cancelReservation(@PathVariable(value = "userId") String userId, 
			@PathVariable(value = "rezervacijaId") String rezervacijaId) throws JsonProcessingException{
		RezervacijaDTO retVal = this.rezervacijaService.cancelReservation(userId.substring(1, userId.length() - 1), rezervacijaId);
		ResponseEntity<RezervacijaDTO> resp= new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		if(retVal == null) {
			resp =  new ResponseEntity<RezervacijaDTO>(HttpStatus.NO_CONTENT);
		}else {
			resp =  new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		}
		met.incrementTotalResponse((Object)resp, "/cancelReservation", "PUT", resp.getStatusCode().value());
		return resp;
	}
	
	@PutMapping("/cancelReservationByHost/{hostId}/{rezervacijaId}")
	public ResponseEntity<RezervacijaDTO> cancelReservationByHost(@PathVariable(value = "hostId") String hostId, 
			@PathVariable(value = "rezervacijaId") String rezervacijaId) throws JsonProcessingException{
		RezervacijaDTO retVal = this.rezervacijaService.cancelHost(hostId.substring(1, hostId.length() - 1), rezervacijaId);
		ResponseEntity<RezervacijaDTO> resp= new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		if(retVal == null) {
			resp =  new ResponseEntity<RezervacijaDTO>(HttpStatus.NO_CONTENT);
		}else {
			resp = new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		}
		met.incrementTotalResponse((Object)resp, "/cancelReservationByHost", "PUT", resp.getStatusCode().value());
		return resp;
	}
	
	@GetMapping("/getAllReservationsByUserGuest/{id}")
	public ResponseEntity<Collection<RezervacijaDTO>> getAllReservationsByUserGuest(@PathVariable(value = "id") String id) throws JsonProcessingException{
		Collection<RezervacijaDTO> retList = this.rezervacijaService.getAllReservationByUserGuestId(id.substring(1, id.length() - 1));
		ResponseEntity<Collection<RezervacijaDTO>> resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		if(retList == null) {
			resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			resp = new ResponseEntity<Collection<RezervacijaDTO>>(retList, HttpStatus.OK);
		}
		met.incrementTotalResponse((Object)resp, "/getAllReservationsByUserGuest", "GET", resp.getStatusCode().value());
		return resp;
	}
	
//	@PostMapping("/giveRatingToSmestaj/{userId}/{smestajId}")
//	public ResponseEntity<?> giveRatingToSmestaj(@PathVariable(value = "userId") Long userId, @PathVariable(value = "smestajId") Long smestajId, @RequestBody OcenaSmestajaDTO ocena){
//		OcenaSmestajaDTO retVal = this.korisnikService.giveRatingToSmestaj(userId, smestajId, ocena);
//		if(retVal == null) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}else {
//			return new ResponseEntity<OcenaSmestajaDTO>(retVal, HttpStatus.OK);
//		}
//	}
	
//	@GetMapping("/canGiveRating/{userId}/{smestajId}")
//	public ResponseEntity<?> canGiveRating(@PathVariable(value = "userId") Long userId, @PathVariable(value = "smestajId") Long smestajId){
//		boolean retVal = this.korisnikService.canGiveRating(userId, smestajId);
//		return new ResponseEntity<Boolean>(retVal, HttpStatus.OK);
//	}
	
	@GetMapping("/getAllReservationsByUserHost/{id}")
	private ResponseEntity<Collection<RezervacijaDTO>> getAllReservationsByUserHost(@PathVariable(value = "id") String id) throws JsonProcessingException{
		Collection<RezervacijaDTO> retList = this.rezervacijaService.getAllReservationsByHostId(id.substring(1, id.length() - 1));
		ResponseEntity<Collection<RezervacijaDTO>> resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);

		if(retList == null) {
			resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			resp = new ResponseEntity<Collection<RezervacijaDTO>>(retList, HttpStatus.OK);
		}
		met.incrementTotalResponse((Object)resp, "/getAllReservationsByUserHost", "GET", resp.getStatusCode().value());
		return resp;
	}
	
	@PutMapping("/approveReservation/{ownerId}/{reservationId}")
	private ResponseEntity<?> approveReservation(@PathVariable(value = "ownerId") String ownerId, @PathVariable(value = "reservationId") String reservationId) 
			throws JsonProcessingException{
		RezervacijaDTO retVal = this.rezervacijaService.approveReservation(reservationId, ownerId.substring(1, ownerId.length() - 1));
		ResponseEntity<RezervacijaDTO> resp= new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		if(retVal == null) {
			resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			resp =  new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		}
		met.incrementTotalResponse((Object)resp, "/approveReservation", "PUT", resp.getStatusCode().value());
		return resp;
	}
	
	@PutMapping("/rejectReservation/{ownerId}/{reservationId}")
	private ResponseEntity<?> rejectReservation(@PathVariable(value = "ownerId") String ownerId, @PathVariable(value = "reservationId") String reservationId)
			throws JsonProcessingException{
		RezervacijaDTO retVal = this.rezervacijaService.rejectReservation(reservationId, ownerId.substring(1, ownerId.length() - 1));
		ResponseEntity<RezervacijaDTO> resp= new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		if(retVal == null) {
			resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			resp = new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		}
		met.incrementTotalResponse((Object)resp, "/rejectReservation", "PUT", resp.getStatusCode().value());
		return resp;
	}
	
	//@PreAuthorize("hasAuthority('GUEST')")
	@Transactional
	@GetMapping("/canGiveRating/{userId}/{smestajId}")
	public ResponseEntity<?> canGiveRating(@PathVariable(value = "userId") String userId, @PathVariable(value = "smestajId") String smestajId) throws JsonProcessingException{
		System.out.println("Can give rating check:");
		boolean retVal = this.rezervacijaService.canGiveRating(userId.substring(1, userId.length() - 1), smestajId);
		ResponseEntity<Boolean> resp= new ResponseEntity<Boolean>(retVal, HttpStatus.OK);
		met.incrementTotalResponse((Object)resp, "/canGiveRating", "GET", resp.getStatusCode().value());
		return resp;
	}
	//@PreAuthorize("hasAuthority('GUEST')")

	@Transactional
	@GetMapping("/canGiveRatingHost/{userId}/{smestajId}")
	public ResponseEntity<?> canGiveRatingHost(@PathVariable(value = "userId") String userId, @PathVariable(value = "smestajId") String smestajId) throws JsonProcessingException{
		System.out.println("Can give rating check:");
		boolean retVal = this.rezervacijaService.canGiveRatingHost(userId.substring(1, userId.length() - 1), smestajId);
		ResponseEntity<Boolean> resp= new ResponseEntity<Boolean>(retVal, HttpStatus.OK);
		met.incrementTotalResponse((Object)resp, "/canGiveRatingHost", "GET", resp.getStatusCode().value());
		return resp;
	}
	
	@GetMapping("/getReservationById/{id}")
	public ResponseEntity<?> getReservation(@PathVariable(value = "id") String id) throws JsonProcessingException{
		RezervacijaDTO retVal = this.rezervacijaService.getRezervacijaById(id);
		ResponseEntity<RezervacijaDTO> resp=  new ResponseEntity<>(HttpStatus.NO_CONTENT);
		if(retVal == null) {
			resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			resp = new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		}
		met.incrementTotalResponse((Object)resp, "/getReservationById", "GET", resp.getStatusCode().value());
		return resp;
	}
	
}
