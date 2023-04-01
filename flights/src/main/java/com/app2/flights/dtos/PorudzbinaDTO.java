package com.app2.flights.dtos;

import com.app2.flights.model.data.StatusPorudzbine;

public class PorudzbinaDTO {

	private String id;
	private String  let;
	private String letBroj;
	private String kupac;
	private int brojKarata;
	private double ukupnaCena;
	private StatusPorudzbine status;
	public PorudzbinaDTO() {
		super();
	}
	
	public StatusPorudzbine getStatus() {
		return status;
	}

	public void setStatus(StatusPorudzbine status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLet() {
		return let;
	}
	public void setLet(String let) {
		this.let = let;
	}
	public String getKupac() {
		return kupac;
	}
	public void setKupac(String kupac) {
		this.kupac = kupac;
	}
	public int getBrojKarata() {
		return brojKarata;
	}
	public void setBrojKarata(int brojKarata) {
		this.brojKarata = brojKarata;
	}
	public double getUkupnaCena() {
		return ukupnaCena;
	}
	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public String getLetBroj() {
		return letBroj;
	}

	public void setLetBroj(String letBroj) {
		this.letBroj = letBroj;
	}

	public PorudzbinaDTO(String id, String let, String letBroj, String kupac, int brojKarata, double ukupnaCena,
			StatusPorudzbine status) {
		super();
		this.id = id;
		this.let = let;
		this.letBroj = letBroj;
		this.kupac = kupac;
		this.brojKarata = brojKarata;
		this.ukupnaCena = ukupnaCena;
		this.status = status;
	}
	
}
