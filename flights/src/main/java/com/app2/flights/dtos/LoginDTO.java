package com.app2.flights.dtos;

import java.io.Serializable;

public class LoginDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2349578404398333397L;
	
	private String email;
	private String password;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
