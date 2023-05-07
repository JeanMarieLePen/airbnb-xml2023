package com.xml.mainapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml.mainapp.model.users.Guest;


public interface GuestRepository extends MongoRepository<Guest, String>{

}
