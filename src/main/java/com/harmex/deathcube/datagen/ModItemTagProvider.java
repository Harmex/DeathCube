package com.harmex.deathcube.datagen;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.util.ModTags;
import com.harmex.deathcube.world.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, TagsProvider<Block> p_256467_, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, p_256467_, DeathCube.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        copy(ModTags.Blocks.ZANTHINE_ORES, ModTags.Items.ZANTHINE_ORES);
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        copy(BlockTags.SIGNS, ItemTags.SIGNS);
        copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        tag(ModTags.Items.OAK_SET)
                .add(ModItems.OAK_HELMET.get())
                .add(ModItems.OAK_CHESTPLATE.get())
                .add(ModItems.OAK_LEGGINGS.get())
                .add(ModItems.OAK_BOOTS.get());
        tag(ModTags.Items.SPRUCE_SET)
                .add(ModItems.SPRUCE_HELMET.get())
                .add(ModItems.SPRUCE_CHESTPLATE.get())
                .add(ModItems.SPRUCE_LEGGINGS.get())
                .add(ModItems.SPRUCE_BOOTS.get());
        tag(ModTags.Items.BIRCH_SET)
                .add(ModItems.BIRCH_HELMET.get())
                .add(ModItems.BIRCH_CHESTPLATE.get())
                .add(ModItems.BIRCH_LEGGINGS.get())
                .add(ModItems.BIRCH_BOOTS.get());
        tag(ModTags.Items.JUNGLE_SET)
                .add(ModItems.JUNGLE_HELMET.get())
                .add(ModItems.JUNGLE_CHESTPLATE.get())
                .add(ModItems.JUNGLE_LEGGINGS.get())
                .add(ModItems.JUNGLE_BOOTS.get());
        tag(ModTags.Items.ACACIA_SET)
                .add(ModItems.ACACIA_HELMET.get())
                .add(ModItems.ACACIA_CHESTPLATE.get())
                .add(ModItems.ACACIA_LEGGINGS.get())
                .add(ModItems.ACACIA_BOOTS.get());
        tag(ModTags.Items.DARK_OAK_SET)
                .add(ModItems.DARK_OAK_HELMET.get())
                .add(ModItems.DARK_OAK_CHESTPLATE.get())
                .add(ModItems.DARK_OAK_LEGGINGS.get())
                .add(ModItems.DARK_OAK_BOOTS.get());
        tag(ModTags.Items.MANGROVE_SET)
                .add(ModItems.MANGROVE_HELMET.get())
                .add(ModItems.MANGROVE_CHESTPLATE.get())
                .add(ModItems.MANGROVE_LEGGINGS.get())
                .add(ModItems.MANGROVE_BOOTS.get());
        tag(ModTags.Items.CHERRY_SET)
                .add(ModItems.CHERRY_HELMET.get())
                .add(ModItems.CHERRY_CHESTPLATE.get())
                .add(ModItems.CHERRY_LEGGINGS.get())
                .add(ModItems.CHERRY_BOOTS.get());
        tag(ModTags.Items.CRIMSON_SET)
                .add(ModItems.CRIMSON_HELMET.get())
                .add(ModItems.CRIMSON_CHESTPLATE.get())
                .add(ModItems.CRIMSON_LEGGINGS.get())
                .add(ModItems.CRIMSON_BOOTS.get());
        tag(ModTags.Items.WARPED_SET)
                .add(ModItems.WARPED_HELMET.get())
                .add(ModItems.WARPED_CHESTPLATE.get())
                .add(ModItems.WARPED_LEGGINGS.get())
                .add(ModItems.WARPED_BOOTS.get());
        tag(ModTags.Items.WOODEN_SETS)
                .addTag(ModTags.Items.OAK_SET)
                .addTag(ModTags.Items.SPRUCE_SET)
                .addTag(ModTags.Items.BIRCH_SET)
                .addTag(ModTags.Items.JUNGLE_SET)
                .addTag(ModTags.Items.ACACIA_SET)
                .addTag(ModTags.Items.DARK_OAK_SET)
                .addTag(ModTags.Items.MANGROVE_SET)
                .addTag(ModTags.Items.CHERRY_SET)
                .addTag(ModTags.Items.CRIMSON_SET)
                .addTag(ModTags.Items.WARPED_SET)
                .add(Items.WOODEN_SWORD)
                .add(Items.WOODEN_PICKAXE)
                .add(Items.WOODEN_AXE)
                .add(Items.WOODEN_SHOVEL)
                .add(Items.WOODEN_HOE);
        tag(ModTags.Items.BONE_SET)
                .add(ModItems.BONE_HELMET.get())
                .add(ModItems.BONE_CHESTPLATE.get())
                .add(ModItems.BONE_LEGGINGS.get())
                .add(ModItems.BONE_BOOTS.get());
        tag(ModTags.Items.COPPER_SET)
                .add(ModItems.COPPER_HELMET.get())
                .add(ModItems.COPPER_CHESTPLATE.get())
                .add(ModItems.COPPER_LEGGINGS.get())
                .add(ModItems.COPPER_BOOTS.get());
        tag(ModTags.Items.IRON_SET)
                .add(Items.IRON_HELMET)
                .add(Items.IRON_CHESTPLATE)
                .add(Items.IRON_LEGGINGS)
                .add(Items.IRON_BOOTS);
        tag(ModTags.Items.GOLDEN_SET)
                .add(Items.GOLDEN_HELMET)
                .add(Items.GOLDEN_CHESTPLATE)
                .add(Items.GOLDEN_LEGGINGS)
                .add(Items.GOLDEN_BOOTS);
        tag(ModTags.Items.DIAMOND_SET)
                .add(Items.DIAMOND_HELMET)
                .add(Items.DIAMOND_CHESTPLATE)
                .add(Items.DIAMOND_LEGGINGS)
                .add(Items.DIAMOND_BOOTS);
        tag(ModTags.Items.NETHERITE_SET)
                .add(Items.NETHERITE_HELMET)
                .add(Items.NETHERITE_CHESTPLATE)
                .add(Items.NETHERITE_LEGGINGS)
                .add(Items.NETHERITE_BOOTS);
        tag(ModTags.Items.EMERALD_SET)
                .add(ModItems.EMERALD_HELMET.get())
                .add(ModItems.EMERALD_CHESTPLATE.get())
                .add(ModItems.EMERALD_LEGGINGS.get())
                .add(ModItems.EMERALD_BOOTS.get());
        tag(ModTags.Items.OBSIDIAN_SET)
                .add(ModItems.OBSIDIAN_HELMET.get())
                .add(ModItems.OBSIDIAN_CHESTPLATE.get())
                .add(ModItems.OBSIDIAN_LEGGINGS.get())
                .add(ModItems.OBSIDIAN_BOOTS.get());
        tag(ModTags.Items.UPGRADEABLE)
                .addTag(ModTags.Items.WOODEN_SETS)
                .addTag(ModTags.Items.BONE_SET)
                .addTag(ModTags.Items.COPPER_SET)
                .addTag(ModTags.Items.IRON_SET)
                .addTag(ModTags.Items.GOLDEN_SET)
                .addTag(ModTags.Items.DIAMOND_SET)
                .addTag(ModTags.Items.NETHERITE_SET)
                .addTag(ModTags.Items.EMERALD_SET)
                .addTag(ModTags.Items.OBSIDIAN_SET);
    }
}
