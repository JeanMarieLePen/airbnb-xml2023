package com.xml.mainapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml.mainapp.model.data.Termin;

public interface TerminRepository extends MongoRepository<Termin, String> {

}
