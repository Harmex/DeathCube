package com.harmex.deathcube.event;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.networking.ModMessages;
import com.harmex.deathcube.recipe.ShapedMatterManipulationRecipe;
import com.harmex.deathcube.recipe.UpgradingStationRecipe;
import com.harmex.deathcube.util.capabilities.thirst.DrinkProperties;
import com.harmex.deathcube.world.entity.ModEntityTypes;
import com.harmex.deathcube.world.entity.ai.attribute.ModAttributes;
import com.harmex.deathcube.world.entity.boss.*;
import com.harmex.deathcube.world.item.custom.ArmorSetItem;
import com.harmex.deathcube.world.item.custom.ArmorSets;
import com.harmex.deathcube.world.item.custom.DyeableArmorSetItem;
import com.harmex.deathcube.world.item.custom.ModPotionItem;
import com.harmex.deathcube.world.level.block.ModBlocks;
import com.harmex.deathcube.world.level.block.entity.ModWoodTypes;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
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

            itemRegisterHelper.register(new ResourceLocation("minecraft", "leather_helmet"),
                    new DyeableArmorSetItem(ArmorSets.LEATHER, EquipmentSlot.HEAD));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "leather_chestplate"),
                    new DyeableArmorSetItem(ArmorSets.LEATHER, EquipmentSlot.CHEST));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "leather_leggings"),
                    new DyeableArmorSetItem(ArmorSets.LEATHER, EquipmentSlot.LEGS));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "leather_boots"),
                    new DyeableArmorSetItem(ArmorSets.LEATHER, EquipmentSlot.FEET));

            itemRegisterHelper.register(new ResourceLocation("minecraft", "chainmail_helmet"),
                    new ArmorSetItem(ArmorSets.CHAIN, EquipmentSlot.HEAD));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "chainmail_chestplate"),
                    new ArmorSetItem(ArmorSets.CHAIN, EquipmentSlot.CHEST));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "chainmail_leggings"),
                    new ArmorSetItem(ArmorSets.CHAIN, EquipmentSlot.LEGS));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "chainmail_boots"),
                    new ArmorSetItem(ArmorSets.CHAIN, EquipmentSlot.FEET));

            itemRegisterHelper.register(new ResourceLocation("minecraft", "iron_helmet"),
                    new ArmorSetItem(ArmorSets.IRON, EquipmentSlot.HEAD));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "iron_chestplate"),
                    new ArmorSetItem(ArmorSets.IRON, EquipmentSlot.CHEST));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "iron_leggings"),
                    new ArmorSetItem(ArmorSets.IRON, EquipmentSlot.LEGS));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "iron_boots"),
                    new ArmorSetItem(ArmorSets.IRON, EquipmentSlot.FEET));

            itemRegisterHelper.register(new ResourceLocation("minecraft", "golden_helmet"),
                    new ArmorSetItem(ArmorSets.GOLD, EquipmentSlot.HEAD));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "golden_chestplate"),
                    new ArmorSetItem(ArmorSets.GOLD, EquipmentSlot.CHEST));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "golden_leggings"),
                    new ArmorSetItem(ArmorSets.GOLD, EquipmentSlot.LEGS));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "golden_boots"),
                    new ArmorSetItem(ArmorSets.GOLD, EquipmentSlot.FEET));

            itemRegisterHelper.register(new ResourceLocation("minecraft", "diamond_helmet"),
                    new ArmorSetItem(ArmorSets.DIAMOND, EquipmentSlot.HEAD));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "diamond_chestplate"),
                    new ArmorSetItem(ArmorSets.DIAMOND, EquipmentSlot.CHEST));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "diamond_leggings"),
                    new ArmorSetItem(ArmorSets.DIAMOND, EquipmentSlot.LEGS));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "diamond_boots"),
                    new ArmorSetItem(ArmorSets.DIAMOND, EquipmentSlot.FEET));

            itemRegisterHelper.register(new ResourceLocation("minecraft", "netherite_helmet"),
                    new ArmorSetItem(ArmorSets.NETHERITE, EquipmentSlot.HEAD));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "netherite_chestplate"),
                    new ArmorSetItem(ArmorSets.NETHERITE, EquipmentSlot.CHEST));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "netherite_leggings"),
                    new ArmorSetItem(ArmorSets.NETHERITE, EquipmentSlot.LEGS));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "netherite_boots"),
                    new ArmorSetItem(ArmorSets.NETHERITE, EquipmentSlot.FEET));
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

    @SubscribeEvent
    public static void onModifyAttribute(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, ModAttributes.MAX_MANA.get());
    }
}
