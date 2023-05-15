package com.xml.mainapp.model.data;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.xml.mainapp.model.users.Host;

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
	

	private String vlasnik;
	private Adresa adresa;
	
	private Collection<String> pogodnosti;
	
	@ElementCollection
	private Collection<byte[]> slike;
	
	@Positive
	private int minGosti;
	
	@Positive
	private int maxGosti;
	
	private Cenovnik cenovnik;
	
	@Nullable
	private Collection<Termin> nedostupni;


	public Smestaj(String id, Integer version, String vlasnik, Adresa adresa, Collection<String> pogodnosti,
			Collection<byte[]> slike, @Positive int minGosti, @Positive int maxGosti, Cenovnik cenovnik,
			Collection<Termin> nedostupni) {
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
		this.nedostupni = nedostupni;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public String getVlasnik() {
		return vlasnik;
	}


	public void setVlasnik(String vlasnik) {
		this.vlasnik = vlasnik;
	}


	public Adresa getAdresa() {
		return adresa;
	}


	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}


	public Collection<String> getPogodnosti() {
		return pogodnosti;
	}


	public void setPogodnosti(Collection<String> pogodnosti) {
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


	public Collection<Termin> getNedostupni() {
		return nedostupni;
	}


	public void setNedostupni(Collection<Termin> nedostupni) {
		this.nedostupni = nedostupni;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
