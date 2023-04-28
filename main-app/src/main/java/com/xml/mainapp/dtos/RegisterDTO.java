package com.xml.mainapp.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import org.hibernate.validator.constraints.Length;

import com.xml.mainapp.model.users.TipKorisnika;

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
	private String lozinka;
	@NotNull
	@NotBlank
	@Length(min=3, max=100)
	private String korIme;
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
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getKorIme() {
		return korIme;
	}
	public void setKorIme(String korIme) {
		this.korIme = korIme;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
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
