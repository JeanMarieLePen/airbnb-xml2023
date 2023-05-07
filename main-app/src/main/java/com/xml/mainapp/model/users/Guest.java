package com.xml.mainapp.model.users;

import java.util.Collection;

import com.xml.mainapp.model.data.OcenaSmestaj;
import com.xml.mainapp.model.data.Rezervacija;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Guest extends Korisnik{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5877889277853685675L;

	@PositiveOrZero
	private int brojOtkazivanja;
	
	@Nullable
	@OneToMany(cascade = CascadeType.ALL, mappedBy="gost")
	private Collection<Rezervacija> rezervacije;

	@Nullable
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "gost")
	private Collection<OcenaSmestaj> oceneSmestaja;
	
	@Nullable
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "gost")
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
