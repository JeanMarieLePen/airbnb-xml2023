package com.app2.flights.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import org.hibernate.validator.constraints.Length;

import com.app2.flights.model.user.TipKorisnika;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7846570012464075658L;
	@NotNull
	@NotBlank
	@Length(min=6, max=30)
	private String password;
	@NotNull
	@NotBlank
	@Length(min=3, max=100)
	private String username;
	@NotNull
	@NotBlank
	@Email
	private String email;
	@NotNull
	@NotBlank
	@Length(min=2, max=100)
	private String ime;
	@NotNull
	@NotBlank
	@Length(min=2, max=100)
	private String prezime;
	@NotNull
	private AdresaDTO adresa;
	
	private TipKorisnika tipKorisnika;
	private Collection<String> slike;

	public RegisterDTO() {
		super();
	}

	public RegisterDTO(@NotNull @NotBlank @Length(min = 6, max = 30) String password,
			@NotNull @NotBlank @Length(min = 3, max = 100) String username, @NotNull @NotBlank @Email String email,
			@NotNull @NotBlank @Length(min = 2, max = 100) String ime,
			@NotNull @NotBlank @Length(min = 2, max = 100) String prezime, @NotNull AdresaDTO adresa,
			TipKorisnika tipKorisnika, Collection<String> slike) {
		super();
		this.password = password;
		this.username = username;
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.tipKorisnika = tipKorisnika;
		this.slike = slike;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public AdresaDTO getAdresa() {
		return adresa;
	}

	public void setAdresa(AdresaDTO adresa) {
		this.adresa = adresa;
	}

	public TipKorisnika getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(TipKorisnika tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}

	public Collection<String> getSlike() {
		return slike;
	}

	public void setSlike(Collection<String> slike) {
		this.slike = slike;
	}
	
	
	
}
