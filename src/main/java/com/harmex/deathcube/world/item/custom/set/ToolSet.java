package com.harmex.deathcube.world.item.custom.set;

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
