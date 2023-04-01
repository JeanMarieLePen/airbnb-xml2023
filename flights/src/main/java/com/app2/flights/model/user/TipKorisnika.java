package com.app2.flights.model.user;

import java.io.Serializable;

public enum TipKorisnika  implements Serializable {
    NEREGISTROVANI(0),
    REG_KOR(1),
    ADMINISTRATOR(2);

    private final int tip;
    private TipKorisnika(int tip){
        this.tip = tip;
    }
    public int getTip(){
        return tip;
    }
}
