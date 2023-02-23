package com.harmex.deathcube.item.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

import javax.annotation.Nullable;

public enum ArmorSets implements ArmorSet {
    OAK("oak", null, 0),
    SPRUCE("spruce", null, 0),
    BIRCH("birch", null, 0),
    JUNGLE("jungle", null, 0),
    ACACIA("acacia", null, 0),
    DARK_OAK("dark_oak", null, 0),
    MANGROVE("mangrove", null, 0),
    CHERRY("cherry", null, 0),
    CRIMSON("crimson", null, 0),
    WARPED("warped", null, 0),
    GOLD("gold", null, 0),
    BONE("bone", null, 0),
    STONE("stone", null, 0),
    COPPER("copper", null, 0),
    IRON("iron", null, 0),
    EMERALD("emerald", MobEffects.HERO_OF_THE_VILLAGE, 0),
    DIAMOND("diamond", null, 0),
    OBSIDIAN("obsidian", MobEffects.FIRE_RESISTANCE, 0),
    NETHERITE("netherite", null, 0);

    private final String name;
    private final MobEffect fullSetBonus;
    private final int effectAmplifier;

    ArmorSets(String pName, @Nullable MobEffect pFullSetBonus, int pEffectAmplifier) {
        name = pName;
        fullSetBonus = pFullSetBonus;
        effectAmplifier = pEffectAmplifier;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public @Nullable MobEffect getFullSetBonus() {
        return fullSetBonus;
    }

    @Override
    public int getEffectAmplifier() {
        return effectAmplifier;
    }
}
