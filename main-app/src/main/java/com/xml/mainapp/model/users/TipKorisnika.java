package com.xml.mainapp.model.users;

import java.io.Serializable;

public enum TipKorisnika  implements Serializable {
    NEREGISTROVANI(0),
    GUEST(1),
    HOST(2);

    private final int tip;
    private TipKorisnika(int tip){
        this.tip = tip;
    }
    public int getTip(){
        return tip;
    }
}
