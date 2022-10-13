package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.config.DeathCubeCommonConfigs;
import com.harmex.deathcube.item.ModArmorMaterials;
import com.harmex.deathcube.item.ModCreativeModeTab;
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

public class ObsidianArmorItem extends ArmorItem {
    private static final MobEffectInstance fullSuitEffect =
            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20, 0,
                    true, false, true);

    public ObsidianArmorItem(EquipmentSlot pSlot) {
        super(ModArmorMaterials.OBSIDIAN, pSlot, new Properties()
                .tab(ModCreativeModeTab.DEATHCUBE_ARMORS_TAB)
                .rarity(ModRarities.EPIC)
        );
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (hasFullSuitOn(player)) {
            if (DeathCubeCommonConfigs.OBSIDIAN_ARMOR_EFFECT.get()) {
                fullSuitEffect(player);
            }
            if (DeathCubeCommonConfigs.OBSIDIAN_ARMOR_DECAY.get()) {
                damageArmor(player);
            }
        }
    }

    private boolean hasFullSuitOn(Player player) {
        for (int i = 0; i < 4; i++) {
            if (!(player.getInventory().getArmor(i).getItem() instanceof ObsidianArmorItem)) {
                return false;
            }
        }
        return true;
    }

    private void fullSuitEffect(Player player) {
        if(!player.hasEffect(fullSuitEffect.getEffect())) {
            player.addEffect(fullSuitEffect);
        }
    }

    private void damageArmor(Player player) {
        if (!player.isCreative() && !player.isSpectator()) {
            if(new Random().nextFloat() < 0.01f) {
                player.getInventory().hurtArmor(DamageSource.MAGIC, 1f, new int[]{0, 1, 2, 3});
            }
        }
    }
}
