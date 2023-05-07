package com.xml2023.smestajmicroservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml2023.smestajmicroservice.model.data.Termin;

public interface TerminRepository extends MongoRepository<Termin, String> {

}
