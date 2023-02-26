package com.harmex.deathcube.event;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.capabilities.equipment.ClientEquippedSetsData;
import com.harmex.deathcube.capabilities.equipment.EquippedSetsData;
import com.harmex.deathcube.capabilities.equipment.EquippedSetsDataProvider;
import com.harmex.deathcube.item.custom.ArmorSet;
import com.harmex.deathcube.item.custom.ArmorSetItem;
import com.harmex.deathcube.networking.ModMessages;
import com.harmex.deathcube.networking.packet.EquipmentDataSyncS2CPacket;
import com.harmex.deathcube.networking.packet.ThirstDataSyncS2CPacket;
import com.harmex.deathcube.capabilities.thirst.ThirstConstants;
import com.harmex.deathcube.capabilities.thirst.ThirstData;
import com.harmex.deathcube.capabilities.thirst.ThirstDataProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.text.DecimalFormat;

@Mod.EventBusSubscriber(modid = DeathCube.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(ThirstDataProvider.PLAYER_THIRST).isPresent()) {
                event.addCapability(new ResourceLocation(DeathCube.MODID, "thirst"), new ThirstDataProvider());
            }
            if (!event.getObject().getCapability(EquippedSetsDataProvider.EQUIPPED_SETS).isPresent()) {
                event.addCapability(new ResourceLocation(DeathCube.MODID, "equipped_sets"), new EquippedSetsDataProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(oldStore ->
                    event.getOriginal().getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(newStore -> {
                newStore.copyFrom(oldStore);
                ModMessages.sendToClient(new ThirstDataSyncS2CPacket(newStore.getThirstLevel(), newStore.getSaturationLevel(), newStore.getExhaustionLevel()), (ServerPlayer) event.getEntity());
            }));
            event.getOriginal().getCapability(EquippedSetsDataProvider.EQUIPPED_SETS).ifPresent(oldStore ->
                    event.getOriginal().getCapability(EquippedSetsDataProvider.EQUIPPED_SETS).ifPresent(newStore -> {
                newStore.copyFrom(oldStore);
                ModMessages.sendToClient(new EquipmentDataSyncS2CPacket(newStore.getEquippedCountForArmorSet()), (ServerPlayer) event.getEntity());
            }));
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ThirstData.class);
        event.register(EquippedSetsData.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.START) {
            Player player = event.player;
            player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirstData -> {
                ModMessages.sendToClient(new ThirstDataSyncS2CPacket(thirstData.getThirstLevel(), thirstData.getSaturationLevel(), thirstData.getExhaustionLevel()), (ServerPlayer) player);
                if (!player.isCreative() && !player.isSpectator()) {
                    thirstData.tick(player);
                }
            });
            player.getCapability(EquippedSetsDataProvider.EQUIPPED_SETS).ifPresent(equipmentData -> {
                ModMessages.sendToClient(new EquipmentDataSyncS2CPacket(equipmentData.getEquippedCountForArmorSet()), ((ServerPlayer) player));
                equipmentData.tick(player);
            });
        }

    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirst ->
                        ModMessages.sendToClient(new ThirstDataSyncS2CPacket(thirst.getThirstLevel(), thirst.getSaturationLevel(), thirst.getExhaustionLevel()), player));
                player.getCapability(EquippedSetsDataProvider.EQUIPPED_SETS).ifPresent(equipmentData ->
                        ModMessages.sendToClient(new EquipmentDataSyncS2CPacket(equipmentData.getEquippedCountForArmorSet()), player));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerEquipArmor(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player player && event.getSlot().isArmor()) {
            ItemStack oldArmor = event.getFrom();
            ItemStack newArmor = event.getTo();

            player.getCapability(EquippedSetsDataProvider.EQUIPPED_SETS).ifPresent(equipmentData -> {
                if (oldArmor.getItem() instanceof ArmorSetItem oldArmorSetItem) {
                    ArmorSet oldArmorSet = oldArmorSetItem.getArmorSet();
                    int oldArmorSetEquippedCount = equipmentData.getArmorSetEquippedCount(player, oldArmorSet);
                    equipmentData.setEquippedNumberForArmorSet(oldArmorSet, oldArmorSetEquippedCount);
                }
                if (newArmor.getItem() instanceof ArmorSetItem newArmorSetItem) {
                    ArmorSet newArmorSet = newArmorSetItem.getArmorSet();
                    int newArmorSetEquippedCount = equipmentData.getArmorSetEquippedCount(player, newArmorSet);
                    equipmentData.setEquippedNumberForArmorSet(newArmorSet, newArmorSetEquippedCount);
                }
            });
        }
    }

    @SubscribeEvent
    public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof Player player && !player.getLevel().isClientSide() && player.getLevel().getDifficulty() != Difficulty.PEACEFUL) {
            player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirstData -> {
                if (player.isSprinting()) thirstData.addExhaustion(ThirstConstants.EXHAUSTION_SPRINT_JUMP);
                else thirstData.addExhaustion(ThirstConstants.EXHAUSTION_JUMP);
            });
        }
    }

    @SubscribeEvent
    public static void onEntityDamage(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player && !player.getLevel().isClientSide() && player.getLevel().getDifficulty() != Difficulty.PEACEFUL) {
            player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirstData ->
                    thirstData.addExhaustion(ThirstConstants.EXHAUSTION_DAMAGE));
        }
    }

    @SubscribeEvent
    public static void onEntityAttack(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof Player player && !player.getLevel().isClientSide() && player.getLevel().getDifficulty() != Difficulty.PEACEFUL) {
            player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirstData ->
                    thirstData.addExhaustion(ThirstConstants.EXHAUSTION_ATTACK));
        }

    }

    @SubscribeEvent
    public static void onEntityDestroyBlock(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (!player.getLevel().isClientSide() && player.getLevel().getDifficulty() != Difficulty.PEACEFUL) {
            player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirstData ->
                    thirstData.addExhaustion(ThirstConstants.EXHAUSTION_MINE));
        }
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack hoveredItem = event.getItemStack();
        if (event.getEntity() != null && event.getEntity().level.isClientSide()) {
            if (hoveredItem.getItem() instanceof ArmorSetItem armorSetItem) {
                if (ClientEquippedSetsData.getEquippedNumberForArmorSet() != null) {
                    ArmorSet armorSet = armorSetItem.getArmorSet();
                    int equippedCount = 0;
                    ChatFormatting color;
                    if (ClientEquippedSetsData.getEquippedNumberForArmorSet().containsKey(armorSetItem.getArmorSet())) {
                        equippedCount = ClientEquippedSetsData.getEquippedNumberForArmorSet().get(armorSet);
                        if (equippedCount == 4) {
                            color = ChatFormatting.GOLD;
                        } else {
                            color = ChatFormatting.DARK_GRAY;
                        }
                    } else {
                        color = ChatFormatting.DARK_GRAY;
                    }
                    Component set = Component.translatable(
                            "itemTooltip." + DeathCube.MODID + ".set",
                            Component.translatable("itemTooltip." + DeathCube.MODID + ".set." + armorSet.getName().toLowerCase())
                                    .withStyle(armorSet.getStyleModifier()),
                            equippedCount)
                            .withStyle(color);
                    event.getToolTip().add(set);

                    if (armorSet.getFullSetBonus() != null) {
                        Component effect = Component.translatable(
                                armorSet.getFullSetBonus().getDescriptionId())
                                .withStyle(color);
                        Component dash = Component.literal(" - ").withStyle(color).append(effect);
                        event.getToolTip().add(dash);
                    }
                }
            }
            if (hoveredItem.isDamageableItem()) {
                int maxDurability = hoveredItem.getMaxDamage();
                int durability = maxDurability - hoveredItem.getDamageValue();
                float durabilityPercent = ((float) durability * 100 / maxDurability);
                ChatFormatting color;
                if (durabilityPercent >= 50) {
                    color = ChatFormatting.GREEN;
                } else if (durabilityPercent >= 10) {
                    color = ChatFormatting.YELLOW;
                } else {
                    color = ChatFormatting.RED;
                }
                Component durabilityComponent = Component.literal(
                        durability + " / " + maxDurability)
                        .withStyle(style -> style.withColor(color))
                        .append(Component.literal(" (" + new DecimalFormat("#.##").format(durabilityPercent) + "%)"));
                event.getToolTip().add(durabilityComponent);
            }
        }

        Rarity rarity = hoveredItem.getRarity();
        Component rarityComponent = Component.translatable(
                "itemTooltip." + DeathCube.MODID + ".rarity." + rarity.name().toLowerCase())
                .withStyle(rarity.getStyleModifier());
        event.getToolTip().add(rarityComponent);
    }
}
