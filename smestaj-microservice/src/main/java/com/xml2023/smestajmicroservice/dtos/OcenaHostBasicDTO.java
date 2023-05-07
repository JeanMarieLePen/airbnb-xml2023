package com.xml2023.smestajmicroservice.dtos;


import java.time.LocalDate;

public class OcenaHostBasicDTO {
	private String id;
	//private KorisnikBasicDTO vlasnik;
	private KorisnikBasicDTO gost;
	private LocalDate datum;
	
	
	public OcenaHostBasicDTO() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public KorisnikBasicDTO getGost() {
		return gost;
	}
	public void setGost(KorisnikBasicDTO gost) {
		this.gost = gost;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	
	
}
