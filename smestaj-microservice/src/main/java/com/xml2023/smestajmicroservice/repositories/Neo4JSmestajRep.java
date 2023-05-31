package com.xml2023.smestajmicroservice.repositories;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.xml2023.mainapp.neo4j.model.Smestaj;

public interface Neo4JSmestajRep extends Neo4jRepository<Smestaj, String> {

//	@Query("MATCH (k:Korisnik)-[:REZERVISE]->")
//	List<Smestaj> findRecommended(String userId);

}
