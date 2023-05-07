package com.example.reservationservice.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Document(collection = "ocenasmestaj_db")
public class OcenaSmestaj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3146125669257521835L;
	@Id
	private String id;
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	private Integer version;
	
	@DBRef
	private Smestaj smestaj;
	
	@DBRef
	private Guest gost;
	
	@PositiveOrZero
	private int ocena;
	
	@NotNull
	private LocalDate datum;

	
	public OcenaSmestaj() {
		super();
	}

	public OcenaSmestaj(String id, Integer version, Smestaj smestaj, Guest gost, @PositiveOrZero int ocena,
			@NotNull LocalDate datum) {
		super();
		this.id = id;
		this.version = version;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Smestaj getSmestaj() {
		return smestaj;
	}

	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}

	public Guest getGost() {
		return gost;
	}

	public void setGost(Guest gost) {
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
