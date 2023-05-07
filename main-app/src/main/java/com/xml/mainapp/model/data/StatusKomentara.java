package com.xml.mainapp.model.data;

import java.io.Serializable;

public enum StatusKomentara implements Serializable{

	NA_CEKANJU(0),
	ODOBREN(1),
	OBRISAN(2),
	OGRANICENE_VIDLJIVOSTI(3);
	
	private final int status;

	public int getStatus() {
		return status;
	}

	private StatusKomentara(int status) {
		this.status = status;
	}	
}
