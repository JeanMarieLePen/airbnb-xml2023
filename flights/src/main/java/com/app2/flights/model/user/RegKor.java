package com.app2.flights.model.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.app2.flights.model.data.Porudzbina;

@Document(collection = "regkor_db")
public class RegKor extends Korisnik{

	//lista porudzbina/karata
	@DBRef
	private Collection<Porudzbina> porudzbine;

	
	public RegKor() {
		super();
	}

	public RegKor(Collection<Porudzbina> porudzbine) {
		super();
		this.porudzbine = porudzbine;
	}

	public RegKor(Korisnik k) {
		super();
		this.setId(k.getId());
		this.setIme(k.getIme());;
		this.setPrezime(k.getPrezime());
		this.setEmail(k.getEmail());
		this.setUsername(k.getUsername());
		this.setPassword(k.getPassword());
		this.setAdresa(k.getAdresa());
		this.setTip(k.getTip());
		this.setStatus(k.getStatus());
		this.porudzbine=new ArrayList<Porudzbina>();
	}

	public Collection<Porudzbina> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(Collection<Porudzbina> porudzbine) {
		this.porudzbine = porudzbine;
	}
	
}
