package com.app2.flights.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app2.flights.model.user.Korisnik;
import com.app2.flights.model.user.RegKor;

public interface KorisnikRep extends MongoRepository<Korisnik, String>{
	public Korisnik findByEmail(String email);
	public Korisnik findByUsername(String username);
	Optional<RegKor> findByApiTokenToken(String token);
}
