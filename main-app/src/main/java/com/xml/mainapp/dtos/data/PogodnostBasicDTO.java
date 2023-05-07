package com.xml.mainapp.dtos.data;


public class PogodnostBasicDTO {
	
	private Long id;
	private String naziv;
	
	public PogodnostBasicDTO() {
		super();
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
