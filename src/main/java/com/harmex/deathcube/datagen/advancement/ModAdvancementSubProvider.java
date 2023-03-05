package com.harmex.deathcube.datagen.advancement;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.world.level.block.ModBlocks;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class ModAdvancementSubProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
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
                .save(saver, new ResourceLocation(DeathCube.MODID, "root"), existingFileHelper);
    }
}
