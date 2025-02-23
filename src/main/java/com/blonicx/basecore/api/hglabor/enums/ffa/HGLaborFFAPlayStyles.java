package com.blonicx.basecore.api.hglabor.enums.ffa;

public enum HGLaborFFAPlayStyles {
    UHC("UHC"),
    SOUP("Soup");

    private final String value;

    HGLaborFFAPlayStyles(String value) {
        this.value = value;
    }

    // Override toString to return the string value directly //
    @Override
    public String toString() {
        return value;
    }
}
