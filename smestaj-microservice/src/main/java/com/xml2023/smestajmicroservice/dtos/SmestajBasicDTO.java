package com.xml2023.smestajmicroservice.dtos;


import java.util.Collection;

public class SmestajBasicDTO {
	private String id;
	private Long hostId;
	private AdresaDTO adresa;
	private Collection<PogodnostBasicDTO> pogodnosti;
	private Collection<byte[]> slike;
	private int minGosti;
	private int maxGosti;
	private CenovnikDTO cenovnik;
	//private Collection<Rezervacija> rezervacije;
	//private Collection<OcenaSmestaj> listaOcena;
	
	public String getId() {
		return id;
	}
	public SmestajBasicDTO() {
		super();
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getHostId() {
		return hostId;
	}
	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}
	public AdresaDTO getAdresa() {
		return adresa;
	}
	public void setAdresa(AdresaDTO adresa) {
		this.adresa = adresa;
	}
	public Collection<PogodnostBasicDTO> getPogodnosti() {
		return pogodnosti;
	}
	public void setPogodnosti(Collection<PogodnostBasicDTO> pogodnosti) {
		this.pogodnosti = pogodnosti;
	}
	public Collection<byte[]> getSlike() {
		return slike;
	}
	public void setSlike(Collection<byte[]> slike) {
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
	
	
}
