package com.app2.flights.dtos;

import java.time.LocalDateTime;

public class TokenCreateDTO {

	private boolean trajni;
	private LocalDateTime vremeGenerisanja;
	private int brojSatiVazenja;
	
	public TokenCreateDTO() {
		super();
	}
	
	public boolean isTrajni() {
		return trajni;
	}
	public void setTrajni(boolean trajni) {
		this.trajni = trajni;
	}
	public LocalDateTime getVremeGenerisanja() {
		return vremeGenerisanja;
	}
	public void setVremeGenerisanja(LocalDateTime vremeGenerisanja) {
		this.vremeGenerisanja = vremeGenerisanja;
	}
	public int getBrojSatiVazenja() {
		return brojSatiVazenja;
	}
	public void setBrojSatiVazenja(int brojSatiVazenja) {
		this.brojSatiVazenja = brojSatiVazenja;
	}
	
	
}
