package com.harmex.deathcube.datagen;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.block.ModBlocks;
import com.harmex.deathcube.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, DeathCube.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        blockItem(ModBlocks.ECHO_AMETHYST_BLOCK.get());
        blockItem(ModBlocks.MATTER_MANIPULATOR.get());
        blockItem(ModBlocks.RESURRECTION_ALTAR.get());

        blockItem(ModBlocks.CHERRY_PLANKS.get());
        plantItem(ModBlocks.CHERRY_SAPLING.get());
        blockItem(ModBlocks.CHERRY_LOG.get());
        blockItem(ModBlocks.STRIPPED_CHERRY_LOG.get());
        blockItem(ModBlocks.STRIPPED_CHERRY_WOOD.get());
        blockItem(ModBlocks.CHERRY_WOOD.get());
        blockItem(ModBlocks.CHERRY_LEAVES.get());
        blockItem(ModBlocks.CHERRY_SLAB.get());
        //blockItem(ModBlocks.CHERRY_FENCE.get());
        blockItem(ModBlocks.CHERRY_STAIRS.get());
        //blockItem(ModBlocks.CHERRY_BUTTON.get());
        blockItem(ModBlocks.CHERRY_PRESSURE_PLATE.get());
        //basicItem(ModBlocks.CHERRY_DOOR.get().asItem());
        //blockItem(ModBlocks.CHERRY_TRAPDOOR.get());
        blockItem(ModBlocks.CHERRY_FENCE_GATE.get());
        basicItem(ModItems.CHERRY_SIGN.get());
        basicItem(ModItems.CHERRY_BOAT.get());

        basicItem(ModItems.ENDER_DRAGON_SCALE.get());
        basicItem(ModItems.WARDEN_HEART.get());
        basicItem(ModItems.FRESH_WATER_BOTTLE.get());
        basicItem(ModItems.ECHO_AMETHYST_SHARD.get());
        basicItem(ModItems.ECHO_AMETHYST_INGOT.get());
        basicItem(ModItems.CHERRY.get());
        basicItem(ModItems.TIME_GEM_APPLE.get());
        basicItem(ModItems.DIAMOND_APPLE.get());
        basicItem(ModItems.NETHERITE_APPLE.get());
        basicItem(ModItems.BEDROCK_APPLE.get());
        basicItem(ModItems.TOTEM_OF_RESURRECTION.get());
        basicItem(ModItems.TIME_GEM.get());
        handheldItem(ModItems.TIME_WAND.get());
        basicItem(ModItems.ENDER_BAG.get());

        basicItem(ModItems.OAK_HELMET.get());
        basicItem(ModItems.OAK_CHESTPLATE.get());
        basicItem(ModItems.OAK_LEGGINGS.get());
        basicItem(ModItems.OAK_BOOTS.get());
        basicItem(ModItems.SPRUCE_HELMET.get());
        basicItem(ModItems.SPRUCE_CHESTPLATE.get());
        basicItem(ModItems.SPRUCE_LEGGINGS.get());
        basicItem(ModItems.SPRUCE_BOOTS.get());
        basicItem(ModItems.BIRCH_HELMET.get());
        basicItem(ModItems.BIRCH_CHESTPLATE.get());
        basicItem(ModItems.BIRCH_LEGGINGS.get());
        basicItem(ModItems.BIRCH_BOOTS.get());
        basicItem(ModItems.JUNGLE_HELMET.get());
        basicItem(ModItems.JUNGLE_CHESTPLATE.get());
        basicItem(ModItems.JUNGLE_LEGGINGS.get());
        basicItem(ModItems.JUNGLE_BOOTS.get());
        basicItem(ModItems.ACACIA_HELMET.get());
        basicItem(ModItems.ACACIA_CHESTPLATE.get());
        basicItem(ModItems.ACACIA_LEGGINGS.get());
        basicItem(ModItems.ACACIA_BOOTS.get());
        basicItem(ModItems.DARK_OAK_HELMET.get());
        basicItem(ModItems.DARK_OAK_CHESTPLATE.get());
        basicItem(ModItems.DARK_OAK_LEGGINGS.get());
        basicItem(ModItems.DARK_OAK_BOOTS.get());
        basicItem(ModItems.MANGROVE_HELMET.get());
        basicItem(ModItems.MANGROVE_CHESTPLATE.get());
        basicItem(ModItems.MANGROVE_LEGGINGS.get());
        basicItem(ModItems.MANGROVE_BOOTS.get());
        basicItem(ModItems.CHERRY_HELMET.get());
        basicItem(ModItems.CHERRY_CHESTPLATE.get());
        basicItem(ModItems.CHERRY_LEGGINGS.get());
        basicItem(ModItems.CHERRY_BOOTS.get());
        basicItem(ModItems.CRIMSON_HELMET.get());
        basicItem(ModItems.CRIMSON_CHESTPLATE.get());
        basicItem(ModItems.CRIMSON_LEGGINGS.get());
        basicItem(ModItems.CRIMSON_BOOTS.get());
        basicItem(ModItems.WARPED_HELMET.get());
        basicItem(ModItems.WARPED_CHESTPLATE.get());
        basicItem(ModItems.WARPED_LEGGINGS.get());
        basicItem(ModItems.WARPED_BOOTS.get());

        basicItem(ModItems.BONE_HELMET.get());
        basicItem(ModItems.BONE_CHESTPLATE.get());
        basicItem(ModItems.BONE_LEGGINGS.get());
        basicItem(ModItems.BONE_BOOTS.get());
        handheldItem(ModItems.BONE_SWORD.get());
        handheldItem(ModItems.BONE_PICKAXE.get());
        handheldItem(ModItems.BONE_AXE.get());
        handheldItem(ModItems.BONE_SHOVEL.get());
        handheldItem(ModItems.BONE_HOE.get());

        basicItem(ModItems.COPPER_HELMET.get());
        basicItem(ModItems.COPPER_CHESTPLATE.get());
        basicItem(ModItems.COPPER_LEGGINGS.get());
        basicItem(ModItems.COPPER_BOOTS.get());
        handheldItem(ModItems.COPPER_SWORD.get());
        handheldItem(ModItems.COPPER_PICKAXE.get());
        handheldItem(ModItems.COPPER_AXE.get());
        handheldItem(ModItems.COPPER_SHOVEL.get());
        handheldItem(ModItems.COPPER_HOE.get());

        basicItem(ModItems.EMERALD_HELMET.get());
        basicItem(ModItems.EMERALD_CHESTPLATE.get());
        basicItem(ModItems.EMERALD_LEGGINGS.get());
        basicItem(ModItems.EMERALD_BOOTS.get());
        handheldItem(ModItems.EMERALD_SWORD.get());
        handheldItem(ModItems.EMERALD_PICKAXE.get());
        handheldItem(ModItems.EMERALD_AXE.get());
        handheldItem(ModItems.EMERALD_SHOVEL.get());
        handheldItem(ModItems.EMERALD_HOE.get());

        basicItem(ModItems.OBSIDIAN_HELMET.get());
        basicItem(ModItems.OBSIDIAN_CHESTPLATE.get());
        basicItem(ModItems.OBSIDIAN_LEGGINGS.get());
        basicItem(ModItems.OBSIDIAN_BOOTS.get());
        handheldItem(ModItems.OBSIDIAN_SWORD.get());
        handheldItem(ModItems.OBSIDIAN_PICKAXE.get());
        handheldItem(ModItems.OBSIDIAN_AXE.get());
        handheldItem(ModItems.OBSIDIAN_SHOVEL.get());
        handheldItem(ModItems.OBSIDIAN_HOE.get());
    }

    private ItemModelBuilder handheldItem(Item item) {
        return handheldItem(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)));
    }

    private ItemModelBuilder handheldItem(ResourceLocation item) {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()));
    }

    private ItemModelBuilder plantItem(Block block) {
        return plantItem(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
    }

    public ItemModelBuilder plantItem(ResourceLocation block) {
        return getBuilder(block.getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(block.getNamespace(), "block/" + block.getPath()));
    }

    private ItemModelBuilder blockItem(Block block) {
        return blockItem(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
    }

    public ItemModelBuilder blockItem(ResourceLocation block) {
        return getBuilder(block.getPath())
                .parent(new ModelFile.UncheckedModelFile(block.getNamespace() + ":block/" + block.getPath()));
    }
}
