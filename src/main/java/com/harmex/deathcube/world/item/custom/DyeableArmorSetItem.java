package com.harmex.deathcube.world.item.custom;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.DyeableArmorItem;

public class DyeableArmorSetItem extends DyeableArmorItem {
    private final ArmorSet armorSet;

    public DyeableArmorSetItem(ArmorSet pArmorSet, EquipmentSlot pEquipmentSlot) {
        super(pArmorSet.getArmorMaterial(), pEquipmentSlot, new Properties().rarity(pArmorSet.getRarity()));
        armorSet = pArmorSet;
    }

    public ArmorSet getArmorSet() {
        return armorSet;
    }
}
