package com.email.emailmicroservice.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.email.emailmicroservice.model.Korisnik;

public interface KorisnikRep extends MongoRepository<Korisnik, String> {
	
	Optional<Korisnik> findByEmail(String email);
}
