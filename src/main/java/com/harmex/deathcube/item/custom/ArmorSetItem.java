package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.item.ModArmorMaterials;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.Random;

public class ArmorSetItem extends ArmorItem {
    private final ArmorSet armorSet;
    private final MobEffectInstance fullSetBonus;
    private boolean isEffectActive;
    protected boolean isEffectEnabled;
    protected boolean isDecayEnabled;

    public ArmorSetItem(ArmorSet pArmorSet, EquipmentSlot pSlot, Properties pProperties) {
        super(ModArmorMaterials.OAK, pSlot, pProperties);
        armorSet = pArmorSet;
        if (pArmorSet.getFullSetBonus() != null) {
            fullSetBonus = new MobEffectInstance(pArmorSet.getFullSetBonus(),
                    1200, pArmorSet.getEffectAmplifier(),
                    true, false, true);
        } else {
            fullSetBonus = null;
        }
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (fullSetBonus != null) {
            if (hasFullSetOn(player)) fullSetEffect(player);
            else if (isEffectActive) {
                if (player.removeEffect(fullSetBonus.getEffect())) isEffectActive = false;
            }
        }
    }

    private boolean hasFullSetOn(Player pPlayer) {
        for (int i = 0; i < 4; i++) {
            if (pPlayer.getInventory().getArmor(i).getItem() instanceof ArmorSetItem armorSetItem) {
                if (armorSetItem.armorSet != armorSet) return false;
            } else return false;
        }
        return true;
    }

    private void fullSetEffect(Player player) {
        if (isEffectEnabled) {
            player.addEffect(fullSetBonus);
            isEffectActive = true;
            if (isDecayEnabled) {
                if (!player.isCreative() && !player.isSpectator()) {
                    if(new Random().nextFloat() < 0.01f) {
                        player.getInventory().hurtArmor(DamageSource.MAGIC, 1f, new int[]{0, 1, 2, 3});
                    }
                }
            }
        }
    }


}
