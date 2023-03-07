package com.harmex.deathcube.world.item.custom.totem;

import com.harmex.deathcube.util.capabilities.equipment.EquipmentDataProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

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
            player.getCapability(EquipmentDataProvider.EQUIPMENT).ifPresent(equipmentData -> {
                if (prevStack.getItem() instanceof TotemItem prevTotemItem) {
                    if (equipmentData.isTotemEquipped(prevTotemItem.getTotem())) {
                        equipmentData.setTotemEquipped(prevTotemItem.getTotem(), false);
                    }
                }
                if (stack.getItem() instanceof TotemItem newTotemItem) {
                    if (!equipmentData.isTotemEquipped(newTotemItem.getTotem())) {
                        equipmentData.setTotemEquipped(newTotemItem.getTotem(), true);
                    }
                }
            });
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player && slotContext.identifier().equals("totem")) {
            player.getCapability(EquipmentDataProvider.EQUIPMENT).ifPresent(equipmentData -> {
                if (stack.getItem() instanceof TotemItem prevTotemItem) {
                    if (equipmentData.isTotemEquipped(prevTotemItem.getTotem())) {
                        equipmentData.setTotemEquipped(prevTotemItem.getTotem(), false);
                    }
                }
                if (newStack.getItem() instanceof TotemItem newTotemItem) {
                    if (!equipmentData.isTotemEquipped(newTotemItem.getTotem())) {
                        equipmentData.setTotemEquipped(newTotemItem.getTotem(), true);
                    }
                }
            });
        }
    }
}
