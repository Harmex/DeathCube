package com.harmex.deathcube.datagen.loot;

import com.harmex.deathcube.world.entity.ModEntityTypes;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class ModEntityLootSubProvider extends EntityLootSubProvider {
    public ModEntityLootSubProvider() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        this.add(ModEntityTypes.AZRATHAL.get(), LootTable.lootTable());
        this.add(ModEntityTypes.BORZADON.get(), LootTable.lootTable());
        this.add(ModEntityTypes.GALTERIUS.get(), LootTable.lootTable());
        this.add(ModEntityTypes.NAERVUS.get(), LootTable.lootTable());
        this.add(ModEntityTypes.ZANUZAL.get(), LootTable.lootTable());
    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return ModEntityTypes.ENTITY_TYPES.getEntries().stream().flatMap(RegistryObject::stream);
    }
}
