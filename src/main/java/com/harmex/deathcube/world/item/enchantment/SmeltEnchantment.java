package com.harmex.deathcube.world.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class SmeltEnchantment extends Enchantment {
    protected SmeltEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, EnchantmentCategory.DIGGER, pApplicableSlots);
    }

    @Override
    public int getMinCost(int pLevel) {
        return 15;
    }

    @Override
    public int getMaxCost(int pLevel) {
        return super.getMaxCost(pLevel) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return super.checkCompatibility(pOther) && pOther != Enchantments.SILK_TOUCH;
    }
}
