package com.harmex.deathcube.datagen.loot;

import com.harmex.deathcube.world.item.ModItems;
import com.harmex.deathcube.world.item.enchantment.ModEnchantments;
import com.harmex.deathcube.world.level.block.ModBlocks;
import com.harmex.deathcube.world.level.block.custom.GoldenCarrotBlock;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModBlockLootSubProvider extends BlockLootSubProvider {
    private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder HAS_SMELTER = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(ModEnchantments.SMELT.get(), MinMaxBounds.Ints.atLeast(1))));
    private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());

    public ModBlockLootSubProvider() {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ECHO_AMETHYST_BLOCK.get());
        dropSelf(ModBlocks.UPGRADING_STATION.get());
        dropSelf(ModBlocks.MATTER_MANIPULATOR.get());
        dropSelf(ModBlocks.RESURRECTION_ALTAR.get());

        LootItemCondition.Builder goldenCarrotsLootCondition =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GOLDEN_CARROTS.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GoldenCarrotBlock.AGE, 7));
        add(ModBlocks.GOLDEN_CARROTS.get(),
                applyExplosionDecay(ModBlocks.GOLDEN_CARROTS.get(), LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.GOLDEN_CARROT)))
                        .withPool(LootPool.lootPool().when(goldenCarrotsLootCondition).add(LootItem.lootTableItem(Items.GOLDEN_CARROT)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

        add(ModBlocks.ZANTHINE_ORE.get(), block ->
                createOreDrop(block, ModItems.ZANTHINE.get(), ModItems.ZANTHINE.get()));
        add(ModBlocks.DEEPSLATE_ZANTHINE_ORE.get(), block ->
                createOreDrop(block, ModItems.ZANTHINE.get(), ModItems.ZANTHINE.get()));
    }

    protected LootTable.Builder createOreDrop(Block pBlock, Item pItem, Item pSmeltedItem) {
        return createOreDropDispatchTable(pBlock, pSmeltedItem, applyExplosionDecay(pBlock, LootItem.lootTableItem(pItem).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected static LootTable.Builder createOreDropDispatchTable(Block pBlock, Item pSmeltedItem, LootPoolEntryContainer.Builder<?> pBuilder) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pSmeltedItem).when(HAS_SMELTER).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).otherwise(LootItem.lootTableItem(pBlock).when(HAS_SILK_TOUCH)).otherwise(pBuilder)));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().flatMap(RegistryObject::stream)::iterator;
    }
}
