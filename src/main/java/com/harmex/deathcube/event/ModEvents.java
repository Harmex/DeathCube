package com.harmex.deathcube.event;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.networking.ModMessages;
import com.harmex.deathcube.networking.packet.ThirstDataSyncS2CPacket;
import com.harmex.deathcube.thirst.ThirstData;
import com.harmex.deathcube.thirst.ThirstDataProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

@Mod.EventBusSubscriber(modid = DeathCube.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(ThirstDataProvider.PLAYER_THIRST).isPresent()) {
                event.addCapability(new ResourceLocation(DeathCube.MODID, "properties"), new ThirstDataProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(oldStore -> {
                event.getOriginal().getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                    ModMessages.sendToClient(new ThirstDataSyncS2CPacket(newStore.getThirst(), newStore.getThirstSaturation()), (ServerPlayer) event.getEntity());
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ThirstData.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.START) {
            event.player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirst -> {
                ModMessages.sendToClient(new ThirstDataSyncS2CPacket(thirst.getThirst(), thirst.getThirstSaturation()), (ServerPlayer) event.player);

                if (!event.player.getAbilities().instabuild) {
                    thirst.tick(event.player);
                }

            });
        }
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    ModMessages.sendToClient(new ThirstDataSyncS2CPacket(thirst.getThirst(), thirst.getThirstSaturation()), player);
                });
            }
        }
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        Rarity rarity = event.getItemStack().getRarity();
        Component rarityComponent =
                Component.translatable("itemTooltip." + DeathCube.MODID + ".rarity." + rarity.name().toLowerCase())
                .withStyle(rarity.getStyleModifier());
        event.getToolTip().add(rarityComponent);
    }
}
