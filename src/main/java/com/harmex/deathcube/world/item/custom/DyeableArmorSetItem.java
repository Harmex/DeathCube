package com.harmex.deathcube.world.item.custom;

import com.harmex.deathcube.world.item.custom.set.ArmorSet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.DyeableArmorItem;

public class DyeableArmorSetItem extends DyeableArmorItem {
    private final ArmorSet armorSet;

    public DyeableArmorSetItem(ArmorSet pArmorSet, ArmorItem.Type pArmorType) {
        super(pArmorSet.getArmorMaterial(), pArmorType, new Properties().rarity(pArmorSet.getRarity()));
        armorSet = pArmorSet;
    }

    public ArmorSet getArmorSet() {
        return armorSet;
    }
}
