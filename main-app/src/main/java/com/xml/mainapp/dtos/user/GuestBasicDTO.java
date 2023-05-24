package com.xml.mainapp.dtos.user;

import java.util.Collection;

import com.xml.mainapp.dtos.data.OcenaSmestajaDTO;
import com.xml.mainapp.dtos.data.RezervacijaDTO;

public class GuestBasicDTO {
	private String id;
	private String email;
	private String korIme;
	private String ime;
	private String preizme;
	private Long version;
	
	private Long brojOtkazivanja;
	private Collection<RezervacijaDTO> rezervacije;
	private Collection<OcenaSmestajaDTO> oceneSmestaja;
	private Collection<OcenaHostBasicDTO> oceneVlasnika;
	
	public GuestBasicDTO() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getKorIme() {
		return korIme;
	}
	public void setKorIme(String korIme) {
		this.korIme = korIme;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPreizme() {
		return preizme;
	}
	public void setPreizme(String preizme) {
		this.preizme = preizme;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Long getBrojOtkazivanja() {
		return brojOtkazivanja;
	}
	public void setBrojOtkazivanja(Long brojOtkazivanja) {
		this.brojOtkazivanja = brojOtkazivanja;
	}
	public Collection<RezervacijaDTO> getRezervacije() {
		return rezervacije;
	}
	public void setRezervacije(Collection<RezervacijaDTO> rezervacije) {
		this.rezervacije = rezervacije;
	}
	public Collection<OcenaSmestajaDTO> getOceneSmestaja() {
		return oceneSmestaja;
	}
	public void setOceneSmestaja(Collection<OcenaSmestajaDTO> oceneSmestaja) {
		this.oceneSmestaja = oceneSmestaja;
	}
	public Collection<OcenaHostBasicDTO> getOceneVlasnika() {
		return oceneVlasnika;
	}
	public void setOceneVlasnika(Collection<OcenaHostBasicDTO> oceneVlasnika) {
		this.oceneVlasnika = oceneVlasnika;
	}
	
	
}
