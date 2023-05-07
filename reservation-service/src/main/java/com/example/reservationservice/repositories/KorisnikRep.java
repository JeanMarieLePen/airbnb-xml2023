package com.example.reservationservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.reservationservice.model.Korisnik;

public interface KorisnikRep extends MongoRepository<Korisnik, String> {
	
	public Korisnik findByEmail(String email);
	public Korisnik findByKorIme(String username);
}
