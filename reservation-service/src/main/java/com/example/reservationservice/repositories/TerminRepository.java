package com.example.reservationservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.reservationservice.model.Termin;

public interface TerminRepository extends MongoRepository<Termin, String> {

}
