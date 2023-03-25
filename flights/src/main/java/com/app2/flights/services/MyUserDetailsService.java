package com.app2.flights.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app2.flights.model.user.Korisnik;
import com.app2.flights.repositories.KorisnikRep;
import com.app2.flights.security.MyUserDetails;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired 
	private KorisnikRep korisnikRep;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		Korisnik tempKorisnik = korisnikRep.findByEmail(email);
		if(tempKorisnik == null) {
			throw new UsernameNotFoundException("Nema korisnika sa tom email adresom.");
		}
		
		MyUserDetails myUserDetails = new MyUserDetails(tempKorisnik);
		System.out.println("Korisnik: " + myUserDetails.getUsername() + ", ULOGA:" + myUserDetails.getAuthorities());
		return myUserDetails;
	}
}
