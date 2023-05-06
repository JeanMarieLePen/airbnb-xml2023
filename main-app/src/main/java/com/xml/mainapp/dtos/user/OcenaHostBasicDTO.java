package com.xml.mainapp.dtos.user;


import java.time.LocalDate;

public class OcenaHostBasicDTO {
	private Long id;
	//private KorisnikBasicDTO vlasnik;
	private KorisnikBasicDTO gost;
	private LocalDate datum;
	
	
	public OcenaHostBasicDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
