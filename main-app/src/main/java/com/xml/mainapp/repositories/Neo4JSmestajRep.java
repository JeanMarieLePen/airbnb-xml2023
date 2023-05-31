package com.xml.mainapp.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.xml.mainapp.neo4j.model.Smestaj;

public interface Neo4JSmestajRep extends Neo4jRepository<Smestaj, String> {

}
