package com.example.reservationservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.reservationservice.neo4j.model.Korisnik;

public interface Neo4JKorisnikRep extends Neo4jRepository<Korisnik, String> {
	Optional<Korisnik> findById(String id);
}
