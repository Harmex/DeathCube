package com.harmex.deathcube.event;

import com.harmex.deathcube.DeathCube;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
}
