package com.xml.mainapp.dtos.user;


import java.util.Collection;

import com.xml.mainapp.dtos.KorisnikDTO;
import com.xml.mainapp.dtos.data.OcenaSmestajaDTO;

import jakarta.validation.constraints.NotNull;


public class GuestDTO extends KorisnikDTO{
	@NotNull
	private int brojOtkazivanja;
	
	private boolean obradjenaRezervacijaNotifikacija;
	
	public GuestDTO() {
		super();
	}
	
	public boolean isObradjenaRezervacijaNotifikacija() {
		return obradjenaRezervacijaNotifikacija;
	}
	public void setObradjenaRezervacijaNotifikacija(boolean obradjenaRezervacijaNotifikacija) {
		this.obradjenaRezervacijaNotifikacija = obradjenaRezervacijaNotifikacija;
	}

	public int getBrojOtkazivanja() {
		return brojOtkazivanja;
	}
	public void setBrojOtkazivanja(int brojOtkazivanja) {
		this.brojOtkazivanja = brojOtkazivanja;
	}
}
