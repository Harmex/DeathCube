package com.harmex.deathcube.util.capabilities.mana;


import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientManaData {
    private static float manaLevel;

    public static void set(float pManaLevel) {
        manaLevel = pManaLevel;
    }

    public static float getManaLevel() {
        return manaLevel;
    }
}
