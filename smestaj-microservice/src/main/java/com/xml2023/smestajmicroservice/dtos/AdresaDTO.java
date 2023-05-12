package com.xml2023.smestajmicroservice.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class AdresaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8603719579936684827L;
	@PositiveOrZero
	private String id;
	@PositiveOrZero
	private Integer version;
	
	@NotBlank
	@Length(min = 5, max = 100)
	private String adresa;
	@NotNull
	private double lat;
	@NotNull
	private double lng;
	
	public AdresaDTO() {
		super();
	}
	public AdresaDTO(AdresaDTO adresa2) {
		this.adresa=adresa2.getAdresa();
		this.lat=adresa2.getLat();
		this.lng=adresa2.getLng();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	
}
