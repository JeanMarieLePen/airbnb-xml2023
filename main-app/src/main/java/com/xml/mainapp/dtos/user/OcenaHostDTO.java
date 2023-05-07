package com.xml.mainapp.dtos.user;


import java.time.LocalDate;

public class OcenaHostDTO {
	private String id;
	private HostDTO vlasnik;
	private GuestDTO gost;
	private int ocena;
	private LocalDate datum;
	
	
	public OcenaHostDTO() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public HostDTO getVlasnik() {
		return vlasnik;
	}
	public void setVlasnik(HostDTO vlasnik) {
		this.vlasnik = vlasnik;
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
