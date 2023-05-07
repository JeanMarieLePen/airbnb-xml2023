package com.xml.mainapp.model.users;

import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.beust.jcommander.internal.Nullable;
import com.xml.mainapp.model.data.Smestaj;

@Document(collection = "hosts_db")
public class Host extends Korisnik{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1302296886495477040L;
	
	private boolean rezAutomatski;
	
	private boolean istaknuti;
	@DBRef
	@Nullable
	private Collection<Smestaj> smestajList;
	
	@DBRef
	@Nullable
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
