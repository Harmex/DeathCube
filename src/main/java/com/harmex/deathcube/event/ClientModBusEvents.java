package com.harmex.deathcube.event;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.block.ModBlocks;
import com.harmex.deathcube.block.entity.ModBlockEntities;
import com.harmex.deathcube.block.entity.ModWoodTypes;
import com.harmex.deathcube.entity.ModEntityTypes;
import com.harmex.deathcube.entity.galterius.GalteriusModel;
import com.harmex.deathcube.entity.galterius.GalteriusRenderer;
import com.harmex.deathcube.screen.MatterManipulatorScreen;
import com.harmex.deathcube.screen.ModMenuTypes;
import com.harmex.deathcube.screen.ResurrectionAltarScreen;
import com.harmex.deathcube.screen.UpgradingStationScreen;
import com.harmex.deathcube.thirst.ThirstHudOverlay;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = DeathCube.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModBusEvents {
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        MenuScreens.register(ModMenuTypes.UPGRADING_STATION_MENU.get(), UpgradingStationScreen::new);
        MenuScreens.register(ModMenuTypes.MATTER_MANIPULATOR_MENU.get(), MatterManipulatorScreen::new);
        MenuScreens.register(ModMenuTypes.RESURRECTION_ALTAR_MENU.get(), ResurrectionAltarScreen::new);

        WoodType.register(ModWoodTypes.CHERRY);
        BlockEntityRenderers.register(ModBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);

        // TODO : remplacer ça là
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHERRY_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHERRY_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_CHERRY_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHERRY_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHERRY_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GOLDEN_CARROTS.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void onRegister(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GalteriusModel.GALTERIUS_LAYER, GalteriusModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.GALTERIUS.get(), GalteriusRenderer::new);
    }

    @SubscribeEvent
    public static void registerGuiOverlay(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("thirst", ThirstHudOverlay.HUD_THIRST);
    }
}

