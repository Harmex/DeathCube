package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.config.DeathCubeCommonConfigs;
import com.harmex.deathcube.item.ModArmorMaterials;
import com.harmex.deathcube.item.ModRarities;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Random;

public class ObsidianArmorItem extends ArmorWithEffectItem {
    public ObsidianArmorItem(EquipmentSlot pSlot) {
        super(ModArmorMaterials.OBSIDIAN, pSlot, new Properties()
                .rarity(ModRarities.EPIC), ArmorSets.OBSIDIAN);
    }
}
