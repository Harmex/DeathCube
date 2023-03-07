package com.harmex.deathcube.world.item;

import com.harmex.deathcube.DeathCube;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    OAK("oak", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F, 0.0F, () -> Ingredient.of(Items.OAK_LOG)
    ),
    SPRUCE("spruce", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F, 0.0F, () -> Ingredient.of(Items.SPRUCE_LOG)
    ),
    BIRCH("birch", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F, 0.0F, () -> Ingredient.of(Items.BIRCH_LOG)
    ),
    JUNGLE("jungle", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F, 0.0F, () -> Ingredient.of(Items.JUNGLE_LOG)
    ),
    ACACIA("acacia", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F, 0.0F, () -> Ingredient.of(Items.ACACIA_LOG)
    ),
    DARK_OAK("dark_oak", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F, 0.0F, () -> Ingredient.of(Items.DARK_OAK_LOG)
    ),
    MANGROVE("mangrove", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F, 0.0F, () -> Ingredient.of(Items.MANGROVE_LOG)
    ),
    CHERRY("cherry", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F, 0.0F, () -> Ingredient.of(Blocks.OAK_WOOD.asItem())
    ),
    CRIMSON("crimson", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F, 0.0F, () -> Ingredient.of(Items.CRIMSON_STEM)
    ),
    WARPED("warped", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F, 0.0F, () -> Ingredient.of(Items.WARPED_STEM)
    ),
    BONE("bone", 5, new int[]{1, 3, 4, 2}, 15, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> Ingredient.of(Items.BONE)
    ),
    COPPER("copper", 15, new int[]{2, 5, 6, 2}, 18, SoundEvents.ARMOR_EQUIP_IRON,
            0.0F, 0.0F, () -> Ingredient.of(Items.COPPER_INGOT)
    ),
    EMERALD("emerald", 28, new int[]{2, 6, 7, 3}, 20, SoundEvents.ARMOR_EQUIP_DIAMOND,
            2.0F, 0.0F, () -> Ingredient.of(Items.EMERALD)
    ),
    OBSIDIAN("obsidian", 38, new int[]{4, 7, 9, 4}, 17, SoundEvents.ARMOR_EQUIP_NETHERITE,
            3.5F, 0.15F, () -> Ingredient.of(Items.OBSIDIAN)
    );

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    ModArmorMaterials(String name, int durabilityMultiplier, int[] slotProtection, int enchantmentValue,
                              SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtection;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.slotProtections[pSlot.getIndex()];
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
