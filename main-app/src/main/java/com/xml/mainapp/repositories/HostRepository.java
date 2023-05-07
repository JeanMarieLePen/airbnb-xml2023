package com.xml.mainapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml.mainapp.model.users.Host;



public interface HostRepository extends MongoRepository<Host, String>{

}
