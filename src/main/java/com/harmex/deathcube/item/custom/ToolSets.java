package com.harmex.deathcube.item.custom;

public enum ToolSets implements ToolSet {
    WOOD("wood"),
    GOLD("gold"),
    BONE("bone"),
    STONE("stone"),
    COPPER("copper"),
    IRON("iron"),
    EMERALD("emerald"),
    DIAMOND("diamond"),
    OBSIDIAN("obsidian"),
    NETHERITE("netherite");

    private final String name;

    ToolSets(String pName) {
        name = pName;
    }

    @Override
    public String getName() {
        return name;
    }
}
