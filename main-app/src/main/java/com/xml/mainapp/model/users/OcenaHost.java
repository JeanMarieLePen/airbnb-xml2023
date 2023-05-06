package com.xml.mainapp.model.users;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class OcenaHost implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2627107159354986096L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	private Integer version;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Host vlasnik;
	@ManyToOne(fetch = FetchType.LAZY)
	private Guest gost;
	@PositiveOrZero
	private int ocena;
	@NotNull
	private LocalDate datum;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Host getVlasnik() {
		return vlasnik;
	}
	public void setVlasnik(Host vlasnik) {
		this.vlasnik = vlasnik;
	}
	public Guest getGost() {
		return gost;
	}
	public void setGost(Guest gost) {
		this.gost = gost;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}		
}
