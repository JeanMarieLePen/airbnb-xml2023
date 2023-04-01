package com.app2.flights.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app2.flights.model.user.Administrator;

public interface AdministratorRep extends MongoRepository<Administrator, String> {

}
