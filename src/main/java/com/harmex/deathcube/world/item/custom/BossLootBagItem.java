package com.harmex.deathcube.world.item.custom;

import com.harmex.deathcube.DeathCube;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.Map;

public class BossLootBagItem extends Item {
    private final ResourceLocation lootLocation;
    private final RandomSource randomSource;

    public BossLootBagItem(Properties pProperties, String pBossName) {
        super(pProperties);
        lootLocation = new ResourceLocation(DeathCube.MODID, "loot_bags/" + pBossName);
        randomSource = RandomSource.create();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack lootBag = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide()) {

            LootTable bossLoot = pLevel.getServer().getLootTables().get(lootLocation);

            LootContext.Builder lootContextBuilder = new LootContext.Builder((ServerLevel) pLevel);
            lootContextBuilder.withLuck(pPlayer.getLuck()).withParameter(LootContextParams.THIS_ENTITY, pPlayer);
            lootContextBuilder.withParameter(LootContextParams.ORIGIN, pPlayer.getEyePosition());


            for (ItemStack loot : bossLoot.getRandomItems(lootContextBuilder.create(LootContextParamSets.CHEST))) {
                pPlayer.addItem(loot);
            }
            lootBag.setCount(lootBag.getCount() - 1);

        }
        return InteractionResultHolder.sidedSuccess(lootBag, pLevel.isClientSide());
    }
}
