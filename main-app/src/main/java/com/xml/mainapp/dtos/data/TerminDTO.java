package com.xml.mainapp.dtos.data;

import java.time.LocalDateTime;

public class TerminDTO {

	private Long id;
	private LocalDateTime pocetak;
	private LocalDateTime kraj;
	private SmestajDTO smestaj;
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

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public SmestajDTO getSmestaj() {
		return smestaj;
	}
	public void setSmestaj(SmestajDTO smestaj) {
		this.smestaj = smestaj;
	}
}
