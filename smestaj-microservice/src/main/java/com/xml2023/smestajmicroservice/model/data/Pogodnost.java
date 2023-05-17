package com.xml2023.smestajmicroservice.model.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import jakarta.validation.constraints.NotBlank;

public class Pogodnost implements Serializable{

	private static final long serialVersionUID = -776142387481773478L;
	@Id
	private String id;

	@NotBlank
	private List<String> nazivi;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getNazivi() {
		return nazivi;
	}

	public void setNazivi(List<String> nazivi) {
		this.nazivi = nazivi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Pogodnost( List<String> nazivi) {
		super();
		this.nazivi = nazivi;
	}

}