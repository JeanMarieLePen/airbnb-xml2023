package com.example.reservationservice.model;

import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.PositiveOrZero;

@Document(collection = "guests_db")
public class Guest extends Korisnik{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5877889277853685675L;

	@PositiveOrZero
	private int brojOtkazivanja;
	
	@Nullable
	@DBRef
	private Collection<Rezervacija> rezervacije;

	@Nullable
	@DBRef
	private Collection<OcenaSmestaj> oceneSmestaja;
	
	@Nullable
	@DBRef
	private Collection<OcenaHost> oceneVlasnika;
	
	public Guest() {
		super();
	}

	

	public Guest(@PositiveOrZero int brojOtkazivanja, Collection<Rezervacija> rezervacije,
			Collection<OcenaSmestaj> oceneSmestaja, Collection<OcenaHost> oceneVlasnika) {
		super();
		this.brojOtkazivanja = brojOtkazivanja;
		this.rezervacije = rezervacije;
		this.oceneSmestaja = oceneSmestaja;
		this.oceneVlasnika = oceneVlasnika;
	}
	
	public Collection<OcenaSmestaj> getOceneSmestaja() {
		return oceneSmestaja;
	}

	public void setOceneSmestaja(Collection<OcenaSmestaj> oceneSmestaja) {
		this.oceneSmestaja = oceneSmestaja;
	}

	public Collection<OcenaHost> getOceneVlasnika() {
		return oceneVlasnika;
	}

	public void setOceneVlasnika(Collection<OcenaHost> oceneVlasnika) {
		this.oceneVlasnika = oceneVlasnika;
	}
	public int getBrojOtkazivanja() {
		return brojOtkazivanja;
	}

	public void setBrojOtkazivanja(int brojOtkazivanja) {
		this.brojOtkazivanja = brojOtkazivanja;
	}

	public Collection<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(Collection<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
}
