package com.harmex.deathcube.event;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.capabilities.equipment.ClientEquippedSetsData;
import com.harmex.deathcube.capabilities.equipment.EquippedSetsData;
import com.harmex.deathcube.capabilities.equipment.EquippedSetsDataProvider;
import com.harmex.deathcube.capabilities.mana.ManaData;
import com.harmex.deathcube.capabilities.mana.ManaDataProvider;
import com.harmex.deathcube.capabilities.skills.SkillsData;
import com.harmex.deathcube.capabilities.skills.SkillsDataProvider;
import com.harmex.deathcube.capabilities.thirst.ThirstConstants;
import com.harmex.deathcube.capabilities.thirst.ThirstData;
import com.harmex.deathcube.capabilities.thirst.ThirstDataProvider;
import com.harmex.deathcube.item.custom.ArmorSet;
import com.harmex.deathcube.item.custom.ArmorSetItem;
import com.harmex.deathcube.networking.ModMessages;
import com.harmex.deathcube.networking.packet.EquipmentDataSyncS2CPacket;
import com.harmex.deathcube.networking.packet.ManaDataSyncS2CPacket;
import com.harmex.deathcube.networking.packet.SkillsDataSyncS2CPacket;
import com.harmex.deathcube.networking.packet.ThirstDataSyncS2CPacket;
import com.harmex.deathcube.world.skill.Skills;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.enchanting.EnchantmentLevelSetEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.text.DecimalFormat;

import static com.harmex.deathcube.world.skill.SkillUtils.*;

@Mod.EventBusSubscriber(modid = DeathCube.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            if (!player.getCapability(ThirstDataProvider.PLAYER_THIRST).isPresent()) {
                event.addCapability(new ResourceLocation(DeathCube.MODID, "thirst"), new ThirstDataProvider());
            }
            if (!player.getCapability(EquippedSetsDataProvider.EQUIPPED_SETS).isPresent()) {
                event.addCapability(new ResourceLocation(DeathCube.MODID, "equipped_sets"), new EquippedSetsDataProvider());
            }
            if (!player.getCapability(ManaDataProvider.PLAYER_MANA).isPresent()) {
                event.addCapability(new ResourceLocation(DeathCube.MODID, "mana"), new ManaDataProvider());
            }
            if (!player.getCapability(SkillsDataProvider.SKILLS).isPresent()) {
                event.addCapability(new ResourceLocation(DeathCube.MODID, "skills"), new SkillsDataProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(oldStore ->
                    event.getEntity().getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                        ModMessages.sendToClient(new ThirstDataSyncS2CPacket(newStore.getThirstLevel(), newStore.getSaturationLevel(), newStore.getExhaustionLevel()), (ServerPlayer) event.getEntity());
            }));
            event.getOriginal().getCapability(EquippedSetsDataProvider.EQUIPPED_SETS).ifPresent(oldStore ->
                    event.getEntity().getCapability(EquippedSetsDataProvider.EQUIPPED_SETS).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                        ModMessages.sendToClient(new EquipmentDataSyncS2CPacket(newStore.getEquippedCountForArmorSet()), (ServerPlayer) event.getEntity());
            }));
            event.getOriginal().getCapability(ManaDataProvider.PLAYER_MANA).ifPresent(oldStore ->
                    event.getEntity().getCapability(ManaDataProvider.PLAYER_MANA).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                        ModMessages.sendToClient(new ManaDataSyncS2CPacket(newStore.getManaLevel()), (ServerPlayer) event.getEntity());
            }));
            event.getOriginal().getCapability(SkillsDataProvider.SKILLS).ifPresent(oldStore ->
                    event.getEntity().getCapability(SkillsDataProvider.SKILLS).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                        ModMessages.sendToClient(new SkillsDataSyncS2CPacket(newStore.getSkillsLVL()), (ServerPlayer) event.getEntity());
                    }));
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.END) {
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
            player.getCapability(ManaDataProvider.PLAYER_MANA).ifPresent(manaData -> {
                ModMessages.sendToClient(new ManaDataSyncS2CPacket(manaData.getManaLevel()), ((ServerPlayer) player));
                if (!player.isCreative() && !player.isSpectator()) {
                    manaData.tick(player);
                }
            });
            player.getCapability(SkillsDataProvider.SKILLS).ifPresent(skillsData -> {
                ModMessages.sendToClient(new SkillsDataSyncS2CPacket(skillsData.getSkillsLVL()), ((ServerPlayer) player));
                skillsData.tick(player);
            });
        }

    }

    @SubscribeEvent
    public static void onPlayerFish(ItemFishedEvent event) {
        Player player = event.getEntity();
        player.getCapability(SkillsDataProvider.SKILLS).ifPresent(skillsData -> {
            for (ItemStack fishedItem : event.getDrops()) {
                skillsData.addXP(Skills.FISHING, XP_FOR_ITEM_FISHED.getOrDefault(fishedItem.getItem(), 0.0F));
            }
        });
    }

    @SubscribeEvent
    public static void onBlockMined(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getState().getBlock();
        player.getCapability(SkillsDataProvider.SKILLS).ifPresent(skillsData -> {
            skillsData.addXP(Skills.MINING, XP_FOR_BLOCK_BROKEN.getOrDefault(block, 0.0F));
        });
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirst ->
                        ModMessages.sendToClient(new ThirstDataSyncS2CPacket(thirst.getThirstLevel(), thirst.getSaturationLevel(), thirst.getExhaustionLevel()), player));
                player.getCapability(EquippedSetsDataProvider.EQUIPPED_SETS).ifPresent(equipmentData ->
                        ModMessages.sendToClient(new EquipmentDataSyncS2CPacket(equipmentData.getEquippedCountForArmorSet()), player));
                player.getCapability(ManaDataProvider.PLAYER_MANA).ifPresent(manaData ->
                        ModMessages.sendToClient(new ManaDataSyncS2CPacket(manaData.getManaLevel()), player));
                player.getCapability(SkillsDataProvider.SKILLS).ifPresent(skillsData ->
                        ModMessages.sendToClient(new SkillsDataSyncS2CPacket(skillsData.getSkillsLVL()), player));
            }
        }
    }

    @SubscribeEvent
    public static void onEntityEquipArmor(LivingEquipmentChangeEvent event) {
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
        if (event.getEntity() instanceof Player player && !player.getLevel().isClientSide() && player.getLevel().getDifficulty() != Difficulty.PEACEFUL
                && !player.isCreative() && !player.isSpectator()) {
            player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirstData -> {
                if (player.isSprinting()) thirstData.addExhaustion(ThirstConstants.EXHAUSTION_SPRINT_JUMP);
                else thirstData.addExhaustion(ThirstConstants.EXHAUSTION_JUMP);
            });
        }
    }

    @SubscribeEvent
    public static void onEntityDamage(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player && !player.getLevel().isClientSide() && player.getLevel().getDifficulty() != Difficulty.PEACEFUL
                && !player.isCreative() && !player.isSpectator()) {
            player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirstData ->
                    thirstData.addExhaustion(ThirstConstants.EXHAUSTION_DAMAGE));
        }
    }

    @SubscribeEvent
    public static void onEntityAttack(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof Player player && !player.getLevel().isClientSide() && player.getLevel().getDifficulty() != Difficulty.PEACEFUL
                && !player.isCreative() && !player.isSpectator()) {
            player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirstData ->
                    thirstData.addExhaustion(ThirstConstants.EXHAUSTION_ATTACK));
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player && !player.getLevel().isClientSide()) {
            EntityType<?> entityType = event.getEntity().getType();
            player.getCapability(SkillsDataProvider.SKILLS).ifPresent(skillsData ->
                    skillsData.addXP(Skills.COMBAT, XP_AMOUNT_FOR_ENTITIES.getOrDefault(entityType, 0.0F)
                            * (event.getEntity() instanceof Slime slime ? slime.getSize() : 1.0F)));
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
