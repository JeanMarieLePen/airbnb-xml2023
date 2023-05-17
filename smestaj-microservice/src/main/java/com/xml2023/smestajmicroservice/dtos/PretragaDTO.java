package com.xml2023.smestajmicroservice.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.xml2023.smestajmicroservice.model.data.Adresa;

public class PretragaDTO {
	private String vlasnik;
	private Adresa adresa;
	private int brGosti;
	private double minCena;
	private double maxCena;
	private double minOcena;
	private double maxOcena;
	private LocalDateTime pocetak;
	private LocalDateTime kraj;
	
	public PretragaDTO(String vlasnik, Adresa adresa,  int brGosti,
			double minCena, double maxCena, double minOcena, double maxOcena, LocalDateTime pocetak,
			LocalDateTime kraj) {
		super();
		this.vlasnik = vlasnik;
		this.adresa = adresa;
		this.brGosti = brGosti;
		this.minCena = minCena;
		this.maxCena = maxCena;
		this.minOcena = minOcena;
		this.maxOcena = maxOcena;
		this.pocetak = pocetak;
		this.kraj = kraj;
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
	
	public int getBrGosti() {
		return brGosti;
	}

	public void serBrGosti(int maxGosti) {
		this.brGosti = maxGosti;
	}

	public double getMinCena() {
		return minCena;
	}

	public void setMinCena(double minCena) {
		this.minCena = minCena;
	}

	public double getMaxCena() {
		return maxCena;
	}

	public void setMaxCena(double maxCena) {
		this.maxCena = maxCena;
	}

	public double getMinOcena() {
		return minOcena;
	}

	public void setMinOcena(double minOcena) {
		this.minOcena = minOcena;
	}

	public double getMaxOcena() {
		return maxOcena;
	}

	public void setMaxOcena(double maxOcena) {
		this.maxOcena = maxOcena;
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
	
	
}
