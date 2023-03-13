package com.harmex.deathcube.world.level.block.custom;

import com.harmex.deathcube.world.level.block.entity.custom.ResurrectionAltarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ResurrectionAltarBlock extends BaseEntityBlock {
    private static final VoxelShape BASE = Block.box(4, 0, 4, 12, 2, 12);
    private static final VoxelShape LEG = Block.box(6, 2, 6, 10, 8, 10);
    private static final VoxelShape SUB_TOP = Block.box(2, 8, 2, 14, 10, 14);
    private static final VoxelShape TOP = Block.box(0, 10, 0, 16, 12, 16);
    private static final VoxelShape SHAPE = Shapes.or(BASE, LEG, SUB_TOP, TOP);
    public ResurrectionAltarBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof ResurrectionAltarBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer) pPlayer), (ResurrectionAltarBlockEntity) blockEntity, pPos);
            } else {
                throw new IllegalStateException("Our container provider is missing !");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new ResurrectionAltarBlockEntity(pPos, pState);
    }
}
