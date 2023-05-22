package com.xml2023.smestajmicroservice.dtos;

import java.util.Collection;

import com.xml2023.smestajmicroservice.model.data.Smestaj;

public class SmestajPretragaDTO {
	private String id;
	private String hostId;
	private AdresaDTO adresa;
	private Collection<String> slike;
	private int minGosti;
	private int maxGosti;
	private double ukCena;
	private double prosecnaOcena;
	private boolean poSmestaju;
	
	
	public SmestajPretragaDTO(String id, String hostId, AdresaDTO adresa,
			Collection<String> slike, int minGosti, int maxGosti, double ukCena, double prosecnaOcena, boolean poSmestaju) {
		super();
		this.id = id;
		this.hostId = hostId;
		this.adresa = adresa;
		this.slike = slike;
		this.minGosti = minGosti;
		this.maxGosti = maxGosti;
		this.ukCena = ukCena;
		this.prosecnaOcena = prosecnaOcena;
		this.poSmestaju=poSmestaju;
	}
	

	public SmestajPretragaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getHostId() {
		return hostId;
	}


	public void setHostId(String hostId) {
		this.hostId = hostId;
	}


	public AdresaDTO getAdresa() {
		return adresa;
	}


	public void setAdresa(AdresaDTO adresa) {
		this.adresa = adresa;
	}

	public Collection<String> getSlike() {
		return slike;
	}


	public void setSlike(Collection<String> slike) {
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


	public double getUkCena() {
		return ukCena;
	}


	public void setUkCena(double ukCena) {
		this.ukCena = ukCena;
	}


	public double getProsecnaOcena() {
		return prosecnaOcena;
	}


	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}


	public boolean isPoSmestaju() {
		return poSmestaju;
	}


	public void setPoSmestaju(boolean poSmestaju) {
		this.poSmestaju = poSmestaju;
	}
	
}
