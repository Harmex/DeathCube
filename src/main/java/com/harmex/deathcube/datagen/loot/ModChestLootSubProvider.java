package com.harmex.deathcube.datagen.loot;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.world.item.ModItems;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class ModChestLootSubProvider implements LootTableSubProvider {

    private static final ResourceLocation WARRIOR_DUNGEON_SECRET =
            new ResourceLocation(DeathCube.MODID, "chests/warrior_dungeon_secret");

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> p_124363_) {
        p_124363_.accept(new ResourceLocation(DeathCube.MODID, "loot_bags/galterius"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).setBonusRolls(ConstantValue.exactly(0.5F))
                        .add(LootItem.lootTableItem(ModItems.GALTERIUS_HELMET.get()))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).setBonusRolls(ConstantValue.exactly(0.5F))
                        .add(LootItem.lootTableItem(ModItems.GALTERIUS_CHESTPLATE.get()))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).setBonusRolls(ConstantValue.exactly(0.5F))
                        .add(LootItem.lootTableItem(ModItems.GALTERIUS_LEGGINGS.get()))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).setBonusRolls(ConstantValue.exactly(0.5F))
                        .add(LootItem.lootTableItem(ModItems.GALTERIUS_BOOTS.get()))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F)))
        );

        p_124363_.accept(WARRIOR_DUNGEON_SECRET, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F))
                        .add(LootItem.lootTableItem(Items.SADDLE).setWeight(20))
                        .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(15))
                        .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(2))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_OTHERSIDE).setWeight(2))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_13).setWeight(15))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_CAT).setWeight(15))
                        .add(LootItem.lootTableItem(Items.NAME_TAG).setWeight(20))
                        .add(LootItem.lootTableItem(Items.GOLDEN_HORSE_ARMOR).setWeight(10))
                        .add(LootItem.lootTableItem(Items.IRON_HORSE_ARMOR).setWeight(15))
                        .add(LootItem.lootTableItem(Items.DIAMOND_HORSE_ARMOR).setWeight(5))
                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(10)
                                .apply(EnchantRandomlyFunction.randomApplicableEnchantment())))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 4.0F))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.BREAD).setWeight(20))
                        .add(LootItem.lootTableItem(Items.WHEAT).setWeight(20)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.BUCKET).setWeight(10))
                        .add(LootItem.lootTableItem(Items.REDSTONE).setWeight(15)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.COAL).setWeight(15)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.MELON_SEEDS).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.PUMPKIN_SEEDS).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.BEETROOT_SEEDS).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(3.0F))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
                        .add(LootItem.lootTableItem(Items.GUNPOWDER).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
                        .add(LootItem.lootTableItem(Items.STRING).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))));
    }
}
