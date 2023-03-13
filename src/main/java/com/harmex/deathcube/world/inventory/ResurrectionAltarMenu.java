package com.harmex.deathcube.world.inventory;

import com.harmex.deathcube.world.inventory.slot.ResurrectionAltarSlot;
import com.harmex.deathcube.world.level.block.ModBlocks;
import com.harmex.deathcube.world.level.block.entity.custom.ResurrectionAltarBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ResurrectionAltarMenu extends AbstractContainerMenu {
    private final ResurrectionAltarBlockEntity blockEntity;
    private final Level level;

    public ResurrectionAltarMenu(int pContainerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(pContainerId, inventory, Objects.requireNonNull(inventory.player.level.getBlockEntity(extraData.readBlockPos())));
    }

    public ResurrectionAltarMenu(int pContainerId, Inventory inventory, BlockEntity blockEntity) {
        super(ModMenuTypes.RESURRECTION_ALTAR_MENU.get(), pContainerId);
        checkContainerSize(inventory, 1);
        this.blockEntity = ((ResurrectionAltarBlockEntity) blockEntity);
        this.level = inventory.player.level;


        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new ResurrectionAltarSlot(handler, 0, 8, 8));
        });
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.RESURRECTION_ALTAR.get());
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack pStack, Slot pSlot) {
        return false;
    }

    @Override
    public boolean canDragTo(Slot pSlot) {
        return false;
    }
}
