package com.app2.flights.dtos;

import java.time.LocalDateTime;

public class PretragaDTO {
	private LocalDateTime pocetak;
	private LocalDateTime kraj;
	private int brKarata;
	private int minCena;
	private int maxCena;
	private AdresaDTO pocetnaLok;
	private AdresaDTO krajnjaLok;
	
	public PretragaDTO(LocalDateTime pocetak, LocalDateTime kraj, int brKarata, int minCena, int maxCena,
			AdresaDTO pocetnaLok, AdresaDTO krajnjaLok) {
		super();
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.brKarata = brKarata;
		this.minCena = minCena;
		this.maxCena = maxCena;
		this.pocetnaLok = pocetnaLok;
		this.krajnjaLok = krajnjaLok;
	}

	public LocalDateTime getPocetak() {
		return pocetak;
	}

	public void setPocetak(LocalDateTime pocetak) {
		this.pocetak = pocetak;
	}

	public LocalDateTime getKraj() {
		return kraj;
	}

	public void setKraj(LocalDateTime kraj) {
		this.kraj = kraj;
	}

	public int getBrKarata() {
		return brKarata;
	}

	public void setBrKarata(int brKarata) {
		this.brKarata = brKarata;
	}

	public int getMinCena() {
		return minCena;
	}

	public void setMinCena(int minCena) {
		this.minCena = minCena;
	}

	public int getMaxCena() {
		return maxCena;
	}

	public void setMaxCena(int maxCena) {
		this.maxCena = maxCena;
	}

	public AdresaDTO getPocetnaLok() {
		return pocetnaLok;
	}

	public void setPocetnaLok(AdresaDTO pocetnaLok) {
		this.pocetnaLok = pocetnaLok;
	}

	public AdresaDTO getKrajnjaLok() {
		return krajnjaLok;
	}

	public void setKrajnjaLok(AdresaDTO krajnjaLok) {
		this.krajnjaLok = krajnjaLok;
	}
}
