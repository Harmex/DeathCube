package com.harmex.deathcube.world.skill;

import net.minecraft.network.chat.Style;

import java.util.function.UnaryOperator;

public enum Skills implements Skill {
    COMBAT("combat", 100, style -> style.withColor(0xAA2020)),
    WOODCUTTING("woodcutting", 100, style -> style.withColor(0x987849)),
    MINING("mining", 100, style -> style.withColor(0x909090)),
    FARMING("farming", 100, style -> style.withColor(0xE1B235)),
    FISHING("fishing", 100, style -> style.withColor(0x00d2FF));
    //ENCHANTING("enchanting", 100, style -> style.withColor(0x7070F0)),
    //MAGIC("magic", 100, style -> style.withColor(0xB364CD));


    private final String name;
    private final int maxLevel;
    private final UnaryOperator<Style> styleModifier;

    Skills(String pName, int pMaxLevel, UnaryOperator<Style> pStyleModifier) {
        name = pName;
        maxLevel = pMaxLevel;
        styleModifier = pStyleModifier;
    }

    public String getName() {
        return name;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public UnaryOperator<Style> getStyleModifier() {
        return styleModifier;
    }
}
