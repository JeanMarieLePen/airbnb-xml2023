package com.app2.flights.model.data;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.index.Indexed;

public class APIToken {

	private boolean trajni;
	@Indexed(unique = true)
	private String token;
	private LocalDateTime vremeGenerisanja;
	private int brojSatiVazenja;
	private LocalDateTime vremeVazenja;
	
	public APIToken() {
		super();
	}
	
	public int getBrojSatiVazenja() {
		return brojSatiVazenja;
	}
	public void setBrojSatiVazenja(int brojSatiVazenja) {
		this.brojSatiVazenja = brojSatiVazenja;
	}
	public boolean isTrajni() {
		return trajni;
	}
	public void setTrajni(boolean trajni) {
		this.trajni = trajni;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getVremeGenerisanja() {
		return vremeGenerisanja;
	}
	public void setVremeGenerisanja(LocalDateTime vremeGenerisanja) {
		this.vremeGenerisanja = vremeGenerisanja;
	}
	public LocalDateTime getVremeVazenja() {
		return vremeVazenja;
	}
	public void setVremeVazenja(LocalDateTime vremeVazenja) {
		this.vremeVazenja = vremeVazenja;
	}
	
	
}
