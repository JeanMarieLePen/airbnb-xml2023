package com.xml.mainapp.dtos;

import java.io.Serializable;

import com.xml.mainapp.dtos.user.HostDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateProfileDTO2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1211878791752394868L;
	
	@NotNull
	private HostDTO hostDTO;
	@NotNull
	private NewSifraDTO novaSifraDTO;
	
	
	public UpdateProfileDTO2() {
		super();
	}
	public HostDTO getHostDTO() {
		return hostDTO;
	}
	public void setHostDTO(HostDTO hostDTO) {
		this.hostDTO = hostDTO;
	}
	public NewSifraDTO getNovaSifraDTO() {
		return novaSifraDTO;
	}
	public void setNovaSifraDTO(NewSifraDTO novaSifraDTO) {
		this.novaSifraDTO = novaSifraDTO;
	}
	
}
