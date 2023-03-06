package com.harmex.deathcube.util;


import com.harmex.deathcube.DeathCube;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> ZANTHINE_ORES = tag("zanthine_ores");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(DeathCube.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> ZANTHINE_ORES = tag("zanthine_ores");
        public static final TagKey<Item> OAK_SET = tag("wooden_sets");
        public static final TagKey<Item> SPRUCE_SET = tag("oak_set");
        public static final TagKey<Item> BIRCH_SET = tag("birch_set");
        public static final TagKey<Item> JUNGLE_SET = tag("jungle_set");
        public static final TagKey<Item> ACACIA_SET = tag("acacia_set");
        public static final TagKey<Item> DARK_OAK_SET = tag("dark_oak_set");
        public static final TagKey<Item> MANGROVE_SET = tag("mangrove_set");
        public static final TagKey<Item> CHERRY_SET = tag("cherry_set");
        public static final TagKey<Item> CRIMSON_SET = tag("crimson_set");
        public static final TagKey<Item> WARPED_SET = tag("warped_set");
        public static final TagKey<Item> WOODEN_SETS = tag("wooden_sets");
        public static final TagKey<Item> BONE_SET = tag("bone_set");
        public static final TagKey<Item> COPPER_SET = tag("copper_set");
        public static final TagKey<Item> IRON_SET = tag("iron_set");
        public static final TagKey<Item> GOLDEN_SET = tag("golden_set");
        public static final TagKey<Item> DIAMOND_SET = tag("diamond_set");
        public static final TagKey<Item> NETHERITE_SET = tag("netherite_set");
        public static final TagKey<Item> EMERALD_SET = tag("emerald_set");
        public static final TagKey<Item> OBSIDIAN_SET = tag("obsidian_set");
        public static final TagKey<Item> UPGRADEABLE = tag("upgradeable");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(DeathCube.MODID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
