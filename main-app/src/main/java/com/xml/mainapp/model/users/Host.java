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
	
	private boolean newNotification;
	private boolean canceledNotification;
	private boolean ratedHostNotification;
	private boolean ratedAccomodationNotification;
	private boolean statusNotification;
	
	
	public Host() {
		super();
	}
		
	public Host(boolean rezAutomatski, boolean istaknuti) {
		super();
		this.rezAutomatski = rezAutomatski;
		this.istaknuti = istaknuti;

	}

	public boolean isNewNotification() {
		return newNotification;
	}

	public void setNewNotification(boolean newNotification) {
		this.newNotification = newNotification;
	}

	public boolean isCanceledNotification() {
		return canceledNotification;
	}

	public void setCanceledNotification(boolean canceledNotification) {
		this.canceledNotification = canceledNotification;
	}

	public boolean isRatedHostNotification() {
		return ratedHostNotification;
	}

	public void setRatedHostNotification(boolean ratedHostNotification) {
		this.ratedHostNotification = ratedHostNotification;
	}

	public boolean isRatedAccomodationNotification() {
		return ratedAccomodationNotification;
	}

	public void setRatedAccomodationNotification(boolean ratedAccomodationNotification) {
		this.ratedAccomodationNotification = ratedAccomodationNotification;
	}

	public boolean isStatusNotification() {
		return statusNotification;
	}

	public void setStatusNotification(boolean statusNotification) {
		this.statusNotification = statusNotification;
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
