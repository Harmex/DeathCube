package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.config.DeathCubeCommonConfigs;
import com.harmex.deathcube.item.ModArmorMaterials;
import com.harmex.deathcube.item.ModRarities;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;

public class EmeraldArmorItem extends ArmorWithEffectItem {
    public EmeraldArmorItem(EquipmentSlot pSlot) {
        super(ModArmorMaterials.EMERALD, pSlot, new Item.Properties()
                .rarity(ModRarities.RARE), ArmorSets.EMERALD);
    }
}
