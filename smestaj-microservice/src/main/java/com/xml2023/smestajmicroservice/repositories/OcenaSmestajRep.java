package com.xml2023.smestajmicroservice.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml2023.smestajmicroservice.model.data.OcenaSmestaj;

public interface OcenaSmestajRep extends MongoRepository<OcenaSmestaj, String> {
	public Collection<OcenaSmestaj> findAllBySmestaj(String smestajId);
	public Collection<OcenaSmestaj> findAllByGost(String gostId);
	public Optional<OcenaSmestaj> findByGostAndSmestaj(String gost, String smestaj);
}
