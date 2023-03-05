package com.harmex.deathcube.world.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TimeWandItem extends Item {
    public TimeWandItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.UNCOMMON)
        );
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if (pStack.hasTag()) {
            assert pStack.getTag() != null;
            if (pStack.getTag().contains("deathcube.saved_dim")) {
                String  savedDim = pStack.getTag().getString("deathcube.saved_dim");

                pTooltipComponents.add(Component.literal(" - Dimension: " + savedDim));
            }
            if (pStack.getTag().contains("deathcube.saved_pos")) {
                int[] savedPos = pStack.getTag().getIntArray("deathcube.saved_pos");

                pTooltipComponents.add(Component.literal(" - X: " + savedPos[0]));
                pTooltipComponents.add(Component.literal(" - Y: " + savedPos[1]));
                pTooltipComponents.add(Component.literal(" - Z: " + savedPos[2]));
            }
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (!pPlayer.isShiftKeyDown()) {
            if (itemStack.getItem() instanceof TimeWandItem) {
                if (itemStack.hasTag()) {
                    if (itemStack.getTag() != null && itemStack.getTag().contains("deathcube.saved_dim")) {
                        String savedDim = itemStack.getTag().getString("deathcube.saved_dim");

                        ResourceLocation location = new ResourceLocation(savedDim);
                        ResourceKey<Level> resourceKey = ResourceKey.create(Registries.DIMENSION, location);
                        MinecraftServer minecraftServer = pLevel.getServer();

                        if (minecraftServer == null) {
                            return InteractionResultHolder.fail(itemStack);
                        }

                        ServerLevel targetDim = minecraftServer.getLevel(resourceKey);

                        if (targetDim != null && targetDim != pLevel) {
                            pPlayer.changeDimension(targetDim);
                        }
                    }
                    if (itemStack.getTag().contains("deathcube.saved_pos")) {
                        int[] savedPos = itemStack.getTag().getIntArray("deathcube.saved_pos");

                        pPlayer.teleportTo(savedPos[0] + 0.5, savedPos[1], savedPos[2] + 0.5);
                        if (!pPlayer.isCreative() && !pPlayer.isSpectator()) {
                            pPlayer.getCooldowns().addCooldown(this, 1200);
                        }

                        return InteractionResultHolder.success(itemStack);
                    } else {
                        return InteractionResultHolder.fail(itemStack);
                    }
                } else {
                    return InteractionResultHolder.fail(itemStack);
                }
            } else {
                return InteractionResultHolder.fail(itemStack);
            }
        }
        return InteractionResultHolder.fail(itemStack);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();
        if (player == null) {
            return InteractionResult.PASS;
        }
        if (player.isShiftKeyDown()) {
            BlockPos blockPos = pContext.getClickedPos();
            Direction clickedFace = pContext.getClickedFace();
            ItemStack itemStack = player.getItemInHand(pContext.getHand());

            String dimToSave = level.dimension().location().toString();

            BlockPos relativeBlockPos = blockPos.relative(clickedFace);

            List<Integer> posToSave = new ArrayList<>();
            posToSave.add(relativeBlockPos.getX());
            posToSave.add(relativeBlockPos.getY());
            posToSave.add(relativeBlockPos.getZ());

            CompoundTag tag = new CompoundTag();
            tag.putIntArray("deathcube.saved_pos", posToSave);
            tag.putString("deathcube.saved_dim", dimToSave);

            itemStack.setTag(tag);

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }
}
