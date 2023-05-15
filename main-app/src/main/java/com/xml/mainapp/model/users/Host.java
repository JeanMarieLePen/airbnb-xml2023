package com.xml.mainapp.model.users;


import org.springframework.data.mongodb.core.mapping.Document;


//@Document(collection = "hosts_db")
public class Host extends Korisnik{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1302296886495477040L;
	
	private boolean rezAutomatski;
	
	private boolean istaknuti;

	
	
	public Host() {
		super();
	}
		
	public Host(boolean rezAutomatski, boolean istaknuti) {
		super();
		this.rezAutomatski = rezAutomatski;
		this.istaknuti = istaknuti;

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

	
	
}
