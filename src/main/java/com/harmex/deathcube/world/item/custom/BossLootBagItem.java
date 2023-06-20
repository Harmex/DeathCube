package com.harmex.deathcube.world.item.custom;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.util.LootBagsLoots;
import com.harmex.deathcube.world.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.*;

public class BossLootBagItem extends Item {
    private final String bossName;
    private final ResourceLocation lootLocation;
    private final RandomSource randomSource;

    public BossLootBagItem(Properties pProperties, String pBossName) {
        super(pProperties);
        bossName = pBossName.toLowerCase();
        lootLocation = new ResourceLocation(DeathCube.MODID, "loot_bags/" + bossName);
        randomSource = RandomSource.create();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack lootBag = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide()) {

            List<ItemStack> finalLoot = processLoot(pPlayer.getLuck());

            for (ItemStack loot : finalLoot) {
                if (!pPlayer.addItem(loot)) {
                    ItemEntity itemEntity = new ItemEntity(pLevel, pPlayer.getX() + EntityType.ITEM.getWidth() / 2,
                            pPlayer.getY(), pPlayer.getZ() + EntityType.ITEM.getWidth() / 2, loot);
                    itemEntity.setThrower(pPlayer.getUUID());
                    itemEntity.setNoPickUpDelay();
                    pLevel.addFreshEntity(itemEntity);
                }
            }
            lootBag.setCount(lootBag.getCount() - 1);

        }
        return InteractionResultHolder.sidedSuccess(lootBag, pLevel.isClientSide());
    }

    private List<ItemStack> processLoot(float pLuck) {
        List<ItemStack> finalLoot = new ArrayList<>();
        if (bossName.equals("galterius")) {
            for (Map.Entry<Item, Float> entry : LootBagsLoots.GALTERIUS_BAG.entrySet()) {
                if (randomSource.nextFloat() <= Mth.clamp(entry.getValue() + entry.getValue() * (pLuck / 2), 0, 1)) {
                    finalLoot.add(entry.getKey().getDefaultInstance());
                }
            }
        }
        return finalLoot;
    }
}
