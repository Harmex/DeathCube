package com.harmex.deathcube.event;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.block.ModBlocks;
import com.harmex.deathcube.block.entity.ModWoodTypes;
import com.harmex.deathcube.entity.ModEntityTypes;
import com.harmex.deathcube.entity.boss.*;
import com.harmex.deathcube.item.custom.ModPotionItem;
import com.harmex.deathcube.networking.ModMessages;
import com.harmex.deathcube.recipe.ShapedMatterManipulationRecipe;
import com.harmex.deathcube.recipe.UpgradingStationRecipe;
import com.harmex.deathcube.thirst.DrinkProperties;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = DeathCube.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.CHERRY_SAPLING.getId(), ModBlocks.POTTED_CHERRY_SAPLING);

            Sheets.addWoodType(ModWoodTypes.CHERRY);

        });
        ModMessages.register();
    }

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
                                    .food(Foods.GOLDEN_CARROT)
                    ));
            itemRegisterHelper.register(new ResourceLocation("minecraft:potion"),
                    new ModPotionItem(new Item.Properties().stacksTo(64),
                            new DrinkProperties.Builder()
                                    .hydration(4)
                                    .saturationModifier(1)
                                    .build()
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
