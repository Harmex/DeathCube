package com.harmex.deathcube.util;

import com.google.common.collect.ImmutableMap;
import com.harmex.deathcube.world.item.ModItems;
import net.minecraft.world.item.Item;

public class LootBagsLoots {
    public static final ImmutableMap<Item, Float> GALTERIUS_BAG = ImmutableMap.<Item, Float>builder()
            .put(ModItems.GALTERIUS_HELMET.get(), 0.5F)
            .put(ModItems.GALTERIUS_CHESTPLATE.get(), 0.5F)
            .put(ModItems.GALTERIUS_LEGGINGS.get(), 0.5F)
            .put(ModItems.GALTERIUS_BOOTS.get(), 0.5F)
            .build();
}
