package com.blonicx.basecore.api.hglabor.enums.ffa;

public enum FFAHeros {
    KATARA("Katara"),
    AANG("Aang"),
    TOPH("Toph");


    private final String value;

    FFAHeros(String value) {
        this.value = value;
    }

    // Override toString to return the string value directly //
    @Override
    public String toString() {
        return value;
    }
}
