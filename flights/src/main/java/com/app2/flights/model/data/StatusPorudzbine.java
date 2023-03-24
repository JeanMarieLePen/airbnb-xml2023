package com.app2.flights.model.data;

import java.io.Serializable;

public enum StatusPorudzbine implements Serializable{
	PENDING(0),
	REZERVISANA(1),
	ODBIJENA(2),
	OTKAZANA(3);
	
	private final int status;
	
	private StatusPorudzbine(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
}
