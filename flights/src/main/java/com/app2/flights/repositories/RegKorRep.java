package com.app2.flights.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app2.flights.model.data.Porudzbina;
import com.app2.flights.model.user.Korisnik;
import com.app2.flights.model.user.RegKor;

public interface RegKorRep extends MongoRepository<RegKor, String>{
	Optional<Porudzbina> findByPorudzbineId(String id);
	public RegKor findByEmail(String email);
	public RegKor findByUsername(String username);
}
