package com.harmex.deathcube.world.inventory;

import com.harmex.deathcube.screen.slot.ModResultSlot;
import com.harmex.deathcube.world.level.block.ModBlocks;
import com.harmex.deathcube.world.level.block.entity.custom.MatterManipulatorBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class MatterManipulatorMenu extends AbstractContainerMenu {
    private final MatterManipulatorBlockEntity blockEntity;
    private final Level level;
    private final ContainerData dataAccess;
    private final int containerSize = 11;

    public MatterManipulatorMenu(int pContainerId, Inventory pInventory, FriendlyByteBuf pExtraData) {
        this(pContainerId, pInventory, pInventory.player.level.getBlockEntity(pExtraData.readBlockPos()), new SimpleContainerData(2));
    }

    public MatterManipulatorMenu(int pContainerId, Inventory inventory, BlockEntity blockEntity, ContainerData dataAccess) {
        super(ModMenuTypes.MATTER_MANIPULATOR_MENU.get(), pContainerId);
        checkContainerSize(inventory, 11);
        this.blockEntity = ((MatterManipulatorBlockEntity) blockEntity);
        this.level = inventory.player.level;
        this.dataAccess = dataAccess;

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            for(int i = 0; i < 3; ++i) {
                for(int j = 0; j < 3; ++j) {
                    this.addSlot(new SlotItemHandler(handler, j + i * 3, 8 + j * 18, 17 + i * 18));
                }
            }
            this.addSlot(new SlotItemHandler(handler, 9, 80, 35));
            this.addSlot(new ModResultSlot(handler, 10, 134, 35));
        });

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        addDataSlots(dataAccess);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public boolean isCrafting() {
        return dataAccess.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = dataAccess.get(0);
        int maxProgress = dataAccess.get(1);
        int progressArrowSize = 24;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack clickedStackCopy = ItemStack.EMPTY;
        Slot clickedSlot = this.slots.get(pIndex);
        if (clickedSlot.hasItem()) {
            ItemStack clickedStack = clickedSlot.getItem();
            clickedStackCopy = clickedStack.copy();
            if (pIndex < this.containerSize) {
                if (!this.moveItemStackTo(clickedStack, this.containerSize, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(clickedStack, 0, this.containerSize, false)) {
                return ItemStack.EMPTY;
            }

            if (clickedStack.isEmpty()) {
                clickedSlot.set(ItemStack.EMPTY);
            } else {
                clickedSlot.setChanged();
            }
        }
        return clickedStackCopy;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.MATTER_MANIPULATOR.get());
    }
}
