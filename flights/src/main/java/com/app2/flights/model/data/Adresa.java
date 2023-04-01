package com.app2.flights.model.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.app2.flights.dtos.AdresaDTO;

@Document(collection = "adresa_db")
public class Adresa {
	
	@Id
	private String id;
	
	private String adresa;
	
	private double lat;
	
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
	public Adresa( String adresa, double lat, double lng) {
		super();
		this.adresa = adresa;
		this.lat = lat;
		this.lng = lng;
	}
	

	public Adresa(AdresaDTO nova) {
		this.adresa = nova.getAdresa();
		this.lat = nova.getLat();
		this.lng = nova.getLng();
	}

	public Adresa(String lokOd) {
		this.setAdresa(adresa);
		this.setLat(0);
		this.setLng(0);
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
