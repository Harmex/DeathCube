package com.harmex.deathcube.world.item.custom.totem;

import com.harmex.deathcube.world.item.ModRarities;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.Nullable;

public enum Totems implements Totem {
    RESURRECTION("resurrection", null, 0, ModRarities.DIVINE),
    REGENERATION("regeneration", MobEffects.REGENERATION, 0, ModRarities.LEGENDARY),
    RESISTANCE("resistance", MobEffects.DAMAGE_RESISTANCE, 0, ModRarities.LEGENDARY);

    private final String name;
    private final MobEffect mobEffect;
    private final int effectAmplifier;
    private final Rarity rarity;

    Totems(String pName, MobEffect pMobEffect, int pEffectAmplifier, Rarity pRarity) {
        name = pName;
        mobEffect = pMobEffect;
        effectAmplifier = pEffectAmplifier;
        rarity = pRarity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Nullable
    @Override
    public MobEffect getMobEffect() {
        return mobEffect;
    }

    @Override
    public int getEffectAmplifier() {
        return effectAmplifier;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }
}
