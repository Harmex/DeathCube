package com.harmex.deathcube.world.inventory.slot;

import com.harmex.deathcube.util.ModTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class MatterManipulatorCatalystSlot extends SlotItemHandler {
    public MatterManipulatorCatalystSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.is(ModTags.Items.DIAMOND_SET);
    }
}
