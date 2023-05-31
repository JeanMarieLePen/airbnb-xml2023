package com.xml2023.smestajmicroservice.dtos;


import java.time.LocalDate;
import java.time.LocalDateTime;


public class OcenaSmestajaDTO {
	private String id;
	private String smestaj;
	private String gost;
	private int ocena;
	private LocalDateTime datum;
	
	
	public OcenaSmestajaDTO() {
		super();
	}


	public OcenaSmestajaDTO(String id, String smestaj, String gost, int ocena, LocalDateTime datum) {
		super();
		this.id = id;
		this.smestaj = smestaj;
		this.gost = gost;
		this.ocena = ocena;
		this.datum = datum;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getSmestaj() {
		return smestaj;
	}


	public void setSmestaj(String smestaj) {
		this.smestaj = smestaj;
	}


	public String getGost() {
		return gost;
	}


	public void setGost(String gost) {
		this.gost = gost;
	}


	public int getOcena() {
		return ocena;
	}


	public void setOcena(int ocena) {
		this.ocena = ocena;
	}


	public LocalDateTime getDatum() {
		return datum;
	}


	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}
	
}
