package com.xml.mainapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml.mainapp.model.data.Smestaj;

public interface SmestajRep extends MongoRepository<Smestaj, String> {

}
