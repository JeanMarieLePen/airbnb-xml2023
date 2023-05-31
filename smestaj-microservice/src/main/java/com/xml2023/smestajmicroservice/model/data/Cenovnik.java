package com.xml2023.smestajmicroservice.model.data;

import java.io.Serializable;

import javax.persistence.Column;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.Node;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Document(collection = "cenovnik_db")
public class Cenovnik implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2589512847403223300L;


	@Id
	private String id;
	
	// true- cena za ceo smestaj, false cena po gostu
	@NotNull
	private boolean poSmestaju;
	@Positive
	private Long cena;
	@Positive
	private Long cenaPraznik;
	@Positive 
	private Long cenaVikend;
	@Positive
	private Long cenaLeto;
	//rucno definisanje Leta
	//private LocalDateTime pocetakLeta;
	//private LocalDateTime krajLeta;
	
	public String getId() {
		return id;
	}
	public Cenovnik() {
		super();
	}
	public Cenovnik(String id, @NotNull boolean poSmestaju, @Positive Long cena, @Positive Long cenaPraznik,
			@Positive Long cenaVikend, @Positive Long cenaLeto) {
		super();
		this.id = id;
		this.poSmestaju = poSmestaju;
		this.cena = cena;
		this.cenaPraznik = cenaPraznik;
		this.cenaVikend = cenaVikend;
		this.cenaLeto = cenaLeto;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isPoSmestaju() {
		return poSmestaju;
	}
	public void setPoSmestaju(boolean poSmestaju) {
		this.poSmestaju = poSmestaju;
	}
	public Long getCena() {
		return cena;
	}
	public void setCena(Long cena) {
		this.cena = cena;
	}
	public Long getCenaPraznik() {
		return cenaPraznik;
	}
	public void setCenaPraznik(Long cenaPraznik) {
		this.cenaPraznik = cenaPraznik;
	}
	public Long getCenaVikend() {
		return cenaVikend;
	}
	public void setCenaVikend(Long cenaVikend) {
		this.cenaVikend = cenaVikend;
	}
	public Long getCenaLeto() {
		return cenaLeto;
	}
	public void setCenaLeto(Long cenaLeto) {
		this.cenaLeto = cenaLeto;
	}
}
