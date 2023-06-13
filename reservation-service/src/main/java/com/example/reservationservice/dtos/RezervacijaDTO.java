package com.example.reservationservice.dtos;

import java.time.LocalDateTime;

import com.example.reservationservice.model.StatusRezervacije;



public class RezervacijaDTO {
	private String adresa;
	private String id;
	private String smestaj;
	private String gost;
	private LocalDateTime odDatum;
	private LocalDateTime doDatum;
	private int brojGostiju;
	private StatusRezervacije status;
	
	
	public RezervacijaDTO() {
		super();
	}


	public RezervacijaDTO(String id, String smestaj, String gost, LocalDateTime odDatum, LocalDateTime doDatum,
			int brojGostiju, StatusRezervacije status, String adresa) {
		super();
		this.adresa = adresa;
		this.id = id;
		this.smestaj = smestaj;
		this.gost = gost;
		this.odDatum = odDatum;
		this.doDatum = doDatum;
		this.brojGostiju = brojGostiju;
		this.status = status;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
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


	public LocalDateTime getOdDatum() {
		return odDatum;
	}


	public void setOdDatum(LocalDateTime odDatum) {
		this.odDatum = odDatum;
	}


	public LocalDateTime getDoDatum() {
		return doDatum;
	}


	public void setDoDatum(LocalDateTime doDatum) {
		this.doDatum = doDatum;
	}


	public int getBrojGostiju() {
		return brojGostiju;
	}


	public void setBrojGostiju(int brojGostiju) {
		this.brojGostiju = brojGostiju;
	}


	public StatusRezervacije getStatus() {
		return status;
	}


	public void setStatus(StatusRezervacije status) {
		this.status = status;
	}

}