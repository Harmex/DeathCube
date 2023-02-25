package com.harmex.deathcube.datagen;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
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

        simpleBlock(ModBlocks.CHERRY_PLANKS.get());
        simpleBlock(ModBlocks.CHERRY_SAPLING.get(), models().cross(ModBlocks.CHERRY_SAPLING.getKey().location().getPath(),
                blockTexture(ModBlocks.CHERRY_SAPLING.get())));
        simpleBlock(ModBlocks.POTTED_CHERRY_SAPLING.get(),
                flowerPotCross(ModBlocks.POTTED_CHERRY_SAPLING.get(), ModBlocks.CHERRY_SAPLING.get()));
        logBlock((RotatedPillarBlock) ModBlocks.CHERRY_LOG.get());
        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_CHERRY_LOG.get());
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_CHERRY_WOOD.get(),
                blockTexture(ModBlocks.STRIPPED_CHERRY_LOG.get()), blockTexture(ModBlocks.STRIPPED_CHERRY_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.CHERRY_WOOD.get(),
                blockTexture(ModBlocks.CHERRY_LOG.get()), blockTexture(ModBlocks.CHERRY_LOG.get()));
        simpleBlock(ModBlocks.CHERRY_LEAVES.get());
        slabBlock((SlabBlock) ModBlocks.CHERRY_SLAB.get(), blockTexture(ModBlocks.CHERRY_PLANKS.get()),
                blockTexture(ModBlocks.CHERRY_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.CHERRY_FENCE.get(), blockTexture(ModBlocks.CHERRY_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.CHERRY_STAIRS.get(), blockTexture(ModBlocks.CHERRY_PLANKS.get()));
        buttonBlock((ButtonBlock) ModBlocks.CHERRY_BUTTON.get(), blockTexture(ModBlocks.CHERRY_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock) ModBlocks.CHERRY_PRESSURE_PLATE.get(), blockTexture(ModBlocks.CHERRY_PLANKS.get()));
        doorBlockWithRenderType((DoorBlock) ModBlocks.CHERRY_DOOR.get(),
                new ResourceLocation(DeathCube.MODID, "block/cherry_door_bottom"),
                new ResourceLocation(DeathCube.MODID, "block/cherry_door_top"),
                new ResourceLocation("minecraft", "cutout"));
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.CHERRY_TRAPDOOR.get(),
                blockTexture(ModBlocks.CHERRY_TRAPDOOR.get()), true,
                new ResourceLocation("minecraft", "cutout"));
        fenceGateBlock((FenceGateBlock) ModBlocks.CHERRY_FENCE_GATE.get(), blockTexture(ModBlocks.CHERRY_PLANKS.get()));
        signBlock((StandingSignBlock) ModBlocks.CHERRY_SIGN.get(), (WallSignBlock) ModBlocks.CHERRY_WALL_SIGN.get(),
                blockTexture(ModBlocks.CHERRY_PLANKS.get()));
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
