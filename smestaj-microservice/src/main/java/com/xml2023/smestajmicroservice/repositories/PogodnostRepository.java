package com.xml2023.smestajmicroservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml2023.smestajmicroservice.model.data.Pogodnost;


public interface PogodnostRepository extends MongoRepository<Pogodnost, String> {

}
