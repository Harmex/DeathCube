package com.harmex.deathcube.util.capabilities.equipment;

import com.harmex.deathcube.world.item.custom.ArmorSet;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ClientEquippedSetsData {
    private static Map<ArmorSet, Integer> equippedNumberForArmorSet;

    public static void set(Map<ArmorSet, Integer> pEquippedNumberForArmorSet) {
        equippedNumberForArmorSet = pEquippedNumberForArmorSet;
    }

    public static Map<ArmorSet, Integer> getEquippedNumberForArmorSet() {
        return equippedNumberForArmorSet;
    }
}
