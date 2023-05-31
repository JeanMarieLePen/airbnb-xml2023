package com.xml.mainapp.initialize;

import java.util.ArrayList;
import java.util.List;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.xml.mainapp.model.data.Adresa;
import com.xml.mainapp.model.data.Pogodnost;
import com.xml.mainapp.model.users.Guest;
import com.xml.mainapp.model.users.Host;
import com.xml.mainapp.model.users.Korisnik;
import com.xml.mainapp.model.users.StatusNaloga;
import com.xml.mainapp.model.users.TipKorisnika;
//import com.xml.mainapp.neo4j.model.OcenaSmestaj;
import com.xml.mainapp.neo4j.model.Smestaj;
import com.xml.mainapp.repositories.KorisnikRep;
import com.xml.mainapp.repositories.Neo4JKorisnikRep;
import com.xml.mainapp.repositories.PogodnostRepository;

@ChangeLog
public class DatabaseChangeLog {

	@ChangeSet(order = "001", id = "seedDatabase", author = "")
	public void seedDatabase(PogodnostRepository pogRep, KorisnikRep korRep, Neo4JKorisnikRep neo4jKorRep) {
		List<Adresa> listaAdresa = new ArrayList<Adresa>();
		listaAdresa.add(makeNewAdresa("Novi Sad, Maksima Gorkog 12", 10.22, 52.02002));
		listaAdresa.add(makeNewAdresa("Kladovo, Omladinska 6", 10.22, 52.02002));
		listaAdresa.add(makeNewAdresa("Beograd, Svetozara Markovica 1", 10.22, 52.02002));
		listaAdresa.add(makeNewAdresa("Subotica, Jovana Nenada BB", 10.22, 52.02002));
		
		Pogodnost pogodnost = new Pogodnost(new ArrayList<String>());
		pogodnost.getNazivi().add("air conditioning");
		pogodnost.getNazivi().add("room service");
		pogodnost.getNazivi().add("hairdresser");
		pogodnost.getNazivi().add("gym");
		pogodnost.getNazivi().add("luggage locker");
		pogodnost.getNazivi().add("safety deposit box");
		pogodnost.getNazivi().add("doctor on call");
		
		pogRep.insert(pogodnost);
		
		List<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
		listaKorisnika.add(makeNewGuest("temp@gmail.com", "zdravko", "tempUsername", "$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq", "Todovski", StatusNaloga.AKTIVAN, TipKorisnika.GUEST, listaAdresa.get(0), 0, true));
		listaKorisnika.add(makeNewGuest("arsen@gmail.com", "Mitar", "mitar22", "$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq", "Mitra", StatusNaloga.AKTIVAN, TipKorisnika.GUEST, listaAdresa.get(1), 5, true));
		listaKorisnika.add(makeNewGuest("smajser@gmail.com", "Slobodan", "sloba", "$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq", "Misovic", StatusNaloga.AKTIVAN, TipKorisnika.GUEST, listaAdresa.get(2), 0, false));
		listaKorisnika.add(makeNewHost("npele96@gmail.com", "Nikola", "npele96", "$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq", "Petkovic", StatusNaloga.AKTIVAN, TipKorisnika.HOST, listaAdresa.get(3), false, false, true, true, true, true, true));
	
		korRep.insert(listaKorisnika);
		
		List<com.xml.mainapp.neo4j.model.Korisnik> listaNeo4j = new ArrayList<com.xml.mainapp.neo4j.model.Korisnik>();
		for(Korisnik k : listaKorisnika.stream().filter(k -> k.getTipKorisnika().equals(TipKorisnika.GUEST)).toList()) {
			com.xml.mainapp.neo4j.model.Korisnik tmpKor = new com.xml.mainapp.neo4j.model.Korisnik();
			tmpKor.setId(k.getId());
//			tmpKor.setDateOcene(new ArrayList<OcenaSmestaj>());
			tmpKor.setRezervisani(new ArrayList<Smestaj>());
			listaNeo4j.add(tmpKor);
		}
		neo4jKorRep.saveAll(listaNeo4j);
		
		
	}
	
	public Adresa makeNewAdresa(String adrStr, double lat, double lng) {
		Adresa adr = new Adresa();
		adr.setAdresa(adrStr);
		adr.setLat(lat);
		adr.setLng(lng);
		return adr;
	}

	
	public Guest makeNewGuest(String email, String ime, String kor_ime, String lozinka, String prezime, StatusNaloga status_naloga, TipKorisnika tip_korisnika, Adresa adresa, int brojOtkazivanja, boolean notifikacije) {
		Guest g = new Guest();
		
		g.setSlike(new ArrayList<byte[]>());
		g.setEmail(email);
		g.setIme(ime);
		g.setPrezime(prezime);
		g.setKorIme(kor_ime);
		g.setLozinka(lozinka);
		g.setStatusNaloga(status_naloga);
		g.setTipKorisnika(tip_korisnika);
		g.setAdresa(adresa);
		g.setBrojOtkazivanja(brojOtkazivanja);
		g.setObradjenaRezervacijaNotifikacija(notifikacije);
		return g;
	}
	public Host makeNewHost(String email, String ime, String kor_ime, String lozinka, String prezime, StatusNaloga status_naloga, TipKorisnika tip_korisnika, Adresa adresa, boolean istaknuti, boolean rez_automatski,
			boolean newNotification, boolean canceledNotification, boolean ratedHostNotification, boolean ratedAccomodationNotification, boolean statusNotification) {
		Host h = new Host();
		
		h.setEmail(email);
		h.setIme(ime);
		h.setPrezime(prezime);
		h.setKorIme(kor_ime);
		h.setLozinka(lozinka);
		h.setStatusNaloga(status_naloga);
		h.setTipKorisnika(tip_korisnika);
		h.setAdresa(adresa);
		h.setSlike(new ArrayList<byte[]>());
		h.setIstaknuti(istaknuti);
		h.setRezAutomatski(rez_automatski);
		h.setNewNotification(newNotification);
		h.setCanceledNotification(canceledNotification);
		h.setRatedHostNotification(ratedHostNotification);
		h.setRatedAccomodationNotification(ratedAccomodationNotification);
		h.setStatusNotification(statusNotification);
		return h;
	}
}
