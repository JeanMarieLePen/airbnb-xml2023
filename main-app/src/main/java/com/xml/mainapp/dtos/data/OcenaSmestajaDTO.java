package com.xml.mainapp.dtos.data;


import java.time.LocalDate;

import com.xml.mainapp.dtos.user.GuestDTO;
import com.xml.mainapp.dtos.user.KorisnikBasicDTO;

public class OcenaSmestajaDTO {
	private Long id;
	private SmestajBasicDTO smestaj;
	private GuestDTO gost;
	private int ocena;
	private LocalDate datum;
	
	
	public OcenaSmestajaDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SmestajBasicDTO getSmestaj() {
		return smestaj;
	}
	public void setSmestaj(SmestajBasicDTO smestaj) {
		this.smestaj = smestaj;
	}
	public GuestDTO getGost() {
		return gost;
	}
	public void setGost(GuestDTO gost) {
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
