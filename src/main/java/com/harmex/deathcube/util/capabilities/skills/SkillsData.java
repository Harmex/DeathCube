package com.harmex.deathcube.util.capabilities.skills;

import com.harmex.deathcube.world.skill.Skill;
import com.harmex.deathcube.world.skill.SkillProperties;
import com.harmex.deathcube.world.skill.Skills;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@AutoRegisterCapability
public class SkillsData {
    private Map<Skill, SkillProperties> skillsLVL;

    public SkillsData() {
        skillsLVL = new HashMap<>();

        for (Skill skill : Skills.values()) {
            skillsLVL.put(skill, new SkillProperties(0, 0.0F, 0.0F));
        }
    }

    public void tick(Player pPlayer) {
        for (Map.Entry<Skill, SkillProperties> entry : skillsLVL.entrySet()) {
            while (entry.getValue().getXp() >= entry.getValue().getRequiredXp()) {
                Skill skill = entry.getKey();
                entry.getValue().lvlUp();
                if (skill == Skills.COMBAT) {
                    Objects.requireNonNull(pPlayer.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(pPlayer.getAttributeBaseValue(Attributes.ATTACK_DAMAGE) + 0.1);
                    Objects.requireNonNull(pPlayer.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(pPlayer.getAttributeBaseValue(Attributes.MAX_HEALTH) + 1);
                    pPlayer.sendSystemMessage(Component.translatable("chat.deathcube.lvlup",
                                    Component.translatable("skill.name.deathcube.combat"),
                                    Component.literal(" + 0.1 ").append(Component.translatable("attribute.name.generic.attack_damage"))
                                            .append(Component.literal(" & + 1 ")).append(Component.translatable("attribute.name.generic.max_health")))
                            .withStyle(entry.getKey().getStyleModifier()));
                }
                if (skill == Skills.MINING) {
                    Objects.requireNonNull(pPlayer.getAttribute(Attributes.ARMOR)).setBaseValue(pPlayer.getAttributeBaseValue(Attributes.ARMOR) + 0.1);
                    Objects.requireNonNull(pPlayer.getAttribute(Attributes.ARMOR_TOUGHNESS)).setBaseValue(pPlayer.getAttributeBaseValue(Attributes.ARMOR_TOUGHNESS) + 0.05);
                    pPlayer.sendSystemMessage(Component.translatable("chat.deathcube.lvlup",
                                    Component.translatable("skill.name.deathcube.mining"),
                                    Component.literal(" + 0.1 ").append(Component.translatable("attribute.name.generic.armor"))
                                            .append(Component.literal(" & + 0.05")).append(Component.translatable("attribute.name.generic.armor_toughness")))
                            .withStyle(entry.getKey().getStyleModifier()));
                }
                if (skill == Skills.FARMING) {
                    Objects.requireNonNull(pPlayer.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(pPlayer.getAttributeBaseValue(Attributes.MOVEMENT_SPEED) + 0.005);
                    pPlayer.sendSystemMessage(Component.translatable("chat.deathcube.lvlup",
                                    Component.translatable("skill.name.deathcube.farming"),
                                    Component.literal(" + 0.005 ").append(Component.translatable("attribute.name.generic.movement_speed")))
                            .withStyle(entry.getKey().getStyleModifier()));
                }
                if (skill == Skills.FISHING) {
                    Objects.requireNonNull(pPlayer.getAttribute(Attributes.LUCK)).setBaseValue(pPlayer.getAttributeBaseValue(Attributes.LUCK) + 0.02);
                    pPlayer.sendSystemMessage(Component.translatable("chat.deathcube.lvlup",
                                    Component.translatable("skill.name.deathcube.fishing"),
                                    Component.literal(" + 0.02 ").append(Component.translatable("attribute.name.generic.armor")))
                            .withStyle(entry.getKey().getStyleModifier()));
                }
            }
        }
    }

    public void copyFrom(SkillsData pSource) {
        skillsLVL = pSource.skillsLVL;
    }

    public void loadNBTData(CompoundTag pNBT) {
        ListTag skillListTag = pNBT.getList("Skills", Tag.TAG_COMPOUND);
        for (int i = 0; i < skillListTag.size(); i++) {
            CompoundTag skillLVLTag = skillListTag.getCompound(i);
            Skill skill = Skills.getByName(skillLVLTag.getString("Skill"));
            skillsLVL.get(skill).setLvl(skillLVLTag.getInt("LVL"));
            skillsLVL.get(skill).setXp(skillLVLTag.getFloat("XP"));
            skillsLVL.get(skill).setTotalXp(skillLVLTag.getFloat("TotalXP"));
        }
    }

    public void saveNBTData(CompoundTag pNBT) {
        ListTag skillListTag = new ListTag();
        for (Map.Entry<Skill, SkillProperties> entry : skillsLVL.entrySet()) {
            CompoundTag skillLVLTag = new CompoundTag();
            skillLVLTag.putString("Skill", entry.getKey().getName());
            skillLVLTag.putInt("LVL", entry.getValue().getLvl());
            skillLVLTag.putFloat("XP", entry.getValue().getXp());
            skillLVLTag.putFloat("TotalXP", entry.getValue().getTotalXp());
            skillListTag.add(skillLVLTag);
        }
        pNBT.put("Skills", skillListTag);
    }

    public Map<Skill, SkillProperties> getSkillsLVL() {
        return skillsLVL;
    }

    public void addXP(Skill skill, Float pAmount) {
        skillsLVL.get(skill).addXp(pAmount);
    }
}
