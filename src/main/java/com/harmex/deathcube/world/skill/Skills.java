package com.harmex.deathcube.world.skill;

import net.minecraft.util.StringRepresentable;

public enum Skills implements Skill, StringRepresentable {
    COMBAT("combat", 100),
    MINING("mining", 100),
    FARMING("farming", 100),
    FISHING("fishing", 100),
    ENCHANTING("enchanting", 100),
    MAGIC("magic", 100);


    private final String name;
    private final int maxLvl;
    private static final StringRepresentable.EnumCodec<Skills> CODEC = StringRepresentable.fromEnum(Skills::values);
    Skills(String pName, int pMaxLvl) {
        name = pName;
        maxLvl = pMaxLvl;
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
    public String getSerializedName() {
        return name;
    }
}
