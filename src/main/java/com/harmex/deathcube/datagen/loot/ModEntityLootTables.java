package com.harmex.deathcube.datagen.loot;

import com.harmex.deathcube.entity.ModEntityTypes;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModEntityLootTables extends EntityLoot {
    @Override
    protected void addTables() {
        this.add(ModEntityTypes.GALTERIUS.get(), LootTable.lootTable());
        this.add(ModEntityTypes.NAERVUS.get(), LootTable.lootTable());
    }

    @Override
    protected @NotNull Iterable<EntityType<?>> getKnownEntities() {
        return ModEntityTypes.ENTITY_TYPES.getEntries().stream().flatMap(RegistryObject::stream)::iterator;
    }
}
