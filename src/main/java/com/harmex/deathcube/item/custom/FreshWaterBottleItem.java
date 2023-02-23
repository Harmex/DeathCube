package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.thirst.DrinkProperties;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class FreshWaterBottleItem extends DrinkableItem {
    public FreshWaterBottleItem() {
        super(new Item.Properties(),
                new DrinkProperties.Builder()
                        .hydration(8)
                        .saturationModifier(10)
                        .build()
        );
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        super.finishUsingItem(pStack, pLevel, pLivingEntity);

        if (!pStack.isEmpty() && pLivingEntity instanceof ServerPlayer player) {
            player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE, 1));
        }

        return pStack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE, 1) : pStack;
    }

}
