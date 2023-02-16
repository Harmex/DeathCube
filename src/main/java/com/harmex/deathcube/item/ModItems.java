package com.harmex.deathcube.item;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.block.ModBlocks;
import com.harmex.deathcube.entity.ModEntityTypes;
import com.harmex.deathcube.item.custom.*;
import net.minecraft.world.entity.EquipmentSlot;
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
                            .rarity(Rarity.UNCOMMON)
                    ) {
                        @Override
                        public boolean isFoil(@NotNull ItemStack pStack) {
                            return true;
                        }
                    });
    public static final RegistryObject<Item> NETHERITE_APPLE =
            ITEMS.register("netherite_apple",
                    () -> new AppleItem(new Item.Properties()
                            .food(ModFoods.NETHERITE_APPLE)
                            .rarity(Rarity.RARE)
                    ) {
                        @Override
                        public boolean isFoil(@NotNull ItemStack pStack) {
                            return true;
                        }
                    });
    public static final RegistryObject<Item> BEDROCK_APPLE =
            ITEMS.register("bedrock_apple",
                    () -> new AppleItem(new Item.Properties()
                            .food(ModFoods.BEDROCK_APPLE)
                            .rarity(Rarity.EPIC)
                    ) {
                        @Override
                        public boolean isFoil(@NotNull ItemStack pStack) {
                            return true;
                        }
                    });
    //endregion
    //region Misc
    public static final RegistryObject<Item> CHERRY_SIGN =
            ITEMS.register("cherry_sign",
                    () -> new SignItem(new Item.Properties()
                            .stacksTo(16)
                            .rarity(ModRarities.COMMON),
                            ModBlocks.CHERRY_SIGN.get(),
                            ModBlocks.CHERRY_WALL_SIGN.get()
                    ));
    public static final RegistryObject<Item> TOTEM_OF_RESURRECTION =
            ITEMS.register("totem_of_resurrection", TotemOfResurrectionItem::new);
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
    /*public static final RegistryObject<Item> CHERRY_BOAT =
            ITEMS.register("cherry_boat",
                    () -> new BoatItem(false, ModBoatEntity.Type.CHERRY, new Item.Properties()
                            .tab(ModCreativeModeTab.DEATHCUBE_MISC_TAB)
                            .stacksTo(1)
                    ));*/
    //endregion
    //region Log Armors
    //Oak
    public static final RegistryObject<Item> OAK_HELMET = ITEMS.register("oak_helmet",
            () -> new ArmorItem(ModArmorMaterials.OAK, EquipmentSlot.HEAD, new Item.Properties()));
    public static final RegistryObject<Item> OAK_CHESTPLATE = ITEMS.register("oak_chestplate",
            () -> new ArmorItem(ModArmorMaterials.OAK, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> OAK_LEGGINGS = ITEMS.register("oak_leggings",
            () -> new ArmorItem(ModArmorMaterials.OAK, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<Item> OAK_BOOTS = ITEMS.register("oak_boots",
            () -> new ArmorItem(ModArmorMaterials.OAK, EquipmentSlot.FEET, new Item.Properties()));
    //Spruce
    public static final RegistryObject<Item> SPRUCE_HELMET = ITEMS.register("spruce_helmet",
            () -> new ArmorItem(ModArmorMaterials.SPRUCE, EquipmentSlot.HEAD, new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_CHESTPLATE = ITEMS.register("spruce_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SPRUCE, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_LEGGINGS = ITEMS.register("spruce_leggings",
            () -> new ArmorItem(ModArmorMaterials.SPRUCE, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_BOOTS = ITEMS.register("spruce_boots",
            () -> new ArmorItem(ModArmorMaterials.SPRUCE, EquipmentSlot.FEET, new Item.Properties()));
    //Birch
    public static final RegistryObject<Item> BIRCH_HELMET = ITEMS.register("birch_helmet",
            () -> new ArmorItem(ModArmorMaterials.BIRCH, EquipmentSlot.HEAD, new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_CHESTPLATE = ITEMS.register("birch_chestplate",
            () -> new ArmorItem(ModArmorMaterials.BIRCH, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_LEGGINGS = ITEMS.register("birch_leggings",
            () -> new ArmorItem(ModArmorMaterials.BIRCH, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_BOOTS = ITEMS.register("birch_boots",
            () -> new ArmorItem(ModArmorMaterials.BIRCH, EquipmentSlot.FEET, new Item.Properties()));
    //Jungle
    public static final RegistryObject<Item> JUNGLE_HELMET = ITEMS.register("jungle_helmet",
            () -> new ArmorItem(ModArmorMaterials.JUNGLE, EquipmentSlot.HEAD, new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_CHESTPLATE = ITEMS.register("jungle_chestplate",
            () -> new ArmorItem(ModArmorMaterials.JUNGLE, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_LEGGINGS = ITEMS.register("jungle_leggings",
            () -> new ArmorItem(ModArmorMaterials.JUNGLE, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_BOOTS = ITEMS.register("jungle_boots",
            () -> new ArmorItem(ModArmorMaterials.JUNGLE, EquipmentSlot.FEET, new Item.Properties()));
    //Acacia
    public static final RegistryObject<Item> ACACIA_HELMET = ITEMS.register("acacia_helmet",
            () -> new ArmorItem(ModArmorMaterials.ACACIA, EquipmentSlot.HEAD, new Item.Properties()));
    public static final RegistryObject<Item> ACACIA_CHESTPLATE = ITEMS.register("acacia_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ACACIA, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> ACACIA_LEGGINGS = ITEMS.register("acacia_leggings",
            () -> new ArmorItem(ModArmorMaterials.ACACIA, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<Item> ACACIA_BOOTS = ITEMS.register("acacia_boots",
            () -> new ArmorItem(ModArmorMaterials.ACACIA, EquipmentSlot.FEET, new Item.Properties()));
    //Dark Oak
    public static final RegistryObject<Item> DARK_OAK_HELMET = ITEMS.register("dark_oak_helmet",
            () -> new ArmorItem(ModArmorMaterials.DARK_OAK, EquipmentSlot.HEAD, new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_CHESTPLATE = ITEMS.register("dark_oak_chestplate",
            () -> new ArmorItem(ModArmorMaterials.DARK_OAK, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_LEGGINGS = ITEMS.register("dark_oak_leggings",
            () -> new ArmorItem(ModArmorMaterials.DARK_OAK, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_BOOTS = ITEMS.register("dark_oak_boots",
            () -> new ArmorItem(ModArmorMaterials.DARK_OAK, EquipmentSlot.FEET, new Item.Properties()));
    //Mangrove
    public static final RegistryObject<Item> MANGROVE_HELMET = ITEMS.register("mangrove_helmet",
            () -> new ArmorItem(ModArmorMaterials.MANGROVE, EquipmentSlot.HEAD, new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_CHESTPLATE = ITEMS.register("mangrove_chestplate",
            () -> new ArmorItem(ModArmorMaterials.MANGROVE, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_LEGGINGS = ITEMS.register("mangrove_leggings",
            () -> new ArmorItem(ModArmorMaterials.MANGROVE, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_BOOTS = ITEMS.register("mangrove_boots",
            () -> new ArmorItem(ModArmorMaterials.MANGROVE, EquipmentSlot.FEET, new Item.Properties()));
    //Cherry
    public static final RegistryObject<Item> CHERRY_HELMET = ITEMS.register("cherry_helmet",
            () -> new ArmorItem(ModArmorMaterials.CHERRY, EquipmentSlot.HEAD, new Item.Properties()));
    public static final RegistryObject<Item> CHERRY_CHESTPLATE = ITEMS.register("cherry_chestplate",
            () -> new ArmorItem(ModArmorMaterials.CHERRY, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> CHERRY_LEGGINGS = ITEMS.register("cherry_leggings",
            () -> new ArmorItem(ModArmorMaterials.CHERRY, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<Item> CHERRY_BOOTS = ITEMS.register("cherry_boots",
            () -> new ArmorItem(ModArmorMaterials.CHERRY, EquipmentSlot.FEET, new Item.Properties()));
    //Crimson
    public static final RegistryObject<Item> CRIMSON_HELMET = ITEMS.register("crimson_helmet",
            () -> new ArmorItem(ModArmorMaterials.CRIMSON, EquipmentSlot.HEAD, new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_CHESTPLATE = ITEMS.register("crimson_chestplate",
            () -> new ArmorItem(ModArmorMaterials.CRIMSON, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_LEGGINGS = ITEMS.register("crimson_leggings",
            () -> new ArmorItem(ModArmorMaterials.CRIMSON, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_BOOTS = ITEMS.register("crimson_boots",
            () -> new ArmorItem(ModArmorMaterials.CRIMSON, EquipmentSlot.FEET, new Item.Properties()));
    //Warped
    public static final RegistryObject<Item> WARPED_HELMET = ITEMS.register("warped_helmet",
            () -> new ArmorItem(ModArmorMaterials.WARPED, EquipmentSlot.HEAD, new Item.Properties()));
    public static final RegistryObject<Item> WARPED_CHESTPLATE = ITEMS.register("warped_chestplate",
            () -> new ArmorItem(ModArmorMaterials.WARPED, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> WARPED_LEGGINGS = ITEMS.register("warped_leggings",
            () -> new ArmorItem(ModArmorMaterials.WARPED, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<Item> WARPED_BOOTS = ITEMS.register("warped_boots",
            () -> new ArmorItem(ModArmorMaterials.WARPED, EquipmentSlot.FEET, new Item.Properties()));
    //endregion
    //region Bone
    //Bone Armor
    public static final RegistryObject<Item> BONE_HELMET = ITEMS.register("bone_helmet",
            () -> new ArmorItem(ModArmorMaterials.BONE, EquipmentSlot.HEAD, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> BONE_CHESTPLATE = ITEMS.register("bone_chestplate",
            () -> new ArmorItem(ModArmorMaterials.BONE, EquipmentSlot.CHEST, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> BONE_LEGGINGS = ITEMS.register("bone_leggings",
            () -> new ArmorItem(ModArmorMaterials.BONE, EquipmentSlot.LEGS, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> BONE_BOOTS = ITEMS.register("bone_boots",
            () -> new ArmorItem(ModArmorMaterials.BONE, EquipmentSlot.FEET, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    //Bone Tools
    public static final RegistryObject<Item> BONE_SWORD = ITEMS.register("bone_sword",
            () -> new SwordItem(ModTiers.BONE, 3, -2.4f, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> BONE_PICKAXE = ITEMS.register("bone_pickaxe",
            () -> new PickaxeItem(ModTiers.BONE, 1, -2.8f, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> BONE_AXE = ITEMS.register("bone_axe",
            () -> new AxeItem(ModTiers.BONE, 6.5f, -3.2f, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> BONE_SHOVEL = ITEMS.register("bone_shovel",
            () -> new ShovelItem(ModTiers.BONE, 1.5f, -3.0f, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> BONE_HOE = ITEMS.register("bone_hoe",
            () -> new HoeItem(ModTiers.BONE, -1, -2.5f, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    //endregion
    //region Copper
    //Copper Armor
    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            () -> new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.HEAD, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            () -> new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.CHEST, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            () -> new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.LEGS, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            () -> new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.FEET, new Item.Properties()
                    .rarity(ModRarities.COMMON)));
    //Copper Tools
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword",
                    () -> new SwordItem(ModTiers.COPPER, 3, -2.4f, new Item.Properties()
                            .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
                    () -> new PickaxeItem(ModTiers.COPPER, 1, -2.8f, new Item.Properties()
                            .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe",
                    () -> new AxeItem(ModTiers.COPPER, 6, -3.1f, new Item.Properties()
                            .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",
                    () -> new ShovelItem(ModTiers.COPPER, 1.5f, -3.0f, new Item.Properties()
                            .rarity(ModRarities.COMMON)));
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe",
                    () -> new HoeItem(ModTiers.COPPER, -2, -1.0f, new Item.Properties()
                            .rarity(ModRarities.COMMON)));
    //endregion
    //region Emerald
    //Emerald Armor
    public static final RegistryObject<Item> EMERALD_HELMET = ITEMS.register("emerald_helmet",
            () -> new EmeraldArmorItem(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = ITEMS.register("emerald_chestplate",
            () -> new EmeraldArmorItem(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> EMERALD_LEGGINGS = ITEMS.register("emerald_leggings",
            () -> new EmeraldArmorItem(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> EMERALD_BOOTS = ITEMS.register("emerald_boots",
            () -> new EmeraldArmorItem(EquipmentSlot.FEET));
    //Emerald Tools
    public static final RegistryObject<Item> EMERALD_SWORD = ITEMS.register("emerald_sword",
            () -> new SwordItem(ModTiers.EMERALD, 3, -2.4f, new Item.Properties()
                    .rarity(ModRarities.UNCOMMON)));
    public static final RegistryObject<Item> EMERALD_PICKAXE = ITEMS.register("emerald_pickaxe",
            () -> new PickaxeItem(ModTiers.EMERALD, 1, -2.8f, new Item.Properties()
                    .rarity(ModRarities.UNCOMMON)));
    public static final RegistryObject<Item> EMERALD_AXE = ITEMS.register("emerald_axe",
            () -> new AxeItem(ModTiers.EMERALD, 5, -3.0f, new Item.Properties()
                    .rarity(ModRarities.UNCOMMON)));
    public static final RegistryObject<Item> EMERALD_SHOVEL = ITEMS.register("emerald_shovel",
            () -> new ShovelItem(ModTiers.EMERALD, 1.5f, -3.0f, new Item.Properties()
                    .rarity(ModRarities.UNCOMMON)));
    public static final RegistryObject<Item> EMERALD_HOE = ITEMS.register("emerald_hoe",
            () -> new HoeItem(ModTiers.EMERALD, -3, 0.0f, new Item.Properties()
                    .rarity(ModRarities.UNCOMMON)));
    //endregion
    //region Obsidian
    //Obsidian Armor
    public static final RegistryObject<Item> OBSIDIAN_HELMET = ITEMS.register("obsidian_helmet",
            () -> new ObsidianArmorItem(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> OBSIDIAN_CHESTPLATE = ITEMS.register("obsidian_chestplate",
            () -> new ObsidianArmorItem(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> OBSIDIAN_LEGGINGS = ITEMS.register("obsidian_leggings",
            () -> new ObsidianArmorItem(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> OBSIDIAN_BOOTS = ITEMS.register("obsidian_boots",
            () -> new ObsidianArmorItem(EquipmentSlot.FEET));
    //Obsidian Tools
    public static final RegistryObject<Item> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword",
            () -> new SwordItem(ModTiers.OBSIDIAN, 3, -2.4f, new Item.Properties()
                    .rarity(ModRarities.RARE)));
    public static final RegistryObject<Item> OBSIDIAN_PICKAXE = ITEMS.register("obsidian_pickaxe",
            () -> new PickaxeItem(ModTiers.OBSIDIAN, 1, -2.8f, new Item.Properties()
                    .rarity(ModRarities.RARE)));
    public static final RegistryObject<Item> OBSIDIAN_AXE = ITEMS.register("obsidian_axe",
            () -> new AxeItem(ModTiers.OBSIDIAN, 5, -3.0f, new Item.Properties()
                    .rarity(ModRarities.RARE)));
    public static final RegistryObject<Item> OBSIDIAN_SHOVEL = ITEMS.register("obsidian_shovel",
            () -> new ShovelItem(ModTiers.OBSIDIAN, 1.5f, -3.0f, new Item.Properties()
                    .rarity(ModRarities.RARE)));
    public static final RegistryObject<Item> OBSIDIAN_HOE = ITEMS.register("obsidian_hoe",
            () -> new HoeItem(ModTiers.OBSIDIAN, -3, 0.0f, new Item.Properties()
                    .rarity(ModRarities.RARE)));
    //endregion
    //region Wands
    public static final RegistryObject<Item> TIME_WAND =
            ITEMS.register("time_wand", TimeWandItem::new);
    //endregion
    //region Bags
    public static final RegistryObject<Item> ENDER_BAG =
            ITEMS.register("ender_bag", EnderBagItem::new);
    public static final RegistryObject<Item> SMALL_BAG =
            ITEMS.register("small_bag", SmallBagItem::new);
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
