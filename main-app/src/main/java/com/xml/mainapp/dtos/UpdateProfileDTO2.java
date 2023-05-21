package com.xml.mainapp.dtos;

import java.io.Serializable;

import com.xml.mainapp.dtos.user.HostDTO;
import com.xml.mainapp.dtos.user.HostUpdateDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateProfileDTO2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1211878791752394868L;
	
	@NotNull
	private HostUpdateDTO hostDTO;
	@NotNull
	private NewSifraDTO novaSifraDTO;
	
	
	public HostUpdateDTO getHostDTO() {
		return hostDTO;
	}
	public void setHostDTO(HostUpdateDTO hostDTO) {
		this.hostDTO = hostDTO;
	}
	public NewSifraDTO getNovaSifraDTO() {
		return novaSifraDTO;
	}
	public void setNovaSifraDTO(NewSifraDTO novaSifraDTO) {
		this.novaSifraDTO = novaSifraDTO;
	}
	
	
	
}
