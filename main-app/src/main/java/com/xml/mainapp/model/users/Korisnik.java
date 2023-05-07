package com.xml.mainapp.model.users;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import org.hibernate.validator.constraints.Length;

import com.xml.mainapp.model.data.Adresa;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Korisnik implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5966705918219356582L;

	@Id
//	@GeneratedValue(strategy = GenerationType.TABLE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition="bigserial", name="id", updatable=false, unique=true)
    private Long id;

    @Version
    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    private Integer version;

    @NotNull
	@NotBlank
	@Length(min=6, max=100)
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
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Adresa.class)
    @JoinColumn(name="adresa_id", referencedColumnName="id")
    @NotNull
    private Adresa adresa;

    @Enumerated
    private StatusNaloga statusNaloga;
    @Enumerated
    private TipKorisnika tipKorisnika;

    @ElementCollection
    private Collection<byte[]> slike;

    @Nullable
    private String activationLink;
    
    
	public Korisnik() {
		super();
	}
	


	public Korisnik(Long id, Integer version, @NotNull @NotBlank @Length(min = 6, max = 100) String lozinka,
			@NotNull @NotBlank @Length(min = 3, max = 100) String korIme, @NotNull @NotBlank @Email String email,
			@NotNull @NotBlank @Length(min = 2, max = 100) String ime,
			@NotNull @NotBlank @Length(min = 2, max = 100) String prezime, @NotNull Adresa adresa,
			StatusNaloga statusNaloga, TipKorisnika tipKorisnika, Collection<byte[]> slike, String activationLink) {
		super();
		this.id = id;
		this.version = version;
		this.lozinka = lozinka;
		this.korIme = korIme;
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.statusNaloga = statusNaloga;
		this.tipKorisnika = tipKorisnika;
		this.slike = slike;
		this.activationLink = activationLink;
	}



	public String getActivationLink() {
		return activationLink;
	}

	public void setActivationLink(String activationLink) {
		this.activationLink = activationLink;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public Adresa getAdresa() {
		return adresa;
	}


	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
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

	public Collection<byte[]> getSlike() {
		return slike;
	}

	public void setSlike(Collection<byte[]> slike) {
		this.slike = slike;
	}
}
