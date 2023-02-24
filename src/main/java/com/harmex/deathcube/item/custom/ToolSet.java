package com.harmex.deathcube.item.custom;

public interface ToolSet {
    String getName();

    /**
     * @return Attack Damage Modifier for -> Sword, Shovel, Pickaxe, Axe, Hoe
     */
    float[] getAttackDamageModifier();

    /**
     * @return Attack Speed Modifier for -> Sword, Shovel, Pickaxe, Axe, Hoe
     */
    float[] getAttackSpeedModifier();
}
