package com.xml.mainapp.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import com.xml.mainapp.dtos.user.GuestDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateProfileDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1269785345731781974L;
	
	@NotNull
	private GuestDTO guestDTO;
	@NotNull
	private NewSifraDTO novaSifraDTO;
	
	public UpdateProfileDTO() {
		super();
	}

	public GuestDTO getGuestDTO() {
		return guestDTO;
	}

	public void setGuestDTO(GuestDTO guestDTO) {
		this.guestDTO = guestDTO;
	}

	public NewSifraDTO getNovaSifraDTO() {
		return novaSifraDTO;
	}

	public void setNovaSifraDTO(NewSifraDTO novaSifraDTO) {
		this.novaSifraDTO = novaSifraDTO;
	}

	
    
    
}
