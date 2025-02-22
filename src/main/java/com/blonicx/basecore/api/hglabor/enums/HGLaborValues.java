package com.blonicx.basecore.api.hglabor.enums;

public enum HGLaborValues {
    KILLS("kills"),
    DEATHS("deaths"),
    BOUNTY("bounty"),
    XP("xp"),
    HIGHEST_KILLSTREAK("highestKillStreak"),
    CURRENT_KILLSTREAK("currentKillStreak");

    private final String value;

    HGLaborValues(String value) {
        this.value = value;
    }

    // Override toString to return the string value directly
    @Override
    public String toString() {
        return value;
    }
}