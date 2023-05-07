package com.xml.mainapp.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.mainapp.dtos.data.RezervacijaDTO;
import com.xml.mainapp.dtos.user.HostDTO;
import com.xml.mainapp.services.HostService;

@RestController
@RequestMapping("/host")
public class HostController {

	@Autowired
	private HostService hostService;
	
	@GetMapping("/getOwnerById/{id}")
	private ResponseEntity<?> getHostById(@PathVariable(value = "id") Long id){
		HostDTO retVal = this.hostService.findHostById(id);
		if(retVal == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<HostDTO>(retVal, HttpStatus.OK);
		}
	}
	@GetMapping("/getAllReservationsByUser/{id}")
	private ResponseEntity<Collection<RezervacijaDTO>> getAllByUser(@PathVariable(value = "id") Long id){
		Collection<RezervacijaDTO> retList = this.hostService.getAllReservationsByHostId(id);
		if(retList == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Collection<RezervacijaDTO>>(retList, HttpStatus.OK);
		}
	}
	@PutMapping("/approveReservation/{ownerId}/{reservationId}")
	private ResponseEntity<?> approveReservation(@PathVariable(value = "ownerId") Long ownerId, @PathVariable(value = "reservationId") Long reservationId){
		RezervacijaDTO retVal = this.hostService.approveReservation(reservationId, ownerId);
		if(retVal == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@PutMapping("/rejectReservation/{ownerId}/{reservationId}")
	private ResponseEntity<?> rejectReservation(@PathVariable(value = "ownerId") Long ownerId, @PathVariable(value = "reservationId") Long reservationId){
		RezervacijaDTO retVal = this.hostService.rejectReservation(reservationId, ownerId);
		if(retVal == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<RezervacijaDTO>(retVal, HttpStatus.OK);
		}
	}
	
}
