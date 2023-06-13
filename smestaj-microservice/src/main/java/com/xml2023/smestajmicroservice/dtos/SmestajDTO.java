package com.xml2023.smestajmicroservice.dtos;

import java.util.ArrayList;
import java.util.Collection;

public class SmestajDTO {
	private String id;
//	private HostDTO vlasnik;
	private String vlasnikId;
	private AdresaDTO adresa;
	private Collection<String> pogodnosti;
	private Collection<String> slike;
	private int minGosti;
	private int maxGosti;
	private CenovnikDTO cenovnik;
	
	private Collection<TerminDTO> nedostupni;
	private Collection<OcenaSmestajaDTO> listaOcena = new ArrayList<OcenaSmestajaDTO>();
	private float prosecnaOcena;
	public SmestajDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public String getVlasnikId() {
		return vlasnikId;
	}

	public void setVlasnikId(String vlasnikId) {
		this.vlasnikId = vlasnikId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Collection<String> getPogodnosti() {
		return pogodnosti;
	}

	public void setPogodnosti(Collection<String> pogodnosti) {
		this.pogodnosti = pogodnosti;
	}

	public Collection<OcenaSmestajaDTO> getListaOcena() {
		return listaOcena;
	}

	public void setListaOcena(Collection<OcenaSmestajaDTO> listaOcena) {
		this.listaOcena = listaOcena;
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

	public CenovnikDTO getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(CenovnikDTO cenovnik) {
		this.cenovnik = cenovnik;
	}

	public Collection<TerminDTO> getNedostupni() {
		return nedostupni;
	}

	public void setNedostupni(Collection<TerminDTO> nedostupni) {
		this.nedostupni = nedostupni;
	}

	public float getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(float prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
}