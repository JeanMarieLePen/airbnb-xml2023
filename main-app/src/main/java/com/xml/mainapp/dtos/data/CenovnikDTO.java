package com.xml.mainapp.dtos.data;


public class CenovnikDTO {
	private Long id;
	private boolean poSmestaju;
	private Long cena;
	private Long cenaPraznik;
	private Long cenaVikend;
	private Long cenaLeto;
	//rucno definisanje Leta
	//private LocalDateTime pocetakLeta;
	//private LocalDateTime krajLeta;
	
	
	public Long getId() {
		return id;
	}
	public CenovnikDTO() {
		super();
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
