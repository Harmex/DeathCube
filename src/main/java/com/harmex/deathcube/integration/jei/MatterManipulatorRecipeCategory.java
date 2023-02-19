package com.harmex.deathcube.integration.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.block.ModBlocks;
import com.harmex.deathcube.recipe.ShapedMatterManipulationRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.List;

public class MatterManipulatorRecipeCategory implements IRecipeCategory<ShapedMatterManipulationRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(DeathCube.MODID, "matter_manipulation_shaped");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(DeathCube.MODID, "textures/gui/matter_manipulator_jei_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;
    private final int regularManipulationTime;
    private final ICraftingGridHelper craftingGridHelper;


    public MatterManipulatorRecipeCategory(IGuiHelper guiHelper, int regularManipulationTime) {
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 148, 54);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.MATTER_MANIPULATOR.get()));
        this.cachedArrows = CacheBuilder.newBuilder()
                .maximumSize(23)
                .build(new CacheLoader<>() {
                    @Override
                    public @NotNull IDrawableAnimated load(@NotNull Integer manipulationTime) {
                        return guiHelper.drawableBuilder(TEXTURE, 0, 148, 22, 16)
                                .buildAnimated(manipulationTime, IDrawableAnimated.StartDirection.LEFT, false);
                    }
                });
        this.regularManipulationTime = regularManipulationTime;
        craftingGridHelper = guiHelper.createCraftingGridHelper();
    }

    @Override
    public @NotNull RecipeType<ShapedMatterManipulationRecipe> getRecipeType() {
        return new RecipeType<>(new ResourceLocation(DeathCube.MODID,
                ShapedMatterManipulationRecipe.Type.ID), ShapedMatterManipulationRecipe.class);
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("gui.jei.deathcube.category.matter_manipulation");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    protected IDrawableAnimated getArrow(ShapedMatterManipulationRecipe recipe) {
        int manipulationTime = recipe.getManipulationTime();
        if (manipulationTime <= 0) {
            manipulationTime = regularManipulationTime;
        }
        return this.cachedArrows.getUnchecked(manipulationTime);
    }

    @Override
    public void draw(@NotNull ShapedMatterManipulationRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull PoseStack stack, double mouseX, double mouseY) {
        IDrawableAnimated arrow = getArrow(recipe);
        arrow.draw(stack, 95, 19);

        drawManipulationTime(recipe, stack, 45);
    }

    protected void drawManipulationTime(ShapedMatterManipulationRecipe recipe, PoseStack poseStack, int y) {
        int manipulationTime = recipe.getManipulationTime();
        if (manipulationTime > 0) {
            int manipulationTimeSeconds = manipulationTime / 20;
            Component timeString = Component.literal( manipulationTimeSeconds + "s");
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(timeString);
            fontRenderer.draw(poseStack, timeString, background.getWidth() - stringWidth, y, 0xFF808080);
        }
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull ShapedMatterManipulationRecipe recipe, @Nonnull IFocusGroup focuses) {
        List<List<ItemStack>> inputs = recipe.getIngredients().stream()
                        .map(ingredient -> List.of(ingredient.getItems()))
                                .toList();

        craftingGridHelper.createAndSetInputs(builder, inputs, recipe.getWidth(), recipe.getHeight());

        builder.addSlot(RecipeIngredientRole.INPUT, 73, 19).addItemStack(recipe.getExtraItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 127, 19).addItemStack(recipe.getResultItem());
    }
}
