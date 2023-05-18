package com.example.reservationservice.controllers;

import java.util.Collection;

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

import com.example.reservationservice.dtos.RezervacijaDTO;
import com.example.reservationservice.services.RezervacijaService;


@RestController
@RequestMapping("/rezervacija")
public class ReservationController {

	@Autowired
	private RezervacijaService rezervacijaService;
	
	@PostMapping("/makeReservation/{userId}/{smestajId}")
	public ResponseEntity<RezervacijaDTO> makeReservation(@PathVariable(value = "userId") String userId, @PathVariable(value = "smestajId") String smestajId, @RequestBody RezervacijaDTO r){
		RezervacijaDTO retVal = this.rezervacijaService.makeReservation(userId.substring(1, userId.length() - 1), smestajId, r);
		if(retVal == null) {
			return new ResponseEntity<RezervacijaDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		}
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
	public ResponseEntity<RezervacijaDTO> cancelReservation(@PathVariable(value = "userId") String userId, @PathVariable(value = "rezervacijaId") String rezervacijaId){
		RezervacijaDTO retVal = this.rezervacijaService.cancelReservation(userId.substring(1, userId.length() - 1), rezervacijaId);
		if(retVal == null) {
			return new ResponseEntity<RezervacijaDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@GetMapping("/getAllReservationsByUserGuest/{id}")
	public ResponseEntity<Collection<RezervacijaDTO>> getAllReservationsByUserGuest(@PathVariable(value = "id") String id){
		Collection<RezervacijaDTO> retList = this.rezervacijaService.getAllReservationByUserGuestId(id.substring(1, id.length() - 1));
		if(retList == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Collection<RezervacijaDTO>>(retList, HttpStatus.OK);
		}
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
	private ResponseEntity<Collection<RezervacijaDTO>> getAllReservationsByUserHost(@PathVariable(value = "id") String id){
		Collection<RezervacijaDTO> retList = this.rezervacijaService.getAllReservationsByHostId(id.substring(1, id.length() - 1));
		if(retList == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Collection<RezervacijaDTO>>(retList, HttpStatus.OK);
		}
	}
	
	@PutMapping("/approveReservation/{ownerId}/{reservationId}")
	private ResponseEntity<?> approveReservation(@PathVariable(value = "ownerId") String ownerId, @PathVariable(value = "reservationId") String reservationId){
		RezervacijaDTO retVal = this.rezervacijaService.approveReservation(reservationId, ownerId.substring(1, ownerId.length() - 1));
		if(retVal == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@PutMapping("/rejectReservation/{ownerId}/{reservationId}")
	private ResponseEntity<?> rejectReservation(@PathVariable(value = "ownerId") String ownerId, @PathVariable(value = "reservationId") String reservationId){
		RezervacijaDTO retVal = this.rezervacijaService.rejectReservation(reservationId, ownerId.substring(1, ownerId.length() - 1));
		if(retVal == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		}
	}
	
}
