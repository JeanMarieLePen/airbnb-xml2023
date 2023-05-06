package com.email.emailmicroservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.email.emailmicroservice.model.Korisnik;

public interface KorisnikRep extends JpaRepository<Korisnik, Long> {
	
	Optional<Korisnik> findByEmail(String email);
}
