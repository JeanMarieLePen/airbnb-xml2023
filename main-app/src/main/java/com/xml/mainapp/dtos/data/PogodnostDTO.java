package com.xml.mainapp.dtos.data;

import java.util.Collection;

public class PogodnostDTO {
	private Long id;
	private String naziv;
	private Collection<Long> listaSmestajaIds;
	
	public PogodnostDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Collection<Long> getListaSmestajaIds() {
		return listaSmestajaIds;
	}
	public void setListaSmestajaIds(Collection<Long> listaSmestajaIds) {
		this.listaSmestajaIds = listaSmestajaIds;
	}
	
	
}
