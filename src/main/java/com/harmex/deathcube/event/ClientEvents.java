package com.harmex.deathcube.event;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.client.KeyBindings;
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
import com.mojang.datafixers.util.Either;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.List;
import java.util.Objects;

import static com.harmex.deathcube.client.gui.overlay.ModGuiOverlay.isSkillVisible;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = DeathCube.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onInput(InputEvent.Key event) {
            if (KeyBindings.keyToggleSkillGui.consumeClick()) {
                isSkillVisible = !isSkillVisible;
            }
        }

        @SubscribeEvent
        public static void onTooltipColorRender(RenderTooltipEvent.Color event) {
            Rarity itemRarity = event.getItemStack().getRarity();
            Style rarityStyle = itemRarity.getStyleModifier().apply(Style.EMPTY);
            int rarityColor = Objects.requireNonNull(rarityStyle.getColor()).getValue() + 0xFF000000;
            event.setBorderStart(rarityColor);
            event.setBorderEnd(rarityColor);
        }

        @SubscribeEvent
        public static void onTooltipGatherComponents(RenderTooltipEvent.GatherComponents event) {
            List<Either<FormattedText, TooltipComponent>> tooltipElements = event.getTooltipElements();

            int removedElementCount = 0;
            for (int i = 0; i < tooltipElements.size() + removedElementCount; i++) {
                if (tooltipElements.get(i - removedElementCount).left().isPresent()) {
                    FormattedText text = tooltipElements.get(i - removedElementCount).left().get();
                    if (text instanceof MutableComponent component) {
                        if (component.getContents() instanceof TranslatableContents contents) {
                            if (contents.getKey().startsWith("item.modifiers.")) {
                                tooltipElements.remove(i - removedElementCount);
                                tooltipElements.remove(i - removedElementCount - 1);
                                removedElementCount += 2;
                            } else if (contents.getKey().startsWith("attribute.modifier.")) {
                                tooltipElements.remove(i - removedElementCount);
                                removedElementCount++;
                            } else if (contents.getKey().startsWith("enchantment.")) {
                                tooltipElements.remove(i - removedElementCount);
                                removedElementCount++;
                            }
                        } else if (component.getSiblings().size() > 0 && component.getSiblings().get(0).getContents() instanceof TranslatableContents contents) {
                            if (contents.getKey().startsWith("attribute.modifier.")) {
                                tooltipElements.remove(i - removedElementCount);
                                removedElementCount++;
                            }
                        }
                    }
                }
            }
        }

        @SubscribeEvent
        public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Pre event) {
            if (Objects.equals(event.getOverlay().id(), new ResourceLocation("minecraft", "player_health"))
                    || Objects.equals(event.getOverlay().id(), new ResourceLocation("minecraft", "food_level"))
                    || Objects.equals(event.getOverlay().id(), new ResourceLocation("minecraft", "armor_level"))) {
                event.setCanceled(true);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = DeathCube.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void clientSetup(final FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.UPGRADING_STATION_MENU.get(), UpgradingStationScreen::new);
            MenuScreens.register(ModMenuTypes.MATTER_MANIPULATOR_MENU.get(), MatterManipulatorScreen::new);
            MenuScreens.register(ModMenuTypes.RESURRECTION_ALTAR_MENU.get(), ResurrectionAltarScreen::new);
        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBindings.keyToggleSkillGui);
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
            event.registerAboveAll("skills_levels", ModGuiOverlay.SKILLS_LEVELS);
        }

        @SubscribeEvent
        public static void addCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
            if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_BUILDING_BLOCKS_TAB.get()) {
                event.accept(ModBlocks.ECHO_AMETHYST_BLOCK);
            }
            if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_COLORED_BLOCKS_TAB.get()) {
                event.accept(ModBlocks.ECHO_AMETHYST_BLOCK);
            }
            if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_NATURAL_BLOCKS_TAB.get()) {
                event.accept(ModBlocks.ZANTHINE_ORE);
                event.accept(ModBlocks.DEEPSLATE_ZANTHINE_ORE);
            }
            if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_FUNCTIONAL_BLOCKS_TAB.get()) {
                event.accept(ModBlocks.UPGRADING_STATION);
                event.accept(ModBlocks.MATTER_MANIPULATOR);
                event.accept(ModBlocks.RESURRECTION_ALTAR);
            }
            if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_REDSTONE_BLOCKS_TAB.get()) {
                event.accept(Blocks.REDSTONE_BLOCK);
            }
            if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_TOOLS_AND_UTILITIES_TAB.get()) {
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
            if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_COMBAT_TAB.get()) {
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
                event.accept(ModItems.GALTERIUS_LOOT_BAG);
            }
            if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_FOOD_AND_DRINKS_TAB.get()) {
                event.accept(ModItems.FRESH_WATER_BOTTLE);
                event.accept(ModItems.TIME_GEM_APPLE);
                event.accept(ModItems.DIAMOND_APPLE);
                event.accept(ModItems.NETHERITE_APPLE);
                event.accept(ModItems.BEDROCK_APPLE);
            }
            if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_INGREDIENTS_TAB.get()) {
                event.accept(ModItems.ZANTHINE);
                event.accept(ModItems.ECHO_AMETHYST_SHARD);
                event.accept(ModItems.ECHO_AMETHYST_INGOT);
                event.accept(ModItems.ENDER_DRAGON_SCALE);
                event.accept(ModItems.WARDEN_HEART);
            }
            if (event.getTab() == ModCreativeModeTabs.DEATHCUBE_SPAWN_EGGS_TAB.get()) {
                event.accept(ModItems.GALTERIUS_SPAWN_EGG);
                event.accept(ModItems.ZANUZAL_SPAWN_EGG);
                event.accept(ModItems.NAERVUS_SPAWN_EGG);
                event.accept(ModItems.AZRATHAL_SPAWN_EGG);
                event.accept(ModItems.BORZADON_SPAWN_EGG);
            }
            //OP Blocks
        }
    }
}
