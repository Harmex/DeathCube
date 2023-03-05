package com.harmex.deathcube.world.item.custom;

import com.harmex.deathcube.util.capabilities.thirst.DrinkProperties;
import com.harmex.deathcube.util.capabilities.thirst.ThirstDataProvider;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class DrinkableItem extends Item {
    private final int hydration;
    private final float saturationModifier;

    public DrinkableItem(Properties pProperties, DrinkProperties pDrinkProperties) {
        super(pProperties);
        hydration = pDrinkProperties.getHydration();
        saturationModifier = pDrinkProperties.getSaturationModifier();
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide()) {
            if (pLivingEntity instanceof ServerPlayer player) {
                CriteriaTriggers.CONSUME_ITEM.trigger(player, pStack);
                player.awardStat(Stats.ITEM_USED.get(this));

                player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    thirst.drink(hydration, saturationModifier);
                });

                if (!player.getAbilities().instabuild) {
                    pStack.shrink(1);
                }
            }
        }
        return pStack;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }
}
