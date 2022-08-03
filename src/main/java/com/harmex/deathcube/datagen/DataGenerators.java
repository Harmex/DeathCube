package com.harmex.deathcube.datagen;

import com.harmex.deathcube.DeathCube;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DeathCube.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true, new ModRecipeProvider(generator));
        generator.addProvider(true, new ModAdvancementProvider(generator, existingFileHelper));
        generator.addProvider(true, new ModLootTableProvider(generator));
        generator.addProvider(true, new ModBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(true, new ModItemModelProvider(generator, existingFileHelper));
        //generator.addProvider(true, new ModBlockTagProvider(generator, existingFileHelper));
        //generator.addProvider(true, new ModItemTagProvider(generator, existingFileHelper));
        generator.addProvider(true, new ModEN_USLanguageProvider(generator, "en_us"));
        generator.addProvider(true, new ModFR_FRLanguageProvider(generator, "fr_fr"));
    }
}
