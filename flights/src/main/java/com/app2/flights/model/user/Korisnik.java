package com.app2.flights.model.user;

import java.util.Collection;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.app2.flights.model.data.Adresa;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Document(collection = "korisnik_db")
public class Korisnik {

	@Id
	private String id;
	
	@Length(min = 2, max = 100)
	private String ime;
	
	@Length(min = 2, max = 100)
	private String  prezime;
	
	@Indexed(unique = true)
	@Email
	@Length(min = 2, max = 50)
	private String email;
	
	@Indexed(unique = true)
	private String username;
	
	private String password;
	
	@DBRef
	@NotNull
	private Adresa adresa;

	@NotNull
	private TipKorisnika tip;
	@NotNull
	private StatusNaloga status;	
	
	@Nullable
    private String activationLink;
	
	@Nullable
    private Collection<byte[]> slike;
	
	public Korisnik() {
		super();
	}
	public Korisnik(String id, @Length(min = 2, max = 100) String ime, @Length(min = 2, max = 100) String prezime,
			@Email @Length(min = 2, max = 50) String email, String username, String password, @NotNull Adresa adresa,
			@NotNull TipKorisnika tip, @NotNull StatusNaloga status) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.username = username;
		this.password = password;
		this.adresa = adresa;
		this.tip = tip;
		this.status = status;
	}
	
	public Collection<byte[]> getSlike() {
		return slike;
	}
	public void setSlike(Collection<byte[]> slike) {
		this.slike = slike;
	}
	public String getActivationLink() {
		return activationLink;
	}
	public void setActivationLink(String activationLink) {
		this.activationLink = activationLink;
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
	public Adresa getAdresa() {
		return adresa;
	}
	public void setAdresa(Adresa adresa) {
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
	public Korisnik(Korisnik k) {
		super();
		this.id = k.getId();
		this.ime = k.getId();
		this.prezime = k.getPrezime();
		this.email = k.getEmail();
		this.username = k.getUsername();
		this.password = k.getPassword();
		this.adresa = k.getAdresa();
		this.tip = k.getTip();
		this.status = k.getStatus();
	}
}
