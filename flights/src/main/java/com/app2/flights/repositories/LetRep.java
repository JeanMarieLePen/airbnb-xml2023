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

	
	//Ako budemo koristili date za datum leta
	//List<Let> findBylokOdIdAndlokDoIdAnddatumIVremeGreaterThanEqualAndkapacitetGreaterThanEqual(
		//	String lokOdId,String lokDoId,LocalDateTime datumIVreme,int brojPutnika);
	
//	//Inace,
//	@Query("{ 'lokOd' : ?0, 'lokDo' : ?1, 'datumIVreme': { $gte: ?2 }, 'kapacitet': { $gte: ?3 } }")
//	List<Let> findLetByLokOdAndLokDoAndDatumIVremeAndKapacitet(
//			Adresa lokOd,
//			Adresa lokDo,
//			LocalDateTime datumIVreme,
//			int brojPutnika);
	
}
