package com.xml2023.smestajmicroservice.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml2023.smestajmicroservice.model.data.Smestaj;

public interface SmestajRep extends MongoRepository<Smestaj, String> {
	Optional<Collection<Smestaj>> findAllByVlasnik(String hostId);
}
