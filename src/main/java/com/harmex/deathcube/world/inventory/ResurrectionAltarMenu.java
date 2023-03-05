package com.harmex.deathcube.world.inventory;

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
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ResurrectionAltarMenu extends AbstractContainerMenu {
    private final ResurrectionAltarBlockEntity blockEntity;
    private final Level level;
    private final int containerSize = 1;

    public ResurrectionAltarMenu(int pContainerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(pContainerId, inventory, inventory.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public ResurrectionAltarMenu(int pContainerId, Inventory inventory, BlockEntity blockEntity) {
        super(ModMenuTypes.RESURRECTION_ALTAR_MENU.get(), pContainerId);
        checkContainerSize(inventory, 1);
        this.blockEntity = ((ResurrectionAltarBlockEntity) blockEntity);
        this.level = inventory.player.level;


        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 98, 35));
        });
        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);
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
    public boolean stillValid(@NotNull Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.MATTER_MANIPULATOR.get());
    }
}
