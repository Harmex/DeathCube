package com.harmex.deathcube.event;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.networking.ModMessages;
import com.harmex.deathcube.recipe.ShapedMatterManipulationRecipe;
import com.harmex.deathcube.recipe.UpgradingStationRecipe;
import com.harmex.deathcube.util.capabilities.thirst.DrinkProperties;
import com.harmex.deathcube.world.entity.ModEntityTypes;
import com.harmex.deathcube.world.entity.ai.attribute.ModAttributes;
import com.harmex.deathcube.world.entity.boss.*;
import com.harmex.deathcube.world.item.custom.DyeableArmorSetItem;
import com.harmex.deathcube.world.item.custom.ModPotionItem;
import com.harmex.deathcube.world.item.custom.set.ArmorSetItem;
import com.harmex.deathcube.world.item.custom.set.ArmorSets;
import com.harmex.deathcube.world.level.block.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import top.theillusivec4.curios.api.SlotTypeMessage;

@Mod.EventBusSubscriber(modid = DeathCube.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        ModMessages.register();
    }

    @SubscribeEvent
    public static void interModEnqueue(InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("totem")
                        .icon(new ResourceLocation(DeathCube.MODID, "slot/totem"))
                        .size(1)
                        .build());
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
                    new DyeableArmorSetItem(ArmorSets.LEATHER, ArmorItem.Type.HELMET));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "leather_chestplate"),
                    new DyeableArmorSetItem(ArmorSets.LEATHER, ArmorItem.Type.CHESTPLATE));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "leather_leggings"),
                    new DyeableArmorSetItem(ArmorSets.LEATHER, ArmorItem.Type.LEGGINGS));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "leather_boots"),
                    new DyeableArmorSetItem(ArmorSets.LEATHER, ArmorItem.Type.BOOTS));

            itemRegisterHelper.register(new ResourceLocation("minecraft", "chainmail_helmet"),
                    new ArmorSetItem(ArmorSets.CHAIN, ArmorItem.Type.HELMET));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "chainmail_chestplate"),
                    new ArmorSetItem(ArmorSets.CHAIN, ArmorItem.Type.CHESTPLATE));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "chainmail_leggings"),
                    new ArmorSetItem(ArmorSets.CHAIN, ArmorItem.Type.LEGGINGS));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "chainmail_boots"),
                    new ArmorSetItem(ArmorSets.CHAIN, ArmorItem.Type.BOOTS));

            itemRegisterHelper.register(new ResourceLocation("minecraft", "iron_helmet"),
                    new ArmorSetItem(ArmorSets.IRON, ArmorItem.Type.HELMET));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "iron_chestplate"),
                    new ArmorSetItem(ArmorSets.IRON, ArmorItem.Type.CHESTPLATE));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "iron_leggings"),
                    new ArmorSetItem(ArmorSets.IRON, ArmorItem.Type.LEGGINGS));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "iron_boots"),
                    new ArmorSetItem(ArmorSets.IRON, ArmorItem.Type.BOOTS));

            itemRegisterHelper.register(new ResourceLocation("minecraft", "golden_helmet"),
                    new ArmorSetItem(ArmorSets.GOLD, ArmorItem.Type.HELMET));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "golden_chestplate"),
                    new ArmorSetItem(ArmorSets.GOLD, ArmorItem.Type.CHESTPLATE));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "golden_leggings"),
                    new ArmorSetItem(ArmorSets.GOLD, ArmorItem.Type.LEGGINGS));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "golden_boots"),
                    new ArmorSetItem(ArmorSets.GOLD, ArmorItem.Type.BOOTS));

            itemRegisterHelper.register(new ResourceLocation("minecraft", "diamond_helmet"),
                    new ArmorSetItem(ArmorSets.DIAMOND, ArmorItem.Type.HELMET));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "diamond_chestplate"),
                    new ArmorSetItem(ArmorSets.DIAMOND, ArmorItem.Type.CHESTPLATE));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "diamond_leggings"),
                    new ArmorSetItem(ArmorSets.DIAMOND, ArmorItem.Type.LEGGINGS));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "diamond_boots"),
                    new ArmorSetItem(ArmorSets.DIAMOND, ArmorItem.Type.BOOTS));

            itemRegisterHelper.register(new ResourceLocation("minecraft", "netherite_helmet"),
                    new ArmorSetItem(ArmorSets.NETHERITE, ArmorItem.Type.HELMET));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "netherite_chestplate"),
                    new ArmorSetItem(ArmorSets.NETHERITE, ArmorItem.Type.CHESTPLATE));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "netherite_leggings"),
                    new ArmorSetItem(ArmorSets.NETHERITE, ArmorItem.Type.LEGGINGS));
            itemRegisterHelper.register(new ResourceLocation("minecraft", "netherite_boots"),
                    new ArmorSetItem(ArmorSets.NETHERITE, ArmorItem.Type.BOOTS));
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
