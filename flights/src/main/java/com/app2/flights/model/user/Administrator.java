package com.app2.flights.model.user;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.app2.flights.model.data.Adresa;
import com.app2.flights.model.data.Let;
import com.app2.flights.model.data.Porudzbina;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class Administrator extends Korisnik{

	
	private Collection<String> letovi;
	
	@DBRef
	private Collection<String> porudzbine;

	public Administrator() {
		super();
	}
	
	public Administrator(Korisnik k) {
		super(k);
	}

	public Administrator( @Length(min = 2, max = 100) String ime, @Length(min = 2, max = 100) String prezime,
			@Email @Length(min = 2, max = 50) String email, String username, String password, @NotNull Adresa adresa,
			@NotNull TipKorisnika tip, @NotNull StatusNaloga status) {
		super();
		this.setIme(ime);
		this.setPrezime(prezime);
		this.setEmail(email);
		this.setPassword(password);
		this.setUsername(username);
		this.setAdresa(adresa);
		this.setTip(TipKorisnika.ADMINISTRATOR);
		this.setStatus(StatusNaloga.AKTIVAN);
		this.porudzbine= new ArrayList<String>();
		this.letovi= new ArrayList<String>();
	}

	public Administrator(Collection<String> letovi, Collection<String> porudzbine) {
		super();
		this.letovi = letovi;
		this.porudzbine = porudzbine;
	}

	public Collection<String> getLetovi() {
		return letovi;
	}

	public void setLetovi(Collection<String> letovi) {
		this.letovi = letovi;
	}

	public Collection<String> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(Collection<String> porudzbine) {
		this.porudzbine = porudzbine;
	}
	
}
