package com.xml.mainapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xml.mainapp.model.users.Korisnik;
import com.xml.mainapp.repositories.KorisnikRep;
import com.xml.mainapp.security.MyUserDetails;

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
