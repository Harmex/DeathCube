package com.harmex.deathcube.world.item.custom.set;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;

public class ArmorSetItem extends ArmorItem {
    private final ArmorSet armorSet;

    public ArmorSetItem(ArmorSet pArmorSet, ArmorItem.Type pArmorType) {
        super(pArmorSet.getArmorMaterial(), pArmorType, new Properties().rarity(pArmorSet.getRarity()));
        armorSet = pArmorSet;
    }

    public ArmorSet getArmorSet() {
        return armorSet;
    }
}
