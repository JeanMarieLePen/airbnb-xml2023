package com.app2.flights.model.user;

import java.util.ArrayList;
import java.util.Collection;


import com.app2.flights.model.data.APIToken;

public class RegKor extends Korisnik{
	private Collection<String> porudzbine;
	private APIToken apiToken;  
	
	public RegKor() {
		super();
	}

	public RegKor(Collection<String> porudzbine) {
		super();
		this.porudzbine = porudzbine;
	}

	public RegKor(Korisnik k) {
		super();
		this.setId(k.getId());
		this.setIme(k.getIme());;
		this.setPrezime(k.getPrezime());
		this.setEmail(k.getEmail());
		this.setUsername(k.getUsername());
		this.setPassword(k.getPassword());
		this.setAdresa(k.getAdresa());
		this.setTip(k.getTip());
		this.setStatus(k.getStatus());
		this.porudzbine= new ArrayList<String>();
	}
	

	public APIToken getApiToken() {
		return apiToken;
	}

	public void setApiToken(APIToken apiToken) {
		this.apiToken = apiToken;
	}

	public Collection<String> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(Collection<String> porudzbine) {
		this.porudzbine = porudzbine;
	}
	
}
