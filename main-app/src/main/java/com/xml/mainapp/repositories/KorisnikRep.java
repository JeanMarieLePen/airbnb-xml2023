package com.xml.mainapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.mainapp.model.users.Korisnik;

public interface KorisnikRep extends JpaRepository<Korisnik, Long> {
	
	public Korisnik findByEmail(String email);
	public Korisnik findByKorIme(String username);
}
