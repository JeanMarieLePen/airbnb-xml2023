package com.xml.mainapp.model.users;

import java.io.Serializable;

public enum StatusNaloga implements Serializable {

    AKTIVAN(0),
    NA_CEKANJU(1),
    OBRISAN(2),
    SUSPENDOVAN(3);

    private final int status;

    private StatusNaloga(int status){
        this.status=status;
    }
    public int getStatus(){
        return status;
    }
}
