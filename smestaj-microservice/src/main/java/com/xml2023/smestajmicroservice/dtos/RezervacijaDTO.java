package com.xml2023.smestajmicroservice.dtos;

import java.time.LocalDateTime;

import com.xml2023.smestajmicroservice.model.data.StatusRezervacije;


public class RezervacijaDTO {
	private String id;
	private SmestajDTO smestaj;
	private KorisnikBasicDTO gost;
	private LocalDateTime odDatum;
	private LocalDateTime doDatum;
	private int brojGostiju;
	private StatusRezervacije status;
	
	
	public RezervacijaDTO() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SmestajDTO getSmestaj() {
		return smestaj;
	}
	public void setSmestaj(SmestajDTO smestajDTO) {
		this.smestaj = smestajDTO;
	}
	public KorisnikBasicDTO getGost() {
		return gost;
	}
	public void setGost(KorisnikBasicDTO gost) {
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
