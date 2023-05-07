package com.example.reservationservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.reservationservice.model.Pogodnost;



public interface PogodnostRepository extends MongoRepository<Pogodnost, String> {

}
