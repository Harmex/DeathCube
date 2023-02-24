package com.harmex.deathcube.event;

import com.harmex.deathcube.DeathCube;
import com.mojang.datafixers.util.Either;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTextTooltip;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = DeathCube.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeBusEvents {
    @SubscribeEvent
    public static void onTooltipColorRender(RenderTooltipEvent.Color event) {
        Rarity itemRarity = event.getItemStack().getRarity();
        int rarityColor = Objects.requireNonNull(Component.literal("")
                .withStyle(itemRarity.getStyleModifier())
                .getStyle().getColor()).getValue();
        rarityColor = rarityColor | 0xFF000000 & event.getBorderStart();
        event.setBorderStart(rarityColor);
        event.setBorderEnd(rarityColor);
    }

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Pre event) {
        if (Objects.equals(event.getOverlay().id(), new ResourceLocation("minecraft", "player_health"))
                || Objects.equals(event.getOverlay().id(), new ResourceLocation("minecraft", "food_level"))
                || Objects.equals(event.getOverlay().id(), new ResourceLocation("minecraft", "armor_level"))) {
            event.setCanceled(true);
        }
    }
}
