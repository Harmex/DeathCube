package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.DeathCube;
import net.minecraft.resources.ResourceLocation;

public enum Apples implements Apple {
    DIAMOND(new ResourceLocation(DeathCube.MODID, "diamond_apple"), 26, new int[] {0, 2}),
    NETHERITE(new ResourceLocation(DeathCube.MODID, "netherite_apple"), 32,  new int[] {1, 4}),
    BEDROCK(new ResourceLocation(DeathCube.MODID, "bedrock_apple"), 40,  new int[] {2, 6});

    private final ResourceLocation itemKey;
    private final float maxHealthBoost;
    private final int[] boostRange;

    Apples(ResourceLocation itemKey, float maxHealthBoost, int[] boostRange) {
        this.itemKey = itemKey;
        this.maxHealthBoost = maxHealthBoost;
        this.boostRange = boostRange;
    }

    @Override
    public ResourceLocation getItemKey() {
        return itemKey;
    }

    @Override
    public float getMaxHealthBoost() {
        return maxHealthBoost;
    }

    @Override
    public int[] getBoostRange() {
        return boostRange;
    }
}
