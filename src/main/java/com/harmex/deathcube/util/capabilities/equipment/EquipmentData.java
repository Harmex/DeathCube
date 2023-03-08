package com.harmex.deathcube.util.capabilities.equipment;

import com.harmex.deathcube.world.item.custom.set.ArmorSet;
import com.harmex.deathcube.world.item.custom.set.ArmorSetItem;
import com.harmex.deathcube.world.item.custom.set.ArmorSets;
import com.harmex.deathcube.world.item.custom.totem.Totem;
import com.harmex.deathcube.world.item.custom.totem.Totems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

@AutoRegisterCapability
public class EquipmentData {
    private Map<ArmorSet, Integer> equippedCountForArmorSet;
    private final Map<ArmorSet, Boolean> isEffectActiveForArmorSet;

    private EnumMap<Totems, Boolean> equippedTotems;

    private ItemStack equippedTor;

    public EquipmentData() {
        equippedCountForArmorSet = new HashMap<>();
        isEffectActiveForArmorSet = new HashMap<>();
        for (ArmorSet armorSet : ArmorSets.values()) {
            isEffectActiveForArmorSet.put(armorSet, false);
        }

        equippedTotems = new EnumMap<>(Totems.class);
        for (Totem totem : Totems.values()) {
            equippedTotems.put(Totems.valueOf(totem.getName().toUpperCase()), false);
        }

        equippedTor = ItemStack.EMPTY;
    }

    public void tick(Player pPlayer) {
        for (Map.Entry<ArmorSet, Integer> entry : equippedCountForArmorSet.entrySet()) {
            ArmorSet armorSet = entry.getKey();
            int equippedCount = entry.getValue();
            if (armorSet.getFullSetBonus() != null) {
                if (equippedCount == 4) {
                    pPlayer.addEffect(new MobEffectInstance(armorSet.getFullSetBonus(), 1209, 0, true, false, true));
                    isEffectActiveForArmorSet.put(armorSet, true);
                } else if (isEffectActiveForArmorSet.get(armorSet)) {
                    pPlayer.removeEffect(armorSet.getFullSetBonus());
                    isEffectActiveForArmorSet.put(armorSet, false);
                }
            }
        }

        for (Map.Entry<Totems, Boolean> entry : equippedTotems.entrySet()) {
            Totems totem = entry.getKey();
            if (totem.getMobEffect() != null) {
                if (equippedTotems.get(totem)) {
                    if (!pPlayer.hasEffect(totem.getMobEffect())) {
                        pPlayer.addEffect(new MobEffectInstance(totem.getMobEffect(), 1209, totem.getEffectAmplifier(), true, false, true));
                    }
                } else {
                    pPlayer.removeEffect(totem.getMobEffect());
                }
            }
        }
    }

    public void copyFrom(EquipmentData pSource) {
        equippedCountForArmorSet = pSource.equippedCountForArmorSet;
        equippedTotems = pSource.equippedTotems;
        equippedTor = pSource.equippedTor;
    }

    public void loadNBTData(CompoundTag pNBT) {
        ListTag equippedSetsListTag = pNBT.getList("EquippedSets", Tag.TAG_COMPOUND);
        for (int i = 0; i < equippedSetsListTag.size(); i++) {
            CompoundTag equippedSetTag = equippedSetsListTag.getCompound(i);
            ArmorSet armorSet = ArmorSets.getByName(equippedSetTag.getString("Set"));
            int equippedCount = equippedSetTag.getInt("Equipped");
            equippedCountForArmorSet.put(armorSet, equippedCount);
        }

        ListTag equippedTotemsListTag = pNBT.getList("EquippedTotems", Tag.TAG_COMPOUND);
        for (int i = 0; i < equippedTotemsListTag.size(); i++) {
            CompoundTag equippedTotemTag = equippedTotemsListTag.getCompound(i);
            equippedTotems.put(Totems.valueOf(equippedTotemTag.getString("Totem").toUpperCase()), true);
        }

        CompoundTag equippedTorTag = pNBT.getCompound("EquippedTor");
        if (equippedTorTag.size() > 0) {
            equippedTor = ItemStack.of(equippedTorTag);
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

        ListTag equippedTotemsListTag = new ListTag();
        for (Map.Entry<Totems, Boolean> entry : equippedTotems.entrySet()) {
            if (entry.getValue()) {
                CompoundTag equippedTotemTag = new CompoundTag();
                equippedTotemTag.putString("Totem", entry.getKey().getName());
                equippedTotemsListTag.add(equippedTotemTag);
            }
        }
        pNBT.put("EquippedTotems", equippedTotemsListTag);

        CompoundTag equippedTorTag = new CompoundTag();
        if (equippedTor != ItemStack.EMPTY) {
            equippedTor.save(equippedTorTag);
        }
        pNBT.put("EquippedTor", equippedTorTag);
    }

    public Map<ArmorSet, Integer> getEquippedCountForArmorSet() {
        return equippedCountForArmorSet;
    }

    public void setEquippedCountForArmorSet(ArmorSet pArmorSet, int pEquippedCount) {
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

    public EnumMap<Totems, Boolean> getEquippedTotems() {
        return equippedTotems;
    }

    public void setTotemEquipped(Totem pTotem, boolean pSetEquipped) {
        equippedTotems.put(Totems.valueOf(pTotem.getName().toUpperCase()), pSetEquipped);
    }

    public boolean isTotemEquipped(Totem pTotem) {
        return equippedTotems.get(Totems.valueOf(pTotem.getName().toUpperCase()));
    }

    public ItemStack getEquippedTor() {
        return equippedTor;
    }

    public void setEquippedTor(ItemStack equippedTor) {
        this.equippedTor = equippedTor;
    }
}
