package com.app2.flights.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app2.flights.model.data.Adresa;

public interface AdresaRep extends MongoRepository<Adresa, String>{

}
