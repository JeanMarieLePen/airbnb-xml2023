package com.app2.flights.dtos;

import java.time.LocalDateTime;

public class TokenPrikazDTO {
	private boolean trajni;
	private LocalDateTime vremeVazenja;
	private String tokenTekst;
	
	public TokenPrikazDTO() {
		super();
	}
	
	public String getTokenTekst() {
		return tokenTekst;
	}

	public void setTokenTekst(String tokenTekst) {
		this.tokenTekst = tokenTekst;
	}

	public boolean isTrajni() {
		return trajni;
	}
	public void setTrajni(boolean trajni) {
		this.trajni = trajni;
	}
	public LocalDateTime getVremeVazenja() {
		return vremeVazenja;
	}
	public void setVremeVazenja(LocalDateTime vremeVazenja) {
		this.vremeVazenja = vremeVazenja;
	}
	
	
}
