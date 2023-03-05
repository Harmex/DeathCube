package com.harmex.deathcube.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.world.level.block.ModBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.util.RecipeMatcher;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UpgradingStationRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    final Ingredient base;
    final NonNullList<Ingredient> ingredients;
    final ItemStack result;

    public UpgradingStationRecipe(ResourceLocation id, Ingredient base, NonNullList<Ingredient> ingredients, ItemStack result) {
        this.id = id;
        this.base = base;
        this.ingredients = ingredients;
        this.result = result;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        return this.base.test(pContainer.getItem(0));
    }

    public boolean hasIngredients(Player pPlayer) {
        List<ItemStack> inputs = new ArrayList<>();
        for (ItemStack playerItem : pPlayer.getInventory().items) {
            for (Ingredient ingredient : this.ingredients) {
                if (ingredient.test(playerItem)) {
                    inputs.add(playerItem);
                }
            }
        }

        return RecipeMatcher.findMatches(inputs, this.ingredients) != null;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        ItemStack result = this.result.copy();
        CompoundTag baseTag = pContainer.getItem(0).getTag();
        if (baseTag != null) {
            result.setTag(baseTag);
        }
        return result;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    public Ingredient getBase() {
        return this.base;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public ItemStack getResultItem() {
        return this.result;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.UPGRADING_STATION.get());
    }

    public static class Type implements RecipeType<UpgradingStationRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "upgrading";
    }

    public static class Serializer implements RecipeSerializer<UpgradingStationRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(DeathCube.MODID, "upgrading");

        @Override
        public UpgradingStationRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            Ingredient base = Ingredient.fromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "base"));
            NonNullList<Ingredient> ingredients = itemsFromJson(GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients"));
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for DeathCube Upgrading");
            } else {
                ItemStack result = CraftingHelper.getItemStack(
                        GsonHelper.getAsJsonObject(pSerializedRecipe, "result"),
                        true, true);
                return new UpgradingStationRecipe(pRecipeId, base, ingredients, result);
            }
        }

        private NonNullList<Ingredient> itemsFromJson(JsonArray pIngredientArray) {
            NonNullList<Ingredient> ingredients = NonNullList.create();

            for (int i = 0; i < pIngredientArray.size(); i++) {
                Ingredient ingredient = Ingredient.fromJson(pIngredientArray.get(i));
                ingredients.add(ingredient);
            }

            return ingredients;
        }

        @Override
        public @Nullable UpgradingStationRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            Ingredient base = Ingredient.fromNetwork(pBuffer);

            int ingredientCount = pBuffer.readVarInt();

            NonNullList<Ingredient> ingredients = NonNullList.withSize(ingredientCount, Ingredient.EMPTY);
            ingredients.replaceAll(ignored -> Ingredient.fromNetwork(pBuffer));

            ItemStack result = pBuffer.readItem();

            return new UpgradingStationRecipe(pRecipeId, base, ingredients, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, UpgradingStationRecipe pRecipe) {
            pRecipe.base.toNetwork(pBuffer);

            pBuffer.writeVarInt(pRecipe.ingredients.size());

            for (Ingredient ingredient : pRecipe.ingredients) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItem(pRecipe.result);
        }
    }
}
