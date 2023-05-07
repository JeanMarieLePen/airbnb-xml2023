package com.xml.mainapp.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml.mainapp.model.data.Adresa;

public interface AdresaRep extends MongoRepository<Adresa, String> {

}
