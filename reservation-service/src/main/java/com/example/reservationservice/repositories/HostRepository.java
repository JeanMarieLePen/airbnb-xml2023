package com.example.reservationservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.reservationservice.model.Host;


public interface HostRepository extends MongoRepository<Host, String>{

}
