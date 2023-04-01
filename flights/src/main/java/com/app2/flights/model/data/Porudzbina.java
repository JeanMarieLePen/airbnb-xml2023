package com.app2.flights.model.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.app2.flights.model.user.Korisnik;
import com.app2.flights.model.user.RegKor;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Document(collection = "porudzbina_db")
public class Porudzbina {

	@Id
	private String id;
	@DBRef
	@NotNull
	private Let let;
	
//	//ako budu rekli da treba da se modeluje i kompanija
//	@NotNull
//	private Kompanija kompanija;
	@DBRef
	@NotNull
	private RegKor kupac;
	@Positive
	private int brojKarata;
	@PositiveOrZero
	private double ukupnaCena;
	
	@Nullable
	private StatusPorudzbine status;
	
	public Porudzbina() {
		super();
	}
	
	public Porudzbina(String id, @NotNull Let let, @NotNull RegKor kupac, @Positive int brojKarata,
			@PositiveOrZero double ukupnaCena, StatusPorudzbine status) {
		super();
		this.id = id;
		this.let = let;
		this.kupac = kupac;
		this.brojKarata = brojKarata;
		this.ukupnaCena = ukupnaCena;
		this.status = status;
	}

	public StatusPorudzbine getStatus() {
		return status;
	}

	public void setStatus(StatusPorudzbine status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Let getLet() {
		return let;
	}
	public void setLet(Let let) {
		this.let = let;
	}
	public RegKor getKupac() {
		return kupac;
	}
	public void setKupac(RegKor kupac) {
		this.kupac = kupac;
	}
	public int getBrojKarata() {
		return brojKarata;
	}
	public void setBrojKarata(int brojKarata) {
		this.brojKarata = brojKarata;
	}
	public double getUkupnaCena() {
		return ukupnaCena;
	}
	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

}
