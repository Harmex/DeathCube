package com.harmex.deathcube.event;

import com.harmex.deathcube.DeathCube;
import com.mojang.datafixers.util.Either;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
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
    public static void onTooltipGatherComponents(RenderTooltipEvent.GatherComponents event) {
        List<Either<FormattedText, TooltipComponent>> tooltipElements = event.getTooltipElements();

        int removedElementCount = 0;
        for (int i = 0; i < tooltipElements.size() + removedElementCount; i++) {
            if (tooltipElements.get(i - removedElementCount).left().isPresent()) {
                FormattedText text = tooltipElements.get(i - removedElementCount).left().get();
                if (text instanceof MutableComponent component) {
                    if (component.getContents() instanceof TranslatableContents contents) {
                        if (contents.getKey().startsWith("item.modifiers.")) {
                            tooltipElements.remove(i - removedElementCount);
                            tooltipElements.remove(i - removedElementCount - 1);
                            removedElementCount += 2;
                        } else if (contents.getKey().startsWith("attribute.modifier.")) {
                            tooltipElements.remove(i - removedElementCount);
                            removedElementCount++;
                        } else if (contents.getKey().startsWith("enchantment.")) {
                            tooltipElements.remove(i - removedElementCount);
                            removedElementCount++;
                        }
                    } else if (component.getSiblings().size() > 0 && component.getSiblings().get(0).getContents() instanceof TranslatableContents contents) {
                        if (contents.getKey().startsWith("attribute.modifier.")) {
                            tooltipElements.remove(i - removedElementCount);
                            removedElementCount++;
                        }
                    }
                }
            }
        }
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
