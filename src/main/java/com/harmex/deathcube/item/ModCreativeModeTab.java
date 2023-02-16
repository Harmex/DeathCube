package com.harmex.deathcube.item;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DeathCube.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static CreativeModeTab DEATHCUBE_BUILDING_BLOCKS_TAB;
    public static CreativeModeTab DEATHCUBE_COLORED_BLOCKS_TAB;
    public static CreativeModeTab DEATHCUBE_NATURAL_BLOCKS_TAB;
    public static CreativeModeTab DEATHCUBE_FUNCTIONAL_BLOCKS_TAB;
    public static CreativeModeTab DEATHCUBE_REDSTONE_BLOCKS_TAB;
    public static CreativeModeTab DEATHCUBE_TOOLS_AND_UTILITIES_TAB;
    public static CreativeModeTab DEATHCUBE_COMBAT_TAB;
    public static CreativeModeTab DEATHCUBE_FOOD_AND_DRINKS_TAB;
    public static CreativeModeTab DEATHCUBE_INGREDIENTS_TAB;
    public static CreativeModeTab DEATHCUBE_SPAWN_EGGS_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        DEATHCUBE_BUILDING_BLOCKS_TAB = event.registerCreativeModeTab(new ResourceLocation(DeathCube.MODID, "building_blocks"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.CHERRY_PLANKS.get()))
                        .title(Component.translatable("itemGroup.deathcube.buildingBlocks")).build());

        DEATHCUBE_COLORED_BLOCKS_TAB = event.registerCreativeModeTab(new ResourceLocation(DeathCube.MODID, "colored_blocks"),
                builder -> builder.icon(() -> new ItemStack(Blocks.AIR))
                        .title(Component.translatable("itemGroup.deathcube.coloredBlocks")).build());

        DEATHCUBE_NATURAL_BLOCKS_TAB = event.registerCreativeModeTab(new ResourceLocation(DeathCube.MODID, "natural_blocks"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.CHERRY_LEAVES.get()))
                        .title(Component.translatable("itemGroup.deathcube.naturalBlocks")).build());

        DEATHCUBE_FUNCTIONAL_BLOCKS_TAB = event.registerCreativeModeTab(new ResourceLocation(DeathCube.MODID, "functional_blocks"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.MATTER_MANIPULATOR.get()))
                        .title(Component.translatable("itemGroup.deathcube.functionalBlocks")).build());

        DEATHCUBE_REDSTONE_BLOCKS_TAB = event.registerCreativeModeTab(new ResourceLocation(DeathCube.MODID, "redstone"),
                builder -> builder.icon(() -> new ItemStack(Items.REDSTONE))
                        .title(Component.translatable("itemGroup.deathcube.redstone")).build());

        DEATHCUBE_TOOLS_AND_UTILITIES_TAB = event.registerCreativeModeTab(new ResourceLocation(DeathCube.MODID, "tools"),
                builder -> builder.icon(() -> new ItemStack(ModItems.TIME_WAND.get()))
                        .title(Component.translatable("itemGroup.deathcube.tools")).build());

        DEATHCUBE_COMBAT_TAB = event.registerCreativeModeTab(new ResourceLocation(DeathCube.MODID, "combat"),
                builder -> builder.icon(() -> new ItemStack(ModItems.OBSIDIAN_SWORD.get()))
                        .title(Component.translatable("itemGroup.deathcube.combat")).build());

        DEATHCUBE_FOOD_AND_DRINKS_TAB = event.registerCreativeModeTab(new ResourceLocation(DeathCube.MODID, "food_and_drinks"),
                builder -> builder.icon(() -> new ItemStack(ModItems.CHERRY.get()))
                        .title(Component.translatable("itemGroup.deathcube.foodAndDrinks")).build());

        DEATHCUBE_INGREDIENTS_TAB = event.registerCreativeModeTab(new ResourceLocation(DeathCube.MODID, "ingredients"),
                builder -> builder.icon(() -> new ItemStack(ModItems.TIME_GEM.get()))
                        .title(Component.translatable("itemGroup.deathcube.ingredients")).build());

        DEATHCUBE_SPAWN_EGGS_TAB = event.registerCreativeModeTab(new ResourceLocation(DeathCube.MODID, "spawn_eggs"),
                builder -> builder.icon(() -> new ItemStack(ModItems.GALTERIUS_SPAWN_EGG.get()))
                        .title(Component.translatable("itemGroup.deathcube.spawnEggs")).build());
    }
}
