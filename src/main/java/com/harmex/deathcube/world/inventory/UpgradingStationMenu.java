package com.harmex.deathcube.world.inventory;

import com.harmex.deathcube.util.ModTags;
import com.harmex.deathcube.world.level.block.ModBlocks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class UpgradingStationMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final ContainerData data;

    public UpgradingStationMenu(int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf pExtraData) {
        this(pContainerId, pPlayerInventory,
                Objects.requireNonNull(pPlayerInventory.player.level.getBlockEntity(pExtraData.readBlockPos())),
                ContainerLevelAccess.NULL,
                new SimpleContainerData(1));
    }

    public UpgradingStationMenu(int pContainerId, Inventory pPlayerInventory, BlockEntity pBlockEntity, ContainerLevelAccess pAccess, ContainerData pData) {
        super(ModMenuTypes.UPGRADING_STATION_MENU.get(), pContainerId);
        checkContainerSize(pPlayerInventory, 1);
        this.access = pAccess;
        this.data = pData;

        addPlayerInventory(pPlayerInventory);
        addPlayerHotbar(pPlayerInventory);

        pBlockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler ->
                this.addSlot(new SlotItemHandler(iItemHandler, 0, 26, 36) {
                    @Override
                    public boolean mayPlace(@NotNull ItemStack stack) {
                        return stack.is(ModTags.Items.UPGRADEABLE);
                    }

                    @Override
                    public int getMaxStackSize() {
                        return 1;
                    }
                })
        );
    }

    @Override
    public void slotsChanged(Container pContainer) {
        super.slotsChanged(pContainer);
        // Here the thing to display the cost
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        Slot clickedSlot = this.slots.get(pIndex);
        if (!clickedSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack clickedStack = clickedSlot.getItem();
        ItemStack clickedStackCopy = clickedStack.copy();

        if (pIndex == 36) {
            if (!moveItemStackTo(clickedStack, 0, 36, true)) {
                return ItemStack.EMPTY;
            }
        } else {
            if (this.slots.get(36).hasItem() || !this.slots.get(36).mayPlace(clickedStack)) {
                return ItemStack.EMPTY;
            }
            this.moveItemStackTo(clickedStack, 36, 37, true);
        }

        if (clickedStack.isEmpty()) {
            clickedSlot.set(ItemStack.EMPTY);
        } else {
            clickedSlot.setChanged();
        }

        if (clickedStack.getCount() == clickedStackCopy.getCount()) {
            return ItemStack.EMPTY;
        }

        clickedSlot.onTake(pPlayer, clickedStack);

        return clickedStackCopy;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.access,
                pPlayer, ModBlocks.UPGRADING_STATION.get());
    }

    public boolean hasBaseIngredient() {
        return data.get(0) != 0;
    }

    private void addPlayerInventory(Inventory pPlayerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(pPlayerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }
    private void addPlayerHotbar(Inventory pPlayerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(pPlayerInventory, i, 8 + i * 18, 142));
        }
    }
}
