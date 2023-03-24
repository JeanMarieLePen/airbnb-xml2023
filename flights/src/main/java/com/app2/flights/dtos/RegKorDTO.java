package com.app2.flights.dtos;

import java.util.Collection;

public class RegKorDTO extends KorisnikDTO{
	private Collection<PorudzbinaDTO> porudzbine;
	
	public RegKorDTO() {
		super();
	}

	public Collection<PorudzbinaDTO> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(Collection<PorudzbinaDTO> porudzbine) {
		this.porudzbine = porudzbine;
	}
	
	
}
