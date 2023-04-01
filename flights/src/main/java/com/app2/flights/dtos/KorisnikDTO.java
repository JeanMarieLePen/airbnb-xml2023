package com.app2.flights.dtos;

import java.util.Collection;

import org.hibernate.validator.constraints.Length;

import com.app2.flights.model.user.StatusNaloga;
import com.app2.flights.model.user.TipKorisnika;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class KorisnikDTO {
	
	private String id;
	@NotBlank
	@Length(min = 2, max = 100)
	private String ime;
	@NotBlank
	@Length(min = 2, max = 100)
	private String prezime;
	@NotBlank
	@Email
	@Length(min = 10, max = 100)
	private String email;
	@NotBlank
	@Length(min = 2, max = 100)
	private String username;
	@NotBlank
	@Length(min = 2, max = 100)
	private String password;
	@NotNull
	private AdresaDTO adresa;
	
	private TipKorisnika tip;
	
	private StatusNaloga status;
	private Collection<String> slike;
		
	public KorisnikDTO() {
		super();
	}
	
	public Collection<String> getSlike() {
		return slike;
	}

	public void setSlike(Collection<String> slike) {
		this.slike = slike;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AdresaDTO getAdresa() {
		return adresa;
	}
	public void setAdresa(AdresaDTO adresa) {
		this.adresa = adresa;
	}
	public TipKorisnika getTip() {
		return tip;
	}
	public void setTip(TipKorisnika tip) {
		this.tip = tip;
	}
	public StatusNaloga getStatus() {
		return status;
	}
	public void setStatus(StatusNaloga status) {
		this.status = status;
	}
	
	
}
