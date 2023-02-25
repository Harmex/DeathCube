package com.harmex.deathcube.capabilities.equipment;

import com.harmex.deathcube.item.custom.ArmorSet;

import java.util.Map;

public class ClientEquippedSetsData {
    private static Map<ArmorSet, Integer> equippedNumberForArmorSet;

    public static void set(Map<ArmorSet, Integer> pEquippedNumberForArmorSet) {
        equippedNumberForArmorSet = pEquippedNumberForArmorSet;
    }

    public static Map<ArmorSet, Integer> getEquippedNumberForArmorSet() {
        return equippedNumberForArmorSet;
    }
}
