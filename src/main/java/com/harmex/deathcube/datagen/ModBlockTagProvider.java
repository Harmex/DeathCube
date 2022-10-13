package com.harmex.deathcube.datagen;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.block.ModBlocks;
import com.harmex.deathcube.util.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagProvider extends BlockTagsProvider {
    protected ModBlockTagProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, DeathCube.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        /* DEATHCUBE */
        tag(ModTags.Blocks.CHERRY_LOGS)
                .add(ModBlocks.CHERRY_LOG.get())
                .add(ModBlocks.CHERRY_WOOD.get())
                .add(ModBlocks.STRIPPED_CHERRY_LOG.get())
                .add(ModBlocks.STRIPPED_CHERRY_WOOD.get());

        tag(ModTags.Blocks.ZANTHINE_ORES)
                .add(ModBlocks.ZANTHINE_ORE.get())
                .add(ModBlocks.DEEPSLATE_ZANTHINE_ORE.get());

        /* MINECRAFT */
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.CHERRY_FENCE_GATE.get())
                .replace(false);
        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_CHERRY_SAPLING.get())
                .replace(false);
        tag(BlockTags.LEAVES)
                .add(ModBlocks.CHERRY_LEAVES.get())
                .replace(false);
        tag(BlockTags.LOGS)
                .addTag(ModTags.Blocks.CHERRY_LOGS)
                .replace(false);
        tag(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.CHERRY_LOGS)
                .replace(false);
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addTag(ModTags.Blocks.ZANTHINE_ORES)
                .replace(false);
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.MATTER_MANIPULATOR.get())
                .add(ModBlocks.UPGRADING_STATION.get())
                .replace(false);
        tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(ModBlocks.CHERRY_LOG.get())
                .replace(false);
        tag(BlockTags.PLANKS)
                .add(ModBlocks.CHERRY_PLANKS.get())
                .replace(false);
        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.CHERRY_SAPLING.get())
                .replace(false);
        tag(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.CHERRY_SIGN.get())
                .replace(false);
        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.CHERRY_BUTTON.get())
                .replace(false);
        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.CHERRY_DOOR.get())
                .replace(false);
        tag(BlockTags.FENCES)
                .add(ModBlocks.CHERRY_FENCE.get())
                .replace(false);
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.CHERRY_PRESSURE_PLATE.get())
                .replace(false);
        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.CHERRY_SLAB.get())
                .replace(false);
        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.CHERRY_STAIRS.get())
                .replace(false);
        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.CHERRY_TRAPDOOR.get())
                .replace(false);
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.CHERRY_LEAVES.get())
                .replace(false);
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MATTER_MANIPULATOR.get())
                .add(ModBlocks.UPGRADING_STATION.get())
                .addTag(ModTags.Blocks.ZANTHINE_ORES)
                .replace(false);
    }
}
