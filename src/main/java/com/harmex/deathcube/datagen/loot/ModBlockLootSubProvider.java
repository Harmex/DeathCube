package com.harmex.deathcube.datagen.loot;

import com.harmex.deathcube.world.item.ModItems;
import com.harmex.deathcube.world.level.block.ModBlocks;
import com.harmex.deathcube.world.level.block.custom.GoldenCarrotBlock;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class ModBlockLootSubProvider extends BlockLootSubProvider {
    private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
    private static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] NORMAL_LEAVES_FRUIT_CHANCES = new float[]{0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F};
    private static final float[] CHERRY_LEAVES_FRUIT_CHANCES = new float[]{0.5F, 0.55555557F, 0.625F, 0.8333334F, 1.0F};

    public ModBlockLootSubProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.ECHO_AMETHYST_BLOCK.get());
        this.dropSelf(ModBlocks.UPGRADING_STATION.get());
        this.dropSelf(ModBlocks.MATTER_MANIPULATOR.get());
        this.dropSelf(ModBlocks.RESURRECTION_ALTAR.get());

        LootItemCondition.Builder goldenCarrotsLootCondition =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GOLDEN_CARROTS.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GoldenCarrotBlock.AGE, 7));
        this.add(ModBlocks.GOLDEN_CARROTS.get(),
                applyExplosionDecay(ModBlocks.GOLDEN_CARROTS.get(), LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.GOLDEN_CARROT)))
                        .withPool(LootPool.lootPool().when(goldenCarrotsLootCondition).add(LootItem.lootTableItem(Items.GOLDEN_CARROT)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

        this.dropSelf(ModBlocks.CHERRY_PLANKS.get());
        this.dropSelf(ModBlocks.CHERRY_SAPLING.get());
        this.dropPottedContents(ModBlocks.POTTED_CHERRY_SAPLING.get());
        this.add(ModBlocks.CHERRY_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.CHERRY_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                                .add(applyExplosionCondition(block, LootItem.lootTableItem(ModItems.CHERRY.get()))
                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE,
                                                CHERRY_LEAVES_FRUIT_CHANCES)))));
        this.dropSelf(ModBlocks.CHERRY_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_CHERRY_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_CHERRY_WOOD.get());
        this.dropSelf(ModBlocks.CHERRY_WOOD.get());
        this.add(ModBlocks.CHERRY_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(ModBlocks.CHERRY_FENCE.get());
        this.dropSelf(ModBlocks.CHERRY_STAIRS.get());
        this.dropSelf(ModBlocks.CHERRY_BUTTON.get());
        this.dropSelf(ModBlocks.CHERRY_PRESSURE_PLATE.get());
        this.add(ModBlocks.CHERRY_DOOR.get(), this::createDoorTable);
        this.dropSelf(ModBlocks.CHERRY_TRAPDOOR.get());
        this.dropSelf(ModBlocks.CHERRY_FENCE_GATE.get());
        this.dropOther(ModBlocks.CHERRY_SIGN.get(), ModItems.CHERRY_SIGN.get());
        this.dropOther(ModBlocks.CHERRY_WALL_SIGN.get(), ModItems.CHERRY_SIGN.get());

        this.add(ModBlocks.ZANTHINE_ORE.get(), block ->
                createOreDrop(block, ModItems.ZANTHINE.get())
        );
        this.add(ModBlocks.DEEPSLATE_ZANTHINE_ORE.get(), block ->
                createOreDrop(block, ModItems.ZANTHINE.get())
        );
    }

    protected LootTable.@NotNull Builder createSlabItemTable(@NotNull Block pBlock) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pBlock).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE)))))));
    }

    protected LootTable.Builder createDoorTable(Block pDoorBlock) {
        return this.createSinglePropConditionTable(pDoorBlock, DoorBlock.HALF, DoubleBlockHalf.LOWER);
    }

    protected <T extends Comparable<T> & StringRepresentable> LootTable.Builder createSinglePropConditionTable(Block pBlock, Property<T> pProperty, T pValue) {
        return LootTable.lootTable().withPool(this.applyExplosionCondition(pBlock, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(pProperty, pValue))))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().flatMap(RegistryObject::stream)::iterator;
    }
}
