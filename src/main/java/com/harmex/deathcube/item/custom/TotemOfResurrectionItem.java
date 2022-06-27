package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.item.ModCreativeModeTab;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
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
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if (!Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.deathcube.shift").withStyle(ChatFormatting.GRAY));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.deathcube.totem_of_resurrection").withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        Objects.requireNonNull(pPlayer.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(20.0);
        pPlayer.setHealth(20.0f);
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }
}
