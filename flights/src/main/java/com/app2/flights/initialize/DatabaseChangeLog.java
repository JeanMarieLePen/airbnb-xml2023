package com.app2.flights.initialize;

import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app2.flights.model.data.Adresa;
import com.app2.flights.model.user.Administrator;
import com.app2.flights.model.user.RegKor;
import com.app2.flights.model.user.StatusNaloga;
import com.app2.flights.model.user.TipKorisnika;
import com.app2.flights.repositories.KorisnikRep;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

@ChangeLog
public class DatabaseChangeLog {

	@ChangeSet(order = "001", id = "seedDatabase", author = "")
	public void seedDatabase(KorisnikRep korRep) {
		Administrator temp = dodajAdmina("admin1");
		korRep.insert(temp);
		
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		RegKor rk = new RegKor();
		rk.setActivationLink(null);
		rk.setEmail("mongodb@test.com");
		rk.setIme("Zdravko");
		rk.setUsername("zdravinjo");
		rk.setPrezime("Tomasevic");
		rk.setPassword(bc.encode("1234"));
		rk.setStatus(StatusNaloga.AKTIVAN);
		rk.setTip(TipKorisnika.REG_KOR);
		rk.setPorudzbine(new ArrayList<String>());
		Adresa a = new Adresa();
		a.setAdresa("Lava Nikolajevica, 21, Beograd");
		a.setLat(21.525);
		a.setLng(24.63632);
		rk.setAdresa(a);
		korRep.insert(rk);

		System.out.println("BROJ KORISNIKA: " + korRep.findAll().size());
		
		
	}
	
	public Administrator dodajAdmina(String username) {
    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
    	Adresa adr= new Adresa("Lava Nikolajevica, 21, Beograd",21.525,24.63632);

    	Administrator a= new Administrator("admin", "adminovic", "adminMail@hhh.com",username, bc.encode("1234"),
    			adr, TipKorisnika.ADMINISTRATOR, StatusNaloga.AKTIVAN);
    	return a;   	
	}
}
