package com.harmex.deathcube.item.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

public interface ArmorSet {
    String getName();

    MobEffect getFullSetEffect();

    int getEffectAmplifier();

    List<ResourceLocation> getSetPieces();
}
