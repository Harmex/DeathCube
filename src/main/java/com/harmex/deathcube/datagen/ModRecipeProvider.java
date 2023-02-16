package com.harmex.deathcube.datagen;

import com.harmex.deathcube.block.ModBlocks;
import com.harmex.deathcube.item.ModItems;
import com.harmex.deathcube.util.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        //region Apples
        applesRecipes(pFinishedRecipeConsumer,
                ModItems.DIAMOND_APPLE.get(),
                Items.DIAMOND,
                "has_diamond");
        applesRecipes(pFinishedRecipeConsumer,
                ModItems.NETHERITE_APPLE.get(),
                Items.NETHERITE_INGOT,
                "has_netherite_ingot");
        applesRecipes(pFinishedRecipeConsumer,
                ModItems.BEDROCK_APPLE.get(),
                Items.BEDROCK,
                "has_bedrock");
        //endregion

        //region Tools & Armors
        toolAndArmorRecipes(pFinishedRecipeConsumer,
                ModItems.BONE_HELMET.get(),
                ModItems.BONE_CHESTPLATE.get(),
                ModItems.BONE_LEGGINGS.get(),
                ModItems.BONE_BOOTS.get(),
                ModItems.BONE_SWORD.get(),
                ModItems.BONE_PICKAXE.get(),
                ModItems.BONE_AXE.get(),
                ModItems.BONE_SHOVEL.get(),
                ModItems.BONE_HOE.get(),
                Items.BONE,
                "has_bone");
        toolAndArmorRecipes(pFinishedRecipeConsumer,
                ModItems.COPPER_HELMET.get(),
                ModItems.COPPER_CHESTPLATE.get(),
                ModItems.COPPER_LEGGINGS.get(),
                ModItems.COPPER_BOOTS.get(),
                ModItems.COPPER_SWORD.get(),
                ModItems.COPPER_PICKAXE.get(),
                ModItems.COPPER_AXE.get(),
                ModItems.COPPER_SHOVEL.get(),
                ModItems.COPPER_HOE.get(),
                Items.COPPER_INGOT,
                "has_copper_ingot");
        //endregion

        //region Armors
        armorRecipes(pFinishedRecipeConsumer,
                ModItems.OAK_HELMET.get(),
                ModItems.OAK_CHESTPLATE.get(),
                ModItems.OAK_LEGGINGS.get(),
                ModItems.OAK_BOOTS.get(),
                Items.OAK_WOOD,
                "has_oak_wood");
        armorRecipes(pFinishedRecipeConsumer,
                ModItems.SPRUCE_HELMET.get(),
                ModItems.SPRUCE_CHESTPLATE.get(),
                ModItems.SPRUCE_LEGGINGS.get(),
                ModItems.SPRUCE_BOOTS.get(),
                Items.SPRUCE_WOOD,
                "has_spruce_wood");
        armorRecipes(pFinishedRecipeConsumer,
                ModItems.BIRCH_HELMET.get(),
                ModItems.BIRCH_CHESTPLATE.get(),
                ModItems.BIRCH_LEGGINGS.get(),
                ModItems.BIRCH_BOOTS.get(),
                Items.BIRCH_WOOD,
                "has_birch_wood");
        armorRecipes(pFinishedRecipeConsumer,
                ModItems.JUNGLE_HELMET.get(),
                ModItems.JUNGLE_CHESTPLATE.get(),
                ModItems.JUNGLE_LEGGINGS.get(),
                ModItems.JUNGLE_BOOTS.get(),
                Items.JUNGLE_WOOD,
                "has_jungle_wood");
        armorRecipes(pFinishedRecipeConsumer,
                ModItems.ACACIA_HELMET.get(),
                ModItems.ACACIA_CHESTPLATE.get(),
                ModItems.ACACIA_LEGGINGS.get(),
                ModItems.ACACIA_BOOTS.get(),
                Items.ACACIA_WOOD,
                "has_acacia_wood");
        armorRecipes(pFinishedRecipeConsumer,
                ModItems.DARK_OAK_HELMET.get(),
                ModItems.DARK_OAK_CHESTPLATE.get(),
                ModItems.DARK_OAK_LEGGINGS.get(),
                ModItems.DARK_OAK_BOOTS.get(),
                Items.DARK_OAK_WOOD,
                "has_dark_oak_wood");
        armorRecipes(pFinishedRecipeConsumer,
                ModItems.MANGROVE_HELMET.get(),
                ModItems.MANGROVE_CHESTPLATE.get(),
                ModItems.MANGROVE_LEGGINGS.get(),
                ModItems.MANGROVE_BOOTS.get(),
                Items.MANGROVE_WOOD,
                "has_mangrove_wood");
        armorRecipes(pFinishedRecipeConsumer,
                ModItems.CHERRY_HELMET.get(),
                ModItems.CHERRY_CHESTPLATE.get(),
                ModItems.CHERRY_LEGGINGS.get(),
                ModItems.CHERRY_BOOTS.get(),
                ModBlocks.CHERRY_WOOD.get().asItem(),
                "has_cherry_wood");
        armorRecipes(pFinishedRecipeConsumer,
                ModItems.CRIMSON_HELMET.get(),
                ModItems.CRIMSON_CHESTPLATE.get(),
                ModItems.CRIMSON_LEGGINGS.get(),
                ModItems.CRIMSON_BOOTS.get(),
                Items.CRIMSON_HYPHAE,
                "has_crimson_hyphae");
        armorRecipes(pFinishedRecipeConsumer,
                ModItems.WARPED_HELMET.get(),
                ModItems.WARPED_CHESTPLATE.get(),
                ModItems.WARPED_LEGGINGS.get(),
                ModItems.WARPED_BOOTS.get(),
                Items.WARPED_HYPHAE,
                "has_warped_hyphae");
        //endregion


        shapedRecipes(pFinishedRecipeConsumer);
        shapelessRecipes(pFinishedRecipeConsumer);
        cookingRecipes(pFinishedRecipeConsumer);
    }

    private void applesRecipes(Consumer<FinishedRecipe> pWriter,
                               Item result, Item ingredient, String unlockCondition) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result)
                .define('#', ingredient)
                .define('X', Items.APPLE)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .unlockedBy(unlockCondition, has(ingredient))
                .save(pWriter);
    }

    private void toolAndArmorRecipes(Consumer<FinishedRecipe> pWriter,
                                     Item helmet, Item chestplate, Item leggings, Item boots, Item sword, Item pickaxe,
                                     Item axe, Item shovel, Item hoe, Item ingredient, String unlockCondition) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
                .define('X', ingredient)
                .pattern("XXX")
                .pattern("X X")
                .unlockedBy(unlockCondition, has(ingredient))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
                .define('X', ingredient)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy(unlockCondition, has(ingredient))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
                .define('X', ingredient)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(unlockCondition, has(ingredient))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
                .define('X', ingredient)
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(unlockCondition, has(ingredient))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
                .define('#', Items.STICK)
                .define('X', ingredient)
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .unlockedBy(unlockCondition, has(ingredient))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
                .define('#', Items.STICK)
                .define('X', ingredient)
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .unlockedBy(unlockCondition, has(ingredient))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
                .define('#', Items.STICK)
                .define('X', ingredient)
                .pattern("XX")
                .pattern("X#")
                .pattern(" #")
                .unlockedBy(unlockCondition, has(ingredient))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
                .define('#', Items.STICK)
                .define('X', ingredient)
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .unlockedBy(unlockCondition, has(ingredient))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
                .define('#', Items.STICK)
                .define('X', ingredient)
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .unlockedBy(unlockCondition, has(ingredient))
                .save(pWriter);

    }


    private void armorRecipes(Consumer<FinishedRecipe> pWriter,
                              Item helmet, Item chestplate, Item leggings, Item boots,
                              Item ingredient, String unlockCondition) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
                .define('X', ingredient)
                .pattern("XXX")
                .pattern("X X")
                .unlockedBy(unlockCondition, has(ingredient))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
                .define('X', ingredient)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy(unlockCondition, has(ingredient))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
                .define('X', ingredient)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(unlockCondition, has(ingredient))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
                .define('X', ingredient)
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(unlockCondition, has(ingredient))
                .save(pWriter);

    }

    private void shapedRecipes(Consumer<FinishedRecipe> pWriter) {

        //region Cherry Furniture
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHERRY_DOOR.get(), 3)
                .define('#', ModBlocks.CHERRY_PLANKS.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .group("wooden_door")
                .unlockedBy("has_cherry_planks", has(ModBlocks.CHERRY_PLANKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHERRY_FENCE.get(), 3)
                .define('#', Items.STICK)
                .define('W', ModBlocks.CHERRY_PLANKS.get())
                .pattern("W#W")
                .pattern("W#W")
                .group("wooden_fence")
                .unlockedBy("has_cherry_planks", has(ModBlocks.CHERRY_PLANKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHERRY_FENCE_GATE.get())
                .define('#', Items.STICK)
                .define('W', ModBlocks.CHERRY_PLANKS.get())
                .pattern("#W#")
                .pattern("#W#")
                .group("wooden_fence_gate")
                .unlockedBy("has_cherry_planks", has(ModBlocks.CHERRY_PLANKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.CHERRY_PRESSURE_PLATE.get())
                .define('#', ModBlocks.CHERRY_PLANKS.get())
                .pattern("##")
                .group("wooden_pressure_plate")
                .unlockedBy("has_cherry_planks", has(ModBlocks.CHERRY_PLANKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.CHERRY_SIGN.get(), 3)
                .define('#', ModBlocks.CHERRY_PLANKS.get())
                .define('X', Items.STICK)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                .group("wooden_sign")
                .unlockedBy("has_cherry_planks", has(ModBlocks.CHERRY_PLANKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHERRY_SLAB.get(), 6)
                .define('#', ModBlocks.CHERRY_PLANKS.get())
                .pattern("###")
                .group("wooden_slab")
                .unlockedBy("has_cherry_planks", has(ModBlocks.CHERRY_PLANKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHERRY_STAIRS.get(), 4)
                .define('#', ModBlocks.CHERRY_PLANKS.get())
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .group("wooden_stairs")
                .unlockedBy("has_cherry_planks", has(ModBlocks.CHERRY_PLANKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHERRY_TRAPDOOR.get(), 2)
                .define('#', ModBlocks.CHERRY_PLANKS.get())
                .pattern("###")
                .pattern("###")
                .group("wooden_trapdoor")
                .unlockedBy("has_cherry_planks", has(ModBlocks.CHERRY_PLANKS.get()))
                .save(pWriter);
        //endregion

        //region Echo Amethyst
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ECHO_AMETHYST_SHARD.get(), 8)
                .define('#', Items.ECHO_SHARD)
                .define('X', Items.AMETHYST_SHARD)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy("has_echo_shard", has(Items.ECHO_SHARD))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ECHO_AMETHYST_BLOCK.get(), 1)
                .define('#', ModItems.ECHO_AMETHYST_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_echo_amethyst_ingot", has(ModItems.ECHO_AMETHYST_INGOT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MATTER_MANIPULATOR.get(), 1)
                .define('X', ModBlocks.ECHO_AMETHYST_BLOCK.get())
                .define('#', ModItems.ECHO_AMETHYST_INGOT.get())
                .define('B', Blocks.BLUE_ICE)
                .define('M', Blocks.MAGMA_BLOCK)
                .pattern("#X#")
                .pattern("#B#")
                .pattern("#M#")
                .unlockedBy("has_echo_amethyst_block", has(ModBlocks.ECHO_AMETHYST_BLOCK.get()))
                .save(pWriter);
        //endregion

    }
    private void shapelessRecipes(Consumer<FinishedRecipe> pWriter) {
        //Cherry Furniture
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHERRY_PLANKS.get(), 4)
                .requires(ModTags.Items.CHERRY_LOGS)
                .group("planks")
                .unlockedBy("has_cherry_logs", has(ModTags.Items.CHERRY_LOGS))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ModBlocks.CHERRY_BUTTON.get())
                .requires(ModBlocks.CHERRY_PLANKS.get())
                .group("wooden_button")
                .unlockedBy("has_cherry_planks", has(ModBlocks.CHERRY_PLANKS.get()))
                .save(pWriter);
    }
    private void cookingRecipes(Consumer<FinishedRecipe> pWriter) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.ECHO_AMETHYST_SHARD.get()),
                        RecipeCategory.MISC, ModItems.ECHO_AMETHYST_INGOT.get(), 0.8F, 200)
                .unlockedBy("has_echo_shard", has(Items.ECHO_SHARD))
                .save(pWriter);
    }
}
