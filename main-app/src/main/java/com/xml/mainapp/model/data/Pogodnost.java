package com.xml.mainapp.model.data;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "pogodnosti_db")
public class Pogodnost implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -776142387481773478L;
	@Id
	private String id;
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	private Integer version;
	@NotBlank
	private String naziv;

	@DBRef
	private Collection<Smestaj> listaSmestaja;
	
	public Pogodnost() {
		super();
	}

	public Pogodnost(String id, @NotBlank String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
