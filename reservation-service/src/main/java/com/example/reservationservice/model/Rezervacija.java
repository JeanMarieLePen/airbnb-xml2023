package com.example.reservationservice.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Document(collection = "rezervacije_db")
public class Rezervacija implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7006531226274487692L;

	@Id
	private String id;
	
	
	private String smestaj;	
	private String gost;
	
	@NotNull
	private LocalDateTime odDatum;
	@NotNull
	private LocalDateTime doDatum;
	@PositiveOrZero
	private int brojGostiju;
	@Enumerated
	private StatusRezervacije status;
	private double cena;
	
	public Rezervacija() {
		super();
	}

	public Rezervacija(String id, String smestaj, String gost, @NotNull LocalDateTime odDatum,
			@NotNull LocalDateTime doDatum, @PositiveOrZero int brojGostiju, StatusRezervacije status, double cena) {
		super();
		this.id = id;
		this.smestaj = smestaj;
		this.gost = gost;
		this.odDatum = odDatum;
		this.doDatum = doDatum;
		this.brojGostiju = brojGostiju;
		this.status = status;
		this.cena=cena;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	
}
