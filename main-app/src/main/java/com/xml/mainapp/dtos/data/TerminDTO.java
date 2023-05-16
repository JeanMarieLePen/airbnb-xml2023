package com.xml.mainapp.dtos.data;

import java.time.LocalDateTime;

public class TerminDTO {

	private String id;
	private LocalDateTime pocetak;
	private LocalDateTime kraj;
	private Integer version;
	
	
	public TerminDTO() {
		super();
	}
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getPocetak() {
		return pocetak;
	}
	public void setPocetak(LocalDateTime pocetak) {
		this.pocetak = pocetak;
	}
	public LocalDateTime getKraj() {
		return kraj;
	}
	public void setKraj(LocalDateTime kraj) {
		this.kraj = kraj;
	}
}
