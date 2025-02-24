package com.blonicx.basecore.api.hglabor.enums.ffa;

public enum FFAPlayStyles {
    UHC("UHC"),
    SOUP("Soup");

    private final String value;

    FFAPlayStyles(String value) {
        this.value = value;
    }

    // Override toString to return the string value directly //
    @Override
    public String toString() {
        return value;
    }
}
