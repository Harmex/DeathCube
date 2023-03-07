package com.harmex.deathcube.world.item.custom.set;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;

public class ArmorSetItem extends ArmorItem {
    private final ArmorSet armorSet;

    public ArmorSetItem(ArmorSet pArmorSet, EquipmentSlot pSlot) {
        super(pArmorSet.getArmorMaterial(), pSlot, new Properties().rarity(pArmorSet.getRarity()));
        armorSet = pArmorSet;
    }

    public ArmorSet getArmorSet() {
        return armorSet;
    }
}
