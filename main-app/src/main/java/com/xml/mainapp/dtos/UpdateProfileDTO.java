package com.xml.mainapp.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

public class UpdateProfileDTO implements Serializable{

	private KorisnikDTO korisnikDTO;
	private NewSifraDTO novaSifraDTO;
	
	public UpdateProfileDTO() {
		super();
	}

	public UpdateProfileDTO(KorisnikDTO korisnikDTO, NewSifraDTO novaSifraDTO) {
		super();
		this.korisnikDTO = korisnikDTO;
		this.novaSifraDTO = novaSifraDTO;
	}

	public KorisnikDTO getKorisnikDTO() {
		return korisnikDTO;
	}

	public void setKorisnikDTO(KorisnikDTO korisnikDTO) {
		this.korisnikDTO = korisnikDTO;
	}

	public NewSifraDTO getNovaSifraDTO() {
		return novaSifraDTO;
	}

	public void setNovaSifraDTO(NewSifraDTO novaSifraDTO) {
		this.novaSifraDTO = novaSifraDTO;
	}

	
    
    
}
