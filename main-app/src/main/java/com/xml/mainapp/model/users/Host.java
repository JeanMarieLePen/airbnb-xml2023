package com.xml.mainapp.model.users;

import java.util.Collection;

import com.xml.mainapp.model.data.Smestaj;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Host extends Korisnik{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1302296886495477040L;
	
	private boolean rezAutomatski;
	
	private boolean istaknuti;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vlasnik")
	private Collection<Smestaj> smestajList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vlasnik")
	private Collection<OcenaHost> ocene;
	
	public Host() {
		super();
	}
		
	public Host(boolean rezAutomatski, boolean istaknuti, Collection<Smestaj> smestajList,
			Collection<OcenaHost> ocene) {
		super();
		this.rezAutomatski = rezAutomatski;
		this.istaknuti = istaknuti;
		this.smestajList = smestajList;
		this.ocene = ocene;
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
	public Collection<Smestaj> getSmestajList() {
		return smestajList;
	}
	public void setSmestajList(Collection<Smestaj> smestajList) {
		this.smestajList = smestajList;
	}
	public Collection<OcenaHost> getOcene() {
		return ocene;
	}
	public void setOcene(Collection<OcenaHost> ocene) {
		this.ocene = ocene;
	}
	
	
}
