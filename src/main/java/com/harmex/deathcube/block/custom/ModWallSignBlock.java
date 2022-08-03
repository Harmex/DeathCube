package com.harmex.deathcube.block.custom;

import com.harmex.deathcube.block.entity.custom.ModSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;

public class ModWallSignBlock extends WallSignBlock {
    public ModWallSignBlock(Properties pProperties, WoodType pWoodType) {
        super(pProperties, pWoodType);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new ModSignBlockEntity(pPos, pState);
    }
}
