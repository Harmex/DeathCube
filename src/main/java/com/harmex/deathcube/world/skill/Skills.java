package com.harmex.deathcube.world.skill;

import net.minecraft.network.chat.Style;
import net.minecraft.util.StringRepresentable;

import java.util.function.UnaryOperator;

public enum Skills implements Skill, StringRepresentable {
    COMBAT("combat", 100, style -> style.withColor(0xAA2020)),
    MINING("mining", 100, style -> style.withColor(0x909090)),
    FARMING("farming", 100, style -> style.withColor(0xE1B235)),
    FISHING("fishing", 100, style -> style.withColor(0x00d2FF)),
    ENCHANTING("enchanting", 100, style -> style.withColor(0x7070F0)),
    MAGIC("magic", 100, style -> style.withColor(0xB364CD));


    private final String name;
    private final int maxLvl;
    private final UnaryOperator<Style> styleModifier;
    private static final StringRepresentable.EnumCodec<Skills> CODEC = StringRepresentable.fromEnum(Skills::values);
    Skills(String pName, int pMaxLvl, UnaryOperator<Style> pStyleModifier) {
        name = pName;
        maxLvl = pMaxLvl;
        styleModifier = pStyleModifier;
    }

    public static Skill getByName(String pName) {
        Skill skill = CODEC.byName(pName);
        return skill != null ? skill : Skills.COMBAT;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxLvl() {
        return maxLvl;
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
