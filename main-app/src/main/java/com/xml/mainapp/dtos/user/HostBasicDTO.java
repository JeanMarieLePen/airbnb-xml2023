package com.xml.mainapp.dtos.user;

import java.util.Collection;

import com.xml.mainapp.dtos.data.SmestajDTO;
import com.xml.mainapp.model.users.OcenaHost;

public class HostBasicDTO {
//	private KorisnikBasicDTO korisnik;
	private String id;
	private String email;
	private String korIme;
	private String ime;
	private String preizme;
	
	private boolean rezAutomatski;
	private boolean istaknuti;
	private Collection<SmestajDTO> smestajList;
	private Collection<OcenaHost> ocene;
	private Long version;
	
	
	public HostBasicDTO() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getKorIme() {
		return korIme;
	}
	public void setKorIme(String korIme) {
		this.korIme = korIme;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPreizme() {
		return preizme;
	}
	public void setPreizme(String preizme) {
		this.preizme = preizme;
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
	public Collection<SmestajDTO> getSmestajList() {
		return smestajList;
	}
	public void setSmestajList(Collection<SmestajDTO> smestajList) {
		this.smestajList = smestajList;
	}
	public Collection<OcenaHost> getOcene() {
		return ocene;
	}
	public void setOcene(Collection<OcenaHost> ocene) {
		this.ocene = ocene;
	}
	
	
	
}
