package com.xml2023.smestajmicroservice.model.data;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nullable;
import javax.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Document("smestaj_db")
public class Smestaj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6956429912337266684L;

	@Id
	private String id;
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	private Integer version;
	
	@Nullable
	@DBRef
	private Host vlasnik;
	
	@Nullable
	@DBRef()
	private Adresa adresa;
	
	@Nullable
	@DBRef
	private Collection<Pogodnost> pogodnosti;
	
	@Nullable
	@ElementCollection
	private Collection<byte[]> slike;
	
	
	@Positive
	private int minGosti;
	
	@Positive
	private int maxGosti;
	
	@Nullable
	@DBRef
	private Cenovnik cenovnik;
	
	@Nullable
	@DBRef
	private Collection<Rezervacija> rezervacije;

	@Nullable
	@DBRef
	private Collection<OcenaSmestaj> listaOcena;
	
	@Nullable
	@DBRef
	private Collection<Termin> nedostupni;
	
	public Smestaj(String id, Integer version, @NotNull Host vlasnik, Adresa adresa, Collection<Pogodnost> pogodnosti,
			Collection<byte[]> slike, @Positive int minGosti, @Positive int maxGosti, Cenovnik cenovnik,
			Collection<Rezervacija> rezervacije, Collection<OcenaSmestaj> listaOcena, Collection<Termin> nedostupni) {
		super();
		this.id = id;
		this.version = version;
		this.vlasnik = vlasnik;
		this.adresa = adresa;
		this.pogodnosti = pogodnosti;
		this.slike = slike;
		this.minGosti = minGosti;
		this.maxGosti = maxGosti;
		this.cenovnik = cenovnik;
		this.rezervacije = rezervacije;
		this.listaOcena = listaOcena;
		this.nedostupni = nedostupni;
	}

	public Smestaj() {
		super();
	}

	public Collection<OcenaSmestaj> getListaOcena() {
		return listaOcena;
	}

	public void setListaOcena(Collection<OcenaSmestaj> listaOcena) {
		this.listaOcena = listaOcena;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Collection<Termin> getNedostupni() {
		return nedostupni;
	}

	public void setNedostupni(Collection<Termin> nedostupni) {
		this.nedostupni = nedostupni;
	}

	public Host getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Host vlasnik) {
		this.vlasnik = vlasnik;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public Collection<Pogodnost> getPogodnosti() {
		return pogodnosti;
	}

	public void setPogodnosti(Collection<Pogodnost> pogodnosti) {
		this.pogodnosti = pogodnosti;
	}

	public Collection<byte[]> getSlike() {
		return slike;
	}

	public void setSlike(Collection<byte[]> slike) {
		this.slike = slike;
	}

	public int getMinGosti() {
		return minGosti;
	}

	public void setMinGosti(int minGosti) {
		this.minGosti = minGosti;
	}

	public int getMaxGosti() {
		return maxGosti;
	}

	public void setMaxGosti(int maxGosti) {
		this.maxGosti = maxGosti;
	}

	public Cenovnik getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
	}

	public Collection<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(Collection<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
	
}
