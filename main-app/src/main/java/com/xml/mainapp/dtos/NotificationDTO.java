package com.xml.mainapp.dtos;

import java.time.LocalDateTime;

public class NotificationDTO {
	private String idRezervacije;
	private String tekst;
	
	public NotificationDTO() {
		super();
	}
	
	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public String getIdRezervacije() {
		return idRezervacije;
	}

	public void setIdRezervacije(String idRezervacije) {
		this.idRezervacije = idRezervacije;
	}
	
}
