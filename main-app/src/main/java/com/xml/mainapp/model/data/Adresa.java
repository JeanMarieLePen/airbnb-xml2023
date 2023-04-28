package com.xml.mainapp.model.data;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Adresa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3844742117528552755L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(columnDefinition = "bigserial", name="id", updatable = false, unique = true)
//	@Column(columnDefinition = "bigserial(10)", name="id", updatable = false, unique = true)
	private Long id;
	
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
	public Adresa(Long id, String adresa, double lat, double lng) {
		super();
		this.id = id;
		this.adresa = adresa;
		this.lat = lat;
		this.lng = lng;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
