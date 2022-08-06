package com.harmex.deathcube.item;

import net.minecraft.world.item.Rarity;

public class ModRarities {
    public static final Rarity JUNK = Rarity.create("junk", style -> style.withColor(0x555555));
    public static final Rarity COMMON = Rarity.create("common", style -> style.withColor(0xFFFFFF));
    public static final Rarity UNCOMMON = Rarity.create("uncommon", style -> style.withColor(0x55FF55));
    public static final Rarity RARE = Rarity.create("rare", style -> style.withColor(0x5555FF));
    public static final Rarity EPIC = Rarity.create("epic", style -> style.withColor(0xAA00AA));
    public static final Rarity LEGENDARY = Rarity.create("legendary", style -> style.withColor(0xFFAA00));
    public static final Rarity HELLISH = Rarity.create("hellish", style -> style.withColor(0x550000));
    public static final Rarity MYTHIC = Rarity.create("mythic", style -> style.withColor(0xFF55FF));
    public static final Rarity DIVINE = Rarity.create("divine", style -> style.withColor(0x55FFFF));
}
