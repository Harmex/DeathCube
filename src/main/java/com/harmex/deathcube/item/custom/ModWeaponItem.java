package com.harmex.deathcube.item.custom;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Vanishable;

public class ModWeaponItem extends TieredItem implements Vanishable {
    public ModWeaponItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }
}
