package com.xml.mainapp.dtos;

public class NotificationHostDTO {
	
	private String idSmestaja;
	private String idKorisnika;
	private int ocena;
	private boolean istaknuti;
	private TipNotifikacije tipNotifikacije;
	private String tekst;
	
	public enum TipNotifikacije{
		NOVA_REZERVACIJA(0),
		OTKAZANA_REZERVACIJA(1),
		OCENJEN_SMESTAJ(2),
		OCENJEN_HOST(3),
		STATUS_ISTAKNUTOG(4);

		TipNotifikacije(int i) {
			// TODO Auto-generated constructor stub
		}
	}
	public NotificationHostDTO() {
		super();
	}
	
	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public TipNotifikacije getTipNotifikacije() {
		return tipNotifikacije;
	}

	public void setTipNotifikacije(TipNotifikacije tipNotifikacije) {
		this.tipNotifikacije = tipNotifikacije;
	}

	public String getIdSmestaja() {
		return idSmestaja;
	}
	public void setIdSmestaja(String idSmestaja) {
		this.idSmestaja = idSmestaja;
	}
	public String getIdKorisnika() {
		return idKorisnika;
	}
	public void setIdKorisnika(String idKorisnika) {
		this.idKorisnika = idKorisnika;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	public boolean isIstaknuti() {
		return istaknuti;
	}
	public void setIstaknuti(boolean istaknuti) {
		this.istaknuti = istaknuti;
	}
	
	
}
