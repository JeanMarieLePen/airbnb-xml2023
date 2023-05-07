package com.xml.mainapp.model.data;

import java.io.Serializable;

public enum StatusRezervacije implements Serializable{
	PENDING(0),
	REZERVISANA(1),
	ODBIJENA(2),
	OTKAZANA(3);
	
	private final int status;
	
	private StatusRezervacije(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
}
