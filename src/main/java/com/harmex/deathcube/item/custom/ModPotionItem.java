package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.thirst.ThirstDataProvider;
import com.harmex.deathcube.thirst.DrinkProperties;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.Level;

public class ModPotionItem extends PotionItem {
    private final int hydration;
    private final float saturationModifier;

    public ModPotionItem(Properties pProperties, DrinkProperties pThirstProperties) {
        super(pProperties);
        hydration = pThirstProperties.getHydration();
        saturationModifier = pThirstProperties.getSaturationModifier();
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

                if (!player.isCreative()) {
                    pStack.shrink(1);
                }
            }
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
