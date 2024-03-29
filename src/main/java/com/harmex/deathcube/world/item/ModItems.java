package com.harmex.deathcube.world.item;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.world.entity.ModEntityTypes;
import com.harmex.deathcube.world.item.custom.*;
import com.harmex.deathcube.world.item.custom.apple.AppleItem;
import com.harmex.deathcube.world.item.custom.apple.Apples;
import com.harmex.deathcube.world.item.custom.set.ArmorSetItem;
import com.harmex.deathcube.world.item.custom.set.ArmorSets;
import com.harmex.deathcube.world.item.custom.totem.TotemItem;
import com.harmex.deathcube.world.item.custom.totem.Totems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DeathCube.MODID);


    public static final RegistryObject<Item> GALTERIUS_LOOT_BAG =
            ITEMS.register("galterius_loot_bag",
                    () -> new BossLootBagItem(new Item.Properties(), "galterius"
                    ));
    //region Foods
    public static final RegistryObject<Item> FRESH_WATER_BOTTLE =
            ITEMS.register("fresh_water_bottle", FreshWaterBottleItem::new);
    public static final RegistryObject<Item> CHERRY =
            ITEMS.register("cherry",
                    () -> new Item(new Item.Properties()
                            .food(ModFoods.CHERRY)
                            .rarity(ModRarities.COMMON)
                    ));
    public static final RegistryObject<Item> TIME_GEM_APPLE =
            ITEMS.register("time_gem_apple",
                    () -> new ChorusFruitItem(new Item.Properties()
                            .food(ModFoods.TIME_GEM_APPLE)
                            .rarity(Rarity.UNCOMMON)
                    ) {
                        @Override
                        public boolean isFoil(@NotNull ItemStack pStack) {
                            return true;
                        }
                    });
    public static final RegistryObject<Item> DIAMOND_APPLE =
            ITEMS.register("diamond_apple",
                    () -> new AppleItem(new Item.Properties()
                            .food(ModFoods.DIAMOND_APPLE)
                            .rarity(Rarity.UNCOMMON),
                            Apples.DIAMOND) {
                        @Override
                        public boolean isFoil(@NotNull ItemStack pStack) {
                            return true;
                        }
                    });
    public static final RegistryObject<Item> NETHERITE_APPLE =
            ITEMS.register("netherite_apple",
                    () -> new AppleItem(new Item.Properties()
                            .food(ModFoods.NETHERITE_APPLE)
                            .rarity(Rarity.RARE),
                            Apples.NETHERITE) {
                        @Override
                        public boolean isFoil(@NotNull ItemStack pStack) {
                            return true;
                        }
                    });
    public static final RegistryObject<Item> BEDROCK_APPLE =
            ITEMS.register("bedrock_apple",
                    () -> new AppleItem(new Item.Properties()
                            .food(ModFoods.BEDROCK_APPLE)
                            .rarity(Rarity.EPIC),
                            Apples.BEDROCK) {
                        @Override
                        public boolean isFoil(@NotNull ItemStack pStack) {
                            return true;
                        }
                    });
    //endregion
    //region Totem
    public static final RegistryObject<Item> TOTEM_OF_RESURRECTION =
            ITEMS.register("totem_of_resurrection",
                    () -> new TotemOfResurrectionItem(new Item.Properties().stacksTo(1).rarity(ModRarities.MYTHIC)));
    public static final RegistryObject<Item> TOTEM_OF_REGENERATION =
            ITEMS.register("totem_of_regeneration",
                    () -> new TotemItem(Totems.REGENERATION));
    public static final RegistryObject<Item> TOTEM_OF_RESISTANCE =
            ITEMS.register("totem_of_resistance",
                    () -> new TotemItem(Totems.RESISTANCE));
    //endregion
    //region Misc
    public static final RegistryObject<Item> TIME_GEM =
            ITEMS.register("time_gem",
                    () -> new Item(new Item.Properties()
                            .rarity(Rarity.UNCOMMON)
                    ));
    public static final RegistryObject<Item> ECHO_AMETHYST_SHARD =
            ITEMS.register("echo_amethyst_shard",
                    () -> new Item(new Item.Properties()
                            .fireResistant()
                            .rarity(ModRarities.EPIC)
                    ));
    public static final RegistryObject<Item> ECHO_AMETHYST_INGOT =
            ITEMS.register("echo_amethyst_ingot",
                    () -> new Item(new Item.Properties()
                            .fireResistant()
                            .rarity(ModRarities.EPIC)
                    ));
    public static final RegistryObject<Item> ENDER_DRAGON_SCALE =
            ITEMS.register("ender_dragon_scale",
                    () -> new Item(new Item.Properties()
                            .fireResistant()
                            .rarity(Rarity.UNCOMMON)
                    ));
    public static final RegistryObject<Item> WARDEN_HEART =
            ITEMS.register("warden_heart",
                    () -> new Item(new Item.Properties()
                            .fireResistant()
                            .rarity(ModRarities.EPIC)
                    ));
    public static final RegistryObject<Item> ZANTHINE =
            ITEMS.register("zanthine",
                    () -> new Item(new Item.Properties()
                            .fireResistant()
                            .rarity(Rarity.UNCOMMON)
                    ));
    //endregion
    //region Log Armors
    //Oak
    public static final RegistryObject<Item> OAK_HELMET = ITEMS.register("oak_helmet",
            () -> new ArmorSetItem(ArmorSets.OAK, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> OAK_CHESTPLATE = ITEMS.register("oak_chestplate",
            () -> new ArmorSetItem(ArmorSets.OAK, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> OAK_LEGGINGS = ITEMS.register("oak_leggings",
            () -> new ArmorSetItem(ArmorSets.OAK, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> OAK_BOOTS = ITEMS.register("oak_boots",
            () -> new ArmorSetItem(ArmorSets.OAK, ArmorItem.Type.BOOTS));
    //Spruce
    public static final RegistryObject<Item> SPRUCE_HELMET = ITEMS.register("spruce_helmet",
            () -> new ArmorSetItem(ArmorSets.SPRUCE, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> SPRUCE_CHESTPLATE = ITEMS.register("spruce_chestplate",
            () -> new ArmorSetItem(ArmorSets.SPRUCE, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> SPRUCE_LEGGINGS = ITEMS.register("spruce_leggings",
            () -> new ArmorSetItem(ArmorSets.SPRUCE, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> SPRUCE_BOOTS = ITEMS.register("spruce_boots",
            () -> new ArmorSetItem(ArmorSets.SPRUCE, ArmorItem.Type.BOOTS));
    //Birch
    public static final RegistryObject<Item> BIRCH_HELMET = ITEMS.register("birch_helmet",
            () -> new ArmorSetItem(ArmorSets.BIRCH, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> BIRCH_CHESTPLATE = ITEMS.register("birch_chestplate",
            () -> new ArmorSetItem(ArmorSets.BIRCH, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> BIRCH_LEGGINGS = ITEMS.register("birch_leggings",
            () -> new ArmorSetItem(ArmorSets.BIRCH, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> BIRCH_BOOTS = ITEMS.register("birch_boots",
            () -> new ArmorSetItem(ArmorSets.BIRCH, ArmorItem.Type.BOOTS));
    //Jungle
    public static final RegistryObject<Item> JUNGLE_HELMET = ITEMS.register("jungle_helmet",
            () -> new ArmorSetItem(ArmorSets.JUNGLE, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> JUNGLE_CHESTPLATE = ITEMS.register("jungle_chestplate",
            () -> new ArmorSetItem(ArmorSets.JUNGLE, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> JUNGLE_LEGGINGS = ITEMS.register("jungle_leggings",
            () -> new ArmorSetItem(ArmorSets.JUNGLE, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> JUNGLE_BOOTS = ITEMS.register("jungle_boots",
            () -> new ArmorSetItem(ArmorSets.JUNGLE, ArmorItem.Type.BOOTS));
    //Acacia
    public static final RegistryObject<Item> ACACIA_HELMET = ITEMS.register("acacia_helmet",
            () -> new ArmorSetItem(ArmorSets.ACACIA, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> ACACIA_CHESTPLATE = ITEMS.register("acacia_chestplate",
            () -> new ArmorSetItem(ArmorSets.ACACIA, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> ACACIA_LEGGINGS = ITEMS.register("acacia_leggings",
            () -> new ArmorSetItem(ArmorSets.ACACIA, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> ACACIA_BOOTS = ITEMS.register("acacia_boots",
            () -> new ArmorSetItem(ArmorSets.ACACIA, ArmorItem.Type.BOOTS));
    //Dark Oak
    public static final RegistryObject<Item> DARK_OAK_HELMET = ITEMS.register("dark_oak_helmet",
            () -> new ArmorSetItem(ArmorSets.DARK_OAK, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> DARK_OAK_CHESTPLATE = ITEMS.register("dark_oak_chestplate",
            () -> new ArmorSetItem(ArmorSets.DARK_OAK, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> DARK_OAK_LEGGINGS = ITEMS.register("dark_oak_leggings",
            () -> new ArmorSetItem(ArmorSets.DARK_OAK, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> DARK_OAK_BOOTS = ITEMS.register("dark_oak_boots",
            () -> new ArmorSetItem(ArmorSets.DARK_OAK, ArmorItem.Type.BOOTS));
    //Mangrove
    public static final RegistryObject<Item> MANGROVE_HELMET = ITEMS.register("mangrove_helmet",
            () -> new ArmorSetItem(ArmorSets.MANGROVE, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> MANGROVE_CHESTPLATE = ITEMS.register("mangrove_chestplate",
            () -> new ArmorSetItem(ArmorSets.MANGROVE, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> MANGROVE_LEGGINGS = ITEMS.register("mangrove_leggings",
            () -> new ArmorSetItem(ArmorSets.MANGROVE, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> MANGROVE_BOOTS = ITEMS.register("mangrove_boots",
            () -> new ArmorSetItem(ArmorSets.MANGROVE, ArmorItem.Type.BOOTS));
    //Cherry
    public static final RegistryObject<Item> CHERRY_HELMET = ITEMS.register("cherry_helmet",
            () -> new ArmorSetItem(ArmorSets.CHERRY, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> CHERRY_CHESTPLATE = ITEMS.register("cherry_chestplate",
            () -> new ArmorSetItem(ArmorSets.CHERRY, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> CHERRY_LEGGINGS = ITEMS.register("cherry_leggings",
            () -> new ArmorSetItem(ArmorSets.CHERRY, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> CHERRY_BOOTS = ITEMS.register("cherry_boots",
            () -> new ArmorSetItem(ArmorSets.CHERRY, ArmorItem.Type.BOOTS));
    //Crimson
    public static final RegistryObject<Item> CRIMSON_HELMET = ITEMS.register("crimson_helmet",
            () -> new ArmorSetItem(ArmorSets.CRIMSON, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> CRIMSON_CHESTPLATE = ITEMS.register("crimson_chestplate",
            () -> new ArmorSetItem(ArmorSets.CRIMSON, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> CRIMSON_LEGGINGS = ITEMS.register("crimson_leggings",
            () -> new ArmorSetItem(ArmorSets.CRIMSON, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> CRIMSON_BOOTS = ITEMS.register("crimson_boots",
            () -> new ArmorSetItem(ArmorSets.CRIMSON, ArmorItem.Type.BOOTS));
    //Warped
    public static final RegistryObject<Item> WARPED_HELMET = ITEMS.register("warped_helmet",
            () -> new ArmorSetItem(ArmorSets.WARPED, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> WARPED_CHESTPLATE = ITEMS.register("warped_chestplate",
            () -> new ArmorSetItem(ArmorSets.WARPED, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> WARPED_LEGGINGS = ITEMS.register("warped_leggings",
            () -> new ArmorSetItem(ArmorSets.WARPED, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> WARPED_BOOTS = ITEMS.register("warped_boots",
            () -> new ArmorSetItem(ArmorSets.WARPED, ArmorItem.Type.BOOTS));
    //endregion
    //region Bone
    //Bone Armor
    public static final RegistryObject<Item> BONE_HELMET = ITEMS.register("bone_helmet",
            () -> new ArmorSetItem(ArmorSets.BONE, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> BONE_CHESTPLATE = ITEMS.register("bone_chestplate",
            () -> new ArmorSetItem(ArmorSets.BONE, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> BONE_LEGGINGS = ITEMS.register("bone_leggings",
            () -> new ArmorSetItem(ArmorSets.BONE, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> BONE_BOOTS = ITEMS.register("bone_boots",
            () -> new ArmorSetItem(ArmorSets.BONE, ArmorItem.Type.BOOTS));
    //Bone Tools
    public static final RegistryObject<Item> BONE_SWORD = ITEMS.register("bone_sword",
            () -> new SwordItem(ModTiers.BONE, 3, -2.4f, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> BONE_SHOVEL = ITEMS.register("bone_shovel",
            () -> new ShovelItem(ModTiers.BONE, 1.5f, -3.0f, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> BONE_PICKAXE = ITEMS.register("bone_pickaxe",
            () -> new PickaxeItem(ModTiers.BONE, 1, -2.8f, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> BONE_AXE = ITEMS.register("bone_axe",
            () -> new AxeItem(ModTiers.BONE, 6.5f, -3.2f, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> BONE_HOE = ITEMS.register("bone_hoe",
            () -> new HoeItem(ModTiers.BONE, -1, -2.5f, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    //endregion
    //region Copper
    //Copper Armor
    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            () -> new ArmorSetItem(ArmorSets.COPPER, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            () -> new ArmorSetItem(ArmorSets.COPPER, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            () -> new ArmorSetItem(ArmorSets.COPPER, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            () -> new ArmorSetItem(ArmorSets.COPPER, ArmorItem.Type.BOOTS));
    //Copper Tools
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword",
                    () -> new SwordItem(ModTiers.COPPER, 3, -2.4f, new Item.Properties()
                            .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModTiers.COPPER, 1.5f, -3.0f, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
                    () -> new PickaxeItem(ModTiers.COPPER, 1, -2.8f, new Item.Properties()
                            .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe",
                    () -> new AxeItem(ModTiers.COPPER, 6, -3.1f, new Item.Properties()
                            .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe",
                    () -> new HoeItem(ModTiers.COPPER, -2, -1.0f, new Item.Properties()
                            .rarity(ModRarities.COMMON)));
    //endregion
    //region Emerald
    //Emerald Armor
    public static final RegistryObject<Item> EMERALD_HELMET = ITEMS.register("emerald_helmet",
            () -> new ArmorSetItem(ArmorSets.EMERALD, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = ITEMS.register("emerald_chestplate",
            () -> new ArmorSetItem(ArmorSets.EMERALD, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> EMERALD_LEGGINGS = ITEMS.register("emerald_leggings",
            () -> new ArmorSetItem(ArmorSets.EMERALD, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> EMERALD_BOOTS = ITEMS.register("emerald_boots",
            () -> new ArmorSetItem(ArmorSets.EMERALD, ArmorItem.Type.BOOTS));
    //Emerald Tools
    public static final RegistryObject<Item> EMERALD_SWORD = ITEMS.register("emerald_sword",
            () -> new SwordItem(ModTiers.EMERALD, 3, -2.4f, new Item.Properties()
                    .rarity(ModRarities.UNCOMMON)));
    public static final RegistryObject<Item> EMERALD_SHOVEL = ITEMS.register("emerald_shovel",
            () -> new ShovelItem(ModTiers.EMERALD, 1.5f, -3.0f, new Item.Properties()
                    .rarity(ModRarities.UNCOMMON)));
    public static final RegistryObject<Item> EMERALD_PICKAXE = ITEMS.register("emerald_pickaxe",
            () -> new PickaxeItem(ModTiers.EMERALD, 1, -2.8f, new Item.Properties()
                    .rarity(ModRarities.UNCOMMON)));
    public static final RegistryObject<Item> EMERALD_AXE = ITEMS.register("emerald_axe",
            () -> new AxeItem(ModTiers.EMERALD, 5, -3.0f, new Item.Properties()
                    .rarity(ModRarities.UNCOMMON)));
    public static final RegistryObject<Item> EMERALD_HOE = ITEMS.register("emerald_hoe",
            () -> new HoeItem(ModTiers.EMERALD, -3, 0.0f, new Item.Properties()
                    .rarity(ModRarities.UNCOMMON)));
    //endregion
    //region Obsidian
    //Obsidian Armor
    public static final RegistryObject<Item> OBSIDIAN_HELMET = ITEMS.register("obsidian_helmet",
            () -> new ArmorSetItem(ArmorSets.OBSIDIAN, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> OBSIDIAN_CHESTPLATE = ITEMS.register("obsidian_chestplate",
            () -> new ArmorSetItem(ArmorSets.OBSIDIAN, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> OBSIDIAN_LEGGINGS = ITEMS.register("obsidian_leggings",
            () -> new ArmorSetItem(ArmorSets.OBSIDIAN, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> OBSIDIAN_BOOTS = ITEMS.register("obsidian_boots",
            () -> new ArmorSetItem(ArmorSets.OBSIDIAN, ArmorItem.Type.BOOTS));
    //Obsidian Tools
    public static final RegistryObject<Item> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword",
            () -> new SwordItem(ModTiers.OBSIDIAN, 3, -2.4f, new Item.Properties()
                    .rarity(ModRarities.RARE)));
    public static final RegistryObject<Item> OBSIDIAN_SHOVEL = ITEMS.register("obsidian_shovel",
            () -> new ShovelItem(ModTiers.OBSIDIAN, 1.5f, -3.0f, new Item.Properties()
                    .rarity(ModRarities.RARE)));
    public static final RegistryObject<Item> OBSIDIAN_PICKAXE = ITEMS.register("obsidian_pickaxe",
            () -> new PickaxeItem(ModTiers.OBSIDIAN, 1, -2.8f, new Item.Properties()
                    .rarity(ModRarities.RARE)));
    public static final RegistryObject<Item> OBSIDIAN_AXE = ITEMS.register("obsidian_axe",
            () -> new AxeItem(ModTiers.OBSIDIAN, 5, -3.0f, new Item.Properties()
                    .rarity(ModRarities.RARE)));
    public static final RegistryObject<Item> OBSIDIAN_HOE = ITEMS.register("obsidian_hoe",
            () -> new HoeItem(ModTiers.OBSIDIAN, -3, 0.0f, new Item.Properties()
                    .rarity(ModRarities.RARE)));
    //endregion
    //region Boss Armors
    public static final RegistryObject<Item> GALTERIUS_HELMET = ITEMS.register("galterius_helmet",
            () -> new ArmorSetItem(ArmorSets.GALTERIUS, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> GALTERIUS_CHESTPLATE = ITEMS.register("galterius_chestplate",
            () -> new ArmorSetItem(ArmorSets.GALTERIUS, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> GALTERIUS_LEGGINGS = ITEMS.register("galterius_leggings",
            () -> new ArmorSetItem(ArmorSets.GALTERIUS, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> GALTERIUS_BOOTS = ITEMS.register("galterius_boots",
            () -> new ArmorSetItem(ArmorSets.GALTERIUS, ArmorItem.Type.BOOTS));
    //endregion
    //region Wands
    public static final RegistryObject<Item> TIME_WAND =
            ITEMS.register("time_wand", TimeWandItem::new);
    //endregion
    //region Bags
    public static final RegistryObject<Item> ENDER_BAG =
            ITEMS.register("ender_bag", EnderBagItem::new);
    //endregion
    //region Spawn Eggs
    public static final RegistryObject<Item> AZRATHAL_SPAWN_EGG =
            ITEMS.register("azrathal_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntityTypes.AZRATHAL,
                            0xE67973, 0x698E45,
                            new Item.Properties()
                                    .rarity(ModRarities.HELLISH)
                    ));
    public static final RegistryObject<Item> BORZADON_SPAWN_EGG =
            ITEMS.register("borzadon_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntityTypes.BORZADON,
                            0x151515, 0x4B4B4B,
                            new Item.Properties()
                                    .rarity(ModRarities.HELLISH)
                    ));
    public static final RegistryObject<Item> GALTERIUS_SPAWN_EGG =
            ITEMS.register("galterius_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntityTypes.GALTERIUS,
                            0x497135, 0x00AFAF,
                            new Item.Properties()
                                    .rarity(ModRarities.EPIC)
                    ));
    public static final RegistryObject<Item> NAERVUS_SPAWN_EGG =
            ITEMS.register("naervus_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntityTypes.NAERVUS,
                            0xE8A074, 0x6B3924,
                            new Item.Properties()
                                    .rarity(ModRarities.HELLISH)
                    ));
    public static final RegistryObject<Item> ZANUZAL_SPAWN_EGG =
            ITEMS.register("zanuzal_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntityTypes.ZANUZAL,
                            0xE8A074, 0x6B3924,
                            new Item.Properties()
                                    .rarity(ModRarities.EPIC)
                    ));
    //endregion

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
