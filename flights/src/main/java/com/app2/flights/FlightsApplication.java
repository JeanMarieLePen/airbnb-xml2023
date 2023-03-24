package com.app2.flights;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app2.flights.model.data.Adresa;
import com.app2.flights.model.data.Porudzbina;
import com.app2.flights.model.user.RegKor;
import com.app2.flights.model.user.StatusNaloga;
import com.app2.flights.model.user.TipKorisnika;
import com.app2.flights.repositories.AdresaRep;
import com.app2.flights.repositories.KorisnikRep;
import com.app2.flights.repositories.RegKorRep;

@SpringBootApplication
@EnableMongoRepositories
@EnableDiscoveryClient
public class FlightsApplication {

	//autowired se ne moze koristiti u okviru main metode jer je ona static
	//sto znaci da se u njoj mogu koristiti samo static promenljive ili promenljive koje su u njoj rucno instancirane
	//Autowired anotacijom obezbedjuje se da se nakon instanciranja odgovarajuceg Bean-a (ovde RegKorRep)
	//on upotrebi tamo gde je potrebno. Problem je u tome sto ce se instanciranje izvrsiti nakon izvrsavanja main metode
	//tako da RegKorRep u main metodi zapravo NULL
	//zbog toga uvodimo runner metodu
	@Autowired
	private RegKorRep regKorRep;
	@Autowired
	private KorisnikRep korRep;
	@Autowired 
	private AdresaRep adrRep;
	
	public static void main(String[] args) {
		SpringApplication.run(FlightsApplication.class, args);

	}
	@Bean
    CommandLineRunner runner(){
        return args -> {
        	if(regKorRep.findAll().size()>0) {
        		System.out.println("BROJ KORISNIKAAAA: " + regKorRep.findAll().size());
        		return;
        	};
            // Save demo data to database
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
    		rk.setPorudzbine(new ArrayList<Porudzbina>());
    		Adresa a = new Adresa();
    		a.setAdresa("Lava Nikolajevica, 21, Beograd");
    		a.setLat(21.525);
    		a.setLng(24.63632);
    		adrRep.save(a);
    		rk.setAdresa(a);
    		regKorRep.save(rk);
    		korRep.save(rk);
    		System.out.println("BROJ KORISNIKA: " + regKorRep.findAll().size());
        };
    }
}
