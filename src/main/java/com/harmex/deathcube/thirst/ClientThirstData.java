package com.harmex.deathcube.thirst;

public class ClientThirstData {
    private static int thirstLevel;
    private static float saturationLevel;
    private static float exhaustionLevel;

    public static void set(int pThirst, float pSaturationLevel, float pExhaustionLevel) {
        thirstLevel = pThirst;
        saturationLevel = pSaturationLevel;
        exhaustionLevel = pExhaustionLevel;
    }

    public static int getThirstLevel() {
        return thirstLevel;
    }

    public static float getSaturationLevel() {
        return saturationLevel;
    }

    public static float getExhaustionLevel() {
        return exhaustionLevel;
    }
}
