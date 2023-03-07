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
        simpleBlock(ModBlocks.ECHO_AMETHYST_BLOCK.get());
        simpleBlock(ModBlocks.UPGRADING_STATION.get());
        simpleBlock(ModBlocks.MATTER_MANIPULATOR.get());
        simpleBlock(ModBlocks.RESURRECTION_ALTAR.get());

        simpleBlock(ModBlocks.ZANTHINE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_ZANTHINE_ORE.get());
    }


    private ModelFile flowerPotCross(Block block, Block plant) {
        return flowerPotCross(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)),
                Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(plant)));
    }

    public ModelFile flowerPotCross(ResourceLocation block, ResourceLocation plant) {
        return models().withExistingParent(block.getPath(), "flower_pot_cross").renderType("cutout")
                .texture("plant", new ResourceLocation(block.getNamespace(), "block/" + plant.getPath()));
    }
}
