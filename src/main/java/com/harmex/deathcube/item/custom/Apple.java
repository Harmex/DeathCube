package com.harmex.deathcube.item.custom;

import net.minecraft.resources.ResourceLocation;

public interface Apple {
    ResourceLocation getItemKey();

    float getMaxHealthBoost();

    int[] getBoostRange();
}
