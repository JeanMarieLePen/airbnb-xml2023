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

@Document(collection = "admin_db")
public class Administrator extends Korisnik{

	@DBRef
	private Collection<Let> letovi;
	
	@DBRef
	private Collection<Porudzbina> porudzbine;

	public Administrator() {
		super();
	}
	
	public Administrator(Korisnik k) {
		super(k);
		// TODO Auto-generated constructor stub
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
		this.porudzbine= new ArrayList<Porudzbina>();
		this.letovi= new ArrayList<Let>();
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
