package com.harmex.deathcube.world.skill;

import net.minecraft.network.chat.Style;

import java.util.function.UnaryOperator;

public interface Skill {
    String getName();

    int getMaxLvl();

    UnaryOperator<Style> getStyleModifier();
}
