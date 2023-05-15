package com.xml.mainapp.model.users;

import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.xml.mainapp.model.data.OcenaSmestaj;
import com.xml.mainapp.model.data.Rezervacija;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.PositiveOrZero;

//@Document(collection = "guests_db")
public class Guest extends Korisnik{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5877889277853685675L;

	@PositiveOrZero
	private int brojOtkazivanja;
	
	public Guest() {
		super();
	}

	public Guest(@PositiveOrZero int brojOtkazivanja) {
		super();
		this.brojOtkazivanja = brojOtkazivanja;

	}
	
	public int getBrojOtkazivanja() {
		return brojOtkazivanja;
	}

	public void setBrojOtkazivanja(int brojOtkazivanja) {
		this.brojOtkazivanja = brojOtkazivanja;
	}


	
}
