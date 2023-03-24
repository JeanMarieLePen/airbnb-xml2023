package com.app2.flights.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app2.flights.model.data.Porudzbina;

public interface PorudzbinaRep extends MongoRepository<Porudzbina, String>{
	List<Porudzbina> findAllByLet(String letId);
}
