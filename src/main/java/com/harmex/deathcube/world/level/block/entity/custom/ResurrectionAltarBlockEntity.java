package com.harmex.deathcube.world.level.block.entity.custom;

import com.harmex.deathcube.world.inventory.ResurrectionAltarMenu;
import com.harmex.deathcube.world.item.ModItems;
import com.harmex.deathcube.world.level.block.entity.ModBlockEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ResurrectionAltarBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> inventoryHandlerLazyOptional = LazyOptional.empty();

    public ResurrectionAltarBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.RESURRECTION_ALTAR_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        ItemStack infiniteTor = new ItemStack(ModItems.TOTEM_OF_RESURRECTION.get());
        infiniteTor.setHoverName(Component.translatable("item.deathcube.totem_of_resurrection")
                .withStyle(ChatFormatting.OBFUSCATED, ChatFormatting.BOLD));

        inventory.setStackInSlot(0, infiniteTor);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("container.deathcube.resurrection_altar");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, @NotNull Player pPlayer) {
        return new ResurrectionAltarMenu(pContainerId, pInventory, this);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return ForgeCapabilities.ITEM_HANDLER.orEmpty(cap, inventoryHandlerLazyOptional);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        inventoryHandlerLazyOptional = LazyOptional.of(() -> inventory);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        inventoryHandlerLazyOptional.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", inventory.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag.getCompound("inventory"));
    }
}
