package com.email.emailmicroservice.model;

import java.io.Serializable;

public enum TipKorisnika  implements Serializable {
    ADMINISTRATOR(0),
    REGISTROVANI_KORISNIK(1),
    AGENT_ULOGA(2),
    TEMP_ULOGA(3);

    private final int tip;
    private TipKorisnika(int tip){
        this.tip = tip;
    }
    public int getTip(){
        return tip;
    }
}
