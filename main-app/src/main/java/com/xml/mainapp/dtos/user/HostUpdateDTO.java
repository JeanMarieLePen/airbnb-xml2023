package com.xml.mainapp.dtos.user;

import java.util.Collection;

import com.xml.mainapp.dtos.KorisnikDTO;
import com.xml.mainapp.dtos.data.SmestajDTO;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public class HostUpdateDTO extends KorisnikDTO{
	@NotNull
	private boolean istaknuti;
	@NotNull
	private boolean rezAutomatski;
	@NotNull
	private int prosecnaOcena;
	
	@NotNull
	private boolean newNotification;
	@NotNull
	private boolean canceledNotification;
	@NotNull
	private boolean ratedHostNotification;
	@NotNull
	private boolean ratedAccomodationNotification;
	@NotNull
	private boolean statusNotification;
	
	public HostUpdateDTO() {
		super();
	}
	public boolean isIstaknuti() {
		return istaknuti;
	}
	public void setIstaknuti(boolean istaknuti) {
		this.istaknuti = istaknuti;
	}
	public boolean isRezAutomatski() {
		return rezAutomatski;
	}
	public void setRezAutomatski(boolean rezAutomatski) {
		this.rezAutomatski = rezAutomatski;
	}
	public int getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(int prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
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
	
}
