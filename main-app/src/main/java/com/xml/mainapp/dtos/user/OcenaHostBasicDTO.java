package com.xml.mainapp.dtos.user;


import java.time.LocalDate;

import com.xml.mainapp.model.users.OcenaHost;

public class OcenaHostBasicDTO {
	private String id;
	private String gost;
	private String vlasnik;
	private int ocena;
	private LocalDate datum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGost() {
		return gost;
	}
	public void setGost(String gost) {
		this.gost = gost;
	}
	public String getVlasnik() {
		return vlasnik;
	}
	public void setVlasnik(String vlasnik) {
		this.vlasnik = vlasnik;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	public OcenaHostBasicDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OcenaHostBasicDTO(String gost, String vlasnik, int ocena, LocalDate datum) {
		super();
		this.gost = gost;
		this.vlasnik = vlasnik;
		this.ocena = ocena;
		this.datum = datum;
	}
	public OcenaHostBasicDTO(OcenaHost o) {
		this.id=o.getId();
		this.vlasnik=o.getVlasnik();
		this.gost=o.getGost();
		this.ocena=o.getOcena();
		this.datum=o.getDatum();
	}	
}
