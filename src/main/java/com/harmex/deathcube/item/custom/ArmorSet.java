package com.harmex.deathcube.item.custom;

import net.minecraft.network.chat.Style;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Rarity;

import javax.annotation.Nullable;
import java.util.function.UnaryOperator;

public interface ArmorSet {
    String getName();

    ArmorMaterial getArmorMaterial();

    @Nullable MobEffect getFullSetBonus();

    int getEffectAmplifier();

    Rarity getRarity();

    UnaryOperator<Style> getStyleModifier();
}
