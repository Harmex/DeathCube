package com.harmex.deathcube.world.item.custom;

public enum ToolSets implements ToolSet {
    WOOD("wood",
            new float[] {3.0F, 1.5F, 1.0F, 6.0F, 0.0F},
            new float[] {-2.4F, -3.0F, -2.8F, -3.2F, -3.0F}),
    GOLD("gold",
            new float[] {3.0F, 1.5F, 1.0F, 6.0F, 0.0F},
            new float[] {-2.4F, -3.0F, -2.8F, -3.0F, -3.0F}),
    BONE("bone",
            new float[] {3.0F, 1.5F, 1.0F, 6.5F, -1.0F},
            new float[] {-2.4F, -3.0F, -2.8F, -3.2F, -2.5F}),
    STONE("stone",
            new float[] {3.0F, 1.5F, 1.0F, 7.0F, -1.0F},
            new float[] {-2.4F, -3.0F, -2.8F, -3.2F, -2.0F}),
    COPPER("copper",
            new float[] {3.0F, 1.5F, 1.0F, 6.0F, -2.0F},
            new float[] {-2.4F, -3.0F, -2.8F, -3.1F, -1.0F}),
    IRON("iron",
            new float[] {3.0F, 1.5F, 1.0F, 6.0F, -2.0F},
            new float[] {-2.4F, -3.0F, -2.8F, -3.1F, -1.0F}),
    EMERALD("emerald",
            new float[] {3.0F, 1.5F, 1.0F, 5.0F, -3.0F},
            new float[] {-2.4F, -3.0F, -2.8F, -3.0F, -0.0F}),
    DIAMOND("diamond",
            new float[] {3.0F, 1.5F, 1.0F, 5.0F, -3.0F},
            new float[] {-2.4F, -3.0F, -2.8F, -3.0F, 0.0F}),
    OBSIDIAN("obsidian",
            new float[] {3.0F, 1.5F, 1.0F, 5.0F, -3.0F},
            new float[] {-2.4F, -3.0F, -2.8F, -3.0F, 0.0F}),
    NETHERITE("netherite",
            new float[] {3.0F, 1.5F, 1.0F, 5.0F, -4.0F},
            new float[] {-2.4F, -3.0F, -2.8F, -3.0F, 0.0F});

    private final String name;
    private final float[] attackDamageModifier;
    private final float[] attackSpeedModifier;

    ToolSets(String pName, float[] pAttackDamageModifier, float[] pAttackSpeedModifier) {
        name = pName;
        attackDamageModifier = pAttackDamageModifier;
        attackSpeedModifier = pAttackSpeedModifier;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float[] getAttackDamageModifier() {
        return attackDamageModifier;
    }

    @Override
    public float[] getAttackSpeedModifier() {
        return attackSpeedModifier;
    }
}
