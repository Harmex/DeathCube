package com.harmex.deathcube.world.skill;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.UUID;

public class SkillData {
    private final Skill skill;
    private int oldLevel;
    private int level;
    private float experience;
    private float totalExperience;
    private float requiredExperience;

    public SkillData(Skill pSkill, int pLevel, float pExperience, float pTotalExperience) {
        skill = pSkill;
        oldLevel = 0;
        level = pLevel;
        experience = pExperience;
        totalExperience = pTotalExperience;
        requiredExperience = (float) (100 * Math.pow(1.05, pLevel));
    }

    public void earnExperience(Player pPlayer, float pAmount) {
        experience += pAmount;
        totalExperience += pAmount;
        while (experience >= requiredExperience && level < skill.getMaxLevel()) {
            levelUp(pPlayer);
        }
    }

    public void levelUp(Player pPlayer) {
        oldLevel = level;
        level++;
        experience -= requiredExperience;
        requiredExperience *= 1.05;
        updateAttributes(pPlayer);
        updateMaxLevelAttributes(pPlayer);
    }

    public void addLevel(Player pPlayer, int pAmount) {
        oldLevel = level;
        level = Math.min(level + pAmount, skill.getMaxLevel());
        if (level != oldLevel) {
            updateData(pPlayer);
            updateMaxLevelAttributes(pPlayer);
        }
    }
    public void subLevel(Player pPlayer, int pAmount) {
        oldLevel = level;
        level = Math.max(level - pAmount, 0);
        if (level != oldLevel) {
            updateData(pPlayer);
            updateMaxLevelAttributes(pPlayer);
        }
    }
    public void setLevel(Player pPlayer, int pLevel) {
        oldLevel = level;
        level = Mth.clamp(pLevel, 0, skill.getMaxLevel());
        if (level != oldLevel) {
            updateData(pPlayer);
            updateMaxLevelAttributes(pPlayer);
        }
    }

    private void updateData(Player pPlayer) {
        experience = 0;
        requiredExperience = (float) (100 * Math.pow(1.05, level));
        totalExperience = 0;
        for (int i = 0; i < level; i++) {
            totalExperience += (float) (100 * Math.pow(1.05, i));
        }
        updateAttributes(pPlayer);
    }
    public void updateAttributes(Player pPlayer) {
        AttributeInstance attackDamageAttribute = pPlayer.getAttribute(Attributes.ATTACK_DAMAGE);
        AttributeInstance maxHealthAttribute = pPlayer.getAttribute(Attributes.MAX_HEALTH);
        AttributeInstance armorAttribute = pPlayer.getAttribute(Attributes.ARMOR);
        AttributeInstance luckAttribute = pPlayer.getAttribute(Attributes.LUCK);
        float attackDamage = 0;
        float maxHealth = 0;
        float armor = 0;
        float luck = 0;

        if (skill.equals(Skills.COMBAT)) {
            if (attackDamageAttribute != null) {
                if (attackDamageAttribute.getModifier(UUID.fromString("547de699-5cf8-d0f4-b73c-4257e12e8d0b")) != null) {
                    attackDamageAttribute.removeModifier(UUID.fromString("547de699-5cf8-d0f4-b73c-4257e12e8d0b"));
                }
                attackDamageAttribute.addPermanentModifier(new AttributeModifier(
                        UUID.fromString("547de699-5cf8-d0f4-b73c-4257e12e8d0b"),
                        "deathcube:skill.combat.attack_damage",
                        0.1 * level,
                        AttributeModifier.Operation.ADDITION));
                attackDamage = (float) (0.1 * level);
            }
            if (maxHealthAttribute != null) {
                if (maxHealthAttribute.getModifier(UUID.fromString("4bcb05e9-51b6-447b-95a5-ce66b9cb820c")) != null) {
                    maxHealthAttribute.removeModifier(UUID.fromString("4bcb05e9-51b6-447b-95a5-ce66b9cb820c"));
                }
                maxHealthAttribute.addPermanentModifier(new AttributeModifier(
                        UUID.fromString("4bcb05e9-51b6-447b-95a5-ce66b9cb820c"),
                        "deathcube:skill.combat.max_health",
                        level,
                        AttributeModifier.Operation.ADDITION));
                maxHealth = level;
                pPlayer.setHealth(pPlayer.getHealth() + maxHealth);
            }
            pPlayer.sendSystemMessage(Component.translatable("chat.deathcube.lvlup",
                            Component.translatable("skill.name.deathcube.combat"),
                            Component.literal(" " + attackDamage + " ").append(Component.translatable("attribute.name.generic.attack_damage"))
                                    .append(Component.literal(" " + maxHealth + " ")).append(Component.translatable("attribute.name.generic.max_health")))
                    .withStyle(skill.getStyleModifier()));
        }
        if (skill.equals(Skills.WOODCUTTING)) {
            if (armorAttribute != null) {
                if (armorAttribute.getModifier(UUID.fromString("2d3c59c4-d339-4287-09e2-46685a197aba")) != null) {
                    armorAttribute.removeModifier(UUID.fromString("2d3c59c4-d339-4287-09e2-46685a197aba"));
                }
                armorAttribute.addPermanentModifier(new AttributeModifier(
                        UUID.fromString("2d3c59c4-d339-4287-09e2-46685a197aba"),
                        "deathcube:skill.woodcutting.armor",
                        0.1 * level,
                        AttributeModifier.Operation.ADDITION));
                armor = (float) (0.1 * level);
            }
            pPlayer.sendSystemMessage(Component.translatable("chat.deathcube.lvlup",
                            Component.translatable("skill.name.deathcube.woodcutting"),
                            Component.literal(" " + armor + " ").append(Component.translatable("attribute.name.generic.armor")))
                    .withStyle(skill.getStyleModifier()));
        }
        if (skill.equals(Skills.MINING)) {
            pPlayer.sendSystemMessage(Component.translatable("chat.deathcube.lvlup",
                            Component.translatable("skill.name.deathcube.mining"))
                    .withStyle(skill.getStyleModifier()));
        }
        if (skill.equals(Skills.FARMING)) {
            pPlayer.sendSystemMessage(Component.translatable("chat.deathcube.lvlup",
                            Component.translatable("skill.name.deathcube.mining"))
                    .withStyle(skill.getStyleModifier()));
        }
        if (skill.equals(Skills.FISHING)) {
            if (luckAttribute != null) {
                if (luckAttribute.getModifier(UUID.fromString("6c39886b-c4b5-e5ee-1d66-4de327295dc3")) != null) {
                    luckAttribute.removeModifier(UUID.fromString("6c39886b-c4b5-e5ee-1d66-4de327295dc3"));
                }
                luckAttribute.addPermanentModifier(new AttributeModifier(
                        UUID.fromString("6c39886b-c4b5-e5ee-1d66-4de327295dc3"),
                        "deathcube:skill.fishing.luck",
                        0.02 * level,
                        AttributeModifier.Operation.ADDITION));
                luck = (float) (0.02 * level);
            }
            pPlayer.sendSystemMessage(Component.translatable("chat.deathcube.lvlup",
                            Component.translatable("skill.name.deathcube.fishing"),
                            Component.literal(" " + luck + " ").append(Component.translatable("attribute.name.generic.armor")))
                    .withStyle(skill.getStyleModifier()));
        }
    }
    public void updateMaxLevelAttributes(Player pPlayer) {
        UUID uuid = null;
        String name = null;
        if (skill.equals(Skills.COMBAT)) {
            uuid = UUID.fromString("1cec0890-122d-695a-5972-0d3eb1aed298");
            name = "deathcube:skill.combat.totem_slot";
        }
        if (skill.equals(Skills.WOODCUTTING)) {
            uuid = UUID.fromString("562c5dbe-e875-270d-a6ee-7b528501c4e0");
            name = "deathcube:skill.woodcutting.totem_slot";
        }
        if (skill.equals(Skills.MINING)) {
            uuid = UUID.fromString("779b2c49-b5a2-a31e-856e-05236439a5e3");
            name = "deathcube:skill.mining.totem_slot";
        }
        if (skill.equals(Skills.FARMING)) {
            uuid = UUID.fromString("94becdce-a7d8-1c9d-9eb4-5b4704dd5918");
            name = "deathcube:skill.farming.totem_slot";
        }
        if (skill.equals(Skills.FISHING)) {
            uuid = UUID.fromString("0e44a703-c3d6-7265-48b2-4932b3e77dc9");
            name = "deathcube:skill.fishing.totem_slot";
        }
        if (uuid != null & name != null) {
            UUID finalUuid = uuid;
            String finalName = name;
            CuriosApi.getCuriosHelper().getCuriosHandler(pPlayer).ifPresent(iCuriosItemHandler -> {
                iCuriosItemHandler.getStacksHandler("totem").ifPresent(iCurioStacksHandler -> {
                    if (level == skill.getMaxLevel()) {
                        iCurioStacksHandler.addPermanentModifier(new AttributeModifier(finalUuid, finalName, 1.0D, AttributeModifier.Operation.ADDITION));
                    } else if (oldLevel == skill.getMaxLevel()) {
                        iCurioStacksHandler.removeModifier(finalUuid);
                    }
                });
            });
        }
    }

    public Skill getSkill() {
        return skill;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int pLevel) {
        level = pLevel;
    }

    public float getExperience() {
        return experience;
    }
    public void setExperience(float pExperience) {
        experience = pExperience;
    }

    public float getTotalExperience() {
        return totalExperience;
    }
    public void setTotalExperience(float pTotalExperience) {
        totalExperience = pTotalExperience;
    }

    public float getRequiredExperience() {
        return requiredExperience;
    }
    public void setRequiredExperience(float requiredExperience) {
        this.requiredExperience = requiredExperience;
    }
}
