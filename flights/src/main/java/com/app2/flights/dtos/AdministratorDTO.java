package com.app2.flights.dtos;

import java.util.Collection;

import com.app2.flights.model.data.Let;

public class AdministratorDTO extends KorisnikDTO{
	private Collection<LetDTO> letovi;
	private Collection<PorudzbinaDTO> porudzbine;
	
	public AdministratorDTO() {
		super();
	}
	
	public Collection<LetDTO> getLetovi() {
		return letovi;
	}
	public void setLetovi(Collection<LetDTO> letovi) {
		this.letovi = letovi;
	}
	public Collection<PorudzbinaDTO> getPorudzbine() {
		return porudzbine;
	}
	public void setPorudzbine(Collection<PorudzbinaDTO> porudzbine) {
		this.porudzbine = porudzbine;
	}
	
	
}
