package com.harmex.deathcube.world.feature;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, DeathCube.MODID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ZANTHINE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ZANTHINE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ZANTHINE_ORE.get().defaultBlockState())
    ));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ZANTHINE_ORE =
            CONFIGURED_FEATURES.register("zanthine_ore", () -> new ConfiguredFeature<>(
                    Feature.ORE,
                    new OreConfiguration(OVERWORLD_ZANTHINE_ORES.get(), 4)
            ));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CHERRY_TREE =
            CONFIGURED_FEATURES.register("cherry_tree", () -> new ConfiguredFeature<>(
                    Feature.TREE,
                    createCherry().build()
            ));

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block pTrunk, Block pLeaves,
                                                                                     int pTrunkHeight, int pTrunkRandA,
                                                                                     int pTrunkRandB, int pFoliageRadius) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(pTrunk),
                new StraightTrunkPlacer(pTrunkHeight, pTrunkRandA, pTrunkRandB),
                BlockStateProvider.simple(pLeaves),
                new BlobFoliagePlacer(ConstantInt.of(pFoliageRadius), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        );
    }

    private static TreeConfiguration.TreeConfigurationBuilder createCherry() {
        return createStraightBlobTree(ModBlocks.CHERRY_LOG.get(), ModBlocks.CHERRY_LEAVES.get(),
                4, 2, 0, 2).ignoreVines();
    }

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
