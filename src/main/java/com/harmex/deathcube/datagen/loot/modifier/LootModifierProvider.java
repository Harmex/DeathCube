package com.harmex.deathcube.datagen.loot.modifier;

import com.harmex.deathcube.world.item.enchantment.ModEnchantments;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class LootModifierProvider extends GlobalLootModifierProvider {
    public LootModifierProvider(PackOutput output, String modid) {
        super(output, modid);
    }

    @Override
    protected void start() {
        add("smelter", new SmelterEnchantmentModifier(
                new LootItemCondition[] {
                        MatchTool.toolMatches(
                                ItemPredicate.Builder.item().hasEnchantment(
                                        new EnchantmentPredicate(ModEnchantments.SMELTER.get(), MinMaxBounds.Ints.atLeast(1))
                                )
                        ).build()
                }
        ));
    }
}
