package com.app2.flights.model.user;

import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.app2.flights.model.data.Let;
import com.app2.flights.model.data.Porudzbina;

@Document(collection = "admin_db")
public class Administrator extends Korisnik{

	@DBRef
	private Collection<Let> letovi;
	
	@DBRef
	private Collection<Porudzbina> porudzbine;

	public Administrator() {
		super();
	}

	public Administrator(Collection<Let> letovi, Collection<Porudzbina> porudzbine) {
		super();
		this.letovi = letovi;
		this.porudzbine = porudzbine;
	}

	public Collection<Let> getLetovi() {
		return letovi;
	}

	public void setLetovi(Collection<Let> letovi) {
		this.letovi = letovi;
	}

	public Collection<Porudzbina> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(Collection<Porudzbina> porudzbine) {
		this.porudzbine = porudzbine;
	}

}
