package com.xml.mainapp.initialize;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.xml.mainapp.model.data.Adresa;
import com.xml.mainapp.model.data.Pogodnost;
import com.xml.mainapp.model.data.Rezervacija;
import com.xml.mainapp.model.data.Smestaj;
import com.xml.mainapp.model.users.Guest;
import com.xml.mainapp.model.users.Host;
import com.xml.mainapp.model.users.Korisnik;
import com.xml.mainapp.model.users.OcenaHost;
import com.xml.mainapp.model.users.StatusNaloga;
import com.xml.mainapp.model.users.TipKorisnika;
import com.xml.mainapp.repositories.AdresaRep;
import com.xml.mainapp.repositories.GuestRepository;
import com.xml.mainapp.repositories.HostRepository;
import com.xml.mainapp.repositories.KorisnikRep;
import com.xml.mainapp.repositories.PogodnostRepository;

@ChangeLog
public class DatabaseChangeLog {

//	@Autowired
//	private AdresaRep adrRep;
//	@Autowired
//	private PogodnostRepository pogRep;
	@ChangeSet(order = "001", id = "seedDatabase", author = "")
	public void seedDatabase(GuestRepository guestRep, HostRepository hostRep, AdresaRep adrRep, PogodnostRepository pogRep, KorisnikRep korRep) {
		List<Adresa> listaAdresa = new ArrayList<Adresa>();
		listaAdresa.add(makeNewAdresa("Novi Sad, Maksima Gorkog 12", 10.22, 52.02002));
		listaAdresa.add(makeNewAdresa("Kladovo, Omladinska 6", 10.22, 52.02002));
		listaAdresa.add(makeNewAdresa("Beograd, Svetozara Markovica 1", 10.22, 52.02002));
		listaAdresa.add(makeNewAdresa("Subotica, Jovana Nenada BB", 10.22, 52.02002));
	
		adrRep.insert(listaAdresa);
		
		List<Pogodnost> listaPogodnosti = new ArrayList<Pogodnost>();
		listaPogodnosti.add(makeNewPogodnost("air conditioning"));
		listaPogodnosti.add(makeNewPogodnost("room service"));
		listaPogodnosti.add(makeNewPogodnost("hairdresser"));
		listaPogodnosti.add(makeNewPogodnost("gym"));
		listaPogodnosti.add(makeNewPogodnost("luggage locker"));
		listaPogodnosti.add(makeNewPogodnost("safety deposit box"));
		listaPogodnosti.add(makeNewPogodnost("doctor on call"));
		
		pogRep.insert(listaPogodnosti);
		
		List<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
		listaKorisnika.add(makeNewKorisnik("temp@gmail.com", "zdravko", "tempUsername", "$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq", "Todovski", StatusNaloga.AKTIVAN, TipKorisnika.GUEST, listaAdresa.get(0)));
		listaKorisnika.add(makeNewKorisnik("arsen@gmail.com", "Mitar", "mitar22", "$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq", "Mitra", StatusNaloga.AKTIVAN, TipKorisnika.GUEST, listaAdresa.get(1)));
		listaKorisnika.add(makeNewKorisnik("smajser@gmail.com", "Slobodan", "sloba", "$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq", "Misovic", StatusNaloga.AKTIVAN, TipKorisnika.GUEST, listaAdresa.get(2)));
		listaKorisnika.add(makeNewKorisnik("npele96@gmail.com", "Nikola", "npele96", "$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq", "Petkovic", StatusNaloga.AKTIVAN, TipKorisnika.HOST, listaAdresa.get(3)));
	
		korRep.insert(listaKorisnika);
		
		List<Guest> listaGostiju = new ArrayList<Guest>();
		listaGostiju.add(makeNewGuest(listaKorisnika.get(0), 0, listaKorisnika.get(0).getId()));
		listaGostiju.add(makeNewGuest(listaKorisnika.get(1), 5, listaKorisnika.get(1).getId()));
		listaGostiju.add(makeNewGuest(listaKorisnika.get(2), 0, listaKorisnika.get(2).getId()));
		
		guestRep.insert(listaGostiju);
		
		List<Host> listaHostova = new ArrayList<Host>();
		listaHostova.add(makeNewHost(listaKorisnika.get(3), false, false, listaKorisnika.get(3).getId()));
		
		hostRep.insert(listaHostova);
	}
	
	public Adresa makeNewAdresa(String adrStr, double lat, double lng) {
		Adresa adr = new Adresa();
		adr.setAdresa(adrStr);
		adr.setLat(lat);
		adr.setLng(lng);
		return adr;
	}
	
	public Pogodnost makeNewPogodnost(String naziv) {
		List<String> pogodnosti= new ArrayList<String>();
		pogodnosti.add("air conditioning");
		pogodnosti.add("room service");
		pogodnosti.add("hairdresser");
		pogodnosti.add("gym");
		pogodnosti.add("luggage locker");
		pogodnosti.add("safety deposit box");
		pogodnosti.add("safety deposit box");
		pogodnosti.add("doctor on call");

		Pogodnost p = new Pogodnost(pogodnosti);
		return p;
	}
	
	public Korisnik makeNewKorisnik(String email, String ime, String kor_ime, String lozinka, String prezime, StatusNaloga status_naloga, TipKorisnika tip_korisnika, Adresa adresa) {
		Korisnik k = new Korisnik();
		k.setSlike(new ArrayList<byte[]>());
		k.setEmail(email);
		k.setIme(ime);
		k.setPrezime(prezime);
		k.setKorIme(kor_ime);
		k.setLozinka(lozinka);
		k.setStatusNaloga(status_naloga);
		k.setTipKorisnika(tip_korisnika);
		k.setAdresa(adresa);
		return k;
	}
	
	public Guest makeNewGuest(Korisnik k, int brojOtkazivanja, String id) {
		Guest g = new Guest();
		
		k.setSlike(new ArrayList<byte[]>());
		g.setEmail(k.getEmail());
		g.setIme(k.getIme());
		g.setPrezime(k.getPrezime());
		g.setKorIme(k.getKorIme());
		g.setLozinka(k.getLozinka());
		g.setStatusNaloga(k.getStatusNaloga());
		g.setTipKorisnika(k.getTipKorisnika());
		g.setAdresa(k.getAdresa());
		g.setBrojOtkazivanja(brojOtkazivanja);
		g.setId(id);
		return g;
	}
	public Host makeNewHost(Korisnik k, boolean istaknuti, boolean rez_automatski, String id) {
		Host h = new Host();
		
		h.setEmail(k.getEmail());
		h.setIme(k.getIme());
		h.setPrezime(k.getPrezime());
		h.setKorIme(k.getKorIme());
		h.setLozinka(k.getLozinka());
		h.setStatusNaloga(k.getStatusNaloga());
		h.setTipKorisnika(k.getTipKorisnika());
		h.setAdresa(k.getAdresa());
		k.setSlike(new ArrayList<byte[]>());
		h.setIstaknuti(istaknuti);
		h.setRezAutomatski(rez_automatski);
		h.setId(id);
		return h;
	}
}
