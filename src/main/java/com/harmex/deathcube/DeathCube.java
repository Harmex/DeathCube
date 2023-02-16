package com.harmex.deathcube;

import com.harmex.deathcube.block.ModBlocks;
import com.harmex.deathcube.block.entity.ModBlockEntities;
import com.harmex.deathcube.block.entity.ModWoodTypes;
import com.harmex.deathcube.config.DeathCubeClientConfigs;
import com.harmex.deathcube.config.DeathCubeCommonConfigs;
import com.harmex.deathcube.entity.ModEntityTypes;
import com.harmex.deathcube.item.ModCreativeModeTab;
import com.harmex.deathcube.item.ModItems;
import com.harmex.deathcube.networking.ModMessages;
import com.harmex.deathcube.painting.ModPaintings;
import com.harmex.deathcube.potion.ModPotions;
import com.harmex.deathcube.recipe.ModRecipes;
import com.harmex.deathcube.screen.ModMenuTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DeathCube.MODID)
public class DeathCube {
    public static final String MODID = "deathcube";
    private static final Logger LOGGER = LogUtils.getLogger();

    public DeathCube() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();



        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModPotions.register(modEventBus);
        ModPaintings.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);


        modEventBus.addListener(this::commonSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, DeathCubeClientConfigs.SPEC, "deathcube-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DeathCubeCommonConfigs.SPEC, "deathcube-common.toml");

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreativeModeTabs);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.CHERRY_SAPLING.getId(), ModBlocks.POTTED_CHERRY_SAPLING);

            Sheets.addWoodType(ModWoodTypes.CHERRY);

            ModMessages.register();
        });

    }


    private void addCreativeModeTabs(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == ModCreativeModeTab.DEATHCUBE_BUILDING_BLOCKS_TAB) {
            event.accept(ModBlocks.CHERRY_LOG);
            event.accept(ModBlocks.CHERRY_WOOD);
            event.accept(ModBlocks.STRIPPED_CHERRY_LOG);
            event.accept(ModBlocks.STRIPPED_CHERRY_WOOD);
            event.accept(ModBlocks.CHERRY_PLANKS);
            event.accept(ModBlocks.CHERRY_STAIRS);
            event.accept(ModBlocks.CHERRY_SLAB);
            event.accept(ModBlocks.CHERRY_FENCE);
            event.accept(ModBlocks.CHERRY_FENCE_GATE);
            event.accept(ModBlocks.CHERRY_DOOR);
            event.accept(ModBlocks.CHERRY_TRAPDOOR);
            event.accept(ModBlocks.CHERRY_PRESSURE_PLATE);
            event.accept(ModBlocks.CHERRY_BUTTON);
            event.accept(ModBlocks.ECHO_AMETHYST_BLOCK);
        }
        if (event.getTab() == ModCreativeModeTab.DEATHCUBE_COLORED_BLOCKS_TAB) {
            event.accept(ModBlocks.ECHO_AMETHYST_BLOCK);
        }
        if (event.getTab() == ModCreativeModeTab.DEATHCUBE_NATURAL_BLOCKS_TAB) {
            event.accept(ModBlocks.CHERRY_LOG);
            event.accept(ModBlocks.CHERRY_LEAVES);
            event.accept(ModBlocks.CHERRY_SAPLING);
            event.accept(ModBlocks.ZANTHINE_ORE);
            event.accept(ModBlocks.DEEPSLATE_ZANTHINE_ORE);
        }
        if (event.getTab() == ModCreativeModeTab.DEATHCUBE_FUNCTIONAL_BLOCKS_TAB) {
            event.accept(ModBlocks.UPGRADING_STATION);
            event.accept(ModBlocks.MATTER_MANIPULATOR);
            event.accept(ModBlocks.RESURRECTION_ALTAR);
            event.accept(ModItems.CHERRY_SIGN);
        }
        if (event.getTab() == ModCreativeModeTab.DEATHCUBE_REDSTONE_BLOCKS_TAB) {
            event.accept(ModBlocks.CHERRY_BUTTON);
            event.accept(ModBlocks.CHERRY_PRESSURE_PLATE);
        }
        if (event.getTab() == ModCreativeModeTab.DEATHCUBE_TOOLS_AND_UTILITIES_TAB) {
            event.accept(ModItems.TOTEM_OF_RESURRECTION);
            event.accept(ModItems.SMALL_BAG);
            event.accept(ModItems.ENDER_BAG);
            event.accept(ModItems.TIME_WAND);
        }
        if (event.getTab() == ModCreativeModeTab.DEATHCUBE_COMBAT_TAB) {
            event.accept(ModItems.BONE_SWORD);
            event.accept(ModItems.COPPER_SWORD);
            event.accept(ModItems.EMERALD_SWORD);
            event.accept(ModItems.OBSIDIAN_SWORD);
            event.accept(ModItems.BONE_AXE);
            event.accept(ModItems.COPPER_AXE);
            event.accept(ModItems.EMERALD_AXE);
            event.accept(ModItems.OBSIDIAN_AXE);
            event.accept(ModItems.OAK_HELMET);
            event.accept(ModItems.OAK_CHESTPLATE);
            event.accept(ModItems.OAK_LEGGINGS);
            event.accept(ModItems.OAK_BOOTS);
            event.accept(ModItems.SPRUCE_HELMET);
            event.accept(ModItems.SPRUCE_CHESTPLATE);
            event.accept(ModItems.SPRUCE_LEGGINGS);
            event.accept(ModItems.SPRUCE_BOOTS);
            event.accept(ModItems.BIRCH_HELMET);
            event.accept(ModItems.BIRCH_CHESTPLATE);
            event.accept(ModItems.BIRCH_LEGGINGS);
            event.accept(ModItems.BIRCH_BOOTS);
            event.accept(ModItems.JUNGLE_HELMET);
            event.accept(ModItems.JUNGLE_CHESTPLATE);
            event.accept(ModItems.JUNGLE_LEGGINGS);
            event.accept(ModItems.JUNGLE_BOOTS);
            event.accept(ModItems.ACACIA_HELMET);
            event.accept(ModItems.ACACIA_CHESTPLATE);
            event.accept(ModItems.ACACIA_LEGGINGS);
            event.accept(ModItems.ACACIA_BOOTS);
            event.accept(ModItems.DARK_OAK_HELMET);
            event.accept(ModItems.DARK_OAK_CHESTPLATE);
            event.accept(ModItems.DARK_OAK_LEGGINGS);
            event.accept(ModItems.DARK_OAK_BOOTS);
            event.accept(ModItems.MANGROVE_HELMET);
            event.accept(ModItems.MANGROVE_CHESTPLATE);
            event.accept(ModItems.MANGROVE_LEGGINGS);
            event.accept(ModItems.MANGROVE_BOOTS);
            event.accept(ModItems.CHERRY_HELMET);
            event.accept(ModItems.CHERRY_CHESTPLATE);
            event.accept(ModItems.CHERRY_LEGGINGS);
            event.accept(ModItems.CHERRY_BOOTS);
            event.accept(ModItems.CRIMSON_HELMET);
            event.accept(ModItems.CRIMSON_CHESTPLATE);
            event.accept(ModItems.CRIMSON_LEGGINGS);
            event.accept(ModItems.CRIMSON_BOOTS);
            event.accept(ModItems.WARPED_HELMET);
            event.accept(ModItems.WARPED_CHESTPLATE);
            event.accept(ModItems.WARPED_LEGGINGS);
            event.accept(ModItems.WARPED_BOOTS);
            event.accept(ModItems.BONE_HELMET);
            event.accept(ModItems.BONE_CHESTPLATE);
            event.accept(ModItems.BONE_LEGGINGS);
            event.accept(ModItems.BONE_BOOTS);
            event.accept(ModItems.COPPER_HELMET);
            event.accept(ModItems.COPPER_CHESTPLATE);
            event.accept(ModItems.COPPER_LEGGINGS);
            event.accept(ModItems.COPPER_BOOTS);
            event.accept(ModItems.EMERALD_HELMET);
            event.accept(ModItems.EMERALD_CHESTPLATE);
            event.accept(ModItems.EMERALD_LEGGINGS);
            event.accept(ModItems.EMERALD_BOOTS);
            event.accept(ModItems.OBSIDIAN_HELMET);
            event.accept(ModItems.OBSIDIAN_CHESTPLATE);
            event.accept(ModItems.OBSIDIAN_LEGGINGS);
            event.accept(ModItems.OBSIDIAN_BOOTS);
        }
        if (event.getTab() == ModCreativeModeTab.DEATHCUBE_FOOD_AND_DRINKS_TAB) {
            event.accept(ModItems.FRESH_WATER_BOTTLE);
            event.accept(ModItems.TIME_GEM_APPLE);
            event.accept(ModItems.DIAMOND_APPLE);
            event.accept(ModItems.NETHERITE_APPLE);
            event.accept(ModItems.BEDROCK_APPLE);
        }
        if (event.getTab() == ModCreativeModeTab.DEATHCUBE_INGREDIENTS_TAB) {
            event.accept(ModItems.ZANTHINE);
            event.accept(ModItems.ECHO_AMETHYST_SHARD);
            event.accept(ModItems.ECHO_AMETHYST_INGOT);
            event.accept(ModItems.ENDER_DRAGON_SCALE);
            event.accept(ModItems.WARDEN_HEART);
        }
        if (event.getTab() == ModCreativeModeTab.DEATHCUBE_SPAWN_EGGS_TAB) {
            event.accept(ModItems.GALTERIUS_SPAWN_EGG);
        }
        //OP Blocks
    }

}
