package com.harmex.deathcube.world.item.custom.totem;

import com.harmex.deathcube.util.capabilities.equipment.EquipmentDataProvider;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Optional;

public class TotemItem extends Item implements ICurioItem {
    private final Totem totem;

    public TotemItem(Totem pTotem) {
        super(new Properties().stacksTo(1).rarity(pTotem.getRarity()));
        totem = pTotem;
    }

    public Totem getTotem() {
        return totem;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player && slotContext.identifier().equals("totem")) {
            updateEquippedTotem(player, prevStack, stack);
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player && slotContext.identifier().equals("totem")) {
            updateEquippedTotem(player, stack, newStack);
        }
    }

    private void updateEquippedTotem(Player pPlayer, ItemStack pPrevStack, ItemStack pNewStack) {
        pPlayer.getCapability(EquipmentDataProvider.EQUIPMENT).ifPresent(equipmentData -> {
            if (pPrevStack.getItem() instanceof TotemItem prevTotemItem) {
                Optional<SlotResult> opt = CuriosApi.getCuriosHelper().findFirstCurio(pPlayer, prevTotemItem);
                if (opt.isEmpty()) {
                    equipmentData.setTotemEquipped(prevTotemItem.getTotem(), false);
                }
            }
            if (pNewStack.getItem() instanceof TotemItem newTotemItem) {
                equipmentData.setTotemEquipped(newTotemItem.getTotem(), true);
            }
        });
    }
}
