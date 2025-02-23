package com.blonicx.basecore.api.hglabor.enums.stats;

public enum HGLaborGameModes {
    FFA("FFA");

    private final String value;

    HGLaborGameModes(String value) {
        this.value = value;
    }

    // Override toString to return the string value directly //
    @Override
    public String toString() {
        return value;
    }
}