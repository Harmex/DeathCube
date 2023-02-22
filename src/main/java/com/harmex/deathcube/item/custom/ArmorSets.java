package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.DeathCube;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

import java.util.List;

public enum ArmorSets implements ArmorSet{
    EMERALD("emerald", MobEffects.HERO_OF_THE_VILLAGE, 0,
            List.of(new ResourceLocation(DeathCube.MODID, "emerald_helmet"),
                    new ResourceLocation(DeathCube.MODID, "emerald_chestplate"),
                    new ResourceLocation(DeathCube.MODID, "emerald_leggings"),
                    new ResourceLocation(DeathCube.MODID, "emerald_boots"))),
    OBSIDIAN("obsidian", MobEffects.FIRE_RESISTANCE, 0,
            List.of(new ResourceLocation(DeathCube.MODID, "obsidian_helmet"),
                    new ResourceLocation(DeathCube.MODID, "obsidian_chestplate"),
                    new ResourceLocation(DeathCube.MODID, "obsidian_leggings"),
                    new ResourceLocation(DeathCube.MODID, "obsidian_boots")));

    private final String name;
    private final MobEffect fullSetEffect;
    private final int effectAmplifier;
    private final List<ResourceLocation> setPieces;

    ArmorSets(String pName, MobEffect fullSetEffect, int effectAmplifier, List<ResourceLocation> setPieces) {
        this.name = pName;
        this.fullSetEffect = fullSetEffect;
        this.effectAmplifier = effectAmplifier;
        this.setPieces = setPieces;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public MobEffect getFullSetEffect() {
        return fullSetEffect;
    }

    @Override
    public int getEffectAmplifier() {
        return effectAmplifier;
    }

    @Override
    public List<ResourceLocation> getSetPieces() {
        return setPieces;
    }
}
