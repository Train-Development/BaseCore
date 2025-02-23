package com.blonicx.basecore.api.hglabor.enums.ffa;

public enum HGLaborFFAHeros {
    KATARA("Katara"),
    AANG("Aang"),
    TOPH("Toph");


    private final String value;

    HGLaborFFAHeros(String value) {
        this.value = value;
    }

    // Override toString to return the string value directly //
    @Override
    public String toString() {
        return value;
    }
}
