package com.xml.mainapp.model.users;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Document(collection = "ocenahost_db")
public class OcenaHost implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2627107159354986096L;
	
	@Id
	private String id;
	private String vlasnik;	
	private String gost;
	@PositiveOrZero
	private int ocena;
	@NotNull
	private LocalDate datum;
	
	
	public OcenaHost() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OcenaHost(String vlasnik, String gost, @PositiveOrZero int ocena, @NotNull LocalDate datum) {
		super();
		this.vlasnik = vlasnik;
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
	public String getVlasnik() {
		return vlasnik;
	}
	public void setVlasnik(String vlasnik) {
		this.vlasnik = vlasnik;
	}
	public String getGost() {
		return gost;
	}
	public void setGost(String gost) {
		this.gost = gost;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
