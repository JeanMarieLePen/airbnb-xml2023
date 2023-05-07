package com.xml.mainapp.dtos.data;

import java.util.Collection;

import com.xml.mainapp.dtos.AdresaDTO;

public class SmestajPretragaDTO {
	private Long id;
	private Long hostId;
	private AdresaDTO adresa;
	private Collection<PogodnostBasicDTO> pogodnosti;
	private Collection<byte[]> slike;
	private int minGosti;
	private int maxGosti;
	private Float ukupnaCena;		//cena koje je na backu izracunata za zadati termin
	private boolean vlasnikIstaknut;
	
	
	public SmestajPretragaDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Float getUkupnaCena() {
		return ukupnaCena;
	}
	public void setUkupnaCena(Float ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}
	public boolean isVlasnikIstaknut() {
		return vlasnikIstaknut;
	}
	public void setVlasnikIstaknut(boolean vlasnikIstaknut) {
		this.vlasnikIstaknut = vlasnikIstaknut;
	}
	
	
}
