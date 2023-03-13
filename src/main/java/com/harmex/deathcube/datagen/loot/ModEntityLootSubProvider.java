package com.harmex.deathcube.datagen.loot;

import com.harmex.deathcube.world.entity.ModEntityTypes;
import com.harmex.deathcube.world.item.ModItems;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetEnchantmentsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
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
        this.add(ModEntityTypes.GALTERIUS.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 7.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 4.0F)))))
                        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.GALTERIUS_HELMET.get())
                                        .apply(new SetEnchantmentsFunction.Builder().withEnchantment(Enchantments.ALL_DAMAGE_PROTECTION, ConstantValue.exactly(2)))
                                        .apply(new SetEnchantmentsFunction.Builder().withEnchantment(Enchantments.UNBREAKING, ConstantValue.exactly(1)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.25F, 0.05F))))
                        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.GALTERIUS_CHESTPLATE.get())
                                        .apply(new SetEnchantmentsFunction.Builder().withEnchantment(Enchantments.ALL_DAMAGE_PROTECTION, ConstantValue.exactly(2)))
                                        .apply(new SetEnchantmentsFunction.Builder().withEnchantment(Enchantments.UNBREAKING, ConstantValue.exactly(1)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.25F, 0.05F))))
                        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.GALTERIUS_LEGGINGS.get())
                                        .apply(new SetEnchantmentsFunction.Builder().withEnchantment(Enchantments.ALL_DAMAGE_PROTECTION, ConstantValue.exactly(2)))
                                        .apply(new SetEnchantmentsFunction.Builder().withEnchantment(Enchantments.UNBREAKING, ConstantValue.exactly(1)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.25F, 0.05F))))
                        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.GALTERIUS_BOOTS.get())
                                        .apply(new SetEnchantmentsFunction.Builder().withEnchantment(Enchantments.ALL_DAMAGE_PROTECTION, ConstantValue.exactly(2)))
                                        .apply(new SetEnchantmentsFunction.Builder().withEnchantment(Enchantments.UNBREAKING, ConstantValue.exactly(1)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.25F, 0.05F)))));
        this.add(ModEntityTypes.NAERVUS.get(), LootTable.lootTable());
        this.add(ModEntityTypes.ZANUZAL.get(), LootTable.lootTable());
    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return ModEntityTypes.ENTITY_TYPES.getEntries().stream().flatMap(RegistryObject::stream);
    }
}
