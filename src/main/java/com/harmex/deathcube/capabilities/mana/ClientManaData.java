package com.harmex.deathcube.capabilities.mana;

public class ClientManaData {
    private static float manaLevel;

    public static void set(float pManaLevel) {
        manaLevel = pManaLevel;
    }

    public static float getManaLevel() {
        return manaLevel;
    }
}
