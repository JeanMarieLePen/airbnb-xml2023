package com.xml.mainapp.model.data;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document("termini_db")
public class Termin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 334341200024100711L;

	@Id
	private String id;
	
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	private Integer version;
	
	private LocalDateTime pocetak;
	private LocalDateTime kraj;
	
	@DBRef
	private Smestaj smestaj;
	
	public Termin() {
		super();
	}

	public Termin(LocalDateTime odDatum, LocalDateTime doDatum) {
		// TODO Auto-generated constructor stub
		this.pocetak = odDatum;
		this.kraj = doDatum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public LocalDateTime getPocetak() {
		return pocetak;
	}

	public void setPocetak(LocalDateTime pocetak) {
		this.pocetak = pocetak;
	}

	public LocalDateTime getKraj() {
		return kraj;
	}

	public void setKraj(LocalDateTime kraj) {
		this.kraj = kraj;
	}

	public Smestaj getSmestaj() {
		return smestaj;
	}

	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}
	
	// komparatori//////////////////////////////////////////////////////////////////////////////////////
		public boolean zavrsen() {
			return kraj.isBefore(LocalDateTime.now());
		}

		public boolean nijePoceo() {
			return pocetak.isBefore(LocalDateTime.now());
		}

		/**
		 * trenutno aktivan, sadasnjost je izmedju pocetka i kraja, ili kraj nije jos
		 * definisan(null)
		 */
		public boolean uToku() {
			LocalDateTime sada = LocalDateTime.now();
			if (pocetak.isBefore(sada) && (kraj.isAfter(sada) || kraj.isEqual(null)))
				return true;
			else
				return false;
		}

		public boolean preklapanjeSa(Termin t) {
			if(this.pripadaTerminu(t.getPocetak()) || this.pripadaTerminu(t.getKraj()))
				return true;
			else return false;
		}

		/**
		 * @param tren
		 * @return da se "tren" nalazi unutar termina nad kojim se poziva metoda
		 */
		public boolean pripadaTerminu(LocalDateTime tren) {
			if (this.getPocetak().isBefore(tren) && this.getKraj().isAfter(tren))
				return true;
			else
				return false;
		}

		/**
		 * @param t
		 * @return Da li je Termin t unutrasnji za Termin nad kojim se poziva metoda
		 */
		public boolean unutarTermina(Termin t) {
			if (this.getPocetak().isBefore(t.getPocetak()) && this.getKraj().isAfter(t.getKraj()))
				return true;
			else
				return false;
		}

		public boolean nadovezujuSe(Termin t1) {
			return t1.getPocetak().isEqual(this.getKraj()) || t1.getKraj().isEqual(this.getPocetak());
		}
	
}
