package com.harmex.deathcube.world.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.ITeleporter;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TotemOfResurrectionItem extends Item implements ITeleporter {
    public TotemOfResurrectionItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();

        if (player == null) {
            return InteractionResult.FAIL;
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
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (pStack.hasTag()) {
            assert pStack.getTag() != null;
            if (pStack.getTag().contains("deathcube.saved_dim") && pStack.getTag().contains("deathcube.saved_pos")) {
                String  savedDim = pStack.getTag().getString("deathcube.saved_dim");
                ResourceLocation savedDimLocation = new ResourceLocation(savedDim);
                int[] savedPos = pStack.getTag().getIntArray("deathcube.saved_pos");

                pTooltipComponents.add(Component.literal(savedPos[0] + ", " + savedPos[1] + ", " + savedPos[2]
                        + " (" + savedDimLocation.getPath() + ")").withStyle(ChatFormatting.GRAY));
            }
        }
    }
}
