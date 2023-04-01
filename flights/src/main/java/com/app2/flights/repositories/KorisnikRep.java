package com.app2.flights.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app2.flights.model.user.Korisnik;

public interface KorisnikRep extends MongoRepository<Korisnik, String>{
	public Korisnik findByEmail(String email);
	public Korisnik findByUsername(String username);
}
