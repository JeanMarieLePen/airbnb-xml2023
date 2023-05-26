package com.app2.flights;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app2.flights.model.data.Adresa;
import com.app2.flights.model.data.Porudzbina;
import com.app2.flights.model.user.Administrator;
import com.app2.flights.model.user.RegKor;
import com.app2.flights.model.user.StatusNaloga;
import com.app2.flights.model.user.TipKorisnika;
import com.app2.flights.repositories.KorisnikRep;
import com.app2.flights.services.FlightsServiceGrpc;
import com.xml2023.flights.FlightsGrpc;
import com.xml2023.flights.FlightsGrpc.FlightsImplBase;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication
@EnableMongoRepositories
@EnableDiscoveryClient
//@EnableMongock
@EnableScheduling
public class FlightsApplication {

	//autowired se ne moze koristiti u okviru main metode jer je ona static
	//sto znaci da se u njoj mogu koristiti samo static promenljive ili promenljive koje su u njoj rucno instancirane
	//Autowired anotacijom obezbedjuje se da se nakon instanciranja odgovarajuceg Bean-a (ovde RegKorRep)
	//on upotrebi tamo gde je potrebno. Problem je u tome sto ce se instanciranje izvrsiti nakon izvrsavanja main metode
	//tako da RegKorRep u main metodi zapravo NULL
	//zbog toga uvodimo runner metodu
	@Autowired
	private KorisnikRep korRep;
	public static void main(String[] args) {
		SpringApplication.run(FlightsApplication.class, args);

	}
	@Bean
    CommandLineRunner runner(){
        return args -> {
        	
        	if(korRep.findAll().stream().filter(k -> k.getTip().equals(TipKorisnika.ADMINISTRATOR)).count() == 0) {
        		dodajAdmina("admin1");
        	}
        	if(korRep.findAll().stream().filter(k -> k.getTip().equals(TipKorisnika.REG_KOR)).count() == 0) {
        		System.out.println("BROJ KORISNIKAAAA: " + korRep.findAll().size());
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
        		korRep.save(rk);
        		System.out.println("BROJ KORISNIKA: " + korRep.findAll().size());
        		return;
        	};
        };
    }
	
	public void dodajAdmina(String username) {
    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
    	Adresa adr= new Adresa("Lava Nikolajevica, 21, Beograd",21.525,24.63632);

    	Administrator a= new Administrator("admin", "adminovic", "adminMail@hhh.com",username, bc.encode("1234"),
    			adr, TipKorisnika.ADMINISTRATOR, StatusNaloga.AKTIVAN);
    	korRep.save(a);   	
	}
	
	@Bean int port() {
		return 7976;
	}
	
	public void stop(Server server) {
	    if (server != null) {
	      server.shutdown();
	    }
	}
	
	@Bean
	public FlightsImplBase flightsImplBaseService() {
		return new FlightsServiceGrpc();
	}
	
	@Bean 
	CommandLineRunner runner2() {
		return args -> {
        	Server server = ServerBuilder.forPort(this.port())
    			.addService(this.flightsImplBaseService())
     	        .build();
        	server.start();
    	    System.out.println("gRPC server started, listening on port " + this.port());
    	    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
    	    	System.out.println("Shutting down gRPC server");
		    	stop(server);
		    }));
        };
	}
}
