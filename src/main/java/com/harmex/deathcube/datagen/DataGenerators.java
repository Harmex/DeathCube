package com.harmex.deathcube.datagen;

import com.harmex.deathcube.DeathCube;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
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
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(output, existingFileHelper));
        //generator.addProvider(true, new ModBlockTagProvider(output, event.getLookupProvider(), existingFileHelper));
        //generator.addProvider(true, new ModItemTagProvider(output, event.getLookupProvider(), new ModBlockTagProvider(output, event.getLookupProvider(), existingFileHelper), existingFileHelper));
        generator.addProvider(event.includeClient(), new ModEN_USLanguageProvider(output, "en_us"));
        generator.addProvider(event.includeClient(), new ModFR_FRLanguageProvider(output, "fr_fr"));
    }
}
