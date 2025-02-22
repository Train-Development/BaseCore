package com.blonicx.basecore.api.hglabor.enums;

public enum HGLaborGameMode {
    FFA("FFA");

    private final String value;

    HGLaborGameMode(String value) {
        this.value = value;
    }

    // Override toString to return the string value directly
    @Override
    public String toString() {
        return value;
    }
}