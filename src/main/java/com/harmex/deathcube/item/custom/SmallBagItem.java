package com.harmex.deathcube.item.custom;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SmallBagItem extends Item {
    public final ItemStackHandler itemHandler = new ItemStackHandler(27);
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public SmallBagItem() {
        super(new Properties()
                .stacksTo(1)
        );
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack pStack, @Nullable CompoundTag pTag) {
        return new ICapabilityProvider() {
            @Override
            public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
                    return lazyItemHandler.cast();
                }
                return ICapabilityProvider.super.getCapability(cap);
            }
        };
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide && pPlayer instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider((pContainerId, pPlayerInventory, pPlayer1) ->
                    ChestMenu.threeRows(pContainerId, pPlayerInventory), getName(pPlayer.getItemInHand(pUsedHand))));
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
