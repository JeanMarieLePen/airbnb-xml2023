package com.xml2023.smestajmicroservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml2023.smestajmicroservice.model.data.Smestaj;

public interface SmestajRep extends MongoRepository<Smestaj, String> {

}
