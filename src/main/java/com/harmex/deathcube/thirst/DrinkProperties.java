package com.harmex.deathcube.thirst;

public class DrinkProperties {
    private final int hydration;
    private final float saturationModifier;

    public DrinkProperties(Builder pBuilder) {
        this.hydration = pBuilder.hydration;
        this.saturationModifier = pBuilder.saturationModifier;
    }

    public int getHydration() {
        return hydration;
    }

    public float getSaturationModifier() {
        return saturationModifier;
    }

    public static class Builder {
        private int hydration;
        private float saturationModifier;

        public Builder hydration(int pHydration) {
            hydration = pHydration;
            return this;
        }

        public Builder saturationModifier(float pSaturationModifier) {
            saturationModifier = pSaturationModifier;
            return this;
        }

        public DrinkProperties build() {
            return new DrinkProperties(this);
        }
    }
}
