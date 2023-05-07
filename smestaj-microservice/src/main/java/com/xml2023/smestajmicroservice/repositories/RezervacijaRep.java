package com.xml2023.smestajmicroservice.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml2023.smestajmicroservice.model.data.Rezervacija;
import com.xml2023.smestajmicroservice.model.data.StatusRezervacije;

public interface RezervacijaRep extends MongoRepository<Rezervacija, String>{

	//vrati listu rezervacija koje se odnose na smestaj sa id-om id
	Optional<Collection<Rezervacija>> findBySmestajIdAndStatus(String id, StatusRezervacije status);
}
