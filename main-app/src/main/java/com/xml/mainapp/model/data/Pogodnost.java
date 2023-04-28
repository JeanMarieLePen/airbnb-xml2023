package com.xml.mainapp.model.data;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Pogodnost implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -776142387481773478L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	private Integer version;
	@NotBlank
	private String naziv;

	@ManyToMany(mappedBy = "pogodnosti")
	private Collection<Smestaj> listaSmestaja;
	
	public Pogodnost() {
		super();
	}

	public Pogodnost(Long id, @NotBlank String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Collection<Smestaj> getListaSmestaja() {
		return listaSmestaja;
	}

	public void setListaSmestaja(Collection<Smestaj> listaSmestaja) {
		this.listaSmestaja = listaSmestaja;
	}

	
}
