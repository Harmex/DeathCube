package com.harmex.deathcube.block.entity.custom;

import com.harmex.deathcube.block.entity.ModBlockEntities;
import com.harmex.deathcube.recipe.ShapedMatterManipulationRecipe;
import com.harmex.deathcube.screen.MatterManipulatorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class MatterManipulatorBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler inventory = new ItemStackHandler(11) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
        }
    };
    private LazyOptional<IItemHandler> inventoryHandlerLazyOptional = LazyOptional.empty();

    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return switch (pIndex) {
                case 0 -> MatterManipulatorBlockEntity.this.manipulationProgress;
                case 1 -> MatterManipulatorBlockEntity.this.manipulationTimeTotal;
                default -> 0;
            };
        }

        @Override
        public void set(int pIndex, int pValue) {
            switch (pIndex) {
                case 0 -> MatterManipulatorBlockEntity.this.manipulationProgress = pValue;
                case 1 -> MatterManipulatorBlockEntity.this.manipulationTimeTotal = pValue;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };
    int manipulationProgress;
    int manipulationTimeTotal;

    public MatterManipulatorBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.MATTER_MANIPULATOR_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.manipulationProgress = 0;
        this.manipulationTimeTotal = 50;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("container.deathcube.matter_manipulator");
    }

    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pInventory, @NotNull Player pPlayer) {
        return new MatterManipulatorMenu(pContainerId, pInventory, this, this.dataAccess);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
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
        pTag.putInt("ManipulationTime", this.manipulationProgress);
        pTag.putInt("ManipulationTimeTotal", this.manipulationTimeTotal);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag.getCompound("inventory"));
        manipulationProgress = pTag.getInt("ManipulationTime");
        manipulationTimeTotal = pTag.getInt("ManipulationTimeTotal");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(this.inventory.getSlots());
        for (int i = 0; i < this.inventory.getSlots(); i++) {
            inventory.setItem(i, this.inventory.getStackInSlot(i));
        }

        if (this.level != null) {
            Containers.dropContents(this.level, this.worldPosition, inventory);
        }
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, MatterManipulatorBlockEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity)) {
            pBlockEntity.manipulationProgress++;
            setChanged(pLevel, pPos, pState);
            if(pBlockEntity.manipulationProgress > pBlockEntity.manipulationTimeTotal) {
                craftItem(pBlockEntity);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    private static boolean hasRecipe(MatterManipulatorBlockEntity pBlockEntity) {
        Level level = pBlockEntity.level;
        SimpleContainer inventory = new SimpleContainer(pBlockEntity.inventory.getSlots());
        for (int i = 0; i < pBlockEntity.inventory.getSlots(); i++) {
            inventory.setItem(i, pBlockEntity.inventory.getStackInSlot(i));
        }

        Optional<ShapedMatterManipulationRecipe> match = level.getRecipeManager()
                .getRecipeFor(ShapedMatterManipulationRecipe.Type.INSTANCE, inventory, level);

        if (match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())) {
            pBlockEntity.manipulationTimeTotal = match.get().getManipulationTime();
        }

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
    }

    private static void craftItem(MatterManipulatorBlockEntity pBlockEntity) {
        Level level = pBlockEntity.level;
        SimpleContainer inventory = new SimpleContainer(pBlockEntity.inventory.getSlots());
        for (int i = 0; i < pBlockEntity.inventory.getSlots(); i++) {
            inventory.setItem(i, pBlockEntity.inventory.getStackInSlot(i));
        }

        Optional<ShapedMatterManipulationRecipe> match = level.getRecipeManager()
                .getRecipeFor(ShapedMatterManipulationRecipe.Type.INSTANCE, inventory, level);


        if(match.isPresent()) {
            NonNullList<Ingredient> ingredients = match.get().getIngredients();
            Map<Item, Integer> itemCount = new HashMap<>();
            for (Ingredient ingredient : ingredients) {
                for (ItemStack itemStack : ingredient.getItems()) {
                    itemCount.put(itemStack.getItem(), itemStack.getCount());
                }
            }
            for (int i = 0; i < pBlockEntity.inventory.getSlots(); i++) {
                    pBlockEntity.inventory.extractItem(i,
                            itemCount.getOrDefault(pBlockEntity.inventory.getStackInSlot(i).getItem(), 1),
                            false);
            }

            pBlockEntity.inventory.insertItem(10, match.get().getResultItem(), false);

            pBlockEntity.resetProgress();
        }
    }

    private void resetProgress() {
        this.manipulationProgress = 0;
        this.manipulationTimeTotal = 200;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer pContainer, ItemStack pResult) {
        return pContainer.getItem(10).getItem() == pResult.getItem() || pContainer.getItem(10).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer pContainer) {
        return pContainer.getItem(10).getMaxStackSize() > pContainer.getItem(10).getCount();
    }
}
