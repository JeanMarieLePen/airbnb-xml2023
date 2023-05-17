package com.xml2023.smestajmicroservice.dtos;


import java.time.LocalDate;


public class OcenaSmestajaDTO {
	private String id;
	private SmestajBasicDTO smestaj;
	private String gost;
	private int ocena;
	private LocalDate datum;
	
	
	public OcenaSmestajaDTO() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SmestajBasicDTO getSmestaj() {
		return smestaj;
	}
	public void setSmestaj(SmestajBasicDTO smestaj) {
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
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	
}
