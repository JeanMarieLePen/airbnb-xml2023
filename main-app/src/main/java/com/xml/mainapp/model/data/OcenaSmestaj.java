package com.xml.mainapp.model.data;

import java.io.Serializable;
import java.time.LocalDate;

import com.xml.mainapp.model.users.Guest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class OcenaSmestaj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3146125669257521835L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	private Integer version;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Smestaj smestaj;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Guest gost;
	
	@PositiveOrZero
	private int ocena;
	
	@NotNull
	private LocalDate datum;

	
	public OcenaSmestaj() {
		super();
	}

	public OcenaSmestaj(Long id, Integer version, Smestaj smestaj, Guest gost, @PositiveOrZero int ocena,
			@NotNull LocalDate datum) {
		super();
		this.id = id;
		this.version = version;
		this.smestaj = smestaj;
		this.gost = gost;
		this.ocena = ocena;
		this.datum = datum;
	}

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

	public Smestaj getSmestaj() {
		return smestaj;
	}

	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
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
