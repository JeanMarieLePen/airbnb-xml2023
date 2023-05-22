package com.xml2023.smestajmicroservice.model.data;

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
	private String smestaj;
	private String gost;
	
	@PositiveOrZero
	private int ocena;
	
	@NotNull
	private LocalDate datum;

	
	public OcenaSmestaj() {
		super();
	}


	public OcenaSmestaj(String smestaj, String gost, @PositiveOrZero int ocena, @NotNull LocalDate datum) {
		super();
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


	public LocalDate getDatum() {
		return datum;
	}


	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
