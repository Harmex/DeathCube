package com.harmex.deathcube.datagen;

import com.harmex.deathcube.datagen.loot.ModBlockLootSubProvider;
import com.harmex.deathcube.datagen.loot.ModChestLootSubProvider;
import com.harmex.deathcube.datagen.loot.ModEntityLootSubProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ModLootTableProvider extends LootTableProvider {

    public ModLootTableProvider(DataGenerator pGenerator) {
        super(pGenerator.getPackOutput(), Collections.emptySet(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLootSubProvider::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(ModEntityLootSubProvider::new, LootContextParamSets.ENTITY),
                new LootTableProvider.SubProviderEntry(ModChestLootSubProvider::new, LootContextParamSets.CHEST)
        ));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @NotNull ValidationContext validationtracker) {
        map.forEach((id, table) -> table.validate(validationtracker));
    }
}
