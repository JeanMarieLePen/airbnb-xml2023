package com.xml.mainapp.model.data;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Cenovnik implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2589512847403223300L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	private Integer version;
	
	// true- cena za ceo smestaj, false cena po gostu
	@NotNull
	private boolean poSmestaju;
	@Positive
	private Long cena;
	@Positive
	private Long cenaPraznik;
	@Positive 
	private Long cenaVikend;
	@Positive
	private Long cenaLeto;
	//rucno definisanje Leta
	//private LocalDateTime pocetakLeta;
	//private LocalDateTime krajLeta;
	
	public Long getId() {
		return id;
	}
	public Cenovnik() {
		super();
	}
	public Cenovnik(Long id, @NotNull boolean poSmestaju, @Positive Long cena, @Positive Long cenaPraznik,
			@Positive Long cenaVikend, @Positive Long cenaLeto) {
		super();
		this.id = id;
		this.poSmestaju = poSmestaju;
		this.cena = cena;
		this.cenaPraznik = cenaPraznik;
		this.cenaVikend = cenaVikend;
		this.cenaLeto = cenaLeto;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isPoSmestaju() {
		return poSmestaju;
	}
	public void setPoSmestaju(boolean poSmestaju) {
		this.poSmestaju = poSmestaju;
	}
	public Long getCena() {
		return cena;
	}
	public void setCena(Long cena) {
		this.cena = cena;
	}
	public Long getCenaPraznik() {
		return cenaPraznik;
	}
	public void setCenaPraznik(Long cenaPraznik) {
		this.cenaPraznik = cenaPraznik;
	}
	public Long getCenaVikend() {
		return cenaVikend;
	}
	public void setCenaVikend(Long cenaVikend) {
		this.cenaVikend = cenaVikend;
	}
	public Long getCenaLeto() {
		return cenaLeto;
	}
	public void setCenaLeto(Long cenaLeto) {
		this.cenaLeto = cenaLeto;
	}
}
