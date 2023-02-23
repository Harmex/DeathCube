package com.harmex.deathcube.item.custom;

import net.minecraft.world.effect.MobEffect;

import javax.annotation.Nullable;

public interface ArmorSet {
    String getName();

    @Nullable MobEffect getFullSetBonus();

    int getEffectAmplifier();
}
