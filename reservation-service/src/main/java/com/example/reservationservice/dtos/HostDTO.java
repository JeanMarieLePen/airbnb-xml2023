package com.example.reservationservice.dtos;


import java.util.Collection;


public class HostDTO extends KorisnikDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4086155352617025191L;

	private boolean istaknuti;
	
	private Collection<SmestajDTO> smestajList;
	private Collection<OcenaHostDTO> ocene;
	private boolean rezAutomatski;
	private int prosecnaOcena;
	
	
	public HostDTO() {
		super();
	}
	
	public int getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(int prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public boolean isRezAutomatski() {
		return rezAutomatski;
	}

	public void setRezAutomatski(boolean rezAutomatski) {
		this.rezAutomatski = rezAutomatski;
	}

	public boolean isIstaknuti() {
		return istaknuti;
	}
	public void setIstaknuti(boolean istaknuti) {
		this.istaknuti = istaknuti;
	}

	public Collection<SmestajDTO> getSmestajList() {
		return smestajList;
	}

	public void setSmestajList(Collection<SmestajDTO> smestajList) {
		this.smestajList = smestajList;
	}

	public Collection<OcenaHostDTO> getOcene() {
		return ocene;
	}

	public void setOcene(Collection<OcenaHostDTO> ocene) {
		this.ocene = ocene;
	}
	

	
}
