package com.harmex.deathcube.util.capabilities.equipment;

import com.harmex.deathcube.world.item.custom.ArmorSet;
import com.harmex.deathcube.world.item.custom.ArmorSetItem;
import com.harmex.deathcube.world.item.custom.ArmorSets;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import java.util.HashMap;
import java.util.Map;

@AutoRegisterCapability
public class EquippedSetsData {
    private Map<ArmorSet, Integer> equippedCountForArmorSet;
    private final Map<ArmorSet, Boolean> isEffectActiveForArmorSet;

    public EquippedSetsData() {
        equippedCountForArmorSet = new HashMap<>();
        isEffectActiveForArmorSet = new HashMap<>();
        for (ArmorSet armorSet : ArmorSets.values()) {
            isEffectActiveForArmorSet.put(armorSet, false);
        }
    }

    public void tick(Player pPlayer) {
        for (Map.Entry<ArmorSet, Integer> entry : equippedCountForArmorSet.entrySet()) {
            ArmorSet armorSet = entry.getKey();
            int equippedCount = entry.getValue();
            if (armorSet.getFullSetBonus() != null) {
                if (equippedCount == 4) {
                    pPlayer.addEffect(new MobEffectInstance(armorSet.getFullSetBonus(), 1209, 0, true, true, true));
                    isEffectActiveForArmorSet.put(armorSet, true);
                } else if (isEffectActiveForArmorSet.get(armorSet)) {
                    pPlayer.removeEffect(armorSet.getFullSetBonus());
                    isEffectActiveForArmorSet.put(armorSet, false);
                }
            }
        }
    }

    public void copyFrom(EquippedSetsData pSource) {
        equippedCountForArmorSet = pSource.equippedCountForArmorSet;
    }

    public void loadNBTData(CompoundTag pNBT) {
        ListTag equippedSetsListTag = pNBT.getList("EquippedSets", Tag.TAG_COMPOUND);
        for (int i = 0; i < equippedSetsListTag.size(); i++) {
            CompoundTag equippedSetTag = equippedSetsListTag.getCompound(i);
            ArmorSet armorSet = ArmorSets.getByName(equippedSetTag.getString("Set"));
            int equippedCount = equippedSetTag.getInt("Equipped");
            equippedCountForArmorSet.put(armorSet, equippedCount);
        }
    }

    public void saveNBTData(CompoundTag pNBT) {
        ListTag equippedSetsListTag = new ListTag();
        for (Map.Entry<ArmorSet, Integer> entry : equippedCountForArmorSet.entrySet()) {
            if (entry.getValue() != 0) {
                CompoundTag equippedSetTag = new CompoundTag();
                equippedSetTag.putString("Set", entry.getKey().getName());
                equippedSetTag.putInt("Equipped", entry.getValue());
                equippedSetsListTag.add(equippedSetTag);
            }
        }
        pNBT.put("EquippedSets", equippedSetsListTag);
    }

    public Map<ArmorSet, Integer> getEquippedCountForArmorSet() {
        return equippedCountForArmorSet;
    }

    public void setEquippedNumberForArmorSet(ArmorSet pArmorSet, int pEquippedCount) {
        equippedCountForArmorSet.put(pArmorSet, pEquippedCount);
    }

    public int getArmorSetEquippedCount(Player pPlayer, ArmorSet pArmorSet) {
        int armorSetEquippedCount = 0;
        for (ItemStack equippedArmorItemStack : pPlayer.getArmorSlots()) {
            if (equippedArmorItemStack.getItem() instanceof ArmorSetItem equippedArmorSetItem) {
                if (equippedArmorSetItem.getArmorSet() == pArmorSet) {
                    armorSetEquippedCount++;
                }
            }
        }
        return armorSetEquippedCount;
    }
}
