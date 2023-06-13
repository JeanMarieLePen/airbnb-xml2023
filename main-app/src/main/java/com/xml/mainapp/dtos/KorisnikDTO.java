package com.xml.mainapp.dtos;

import java.io.Serializable;
import java.util.Collection;

import com.xml.mainapp.dtos.data.SmestajDTO;
import com.xml.mainapp.model.users.StatusNaloga;
import com.xml.mainapp.model.users.TipKorisnika;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class KorisnikDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 935928709318268339L;
	
	
	private String id;
	@NotNull
	private String lozinka;
	@NotNull
	@NotBlank
    private String korIme;
	@NotNull
	@NotBlank
    private String email;
	@NotNull
	@NotBlank
    private String ime;
	@NotNull
	@NotBlank
    private String prezime;
	@NotNull
    private AdresaDTO adresa;
	@NotNull
    private StatusNaloga statusNaloga;
	@NotNull
    private TipKorisnika tipKorisnika;
    
	@NotNull
    private Collection<String> slike;
    
	public KorisnikDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public StatusNaloga getStatusNaloga() {
		return statusNaloga;
	}

	public void setStatusNaloga(StatusNaloga statusNaloga) {
		this.statusNaloga = statusNaloga;
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