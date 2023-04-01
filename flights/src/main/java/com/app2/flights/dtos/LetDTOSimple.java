package com.app2.flights.dtos;

import java.time.LocalDateTime;
import java.util.Collection;

public class LetDTOSimple {
	private String id;
	private String lokOd;
	private String lokDo;
	private String brojLeta;
	private int kapacitet;
	private double cena;
	private LocalDateTime datumIVreme;	
	private int brSlobMesta;
	
	
	
	public LetDTOSimple(String id, String lokOd, String lokDo, String brojLeta, int kapacitet, double cena,
			LocalDateTime datumIVreme, int brSlobMesta) {
		super();
		this.id = id;
		this.lokOd = lokOd;
		this.lokDo = lokDo;
		this.brojLeta = brojLeta;
		this.kapacitet = kapacitet;
		this.cena = cena;
		this.datumIVreme = datumIVreme;
		this.brSlobMesta = brSlobMesta;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLokOd() {
		return lokOd;
	}
	public void setLokOd(String lokOd) {
		this.lokOd = lokOd;
	}
	public String getLokDo() {
		return lokDo;
	}
	public void setLokDo(String lokDo) {
		this.lokDo = lokDo;
	}
	public String getBrojLeta() {
		return brojLeta;
	}
	public void setBrojLeta(String brojLeta) {
		this.brojLeta = brojLeta;
	}
	public int getKapacitet() {
		return kapacitet;
	}
	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public LocalDateTime getDatumIVreme() {
		return datumIVreme;
	}
	public void setDatumIVreme(LocalDateTime datumIVreme) {
		this.datumIVreme = datumIVreme;
	}
	public int getBrSlobMesta() {
		return brSlobMesta;
	}
	public void setBrSlobMesta(int brSlobMesta) {
		this.brSlobMesta = brSlobMesta;
	}
}
