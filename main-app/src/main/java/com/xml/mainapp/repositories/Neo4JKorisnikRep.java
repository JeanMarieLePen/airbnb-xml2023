package com.xml.mainapp.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.xml.mainapp.neo4j.model.Korisnik;


public interface Neo4JKorisnikRep extends Neo4jRepository<Korisnik, String> {

}
