package com.harmex.deathcube.datagen.loot.modifier;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

public class CompactEnchantmentModifier extends LootModifier {
    public static final Supplier<Codec<CompactEnchantmentModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, CompactEnchantmentModifier::new)));

    protected CompactEnchantmentModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ObjectArrayList<ItemStack> ret = new ObjectArrayList<>();
        generatedLoot.forEach(stack -> {
            ItemStack stackOfOne = stack.copyWithCount(1);
            ItemStack processedStackOfOne = ItemStack.EMPTY;
            int compactCount = 0;
            for (int i = 0; i < stack.getCount(); i++) {
                if (context.getRandom().nextFloat() > 0.5F) {
                    processedStackOfOne = compact(stackOfOne, context);
                    compactCount++;
                }
            }
            if (compactCount < stack.getCount()) {
                stack.setCount(stack.getCount() - compactCount);
                ret.add(stack);
            }
            if (compactCount > 0) {
                ret.add(new ItemStack(processedStackOfOne.getItem(), compactCount));
            }
        });
        return ret;
    }

    private static ItemStack compact(ItemStack stack, LootContext context) {
        CraftingContainer craftingContainer3x3 = new CraftingContainer(new AbstractContainerMenu(null, -1) {
            @Override
            public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
                return ItemStack.EMPTY;
            }

            @Override
            public boolean stillValid(Player pPlayer) {
                return false;
            }
        }, 3, 3);
        for (int i = 0; i < 9; i++) {
            craftingContainer3x3.setItem(i, stack);
        }
        CraftingContainer craftingContainer2x2 = new CraftingContainer(new AbstractContainerMenu(null, -1) {
            @Override
            public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
                return ItemStack.EMPTY;
            }

            @Override
            public boolean stillValid(Player pPlayer) {
                return false;
            }
        }, 2, 2);
        for (int i = 0; i < 4; i++) {
            craftingContainer2x2.setItem(i, stack);
        }

        Optional<ItemStack> result3x3 = context.getLevel().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftingContainer3x3, context.getLevel())
                .map(CraftingRecipe::getResultItem);
        Optional<ItemStack> result2x2 = context.getLevel().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftingContainer2x2, context.getLevel())
                .map(CraftingRecipe::getResultItem);

        if (result3x3.isPresent()) {
            return result3x3.filter(itemStack -> !itemStack.isEmpty())
                    .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                    .orElse(stack);
        } else if (result2x2.isPresent()) {
            return result2x2.filter(itemStack -> !itemStack.isEmpty())
                    .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                    .orElse(stack);
        }
        return stack;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
