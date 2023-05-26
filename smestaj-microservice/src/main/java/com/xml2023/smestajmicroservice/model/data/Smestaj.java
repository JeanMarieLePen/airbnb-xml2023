package com.xml2023.smestajmicroservice.model.data;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import jakarta.annotation.Nullable;
import javax.persistence.*;
import jakarta.validation.constraints.Positive;

@Node
@Document("smestaj_db")
public class Smestaj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6956429912337266684L;

	@Id
	@org.springframework.data.neo4j.core.schema.Id
	private String id;

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
	
	@Relationship(type="Nedostupni_termini", direction = Direction.OUTGOING)
	@Nullable
	private Collection<Termin> nedostupni;


	public Smestaj( String vlasnik, Adresa adresa, Collection<String> pogodnosti,
			Collection<byte[]> slike, @Positive int minGosti, @Positive int maxGosti, Cenovnik cenovnik,
			Collection<Termin> nedostupni) {
		super();
		this.vlasnik = vlasnik;
		this.adresa = adresa;
		this.pogodnosti = pogodnosti;
		this.slike = slike;
		this.minGosti = minGosti;
		this.maxGosti = maxGosti;
		this.cenovnik = cenovnik;
		this.nedostupni = nedostupni;
	}


	public Smestaj() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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
