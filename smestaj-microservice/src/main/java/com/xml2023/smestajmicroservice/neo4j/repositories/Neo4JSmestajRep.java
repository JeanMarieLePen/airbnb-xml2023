package com.xml2023.smestajmicroservice.neo4j.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.xml2023.smestajmicroservice.model.data.Smestaj;

public interface Neo4JSmestajRep extends Neo4jRepository<Smestaj, String> {

}
