package com.harmex.deathcube.world.item.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.List;

public class BossLootBagItem extends Item {
    private final ResourceLocation lootTableLocation;

    public BossLootBagItem(Properties pProperties, ResourceLocation pLootTable) {
        super(pProperties);
        lootTableLocation = pLootTable;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack lootBag = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide()) {
            LootTable lootTable = pLevel.getServer().getLootTables().get(lootTableLocation);

            LootContext.Builder lootContextBuilder = new LootContext.Builder((ServerLevel) pLevel);
            lootContextBuilder.withParameter(LootContextParams.THIS_ENTITY, pPlayer);
            lootContextBuilder.withParameter(LootContextParams.ORIGIN, pPlayer.getEyePosition());

            List<ItemStack> loots = lootTable.getRandomItems(lootContextBuilder.create(LootContextParamSets.CHEST));

            for (ItemStack loot : loots) {
                pPlayer.addItem(loot);
            }

            lootBag.setCount(lootBag.getCount() - 1);
        }
        return InteractionResultHolder.sidedSuccess(lootBag, pLevel.isClientSide());
    }
}
