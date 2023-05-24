package com.xml.mainapp.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xml.mainapp.model.users.OcenaHost;

public interface OcenaHostRep extends MongoRepository<OcenaHost, String>{
	public Optional<OcenaHost> findByGostAndVlasnik(String gost, String vlasnik);
	public Optional<Collection<OcenaHost>> findAllByVlasnik(String vlasnik);

}
