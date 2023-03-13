package com.harmex.deathcube.world.item.custom.set;

import com.harmex.deathcube.world.item.ModArmorMaterials;
import com.harmex.deathcube.world.item.ModRarities;
import net.minecraft.network.chat.Style;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Rarity;

import javax.annotation.Nullable;
import java.util.function.UnaryOperator;

public enum ArmorSets implements ArmorSet, StringRepresentable {
    OAK("oak", ModArmorMaterials.OAK, null, 0, ModRarities.COMMON, style -> style.withColor(0x987849)),
    SPRUCE("spruce", ModArmorMaterials.SPRUCE, null, 0, ModRarities.COMMON, style -> style.withColor(0x553A1F)),
    BIRCH("birch", ModArmorMaterials.BIRCH, null, 0, ModRarities.COMMON, style -> style.withColor(0xFFFFFF)),
    JUNGLE("jungle", ModArmorMaterials.JUNGLE, null, 0, ModRarities.COMMON, style -> style.withColor(0x766626)),
    ACACIA("acacia", ModArmorMaterials.ACACIA, null, 0, ModRarities.COMMON, style -> style.withColor(0x8D8477)),
    DARK_OAK("dark_oak", ModArmorMaterials.DARK_OAK, null, 0, ModRarities.COMMON, style -> style.withColor(0x584428)),
    MANGROVE("mangrove", ModArmorMaterials.MANGROVE, null, 0, ModRarities.COMMON, style -> style.withColor(0x675230)),
    CHERRY("cherry", ModArmorMaterials.CHERRY, null, 0, ModRarities.COMMON, style -> style.withColor(0xA0726B)),
    CRIMSON("crimson", ModArmorMaterials.CRIMSON, null, 0, ModRarities.COMMON, style -> style.withColor(0xB12727)),
    WARPED("warped", ModArmorMaterials.WARPED, null, 0, ModRarities.COMMON, style -> style.withColor(0x14956F)),
    LEATHER("leather", ArmorMaterials.LEATHER, null, 0, ModRarities.COMMON, style -> style.withColor(0x955E3B)),
    GOLD("gold", ArmorMaterials.GOLD, null, 0, ModRarities.UNCOMMON, style -> style.withColor(0xE9B115)),
    BONE("bone", ModArmorMaterials.BONE, null, 0, ModRarities.COMMON, style -> style.withColor(0xBCBCBC)),
    STONE("stone", ModArmorMaterials.OAK, null, 0, ModRarities.COMMON, style -> style.withColor(0xFFFFFF)),
    CHAIN("chainmail", ArmorMaterials.CHAIN, null, 0, ModRarities.UNCOMMON, style -> style.withColor(0xD8D8D8)),
    COPPER("copper", ModArmorMaterials.COPPER, null, 0, ModRarities.UNCOMMON, style -> style.withColor(0xFFAF81)),
    IRON("iron", ArmorMaterials.IRON, null, 0, ModRarities.UNCOMMON, style -> style.withColor(0x969696)),
    EMERALD("emerald", ModArmorMaterials.EMERALD, MobEffects.HERO_OF_THE_VILLAGE, 0, ModRarities.RARE, style -> style.withColor(0x00CB63)),
    DIAMOND("diamond", ArmorMaterials.DIAMOND, null, 0, ModRarities.RARE, style -> style.withColor(0x20C5B5)),
    OBSIDIAN("obsidian", ModArmorMaterials.OBSIDIAN, MobEffects.FIRE_RESISTANCE, 0, ModRarities.RARE, style -> style.withColor(0x6B4798)),
    NETHERITE("netherite", ArmorMaterials.NETHERITE, null, 0, ModRarities.EPIC, style -> style.withColor(0x766A76)),
    GALTERIUS("galterius", ModArmorMaterials.GALTERIUS, null, 0, ModRarities.EPIC, style -> style.withColor(0x969696));

    private final String name;
    private final ArmorMaterial armorMaterial;
    private final MobEffect fullSetBonus;
    private final int effectAmplifier;
    private final Rarity rarity;
    private final UnaryOperator<Style> styleModifier;
    private static final StringRepresentable.EnumCodec<ArmorSets> CODEC = StringRepresentable.fromEnum(ArmorSets::values);

    ArmorSets(String pName, ArmorMaterial pArmorMaterial, @Nullable MobEffect pFullSetBonus,
              int pEffectAmplifier, Rarity pRarity, UnaryOperator<Style> pStyleModifier) {
        name = pName;
        armorMaterial = pArmorMaterial;
        fullSetBonus = pFullSetBonus;
        effectAmplifier = pEffectAmplifier;
        rarity = pRarity;
        styleModifier = pStyleModifier;
    }

    public static ArmorSet getByName(String pName) {
        ArmorSet armorSet = CODEC.byName(pName);
        return armorSet != null ? armorSet : ArmorSets.OAK;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArmorMaterial getArmorMaterial() {
        return armorMaterial;
    }

    @Override
    public @Nullable MobEffect getFullSetBonus() {
        return fullSetBonus;
    }

    @Override
    public int getEffectAmplifier() {
        return effectAmplifier;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public UnaryOperator<Style> getStyleModifier() {
        return styleModifier;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
