package com.app2.flights.dtos;

import java.io.Serializable;

public class LoginResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7349853780448514086L;
	private final String odgovor;

	public LoginResponseDTO(String odgovor) {
		super();
		this.odgovor = odgovor;
	}

	public String getOdgovor() {
		return odgovor;
	}
	
	
}
