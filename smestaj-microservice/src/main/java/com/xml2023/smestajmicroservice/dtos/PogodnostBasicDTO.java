package com.xml2023.smestajmicroservice.dtos;


public class PogodnostBasicDTO {
	
	private String id;
	private String naziv;
	
	public PogodnostBasicDTO() {
		super();
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}