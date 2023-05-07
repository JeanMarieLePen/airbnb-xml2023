package com.xml.mainapp.dtos.user;


import java.util.Collection;

import com.xml.mainapp.dtos.KorisnikDTO;
import com.xml.mainapp.dtos.data.OcenaSmestajaDTO;
import com.xml.mainapp.dtos.data.RezervacijaDTO;


public class GuestDTO extends KorisnikDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4820560307064911979L;
	private int brojOtkazivanja;
	private Collection<OcenaSmestajaDTO> oceneSmestaja;
	private Collection<RezervacijaDTO> rezervacije;
	private Collection<OcenaHostDTO> oceneVlasnika;
	
	
	public GuestDTO() {
		super();
	}
	public int getBrojOtkazivanja() {
		return brojOtkazivanja;
	}
	public void setBrojOtkazivanja(int brojOtkazivanja) {
		this.brojOtkazivanja = brojOtkazivanja;
	}
	public Collection<OcenaSmestajaDTO> getOceneSmestaja() {
		return oceneSmestaja;
	}
	public void setOceneSmestaja(Collection<OcenaSmestajaDTO> oceneSmestaja) {
		this.oceneSmestaja = oceneSmestaja;
	}
	public Collection<RezervacijaDTO> getRezervacije() {
		return rezervacije;
	}
	public void setRezervacije(Collection<RezervacijaDTO> rezervacije) {
		this.rezervacije = rezervacije;
	}
	public Collection<OcenaHostDTO> getOceneVlasnika() {
		return oceneVlasnika;
	}
	public void setOceneVlasnika(Collection<OcenaHostDTO> oceneVlasnika) {
		this.oceneVlasnika = oceneVlasnika;
	}
	
	

}
