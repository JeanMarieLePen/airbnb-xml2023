package com.app2.flights.dtos;

import io.micrometer.core.instrument.distribution.TimeWindowPercentileHistogram;

public class PorudzbinaDTOnova {
	private String let;
	private String kupac;
	private int brojKarata;
	
	public PorudzbinaDTOnova(String let, String kupac, int brojKarata) {
		super();
		this.let = let;
		this.kupac = kupac;
		this.brojKarata = brojKarata;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Nova Porudzbina :"+"[\nlet : "+this.getLet()+",\nkupac : "+this.getKupac()+",\nbrojKarata : "+this.getBrojKarata()+"\n]\n";
	}

}
