package com.xml2023.smestajmicroservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml2023.smestajmicroservice.model.data.Cenovnik;

public interface CenovnikRepository extends MongoRepository<Cenovnik, String> {

}
