package com.app2.flights.repositories;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.app2.flights.model.data.Adresa;
import com.app2.flights.model.data.Let;

@Repository
public interface LetRep extends MongoRepository<Let, String>{

	
}
