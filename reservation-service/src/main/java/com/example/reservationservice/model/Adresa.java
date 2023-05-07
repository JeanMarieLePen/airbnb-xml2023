package com.example.reservationservice.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import javax.persistence.*;
@Document(collection = "adresa_db")
public class Adresa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3844742117528552755L;

	@Id
	private String id;
	
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	private Integer version;
	
	@NotBlank
	@Length(min = 5, max = 100)
	private String adresa;
	@NotNull
	private double lat;
	@NotNull
	private double lng;
	
	public Adresa() {
		super();
	}
	public Adresa(String id, String adresa, double lat, double lng) {
		super();
		this.id = id;
		this.adresa = adresa;
		this.lat = lat;
		this.lng = lng;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
