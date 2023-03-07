package com.harmex.deathcube.util.capabilities.equipment;

import com.harmex.deathcube.world.item.custom.set.ArmorSet;
import com.harmex.deathcube.world.item.custom.totem.Totems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.EnumMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ClientEquipmentData {
    private static Map<ArmorSet, Integer> equippedNumberForArmorSet;
    private static EnumMap<Totems, Boolean> equippedTotems;

    public static void set(Map<ArmorSet, Integer> pEquippedNumberForArmorSet, EnumMap<Totems, Boolean> pEquippedTotems) {
        equippedNumberForArmorSet = pEquippedNumberForArmorSet;
        equippedTotems = pEquippedTotems;
    }

    public static Map<ArmorSet, Integer> getEquippedNumberForArmorSet() {
        return equippedNumberForArmorSet;
    }

    public static EnumMap<Totems, Boolean> getEquippedTotems() {
        return equippedTotems;
    }
}
