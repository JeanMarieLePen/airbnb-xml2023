package com.xml.mainapp.model.data;

import java.io.Serializable;
import java.util.Collection;

import com.xml.mainapp.model.users.Host;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Smestaj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6956429912337266684L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="bigserial", name="id", updatable=false, unique=true)
	private Long id;
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	private Integer version;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Host vlasnik;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="adresa_id", referencedColumnName = "id")
	private Adresa adresa;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name="smestaj_pogodnosti", joinColumns = @JoinColumn(name="pogodnost_id"),
	inverseJoinColumns = @JoinColumn(name = "smestaj_id"))
	private Collection<Pogodnost> pogodnosti;
	
	@ElementCollection
	private Collection<byte[]> slike;
	
	@Positive
	private int minGosti;
	
	@Positive
	private int maxGosti;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="cenovnik_id", referencedColumnName="id")
	private Cenovnik cenovnik;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="smestaj",  orphanRemoval = true)
	private Collection<Rezervacija> rezervacije;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "smestaj",  orphanRemoval = true)
	private Collection<OcenaSmestaj> listaOcena;
	
	@Nullable
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinTable(name = "smestaj_termini", joinColumns = {
//			@JoinColumn(name = "smestaj_id", referencedColumnName = "id")}, inverseJoinColumns = {
//					@JoinColumn(name = "termin_id", referencedColumnName = "id")})
//	@JoinColumn(name="smestaj_id", referencedColumnName = "id")
	private Collection<Termin> nedostupni;
	
	public Smestaj(Long id, Integer version, @NotNull Host vlasnik, Adresa adresa, Collection<Pogodnost> pogodnosti,
			Collection<byte[]> slike, @Positive int minGosti, @Positive int maxGosti, Cenovnik cenovnik,
			Collection<Rezervacija> rezervacije, Collection<OcenaSmestaj> listaOcena, Collection<Termin> nedostupni) {
		super();
		this.id = id;
		this.version = version;
		this.vlasnik = vlasnik;
		this.adresa = adresa;
		this.pogodnosti = pogodnosti;
		this.slike = slike;
		this.minGosti = minGosti;
		this.maxGosti = maxGosti;
		this.cenovnik = cenovnik;
		this.rezervacije = rezervacije;
		this.listaOcena = listaOcena;
		this.nedostupni = nedostupni;
	}

	public Smestaj() {
		super();
	}

	public Collection<OcenaSmestaj> getListaOcena() {
		return listaOcena;
	}

	public void setListaOcena(Collection<OcenaSmestaj> listaOcena) {
		this.listaOcena = listaOcena;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Collection<Termin> getNedostupni() {
		return nedostupni;
	}

	public void setNedostupni(Collection<Termin> nedostupni) {
		this.nedostupni = nedostupni;
	}

	public Host getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Host vlasnik) {
		this.vlasnik = vlasnik;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public Collection<Pogodnost> getPogodnosti() {
		return pogodnosti;
	}

	public void setPogodnosti(Collection<Pogodnost> pogodnosti) {
		this.pogodnosti = pogodnosti;
	}

	public Collection<byte[]> getSlike() {
		return slike;
	}

	public void setSlike(Collection<byte[]> slike) {
		this.slike = slike;
	}

	public int getMinGosti() {
		return minGosti;
	}

	public void setMinGosti(int minGosti) {
		this.minGosti = minGosti;
	}

	public int getMaxGosti() {
		return maxGosti;
	}

	public void setMaxGosti(int maxGosti) {
		this.maxGosti = maxGosti;
	}

	public Cenovnik getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
	}

	public Collection<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(Collection<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
	
}
