package com.harmex.deathcube.datagen;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.datagen.loot.modifier.LootModifierProvider;
import com.harmex.deathcube.datagen.tag.ModEntityTypeTagsProvider;
import com.harmex.deathcube.datagen.tag.ModBlockTagsProvider;
import com.harmex.deathcube.datagen.tag.ModItemTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DeathCube.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeServer(), new ModRecipeProvider(output));
        generator.addProvider(event.includeServer(), new ModAdvancementProvider(output, event.getLookupProvider(), existingFileHelper));
        generator.addProvider(event.includeServer(), new ModLootTableProvider(generator));
        generator.addProvider(event.includeServer(), new LootModifierProvider(output, DeathCube.MODID));
        //TagsProvider<Block> blockTagsProvider = generator.addProvider(event.includeServer(), new ModBlockTagsProvider(output, event.getLookupProvider(), existingFileHelper));
        //generator.addProvider(event.includeServer(), new ModItemTagsProvider(output, event.getLookupProvider(), blockTagsProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new ModEntityTypeTagsProvider(output, event.getLookupProvider(), existingFileHelper));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModEN_USLanguageProvider(output, "en_us"));
        generator.addProvider(event.includeClient(), new ModFR_FRLanguageProvider(output, "fr_fr"));
    }
}
