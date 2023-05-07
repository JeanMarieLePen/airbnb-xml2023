package com.xml.mainapp.dtos.data;

import java.util.Collection;

public class PogodnostDTO {
	private String id;
	private String naziv;
	private Collection<String> listaSmestajaIds;
	
	public PogodnostDTO() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Collection<String> getListaSmestajaIds() {
		return listaSmestajaIds;
	}
	public void setListaSmestajaIds(Collection<String> listaSmestajaIds) {
		this.listaSmestajaIds = listaSmestajaIds;
	}
	
	
}
