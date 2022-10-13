package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.item.ModCreativeModeTab;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TotemOfResurrectionItem extends Item {
    public TotemOfResurrectionItem() {
        super(new Properties()
                .tab(ModCreativeModeTab.DEATHCUBE_MISC_TAB)
                .stacksTo(1)
                .rarity(Rarity.UNCOMMON)
                .fireResistant()
        );
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        Objects.requireNonNull(pPlayer.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(20.0);
        pPlayer.setHealth(20.0f);
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }
}
