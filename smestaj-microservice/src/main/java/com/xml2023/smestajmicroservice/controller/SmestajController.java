package com.xml2023.smestajmicroservice.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xml2023.smestajmicroservice.dtos.PretragaDTO;
import com.xml2023.smestajmicroservice.dtos.SmestajDTO;
import com.xml2023.smestajmicroservice.dtos.SmestajPretragaDTO;
import com.xml2023.smestajmicroservice.services.PretragaService;
import com.xml2023.smestajmicroservice.services.SmestajService;


@RestController
@RequestMapping("/smestaj")
public class SmestajController {

	@Autowired
	private SmestajService smestajService;
	@Autowired PretragaService pServ;
	
	@PostMapping("/create")
	public ResponseEntity<SmestajDTO> createNew(@RequestBody SmestajDTO smestaj){
		SmestajDTO retVal = this.smestajService.createNew(smestaj);
		if(retVal == null) {
			return new ResponseEntity<SmestajDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<SmestajDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@PutMapping("/edit/{smestajId}/{vlasnikId}")
	public ResponseEntity<SmestajDTO> editSmestaj(@RequestBody SmestajDTO smestaj, @PathVariable(value = "smestajId") String smestajId, @PathVariable(value = "vlasnikId") String vlasnikId){
		SmestajDTO retVal = this.smestajService.editSmestaj(smestaj, smestajId, vlasnikId.substring(1, vlasnikId.length()-1));
		if(retVal == null) {
			return new ResponseEntity<SmestajDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<SmestajDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/remove/{smestajId}/{vlasnikId}")
	public ResponseEntity<SmestajDTO> removeSmestaj(@PathVariable(value = "smestajId") String smestajId, @PathVariable(value = "vlasnikId") String vlasnikId){
		SmestajDTO retVal = this.smestajService.removeSmestaj(smestajId, vlasnikId.substring(1, vlasnikId.length()-1));
		if(retVal == null) {
			return new ResponseEntity<SmestajDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<SmestajDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@GetMapping("/getByIdSmestaja/{id}")
	public ResponseEntity<SmestajDTO> getByIdSmestaja(@PathVariable(value = "id") String smestajId){
		SmestajDTO retVal = this.smestajService.getByIdSmestaja(smestajId);
		if(retVal == null) {
			return new ResponseEntity<SmestajDTO>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<SmestajDTO>(retVal, HttpStatus.OK);
		}
	}
	
	@GetMapping("/getAllSmestaj")
	public ResponseEntity<Collection<SmestajDTO>> getAllSmestaj(){
		Collection<SmestajDTO> retList = this.smestajService.getAll();
		if(retList == null)
			return new ResponseEntity<Collection<SmestajDTO>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Collection<SmestajDTO>>(retList, HttpStatus.OK);
	}
	@GetMapping("/getAllPogodnosti")
	public ResponseEntity<Collection<String>> getAllPogodnosti(){
		Collection<String> retList = this.smestajService.getAllPogodnosti();
		if(retList == null)
			return new ResponseEntity<Collection<String>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Collection<String>>(retList, HttpStatus.OK);
	}
	
	@PostMapping("/pretraga")
	public ResponseEntity<Collection<SmestajPretragaDTO>> pretraga(@RequestBody PretragaDTO dto){
		System.out.println("Pretraga");
		Collection<SmestajPretragaDTO> lista=pServ.pretraga(dto);
		return new ResponseEntity<Collection<SmestajPretragaDTO>>(lista,HttpStatus.OK);
	}
}
