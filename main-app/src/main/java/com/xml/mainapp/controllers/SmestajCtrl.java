package com.xml.mainapp.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.mainapp.dtos.data.PogodnostDTO;
import com.xml.mainapp.dtos.data.SmestajDTO;
import com.xml.mainapp.services.SmestajService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/smestaj")
public class SmestajCtrl {

	@Autowired
	private SmestajService smestajService;
	
	@PreAuthorize("hasAnyAuthority('GUEST', 'HOST')")
//	@Transactional
	@PostMapping("/create")
	public ResponseEntity<SmestajDTO> createNew(@RequestBody SmestajDTO smestaj){
		SmestajDTO retVal = this.smestajService.createNew(smestaj);
		if(retVal == null) {
			return new ResponseEntity<SmestajDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<SmestajDTO>(retVal, HttpStatus.NO_CONTENT);
		}
	}
	
	@PreAuthorize("hasAnyAuthority('GUEST', 'HOST')")
//	@Transactional
	@PutMapping("/edit/{smestajId}/{vlasnikId}")
	public ResponseEntity<SmestajDTO> editSmestaj(@RequestBody SmestajDTO smestaj, @PathVariable(value = "smestajId") Long smestajId, @PathVariable(value = "vlasnikId") Long vlasnikId){
		SmestajDTO retVal = this.smestajService.editSmestaj(smestaj, smestajId, vlasnikId);
		if(retVal == null) {
			return new ResponseEntity<SmestajDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<SmestajDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAnyAuthority('GUEST', 'HOST')")
//	@Transactional
	@DeleteMapping("/remove/{smestajId}/{vlasnikId}")
	public ResponseEntity<SmestajDTO> removeSmestaj(@PathVariable(value = "smestajId") Long smestajId, @PathVariable(value = "vlasnikId") Long vlasnikId){
		SmestajDTO retVal = this.smestajService.removeSmestaj(smestajId, vlasnikId);
		if(retVal == null) {
			return new ResponseEntity<SmestajDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<SmestajDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAnyAuthority('GUEST', 'HOST')")
//	@Transactional
	@GetMapping("/getByIdSmestaja/{id}")
	public ResponseEntity<SmestajDTO> getByIdSmestaja(@PathVariable(value = "id") Long smestajId){
		SmestajDTO retVal = this.smestajService.getByIdSmestaja(smestajId);
		if(retVal == null) {
			return new ResponseEntity<SmestajDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<SmestajDTO>(retVal, HttpStatus.OK);
		}
	}
	@PreAuthorize("hasAnyAuthority('GUEST', 'HOST')")
//	@Transactional
	@GetMapping("/getAllSmestaj")
	public ResponseEntity<Collection<SmestajDTO>> getAllSmestaj(){
		Collection<SmestajDTO> retList = this.smestajService.getAll();
		if(retList == null)
			return new ResponseEntity<Collection<SmestajDTO>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Collection<SmestajDTO>>(retList, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('GUEST', 'HOST')")
	@GetMapping("/getAllPogodnosti")
	public ResponseEntity<Collection<PogodnostDTO>> getAllPogodnosti(){
		Collection<PogodnostDTO> retList = this.smestajService.getAllPogodnosti();
		if(retList == null)
			return new ResponseEntity<Collection<PogodnostDTO>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Collection<PogodnostDTO>>(retList, HttpStatus.OK);
	}
}
