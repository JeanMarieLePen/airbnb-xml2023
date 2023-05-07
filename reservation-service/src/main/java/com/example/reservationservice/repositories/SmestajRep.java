package com.example.reservationservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.reservationservice.model.Smestaj;


public interface SmestajRep extends MongoRepository<Smestaj, String> {

}
