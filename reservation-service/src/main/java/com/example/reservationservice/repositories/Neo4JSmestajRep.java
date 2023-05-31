package com.example.reservationservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.reservationservice.neo4j.model.Smestaj;


public interface Neo4JSmestajRep extends Neo4jRepository<Smestaj, String> {
} 
