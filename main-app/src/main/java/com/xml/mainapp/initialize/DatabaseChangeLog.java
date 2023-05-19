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
import com.xml.mainapp.repositories.AdresaRep;
import com.xml.mainapp.repositories.GuestRepository;
import com.xml.mainapp.repositories.HostRepository;
import com.xml.mainapp.repositories.KorisnikRep;
import com.xml.mainapp.repositories.PogodnostRepository;

@ChangeLog
public class DatabaseChangeLog {

	@ChangeSet(order = "001", id = "seedDatabase", author = "")
	public void seedDatabase(GuestRepository guestRep, HostRepository hostRep, AdresaRep adrRep, PogodnostRepository pogRep, KorisnikRep korRep) {
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
		listaKorisnika.add(makeNewGuest("temp@gmail.com", "zdravko", "tempUsername", "$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq", "Todovski", StatusNaloga.AKTIVAN, TipKorisnika.GUEST, listaAdresa.get(0), 0));
		listaKorisnika.add(makeNewGuest("arsen@gmail.com", "Mitar", "mitar22", "$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq", "Mitra", StatusNaloga.AKTIVAN, TipKorisnika.GUEST, listaAdresa.get(1), 5));
		listaKorisnika.add(makeNewGuest("smajser@gmail.com", "Slobodan", "sloba", "$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq", "Misovic", StatusNaloga.AKTIVAN, TipKorisnika.GUEST, listaAdresa.get(2), 0));
		listaKorisnika.add(makeNewHost("npele96@gmail.com", "Nikola", "npele96", "$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq", "Petkovic", StatusNaloga.AKTIVAN, TipKorisnika.HOST, listaAdresa.get(3), false, false));
	
		korRep.insert(listaKorisnika);
	}
	
	public Adresa makeNewAdresa(String adrStr, double lat, double lng) {
		Adresa adr = new Adresa();
		adr.setAdresa(adrStr);
		adr.setLat(lat);
		adr.setLng(lng);
		return adr;
	}

	
	public Guest makeNewGuest(String email, String ime, String kor_ime, String lozinka, String prezime, StatusNaloga status_naloga, TipKorisnika tip_korisnika, Adresa adresa, int brojOtkazivanja) {
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
		return g;
	}
	public Host makeNewHost(String email, String ime, String kor_ime, String lozinka, String prezime, StatusNaloga status_naloga, TipKorisnika tip_korisnika, Adresa adresa, boolean istaknuti, boolean rez_automatski) {
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
		return h;
	}
}
