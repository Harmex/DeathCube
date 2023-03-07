package com.harmex.deathcube.world.item.custom.totem;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Rarity;

import javax.annotation.Nullable;

public interface Totem {
    String getName();

    @Nullable
    MobEffect getMobEffect();

    int getEffectAmplifier();

    Rarity getRarity();
}
