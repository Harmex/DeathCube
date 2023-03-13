package com.harmex.deathcube.event;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.client.gui.overlay.ModGuiOverlay;
import com.harmex.deathcube.client.gui.screens.inventory.MatterManipulatorScreen;
import com.harmex.deathcube.client.gui.screens.inventory.ResurrectionAltarScreen;
import com.harmex.deathcube.client.gui.screens.inventory.UpgradingStationScreen;
import com.harmex.deathcube.client.model.*;
import com.harmex.deathcube.client.renderer.blockentity.ResurrectionAltarRenderer;
import com.harmex.deathcube.client.renderer.entity.*;
import com.harmex.deathcube.world.entity.ModEntityTypes;
import com.harmex.deathcube.world.inventory.ModMenuTypes;
import com.harmex.deathcube.world.item.ModCreativeModeTabs;
import com.harmex.deathcube.world.item.ModItems;
import com.harmex.deathcube.world.level.block.ModBlocks;
import com.harmex.deathcube.world.level.block.entity.ModBlockEntities;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = DeathCube.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModBusEvents {
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        MenuScreens.register(ModMenuTypes.UPGRADING_STATION_MENU.get(), UpgradingStationScreen::new);
        MenuScreens.register(ModMenuTypes.MATTER_MANIPULATOR_MENU.get(), MatterManipulatorScreen::new);
        MenuScreens.register(ModMenuTypes.RESURRECTION_ALTAR_MENU.get(), ResurrectionAltarScreen::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AzrathalModel.AZRATHAL_LAYER, AzrathalModel::createBodyLayer);
        event.registerLayerDefinition(BorzadonModel.BORZADON_LAYER, BorzadonModel::createBodyLayer);
        event.registerLayerDefinition(GalteriusModel.GALTERIUS_LAYER, GalteriusModel::createBodyLayer);
        event.registerLayerDefinition(NaervusModel.NAERVUS_LAYER, NaervusModel::createBodyLayer);
        event.registerLayerDefinition(ZanuzalModel.ZANUZAL_LAYER, ZanuzalModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.AZRATHAL.get(), AzrathalRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.BORZADON.get(), BorzadonRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.GALTERIUS.get(), GalteriusRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.NAERVUS.get(), NaervusRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.ZANUZAL.get(), ZanuzalRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntities.RESURRECTION_ALTAR_BLOCK_ENTITY.get(), ResurrectionAltarRenderer::new);
    }

    @SubscribeEvent
    public static void registerGuiOverlay(RegisterGuiOverlaysEvent event) {
        event.registerAbove(new ResourceLocation("minecraft", "boss_event_progress"),
                "player_health", ModGuiOverlay.PLAYER_HEALTH);
        event.registerAbove(new ResourceLocation(DeathCube.MODID, "player_health"),
                "armor_level", ModGuiOverlay.ARMOR_LEVEL);
        /*event.registerAbove(new ResourceLocation(DeathCube.MODID, "armor_level"),
                "player_mana", ModGuiOverlay.PLAYER_MANA);*/
        event.registerAbove(new ResourceLocation(DeathCube.MODID, "armor_level"),
                "food_level", ModGuiOverlay.FOOD_LEVEL);
        event.registerAbove(new ResourceLocation(DeathCube.MODID, "food_level"),
                "thirst_level", ModGuiOverlay.THIRST_LEVEL);
        event.registerAboveAll("experience_bar", ModGuiOverlay.EXPERIENCE_BAR);
    }

    @SubscribeEvent
    public static void addCreativeModeTabs(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_BUILDING_BLOCKS_TAB) {
            event.accept(ModBlocks.ECHO_AMETHYST_BLOCK);
        }
        if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_COLORED_BLOCKS_TAB) {
            event.accept(ModBlocks.ECHO_AMETHYST_BLOCK);
        }
        if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_NATURAL_BLOCKS_TAB) {
            event.accept(ModBlocks.ZANTHINE_ORE);
            event.accept(ModBlocks.DEEPSLATE_ZANTHINE_ORE);
        }
        if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_FUNCTIONAL_BLOCKS_TAB) {
            event.accept(ModBlocks.UPGRADING_STATION);
            event.accept(ModBlocks.MATTER_MANIPULATOR);
            event.accept(ModBlocks.RESURRECTION_ALTAR);
        }
        if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_REDSTONE_BLOCKS_TAB) {

        }
        if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_TOOLS_AND_UTILITIES_TAB) {
            event.accept(ModItems.BONE_SHOVEL);
            event.accept(ModItems.BONE_PICKAXE);
            event.accept(ModItems.BONE_AXE);
            event.accept(ModItems.BONE_HOE);
            event.accept(ModItems.COPPER_SHOVEL);
            event.accept(ModItems.COPPER_PICKAXE);
            event.accept(ModItems.COPPER_AXE);
            event.accept(ModItems.COPPER_HOE);
            event.accept(ModItems.EMERALD_SHOVEL);
            event.accept(ModItems.EMERALD_PICKAXE);
            event.accept(ModItems.EMERALD_AXE);
            event.accept(ModItems.EMERALD_HOE);
            event.accept(ModItems.OBSIDIAN_SHOVEL);
            event.accept(ModItems.OBSIDIAN_PICKAXE);
            event.accept(ModItems.OBSIDIAN_AXE);
            event.accept(ModItems.OBSIDIAN_HOE);
            event.accept(ModItems.ENDER_BAG);
            event.accept(ModItems.TIME_WAND);
            event.accept(ModItems.TOTEM_OF_RESURRECTION);
            event.accept(ModItems.TOTEM_OF_REGENERATION);
            event.accept(ModItems.TOTEM_OF_RESISTANCE);
        }
        if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_COMBAT_TAB) {
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
            event.accept(ModItems.GALTERIUS_HELMET);
            event.accept(ModItems.GALTERIUS_CHESTPLATE);
            event.accept(ModItems.GALTERIUS_LEGGINGS);
            event.accept(ModItems.GALTERIUS_BOOTS);
        }
        if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_FOOD_AND_DRINKS_TAB) {
            event.accept(ModItems.FRESH_WATER_BOTTLE);
            event.accept(ModItems.TIME_GEM_APPLE);
            event.accept(ModItems.DIAMOND_APPLE);
            event.accept(ModItems.NETHERITE_APPLE);
            event.accept(ModItems.BEDROCK_APPLE);
        }
        if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_INGREDIENTS_TAB) {
            event.accept(ModItems.ZANTHINE);
            event.accept(ModItems.ECHO_AMETHYST_SHARD);
            event.accept(ModItems.ECHO_AMETHYST_INGOT);
            event.accept(ModItems.ENDER_DRAGON_SCALE);
            event.accept(ModItems.WARDEN_HEART);
        }
        if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_SPAWN_EGGS_TAB) {
            event.accept(ModItems.GALTERIUS_SPAWN_EGG);
            event.accept(ModItems.ZANUZAL_SPAWN_EGG);
            event.accept(ModItems.NAERVUS_SPAWN_EGG);
            event.accept(ModItems.AZRATHAL_SPAWN_EGG);
            event.accept(ModItems.BORZADON_SPAWN_EGG);
        }
        //OP Blocks
    }
}

