package com.app2.flights.model.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.app2.flights.dtos.LetDTO;
import com.app2.flights.model.user.RegKor;

import jakarta.annotation.Nullable;

@Document(collection = "let_db")
public class Let {

	@Id
	private String id;
	
	private Adresa lokOd;
	private Adresa lokDo;
	
	private String brojLeta;
	//brojSlobodnihMesta
	private int kapacitet;
	//cenaPoPutniku
	private double cena;
	//ovo moze biti samo Date?
	private LocalDateTime datumIVreme;
	@DBRef
	@Nullable
	private Collection<Porudzbina> listaPorudzbina;
	
	public Let() {
		super();
	}
	public Let(String id, Adresa lokOd, Adresa lokDo, String brojLeta, int kapacitet, double cena,
			LocalDateTime datumIVreme, Collection<Porudzbina> listaPorudzbina) {
		super();
		this.id = id;
		this.lokOd = lokOd;
		this.lokDo = lokDo;
		this.brojLeta = brojLeta;
		this.kapacitet = kapacitet;
		this.cena = cena;
		this.datumIVreme = datumIVreme;
		this.listaPorudzbina = listaPorudzbina;
	}
	public Let(LetDTO novi) {
		super();
		this.lokOd = new Adresa(novi.getLokOd());
		this.lokDo = new Adresa(novi.getLokDo());
		this.brojLeta = novi.getBrojLeta();
		this.kapacitet = novi.getKapacitet();
		this.cena = novi.getCena();
		this.datumIVreme = novi.getDatumIVreme();
		this.listaPorudzbina = new ArrayList<Porudzbina>();
	}

	public Collection<Porudzbina> getListaPorudzbina() {
		return listaPorudzbina;
	}
	public void setListaPorudzbina(Collection<Porudzbina> listaPorudzbina) {
		this.listaPorudzbina = listaPorudzbina;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLokOdAddress() {
		return lokOd.getAdresa();
	}
	
	public Adresa getLokOd() {
		return lokOd;
	}
	public void setLokOd(Adresa lokOd) {
		this.lokOd = lokOd;
	}
	public void setLokOdAddress(String lokOd) {
		this.lokOd.setAdresa(lokOd);
	}
	
	public Adresa getLokDo() {
		return lokDo;
	}
	
	public String getLokDoAddress() 
	{
		return lokDo.getAdresa();
	}
	public void setLokDo(Adresa lokDo) {
		this.lokDo = lokDo;
	}
	
	public void setLokDoAddress(String lokDo) {
		this.lokDo.setAdresa(lokDo);
	}
	
	public String getBrojLeta() {
		return brojLeta;
	}
	public void setBrojLeta(String brojLeta) {
		this.brojLeta = brojLeta;
	}
	public int getKapacitet() {
		return kapacitet;
	}
	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public LocalDateTime getDatumIVreme() {
		return datumIVreme;
	}
	public void setDatumIVreme(LocalDateTime datumIVreme) {
		this.datumIVreme = datumIVreme;
	}
	public int brZauzetoihMesta() {
		return this.getListaPorudzbina().stream().map(x->x.getBrojKarata()).collect(Collectors.summingInt(Integer::intValue));	 
	}
}
