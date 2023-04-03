package com.harmex.deathcube.world.item;

import com.harmex.deathcube.DeathCube;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    OAK("oak", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 1);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 2);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 3);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> Ingredient.of(Items.OAK_LOG)),
    SPRUCE("spruce", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 1);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 2);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 3);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> Ingredient.of(Items.SPRUCE_LOG)),
    BIRCH("birch", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 1);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 2);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 3);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> Ingredient.of(Items.BIRCH_LOG)),
    JUNGLE("jungle", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 1);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 2);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 3);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> Ingredient.of(Items.JUNGLE_LOG)),
    ACACIA("acacia", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 1);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 2);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 3);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> Ingredient.of(Items.ACACIA_LOG)),
    DARK_OAK("dark_oak", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 1);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 2);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 3);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> Ingredient.of(Items.DARK_OAK_LOG)),
    MANGROVE("mangrove", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 1);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 2);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 3);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> Ingredient.of(Items.MANGROVE_LOG)),
    CHERRY("cherry", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 1);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 2);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 3);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> Ingredient.of(Blocks.OAK_WOOD.asItem())),
    CRIMSON("crimson", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 1);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 2);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 3);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> Ingredient.of(Items.CRIMSON_STEM)),
    WARPED("warped", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 1);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 2);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 3);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> Ingredient.of(Items.WARPED_STEM)),
    BONE("bone", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 1);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 3);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 4);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 2);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items.BONE)),
    COPPER("copper", 15, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 2);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 5);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 6);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 2);
    }), 18, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.COPPER_INGOT)),
    EMERALD("emerald", 28, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 2);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 6);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 7);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 3);
    }), 20, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> Ingredient.of(Items.EMERALD)),
    OBSIDIAN("obsidian", 38, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 4);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 7);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 9);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 4);
    }), 17, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.5F, 0.15F, () -> Ingredient.of(Items.OBSIDIAN)),
    GALTERIUS("galterius", 20, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 2);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 5);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 7);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 2);
    }), 20, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT));

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), typeIntegerEnumMap -> {
        typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 13);
        typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 15);
        typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 16);
        typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 11);
    });
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    ModArmorMaterials(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> pProtectionForType, int enchantmentValue,
                              SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionForType = pProtectionForType;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getDurabilityForType(ArmorItem.Type pArmorType) {
        return HEALTH_FOR_TYPE.get(pArmorType) * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.Type pArmorType) {
        return this.protectionForType.get(pArmorType);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull SoundEvent getEquipSound() {
        return this.sound;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public @NotNull String getName() {
        return DeathCube.MODID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
