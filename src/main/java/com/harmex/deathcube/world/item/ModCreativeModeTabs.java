package com.harmex.deathcube.world.item;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.world.level.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DeathCube.MODID);

    public static RegistryObject<CreativeModeTab> DEATHCUBE_BUILDING_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("building_blocks", () ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.ECHO_AMETHYST_BLOCK.get()))
                            .title(Component.translatable("itemGroup." + DeathCube.MODID + ".buildingBlocks")).build());

    public static RegistryObject<CreativeModeTab> DEATHCUBE_COLORED_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("colored_blocks", () ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.ECHO_AMETHYST_BLOCK.get()))
                            .title(Component.translatable("itemGroup." + DeathCube.MODID + ".coloredBlocks")).build());

    public static RegistryObject<CreativeModeTab> DEATHCUBE_NATURAL_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("natural_blocks", () ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.ZANTHINE_ORE.get()))
                            .title(Component.translatable("itemGroup." + DeathCube.MODID + ".naturalBlocks")).build());

    public static RegistryObject<CreativeModeTab> DEATHCUBE_FUNCTIONAL_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("functional_blocks", () ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.MATTER_MANIPULATOR.get()))
                            .title(Component.translatable("itemGroup." + DeathCube.MODID + ".functionalBlocks")).build());

    public static RegistryObject<CreativeModeTab> DEATHCUBE_REDSTONE_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("redstone_blocks", () ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(Items.REDSTONE))
                            .title(Component.translatable("itemGroup." + DeathCube.MODID + ".redstone")).build());

    public static RegistryObject<CreativeModeTab> DEATHCUBE_TOOLS_AND_UTILITIES_TAB =
            CREATIVE_MODE_TABS.register("tools", () ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TIME_WAND.get()))
                            .title(Component.translatable("itemGroup." + DeathCube.MODID + ".tools")).build());

    public static RegistryObject<CreativeModeTab> DEATHCUBE_COMBAT_TAB =
            CREATIVE_MODE_TABS.register("combat", () ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.OBSIDIAN_SWORD.get()))
                            .title(Component.translatable("itemGroup." + DeathCube.MODID + ".combat")).build());

    public static RegistryObject<CreativeModeTab> DEATHCUBE_FOOD_AND_DRINKS_TAB =
            CREATIVE_MODE_TABS.register("food_and_drinks", () ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CHERRY.get()))
                            .title(Component.translatable("itemGroup." + DeathCube.MODID + ".foodAndDrinks")).build());

    public static RegistryObject<CreativeModeTab> DEATHCUBE_INGREDIENTS_TAB =
            CREATIVE_MODE_TABS.register("ingredients", () ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TIME_GEM.get()))
                            .title(Component.translatable("itemGroup." + DeathCube.MODID + ".ingredients")).build());

    public static RegistryObject<CreativeModeTab> DEATHCUBE_SPAWN_EGGS_TAB =
            CREATIVE_MODE_TABS.register("spawn_eggs", () ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GALTERIUS_SPAWN_EGG.get()))
                            .title(Component.translatable("itemGroup." + DeathCube.MODID + ".spawnEggs")).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
