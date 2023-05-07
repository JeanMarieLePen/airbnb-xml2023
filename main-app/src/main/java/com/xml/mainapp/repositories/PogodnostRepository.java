package com.xml.mainapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml.mainapp.model.data.Pogodnost;



public interface PogodnostRepository extends MongoRepository<Pogodnost, String> {

}
