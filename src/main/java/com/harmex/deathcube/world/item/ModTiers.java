package com.harmex.deathcube.world.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final Tier BONE = new ForgeTier(1, 111, 3.0f,
            0.5f, 10, BlockTags.NEEDS_STONE_TOOL,
            () -> Ingredient.of(Items.BONE));

    public static final ForgeTier COPPER = new ForgeTier(2, 250, 6.0f,
            2.0f, 15, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(Items.COPPER_INGOT));

    public static final ForgeTier EMERALD = new ForgeTier(3, 536, 8.0f,
            3.0f, 18, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(Items.EMERALD));

    public static final ForgeTier OBSIDIAN = new ForgeTier(4, 2031, 9.0f,
            4.0f, 15, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(Items.OBSIDIAN));
}
