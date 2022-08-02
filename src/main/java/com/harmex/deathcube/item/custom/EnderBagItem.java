package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.item.ModCreativeModeTab;
import com.harmex.deathcube.item.ModRarities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class EnderBagItem extends Item {
    private static final Component CONTAINER_TITLE = Component.translatable("container.enderchest");

    public EnderBagItem() {
        super(new Properties()
                .tab(ModCreativeModeTab.DEATHCUBE_TOOLS_TAB)
                .stacksTo(1)
                .rarity(ModRarities.EPIC)
        );
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        PlayerEnderChestContainer playerenderchestcontainer = pPlayer.getEnderChestInventory();
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (itemStack.getItem() instanceof EnderBagItem) {
            if (pLevel.isClientSide) {
                return InteractionResultHolder.success(itemStack);
            } else {
                pPlayer.openMenu(new SimpleMenuProvider((Id, Player, BlockEntity) -> ChestMenu
                        .threeRows(Id, Player, playerenderchestcontainer), CONTAINER_TITLE));
                return InteractionResultHolder.consume(itemStack);
            }
        } else {
            return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide);
        }
    }
}
