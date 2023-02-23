package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.config.DeathCubeCommonConfigs;
import com.harmex.deathcube.item.ModRarities;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EmeraldArmorItem extends ArmorSetItem {
    private boolean isConfigLoaded = false;

    public EmeraldArmorItem(EquipmentSlot pSlot) {
        super(ArmorSets.EMERALD, pSlot, new Item.Properties()
                .rarity(ModRarities.RARE));
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (!isConfigLoaded) {
            super.isEffectEnabled = DeathCubeCommonConfigs.EMERALD_ARMOR_EFFECT.get();
            super.isDecayEnabled = DeathCubeCommonConfigs.EMERALD_ARMOR_DECAY.get();
            isConfigLoaded = true;
        }
        super.onArmorTick(stack, level, player);
    }
}
