package com.xml.mainapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml.mainapp.model.users.Korisnik;

public interface KorisnikRep extends MongoRepository<Korisnik, String> {
	
	public Korisnik findByEmail(String email);
	public Korisnik findByKorIme(String username);
}
