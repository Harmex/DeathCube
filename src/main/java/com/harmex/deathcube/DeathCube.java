package com.harmex.deathcube;

import com.harmex.deathcube.block.ModBlocks;
import com.harmex.deathcube.block.entity.ModBlockEntities;
import com.harmex.deathcube.block.entity.ModWoodTypes;
import com.harmex.deathcube.config.DeathCubeClientConfigs;
import com.harmex.deathcube.config.DeathCubeCommonConfigs;
import com.harmex.deathcube.entity.ModEntityTypes;
import com.harmex.deathcube.item.ModItems;
import com.harmex.deathcube.networking.ModMessages;
import com.harmex.deathcube.painting.ModPaintings;
import com.harmex.deathcube.potion.ModPotions;
import com.harmex.deathcube.recipe.ModRecipes;
import com.harmex.deathcube.screen.ModMenuTypes;
import com.harmex.deathcube.world.effect.ModMobEffects;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(DeathCube.MODID)
public class DeathCube {
    public static final String MODID = "deathcube";
    private static final Logger LOGGER = LogUtils.getLogger();

    public DeathCube() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        ModMobEffects.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModPotions.register(modEventBus);
        ModPaintings.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);


        modEventBus.addListener(this::commonSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, DeathCubeClientConfigs.SPEC, "deathcube-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DeathCubeCommonConfigs.SPEC, "deathcube-common.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.CHERRY_SAPLING.getId(), ModBlocks.POTTED_CHERRY_SAPLING);

            Sheets.addWoodType(ModWoodTypes.CHERRY);

        });
        ModMessages.register();
    }
}
