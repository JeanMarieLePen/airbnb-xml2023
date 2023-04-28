package com.xml.mainapp.model.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.xml.mainapp.model.users.Guest;
import com.xml.mainapp.model.users.Korisnik;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Rezervacija implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7006531226274487692L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "bigserial", name = "id", updatable = false, unique = true)	
	private Long id;
	
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	private Integer version;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Smestaj smestaj;
	
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="rezervisao_id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Guest gost;
	
	@NotNull
	private LocalDateTime odDatum;
	@NotNull
	private LocalDateTime doDatum;
	@PositiveOrZero
	private int brojGostiju;
	@Enumerated
	private StatusRezervacije status;
	
	public Rezervacija() {
		super();
	}

	
	public Rezervacija(Long id, Integer version, Smestaj smestaj, Guest gost, @NotNull LocalDateTime odDatum,
			@NotNull LocalDateTime doDatum, @PositiveOrZero int brojGostiju, StatusRezervacije status) {
		super();
		this.id = id;
		this.version = version;
		this.smestaj = smestaj;
		this.gost = gost;
		this.odDatum = odDatum;
		this.doDatum = doDatum;
		this.brojGostiju = brojGostiju;
		this.status = status;
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

	public Smestaj getSmestaj() {
		return smestaj;
	}

	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}

	public Guest getGost() {
		return gost;
	}

	public void setGost(Guest gost) {
		this.gost = gost;
	}

	public LocalDateTime getOdDatum() {
		return odDatum;
	}

	public void setOdDatum(LocalDateTime odDatum) {
		this.odDatum = odDatum;
	}

	public LocalDateTime getDoDatum() {
		return doDatum;
	}

	public void setDoDatum(LocalDateTime doDatum) {
		this.doDatum = doDatum;
	}

	public int getBrojGostiju() {
		return brojGostiju;
	}

	public void setBrojGostiju(int brojGostiju) {
		this.brojGostiju = brojGostiju;
	}

	public StatusRezervacije getStatus() {
		return status;
	}

	public void setStatus(StatusRezervacije status) {
		this.status = status;
	}
}
