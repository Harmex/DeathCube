package com.harmex.deathcube.equipment;

import com.harmex.deathcube.item.custom.ArmorSet;
import com.harmex.deathcube.item.custom.ArmorSetItem;
import com.harmex.deathcube.item.custom.ArmorSets;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class EquipmentData {
    private Map<ArmorSet, Integer> equippedNumberForArmorSet;
    private final Map<ArmorSet, Boolean> isEffectActiveForArmorSet;

    public EquipmentData() {
        equippedNumberForArmorSet = new HashMap<>();
        isEffectActiveForArmorSet = new HashMap<>();
        for (ArmorSet armorSet : ArmorSets.values()) {
            isEffectActiveForArmorSet.put(armorSet, false);
        }
    }

    public void tick(Player player) {
        for (Map.Entry<ArmorSet, Integer> entry : equippedNumberForArmorSet.entrySet()) {
            ArmorSet armorSet = entry.getKey();
            int equippedCount = entry.getValue();
            if (armorSet.getFullSetBonus() != null) {
                if (equippedCount == 4) {
                    player.addEffect(new MobEffectInstance(armorSet.getFullSetBonus(), 1200, 0, true, true, true));
                    isEffectActiveForArmorSet.put(armorSet, true);
                } else if (isEffectActiveForArmorSet.get(armorSet)) {
                    player.removeEffect(armorSet.getFullSetBonus());
                    isEffectActiveForArmorSet.put(armorSet, false);
                }
            }
        }
    }

    public void copyFrom(EquipmentData pSource) {
        equippedNumberForArmorSet = pSource.equippedNumberForArmorSet;
    }

    public void loadNBTData(CompoundTag pNBT) {
        ListTag listTag = pNBT.getList("EquippedSets", Tag.TAG_COMPOUND);
        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag compoundTag = listTag.getCompound(i);
            ArmorSet armorSet = ArmorSets.getByName(compoundTag.getString("Set"));
            int equippedCount = compoundTag.getInt("Equipped");
            equippedNumberForArmorSet.put(armorSet, equippedCount);
        }
    }

    public void saveNBTData(CompoundTag pNBT) {
        ListTag listTag = new ListTag();
        for (Map.Entry<ArmorSet, Integer> entry : equippedNumberForArmorSet.entrySet()) {
            if (entry.getValue() != 0) {
                CompoundTag tag = new CompoundTag();
                tag.putString("Set", entry.getKey().getName());
                tag.putInt("Equipped", entry.getValue());
                listTag.add(tag);
            }
        }
        pNBT.put("EquippedSets", listTag);
    }

    public Map<ArmorSet, Integer> getEquippedNumberForArmorSet() {
        return equippedNumberForArmorSet;
    }

    public void setEquippedNumberForArmorSet(ArmorSet pArmorSet, int pArmorSetEquippedCount) {
        equippedNumberForArmorSet.put(pArmorSet, pArmorSetEquippedCount);
    }

    public int getArmorSetEquippedCount(Player player, ArmorSet armorSet) {
        int armorSetEquippedCount = 0;
        for (ItemStack itemStack : player.getArmorSlots()) {
            if (itemStack.getItem() instanceof ArmorSetItem armorSetItem) {
                if (armorSetItem.getArmorSet() == armorSet) {
                    armorSetEquippedCount++;
                }
            }
        }
        return armorSetEquippedCount;
    }
}
