package com.harmex.deathcube.item.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Random;

public class ArmorWithEffectItem extends ArmorItem {
    private final ArmorSet armorSet;
    private final MobEffectInstance fullSetEffect;
    private final List<ResourceLocation> setPieces;
    private boolean isEffectActive;
    private boolean isEffectEnabled;
    private boolean isDecayEnabled;

    public ArmorWithEffectItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties, ArmorSet pArmorSet) {
        super(pMaterial, pSlot, pProperties);
        armorSet = pArmorSet;
        fullSetEffect = new MobEffectInstance(armorSet.getFullSetEffect(),
                1200, armorSet.getEffectAmplifier(),
                true, false, true);
        setPieces = armorSet.getSetPieces();
        isEffectEnabled = true;
        isDecayEnabled = true;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (hasFullSetOn(player)) fullSetEffect(player);
    }

    private boolean hasFullSetOn(Player player) {
        for (int i = 0; i < 4; i++) {
            if (!setPieces.contains(ForgeRegistries.ITEMS.getKey(player.getInventory().getArmor(i).getItem()))) {
                return false;
            }
        }
        return true;
    }

    private void fullSetEffect(Player player) {
        if (isEffectEnabled) {
            player.addEffect(fullSetEffect);
            isEffectActive = true;
            if (isDecayEnabled) {
                if (!player.isCreative() && !player.isSpectator()) {
                    if(new Random().nextFloat() < 0.01f) {
                        player.getInventory().hurtArmor(DamageSource.MAGIC, 1f, new int[]{0, 1, 2, 3});
                    }
                }
            }
        } else if (isEffectActive) {
            player.removeEffect(fullSetEffect.getEffect());
            isEffectActive = false;
        }
    }
}
