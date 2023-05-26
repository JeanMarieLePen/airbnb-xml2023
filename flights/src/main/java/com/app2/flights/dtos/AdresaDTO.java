package com.app2.flights.dtos;

import com.app2.flights.model.data.Adresa;

public class AdresaDTO {

	private String id;
	private String adresa;
	private double lat;
	private double lng;
	
	public AdresaDTO() {
		super();
	}
	public AdresaDTO(Adresa lokOd) {
		this.setAdresa(lokOd.getAdresa());
		this.setLat(lokOd.getLat());
		this.setLng(lokOd.getLng());
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
