package com.harmex.deathcube.datagen.tag;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.util.ModTags;
import com.harmex.deathcube.world.level.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, DeathCube.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        /* DEATHCUBE */
        tag(ModTags.Blocks.ZANTHINE_ORES)
                .add(ModBlocks.ZANTHINE_ORE.get())
                .add(ModBlocks.DEEPSLATE_ZANTHINE_ORE.get());

        /* MINECRAFT */
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addTag(ModTags.Blocks.ZANTHINE_ORES)
                .replace(false);
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.MATTER_MANIPULATOR.get())
                .add(ModBlocks.UPGRADING_STATION.get())
                .replace(false);
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MATTER_MANIPULATOR.get())
                .add(ModBlocks.UPGRADING_STATION.get())
                .addTag(ModTags.Blocks.ZANTHINE_ORES)
                .replace(false);
    }
}
