package com.harmex.deathcube.item.custom;

public enum ItemSets implements ItemSet{
    OAK("oak", ArmorSets.OAK, ToolSets.WOOD),
    SPRUCE("spruce", ArmorSets.SPRUCE, ToolSets.WOOD),
    BIRCH("birch", ArmorSets.BIRCH, ToolSets.WOOD),
    JUNGLE("jungle", ArmorSets.JUNGLE, ToolSets.WOOD),
    ACACIA("acacia", ArmorSets.ACACIA, ToolSets.WOOD),
    DARK_OAK("dark_oak", ArmorSets.DARK_OAK, ToolSets.WOOD),
    MANGROVE("mangrove", ArmorSets.MANGROVE, ToolSets.WOOD),
    CHERRY("cherry", ArmorSets.CHERRY, ToolSets.WOOD),
    CRIMSON("crimson", ArmorSets.CRIMSON, ToolSets.WOOD),
    WARPED("warped", ArmorSets.WARPED, ToolSets.WOOD),
    GOLD("gold", ArmorSets.GOLD, ToolSets.GOLD),
    BONE("bone", ArmorSets.BONE, ToolSets.BONE),
    STONE("stone", ArmorSets.STONE, ToolSets.STONE),
    COPPER("copper", ArmorSets.COPPER, ToolSets.COPPER),
    IRON("iron", ArmorSets.IRON, ToolSets.IRON),
    EMERALD("emerald", ArmorSets.EMERALD, ToolSets.EMERALD),
    DIAMOND("diamond", ArmorSets.DIAMOND, ToolSets.DIAMOND),
    OBSIDIAN("obsidian", ArmorSets.OBSIDIAN, ToolSets.OBSIDIAN),
    NETHERITE("netherite", ArmorSets.NETHERITE, ToolSets.NETHERITE);

    private final String name;
    private final ArmorSet armorSet;
    private final ToolSet toolSet;

    ItemSets(String pName, ArmorSet pArmorSet, ToolSet pToolSet) {
        name = pName;
        armorSet = pArmorSet;
        toolSet = pToolSet;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArmorSet getArmorSet() {
        return armorSet;
    }

    @Override
    public ToolSet getToolSet() {
        return toolSet;
    }
}
