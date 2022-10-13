package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.item.ModCreativeModeTab;
import com.harmex.deathcube.thirst.ThirstDataProvider;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class FreshWaterBottleItem extends Item {
    public FreshWaterBottleItem() {
        super(new Item.Properties()
                .tab(ModCreativeModeTab.DEATHCUBE_FOODS_TAB)
        );
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, Level pLevel, @NotNull LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide()) {
            if (pLivingEntity instanceof ServerPlayer player) {
                CriteriaTriggers.CONSUME_ITEM.trigger(player, pStack);
                player.awardStat(Stats.ITEM_USED.get(this));

                player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    thirst.addThirst(9);
                    thirst.addThirstSaturation(3);
                });

                if (!player.getAbilities().instabuild) {
                    pStack.shrink(1);
                    if (!pStack.isEmpty()) {
                        player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE, 1));
                    }
                }
            }
        }
        return pStack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : pStack;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack pStack) {
        return 32;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }

}
