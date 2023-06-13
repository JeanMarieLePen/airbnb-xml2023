package com.xml.mainapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.xml.mainapp.model.users.Guest;
import com.xml.mainapp.model.users.Host;
import com.xml.mainapp.model.users.Korisnik;
@Repository
public interface KorisnikRep extends MongoRepository<Korisnik, String> {
	
	public Korisnik findByEmail(String email);
	public Korisnik findByKorIme(String username);
	public Guest findGuestById(String id);
	public Host findHostById(String id);
}
