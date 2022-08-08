package com.harmex.deathcube.event;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.block.ModBlocks;
import com.harmex.deathcube.entity.ModEntityTypes;
import com.harmex.deathcube.entity.boss.*;
//import com.harmex.deathcube.event.loot.EnderDragonScaleFromEnderDragonAdditionModifier;
//import com.harmex.deathcube.event.loot.WardenHeartFromWardenAdditionModifier;
import com.harmex.deathcube.recipe.ShapedMatterManipulationRecipe;
import com.harmex.deathcube.recipe.UpgradingStationRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = DeathCube.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

    @SubscribeEvent
    public static void registerEvent(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.RECIPE_TYPES, recipeTypeRegisterHelper -> {
            recipeTypeRegisterHelper.register(new ResourceLocation(DeathCube.MODID, ShapedMatterManipulationRecipe.Type.ID),
                    ShapedMatterManipulationRecipe.Type.INSTANCE);
            recipeTypeRegisterHelper.register(new ResourceLocation(DeathCube.MODID, UpgradingStationRecipe.Type.ID),
                    UpgradingStationRecipe.Type.INSTANCE);
        });

        event.register(ForgeRegistries.Keys.ITEMS, itemRegisterHelper -> {
            itemRegisterHelper.register(new ResourceLocation("minecraft:golden_carrot"),
                    new ItemNameBlockItem(ModBlocks.GOLDEN_CARROTS.get(),
                            new Item.Properties()
                                    .tab(CreativeModeTab.TAB_FOOD)
                                    .food(Foods.GOLDEN_CARROT)
                    ));
        });
    }

    @SubscribeEvent
    public static void onCreateAttribute(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.AZRATHAL.get(), Azrathal.createAttributes().build());
        event.put(ModEntityTypes.BORZADON.get(), Borzadon.createAttributes().build());
        event.put(ModEntityTypes.GALTERIUS.get(), Galterius.createAttributes().build());
        event.put(ModEntityTypes.NAERVUS.get(), Naervus.createAttributes().build());
        event.put(ModEntityTypes.ZANUZAL.get(), Zanuzal.createAttributes().build());
    }
}
