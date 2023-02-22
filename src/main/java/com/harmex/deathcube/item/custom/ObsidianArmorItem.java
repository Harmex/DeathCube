package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.config.DeathCubeCommonConfigs;
import com.harmex.deathcube.item.ModArmorMaterials;
import com.harmex.deathcube.item.ModRarities;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ObsidianArmorItem extends ArmorSetItem {
    private boolean isConfigLoaded = false;

    public ObsidianArmorItem(EquipmentSlot pSlot) {
        super(ModArmorMaterials.OBSIDIAN, pSlot, new Properties()
                .rarity(ModRarities.EPIC), ArmorSets.OBSIDIAN);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (!isConfigLoaded) {
            super.isEffectEnabled = DeathCubeCommonConfigs.OBSIDIAN_ARMOR_EFFECT.get();
            super.isDecayEnabled = DeathCubeCommonConfigs.OBSIDIAN_ARMOR_DECAY.get();
            isConfigLoaded = true;
        }
        super.onArmorTick(stack, level, player);
    }
}
