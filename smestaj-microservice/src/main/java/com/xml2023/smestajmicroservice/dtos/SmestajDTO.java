package com.xml2023.smestajmicroservice.dtos;

import java.util.Collection;

public class SmestajDTO {
	private String id;
//	private HostDTO vlasnik;
	private String vlasnikId;
	private AdresaDTO adresa;
	private Collection<PogodnostDTO> pogodnosti;
	private Collection<String> slike;
	private int minGosti;
	private int maxGosti;
	private CenovnikDTO cenovnik;
	
	private Collection<TerminDTO> nedostupni;
	private Collection<RezervacijaDTO> rezervacije;
	private Collection<OcenaSmestajaDTO> listaOcena;

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

	public Collection<RezervacijaDTO> getRezervacije() {
		return rezervacije;
	}

//	public HostDTO getVlasnik() {
//		return vlasnik;
//	}
//
//	public void setVlasnik(HostDTO vlasnik) {
//		this.vlasnik = vlasnik;
//	}

	public void setRezervacije(Collection<RezervacijaDTO> rezervacije) {
		this.rezervacije = rezervacije;
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

	public Collection<PogodnostDTO> getPogodnosti() {
		return pogodnosti;
	}

	public void setPogodnosti(Collection<PogodnostDTO> pogodnosti) {
		this.pogodnosti = pogodnosti;
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


}
