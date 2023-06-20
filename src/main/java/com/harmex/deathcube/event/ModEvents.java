package com.harmex.deathcube.event;

import com.google.common.collect.Multimap;
import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.networking.ModMessages;
import com.harmex.deathcube.networking.packet.EquipmentDataSyncS2CPacket;
import com.harmex.deathcube.networking.packet.ManaDataSyncS2CPacket;
import com.harmex.deathcube.networking.packet.SkillsDataSyncS2CPacket;
import com.harmex.deathcube.networking.packet.ThirstDataSyncS2CPacket;
import com.harmex.deathcube.util.capabilities.equipment.ClientEquipmentData;
import com.harmex.deathcube.util.capabilities.equipment.EquipmentDataProvider;
import com.harmex.deathcube.util.capabilities.mana.ManaDataProvider;
import com.harmex.deathcube.util.capabilities.skills.SkillsDataProvider;
import com.harmex.deathcube.util.capabilities.thirst.ThirstConstants;
import com.harmex.deathcube.util.capabilities.thirst.ThirstDataProvider;
import com.harmex.deathcube.world.item.ModItems;
import com.harmex.deathcube.world.item.custom.TotemOfResurrectionItem;
import com.harmex.deathcube.world.item.custom.set.ArmorSet;
import com.harmex.deathcube.world.item.custom.set.ArmorSetItem;
import com.harmex.deathcube.world.skill.Skills;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static com.harmex.deathcube.world.skill.SkillUtils.*;

@Mod.EventBusSubscriber(modid = DeathCube.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            if (!player.getCapability(ThirstDataProvider.PLAYER_THIRST).isPresent()) {
                event.addCapability(new ResourceLocation(DeathCube.MODID, "thirst"), new ThirstDataProvider());
            }
            if (!player.getCapability(EquipmentDataProvider.EQUIPMENT).isPresent()) {
                event.addCapability(new ResourceLocation(DeathCube.MODID, "equipment"), new EquipmentDataProvider());
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
    public static void onPlayerDrops(LivingDropsEvent event) {
        if (event.getEntity() instanceof Player player) {
            player.getCapability(EquipmentDataProvider.EQUIPMENT).ifPresent(equipmentData -> {
                event.getDrops().removeIf(drop -> drop.getItem().equals(equipmentData.getEquippedTor()));
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        Player originalPlayer = event.getOriginal();
        Player newPlayer = event.getEntity();
        originalPlayer.reviveCaps();
        originalPlayer.getCapability(SkillsDataProvider.SKILLS).ifPresent(oldStore -> {
            newPlayer.getCapability(SkillsDataProvider.SKILLS).ifPresent(newStore -> {
                newStore.copyFrom(oldStore);
                newStore.updateAttributes(newPlayer);
                ModMessages.sendToClient(new SkillsDataSyncS2CPacket(newStore.getSkills()), (ServerPlayer) event.getEntity());
            });
        });

        originalPlayer.getCapability(EquipmentDataProvider.EQUIPMENT).ifPresent(oldStore -> {
            newPlayer.getCapability(EquipmentDataProvider.EQUIPMENT).ifPresent(newStore -> {
                newStore.copyFrom(oldStore);
                ModMessages.sendToClient(new EquipmentDataSyncS2CPacket(newStore.getEquippedCountForArmorSet(), newStore.getEquippedTotems()), (ServerPlayer) event.getEntity());
            });
        });
        originalPlayer.invalidateCaps();
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide()) {
            if (!player.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
                player.getCapability(EquipmentDataProvider.EQUIPMENT).ifPresent(equipmentData -> {
                    CuriosApi.getCuriosHelper().setEquippedCurio(player, "totem", 0, equipmentData.getEquippedTor());
                });
            }
            ItemStack originalTor = ItemStack.EMPTY;
            Optional<SlotResult> opt = CuriosApi.getCuriosHelper().findFirstCurio(player, ModItems.TOTEM_OF_RESURRECTION.get());
            if (opt.isPresent()) {
                originalTor = opt.get().stack();
            }
            if (originalTor != ItemStack.EMPTY) {
                CuriosApi.getCuriosHelper().setEquippedCurio(player, "totem", 0, originalTor);
                if (originalTor.getItem() instanceof TotemOfResurrectionItem torItem) {
                    if (originalTor.hasTag()) {
                        if (originalTor.getTag() != null && originalTor.getTag().contains("deathcube.saved_dim") && originalTor.getTag().contains("deathcube.saved_pos")) {
                            String savedDim = originalTor.getTag().getString("deathcube.saved_dim");
                            int[] savedPos = originalTor.getTag().getIntArray("deathcube.saved_pos");

                            ResourceLocation location = new ResourceLocation(savedDim);
                            ResourceKey<Level> resourceKey = ResourceKey.create(Registries.DIMENSION, location);
                            MinecraftServer minecraftServer = player.level().getServer();


                            assert minecraftServer != null;
                            ServerLevel targetDim = minecraftServer.getLevel(resourceKey);

                            if (targetDim != null && targetDim != player.level()) {
                                player.changeDimension(targetDim, torItem);
                            }
                            player.teleportTo(savedPos[0] + 0.5, savedPos[1], savedPos[2] + 0.5);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerSetRespawnPoint(PlayerSetSpawnEvent event) {
        event.setCanceled(true);
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
            player.getCapability(EquipmentDataProvider.EQUIPMENT).ifPresent(equipmentData -> {
                ModMessages.sendToClient(new EquipmentDataSyncS2CPacket(equipmentData.getEquippedCountForArmorSet(), equipmentData.getEquippedTotems()), ((ServerPlayer) player));
                equipmentData.tick(player);
            });
            player.getCapability(ManaDataProvider.PLAYER_MANA).ifPresent(manaData -> {
                ModMessages.sendToClient(new ManaDataSyncS2CPacket(manaData.getManaLevel()), ((ServerPlayer) player));
                if (!player.isCreative() && !player.isSpectator()) {
                    manaData.tick(player);
                }
            });
            player.getCapability(SkillsDataProvider.SKILLS).ifPresent(skillsData -> {
                ModMessages.sendToClient(new SkillsDataSyncS2CPacket(skillsData.getSkills()), ((ServerPlayer) player));
                skillsData.tick(player);
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerFish(ItemFishedEvent event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide() && !player.isCreative() && !player.isSpectator()) {
            player.getCapability(SkillsDataProvider.SKILLS).ifPresent(skillsData -> {
                for (ItemStack fishedItem : event.getDrops()) {
                    skillsData.addExperience(player, Skills.FISHING, XP_FOR_ITEM_FISHED.getOrDefault(fishedItem.getItem(), 0.0F));
                }
            });
        }
    }

    @SubscribeEvent
    public static void onBlockMined(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getState().getBlock();
        if (!player.level().isClientSide() && !player.isCreative() && !player.isSpectator()) {
            if (player.level().getDifficulty() != Difficulty.PEACEFUL) {
                player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirstData ->
                        thirstData.addExhaustion(ThirstConstants.EXHAUSTION_MINE));
            }
            player.getCapability(SkillsDataProvider.SKILLS).ifPresent(skillsData -> {
                if (XP_FOR_BLOCK_MINED.containsKey(block)) {
                    skillsData.addExperience(player, Skills.MINING, XP_FOR_BLOCK_MINED.get(block));
                } else if (XP_FOR_CROP_HARVESTED.containsKey(block)) {
                    if (!(block instanceof CropBlock cropBlock)) {
                        skillsData.addExperience(player, Skills.FARMING, XP_FOR_CROP_HARVESTED.get(block));
                    } else if (cropBlock.isMaxAge(event.getState())) {
                        skillsData.addExperience(player, Skills.FARMING, XP_FOR_CROP_HARVESTED.get(cropBlock));
                    }
                } else if (XP_FOR_WOOD_CUT.containsKey(block)) {
                    skillsData.addExperience(player, Skills.WOODCUTTING, XP_FOR_WOOD_CUT.get(block));
                }
            });
        }
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirst ->
                        ModMessages.sendToClient(new ThirstDataSyncS2CPacket(thirst.getThirstLevel(), thirst.getSaturationLevel(), thirst.getExhaustionLevel()), player));
                player.getCapability(EquipmentDataProvider.EQUIPMENT).ifPresent(equipmentData ->
                        ModMessages.sendToClient(new EquipmentDataSyncS2CPacket(equipmentData.getEquippedCountForArmorSet(), equipmentData.getEquippedTotems()), player));
                player.getCapability(ManaDataProvider.PLAYER_MANA).ifPresent(manaData ->
                        ModMessages.sendToClient(new ManaDataSyncS2CPacket(manaData.getManaLevel()), player));
                player.getCapability(SkillsDataProvider.SKILLS).ifPresent(skillsData ->
                        ModMessages.sendToClient(new SkillsDataSyncS2CPacket(skillsData.getSkills()), player));
            }
        }
    }

    @SubscribeEvent
    public static void onEntityEquipArmor(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player player && event.getSlot().isArmor()) {
            ItemStack oldArmor = event.getFrom();
            ItemStack newArmor = event.getTo();

            player.getCapability(EquipmentDataProvider.EQUIPMENT).ifPresent(equipmentData -> {
                if (oldArmor.getItem() instanceof ArmorSetItem oldArmorSetItem) {
                    ArmorSet oldArmorSet = oldArmorSetItem.getArmorSet();
                    int oldArmorSetEquippedCount = equipmentData.getArmorSetEquippedCount(player, oldArmorSet);
                    equipmentData.setEquippedCountForArmorSet(oldArmorSet, oldArmorSetEquippedCount);
                }
                if (newArmor.getItem() instanceof ArmorSetItem newArmorSetItem) {
                    ArmorSet newArmorSet = newArmorSetItem.getArmorSet();
                    int newArmorSetEquippedCount = equipmentData.getArmorSetEquippedCount(player, newArmorSet);
                    equipmentData.setEquippedCountForArmorSet(newArmorSet, newArmorSetEquippedCount);
                }
            });
        }
    }

    @SubscribeEvent
    public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide() && player.level().getDifficulty() != Difficulty.PEACEFUL
                && !player.isCreative() && !player.isSpectator()) {
            player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirstData -> {
                if (player.isSprinting()) thirstData.addExhaustion(ThirstConstants.EXHAUSTION_SPRINT_JUMP);
                else thirstData.addExhaustion(ThirstConstants.EXHAUSTION_JUMP);
            });
        }
    }

    @SubscribeEvent
    public static void onEntityDamage(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide() && player.level().getDifficulty() != Difficulty.PEACEFUL
                && !player.isCreative() && !player.isSpectator()) {
            player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirstData ->
                    thirstData.addExhaustion(ThirstConstants.EXHAUSTION_DAMAGE));
        }
    }

    @SubscribeEvent
    public static void onEntityAttack(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof Player player && !player.level().isClientSide()
                && !player.isCreative() && !player.isSpectator()) {
            player.getCapability(SkillsDataProvider.SKILLS).ifPresent(skillsData -> {
                skillsData.addExperience(player, Skills.COMBAT, event.getAmount());
            });
            if (player.level().getDifficulty() != Difficulty.PEACEFUL) {
                player.getCapability(ThirstDataProvider.PLAYER_THIRST).ifPresent(thirstData ->
                        thirstData.addExhaustion(ThirstConstants.EXHAUSTION_ATTACK));
            }
        }
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack hoveredStack = event.getItemStack();
        if (event.getEntity() != null && event.getEntity().level().isClientSide()) {
            // Show Attribute Modifiers (Armor, Attack Damage, etc.)
            if (hoveredStack.getItem() instanceof ArmorItem
                    || hoveredStack.getItem() instanceof TieredItem
                    || hoveredStack.getItem() instanceof TridentItem) {
                Multimap<Attribute, AttributeModifier> attributeModifiers =
                        hoveredStack.getAttributeModifiers(LivingEntity.getEquipmentSlotForItem(hoveredStack));
                for (Map.Entry<Attribute, Collection<AttributeModifier>> entry : attributeModifiers.asMap().entrySet()) {
                    Attribute attribute = entry.getKey();
                    float amount = 0;
                    for (AttributeModifier attributeModifier : entry.getValue()) {
                        if (attribute == Attributes.ATTACK_SPEED) {
                            amount += 4;
                        }
                        amount += attributeModifier.getAmount();
                    }
                    ChatFormatting color = ChatFormatting.BLACK;
                    if (attribute == Attributes.MAX_HEALTH) {
                        color = ChatFormatting.RED;
                    } else if (attribute == Attributes.KNOCKBACK_RESISTANCE) {
                        color = ChatFormatting.DARK_GRAY;
                    } else if (attribute == Attributes.MOVEMENT_SPEED) {
                        color = ChatFormatting.YELLOW;
                    } else if (attribute == Attributes.ATTACK_DAMAGE) {
                        color = ChatFormatting.DARK_RED;
                    } else if (attribute == Attributes.ATTACK_KNOCKBACK) {
                        color = ChatFormatting.BLUE;
                    } else if (attribute == Attributes.ATTACK_SPEED) {
                        color = ChatFormatting.GOLD;
                    } else if (attribute == Attributes.ARMOR) {
                        color = ChatFormatting.WHITE;
                    } else if (attribute == Attributes.ARMOR_TOUGHNESS) {
                        color = ChatFormatting.GRAY;
                    } else if (attribute == Attributes.LUCK) {
                        color = ChatFormatting.GREEN;
                    }
                    if (amount != 0) {
                        Component attributeText = Component.translatable(attribute.getDescriptionId()).withStyle(ChatFormatting.GRAY)
                                .append(Component.literal(": "))
                                .append(Component.literal(String.valueOf(new DecimalFormat("#.##").format(amount))).withStyle(color));
                        event.getToolTip().add(attributeText);
                    }
                }
                event.getToolTip().add(Component.empty());
            }

            //Show the armor set if the item has one
            if (hoveredStack.getItem() instanceof ArmorSetItem armorSetItem) {
                if (ClientEquipmentData.getEquippedNumberForArmorSet() != null) {
                    ArmorSet armorSet = armorSetItem.getArmorSet();
                    int equippedCount = 0;
                    ChatFormatting color;
                    if (ClientEquipmentData.getEquippedNumberForArmorSet().containsKey(armorSetItem.getArmorSet())) {
                        equippedCount = ClientEquipmentData.getEquippedNumberForArmorSet().get(armorSet);
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
                    event.getToolTip().add(Component.empty());
                }
            }

            // Show Enchantment List if the item is enchanted
            if (!hoveredStack.getAllEnchantments().isEmpty()) {
                for (Map.Entry<Enchantment, Integer> entry : hoveredStack.getAllEnchantments().entrySet()) {
                    ChatFormatting color = ChatFormatting.BLUE;
                    if (entry.getKey().isCurse()) {
                        color = ChatFormatting.RED;
                    } else if (entry.getKey().isTreasureOnly()) {
                        color = ChatFormatting.GREEN;
                    }
                    MutableComponent enchantmentText = Component.literal("")
                            .append(Component.translatable(entry.getKey().getDescriptionId())).withStyle(color);
                    if (entry.getValue() > 1) {
                            enchantmentText.append(Component.literal(" " + entry.getValue()));
                    }
                    event.getToolTip().add(enchantmentText);
                }
                event.getToolTip().add(Component.empty());
            }
            if (hoveredStack.getItem() instanceof EnchantedBookItem) {
                ListTag enchantmentsTag = EnchantedBookItem.getEnchantments(hoveredStack);
                for (int i = 0; i < enchantmentsTag.size(); i++) {
                    CompoundTag enchantmentTag = enchantmentsTag.getCompound(i);
                    Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(EnchantmentHelper.getEnchantmentId(enchantmentTag));
                    int enchantmentLevel = enchantmentTag.getInt("lvl");
                    ChatFormatting color = ChatFormatting.BLUE;
                    assert enchantment != null;
                    if (enchantment.isCurse()) {
                        color = ChatFormatting.RED;
                    } else if (enchantment.isTreasureOnly()) {
                        color = ChatFormatting.GREEN;
                    }
                    MutableComponent enchantmentText = Component.literal("")
                            .append(Component.translatable(enchantment.getDescriptionId())).withStyle(color);
                    if (enchantmentLevel > 1) {
                        enchantmentText.append(Component.literal(" " + enchantmentLevel));
                    }
                    event.getToolTip().add(enchantmentText);
                }
            }

            // Show Durability if the item can be damaged
            if (hoveredStack.isDamageableItem()) {
                int maxDurability = hoveredStack.getMaxDamage();
                int durability = maxDurability - hoveredStack.getDamageValue();
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

        Rarity rarity = hoveredStack.getRarity();
        Component rarityComponent = Component.translatable(
                "itemTooltip." + DeathCube.MODID + ".rarity." + rarity.name().toLowerCase())
                .withStyle(rarity.getStyleModifier());
        event.getToolTip().add(rarityComponent);
    }
}