package com.app2.flights.dtos;

import java.time.LocalDateTime;
import java.util.Collection;

import com.app2.flights.model.data.Adresa;

public class LetDTO {

	private String id;
	private AdresaDTO lokOd;
	private AdresaDTO lokDo;
	private String brojLeta;
	private int kapacitet;
	private double cena;
	private LocalDateTime datumIVreme;	
	private Collection<PorudzbinaDTO> listaPorudzbina;
	public LetDTO() {
		super();
	}
	
	public Collection<PorudzbinaDTO> getListaPorudzbina() {
		return listaPorudzbina;
	}
	public void setListaPorudzbina(Collection<PorudzbinaDTO> listaPorudzbina) {
		this.listaPorudzbina = listaPorudzbina;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public AdresaDTO getLokOd() {
		return lokOd;
	}
	public void setLokOd(AdresaDTO lokOd) {
		this.lokOd = lokOd;
	}
	public AdresaDTO getLokDo() {
		return lokDo;
	}
	public void setLokDo(AdresaDTO lokDo) {
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
	
	
}
