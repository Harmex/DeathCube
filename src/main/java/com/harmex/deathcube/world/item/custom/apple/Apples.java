package com.harmex.deathcube.world.item.custom.apple;

public enum Apples implements Apple {
    DIAMOND(26, new int[] {0, 2}),
    NETHERITE(32,  new int[] {1, 4}),
    BEDROCK(40,  new int[] {2, 6});

    private final float maxHealthBoost;
    private final int[] boostRange;

    Apples(float maxHealthBoost, int[] boostRange) {
        this.maxHealthBoost = maxHealthBoost;
        this.boostRange = boostRange;
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
