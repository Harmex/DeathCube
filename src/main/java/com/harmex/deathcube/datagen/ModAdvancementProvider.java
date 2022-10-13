package com.harmex.deathcube.datagen;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.block.ModBlocks;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {
    public ModAdvancementProvider(DataGenerator generatorIn, ExistingFileHelper fileHelperIn) {
        super(generatorIn, fileHelperIn);
    }

    @Override
    protected void registerAdvancements(@NotNull Consumer<Advancement> consumer, @NotNull ExistingFileHelper fileHelper) {
        Advancement.Builder.advancement()
                .addCriterion("crafting_table", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRAFTING_TABLE))
                .display(ModBlocks.CHERRY_LOG.get(),
                        Component.translatable("advancements.deathcube.root.title"),    //Title
                        Component.translatable("advancements.deathcube.root.description"),  //Description
                        new ResourceLocation(DeathCube.MODID, "textures/gui/advancements/backgrounds"), //Background
                        FrameType.TASK,
                        true,
                        true,
                        false)
                .save(consumer, new ResourceLocation(DeathCube.MODID, "root"), fileHelper);
    }
}
