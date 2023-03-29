package com.app2.flights.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app2.flights.dtos.LetDTO;
import com.app2.flights.dtos.LetDTOSimple;
import com.app2.flights.services.LetService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/let")
public class LetCtrl {

	@Autowired
	private LetService letService;
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<LetDTOSimple> getLetById(@PathVariable(value = "id") String id){
		LetDTOSimple retVal = this.letService.getLetById(id);
		if(retVal == null) {
			return new ResponseEntity<LetDTOSimple>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<LetDTOSimple>(retVal, HttpStatus.OK);
		}
	}
	
	@GetMapping("/{lokOd}/{lokDo}")
	public ResponseEntity<List<LetDTOSimple>> findLetovi(@PathVariable String lokOd,@PathVariable String lokDo,
			@RequestParam @DateTimeFormat(pattern="dd-MM-yyyy'T'HH:mm:ss") LocalDateTime datumIVreme,
			@RequestParam int brojPutnika)
	{
		List<LetDTOSimple> letovi = letService.findLetovi(lokOd, lokDo, datumIVreme, brojPutnika);
		
		if(letovi.isEmpty()) {
			return new ResponseEntity<List<LetDTOSimple>>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<LetDTOSimple>>(letovi,HttpStatus.OK);
		}
	}
	
	@PostMapping("/addNew")
	public ResponseEntity<LetDTO> addNew(@Validated @RequestBody LetDTO l){
		LetDTO retVal = letService.addNew(l);
		if(retVal == null) {
			return new ResponseEntity<LetDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<LetDTO>(HttpStatus.OK);
		}
	}
	
	@PutMapping("/editLet/{id}")
	public ResponseEntity<LetDTO> editLet(@Validated @RequestBody LetDTO l){
		LetDTO retVal = letService.editLet(l);
		if(retVal == null) {
			return new ResponseEntity<LetDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<LetDTO>(retVal, HttpStatus.OK);
		}
	}
	

	
	
	
	
	
}
