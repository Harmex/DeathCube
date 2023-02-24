package com.harmex.deathcube.equipment;

import com.harmex.deathcube.item.custom.ArmorSet;

import java.util.Map;

public class ClientEquipmentData {
    private static Map<ArmorSet, Integer> equippedNumberForArmorSet;

    public static void set(Map<ArmorSet, Integer> pEquippedNumberForArmorSet) {
        equippedNumberForArmorSet = pEquippedNumberForArmorSet;
    }

    public static Map<ArmorSet, Integer> getEquippedNumberForArmorSet() {
        return equippedNumberForArmorSet;
    }
}
