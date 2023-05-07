package com.example.reservationservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.reservationservice.model.Guest;

public interface GuestRepository extends MongoRepository<Guest, String>{

}
