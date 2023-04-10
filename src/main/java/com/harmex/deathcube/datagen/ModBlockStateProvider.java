package com.harmex.deathcube.datagen;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.world.level.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, DeathCube.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        verticalBlock(ModBlocks.ECHO_AMETHYST_BLOCK.get());
        simpleBlock(ModBlocks.UPGRADING_STATION.get());
        simpleBlock(ModBlocks.MATTER_MANIPULATOR.get());

        simpleBlock(ModBlocks.ZANTHINE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_ZANTHINE_ORE.get());
    }


    private ModelFile verticalBlock(Block pBlock) {
        return verticalBlock(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(pBlock)));
    }

    public ModelFile verticalBlock(ResourceLocation block) {
        return models().withExistingParent(block.getPath(), "cube_bottom_top")
                .texture("top", new ResourceLocation(block.getNamespace(), "block/" + block.getPath() + "_top"))
                .texture("side", new ResourceLocation(block.getNamespace(), "block/" + block.getPath() + "_side"))
                .texture("bottom", new ResourceLocation(block.getNamespace(), "block/" + block.getPath() + "_bottom"));
    }
}
