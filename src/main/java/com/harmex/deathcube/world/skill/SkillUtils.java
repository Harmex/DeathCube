package com.harmex.deathcube.world.skill;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class SkillUtils {
    public static final ImmutableMap<Item, Float> XP_FOR_ITEM_FISHED =
            ImmutableMap.<Item, Float>builder()
                    .put(Items.COD, 10.0F)
                    .put(Items.SALMON, 25.0F)
                    .put(Items.TROPICAL_FISH, 50.0F)
                    .put(Items.PUFFERFISH, 40.0F)
                    .put(Items.LILY_PAD, 5.0F)
                    .put(Items.LEATHER_BOOTS, 10.0F)
                    .put(Items.LEATHER, 5.0F)
                    .put(Items.BONE, 5.0F)
                    .put(Items.POTION, 5.0F)
                    .put(Items.STRING, 5.0F)
                    .put(Items.FISHING_ROD, 50.0F)
                    .put(Items.BOWL, 5.0F)
                    .put(Items.STICK, 5.0F)
                    .put(Items.INK_SAC, 5.0F)
                    .put(Items.TRIPWIRE_HOOK, 5.0F)
                    .put(Items.ROTTEN_FLESH, 5.0F)
                    .put(Items.BAMBOO, 5.0F)
                    .put(Items.NAME_TAG, 40.0F)
                    .put(Items.SADDLE, 40.0F)
                    .put(Items.BOW, 50.0F)
                    .put(Items.ENCHANTED_BOOK, 50.0F)
                    .put(Items.NAUTILUS_SHELL, 75.0F)
                    .build();

    public static final ImmutableMap<Block, Float> XP_FOR_BLOCK_MINED =
            ImmutableMap.<Block, Float>builder()
                    .put(Blocks.STONE, 2.5F)
                    .put(Blocks.COAL_ORE, 5.0F)
                    .build();

    public static final ImmutableMap<Block, Float> XP_FOR_CROP_HARVESTED =
            ImmutableMap.<Block, Float>builder()
                    .put(Blocks.WHEAT, 2.5F)
                    .put(Blocks.CARROTS, 5.0F)
                    .put(Blocks.POTATOES, 5.0F)
                    .put(Blocks.BEETROOTS, 5.0F)
                    .put(Blocks.SUGAR_CANE, 5.0F)
                    .put(Blocks.PUMPKIN, 5.0F)
                    .put(Blocks.MELON, 5.0F)
                    .put(Blocks.GRASS, 5.0F)
                    .put(Blocks.TALL_GRASS, 5.0F)
                    .put(Blocks.SEAGRASS, 5.0F)
                    .put(Blocks.TALL_SEAGRASS, 5.0F)
                    .build();
}
