package com.xml.mainapp.dtos;

import java.time.LocalDateTime;

public class NotificationDTO {
	private Long idSmestaja;
	private LocalDateTime vreme;
	private String ime;
	private String prezime;
	private String brojDana;
	
	public NotificationDTO() {
		super();
	}
	
	public Long getIdSmestaja() {
		return idSmestaja;
	}
	public void setIdSmestaja(Long idSmestaja) {
		this.idSmestaja = idSmestaja;
	}
	public LocalDateTime getVreme() {
		return vreme;
	}
	public void setVreme(LocalDateTime vreme) {
		this.vreme = vreme;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getBrojDana() {
		return brojDana;
	}
	public void setBrojDana(String brojDana) {
		this.brojDana = brojDana;
	}
	
	
}
