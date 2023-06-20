package com.harmex.deathcube;

import com.harmex.deathcube.config.DeathCubeClientConfigs;
import com.harmex.deathcube.config.DeathCubeCommonConfigs;
import com.harmex.deathcube.datagen.loot.modifier.GlobalLootModifiers;
import com.harmex.deathcube.painting.ModPaintings;
import com.harmex.deathcube.recipe.ModRecipes;
import com.harmex.deathcube.world.effect.ModMobEffects;
import com.harmex.deathcube.world.entity.ModEntityTypes;
import com.harmex.deathcube.world.entity.ai.attribute.ModAttributes;
import com.harmex.deathcube.world.inventory.ModMenuTypes;
import com.harmex.deathcube.world.item.ModCreativeModeTabs;
import com.harmex.deathcube.world.item.ModItems;
import com.harmex.deathcube.world.item.enchantment.ModEnchantments;
import com.harmex.deathcube.world.level.block.ModBlocks;
import com.harmex.deathcube.world.level.block.entity.ModBlockEntities;
import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(DeathCube.MODID)
public class DeathCube {
    public static final String MODID = "deathcube";
    private static final Logger LOGGER = LogUtils.getLogger();

    public DeathCube() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModPaintings.register(modEventBus);
        ModMobEffects.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModEnchantments.register(modEventBus);
        ModAttributes.register(modEventBus);
        GlobalLootModifiers.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, DeathCubeClientConfigs.SPEC, "deathcube-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DeathCubeCommonConfigs.SPEC, "deathcube-common.toml");

        LOGGER.info("Deathcube Loaded");
    }
}
