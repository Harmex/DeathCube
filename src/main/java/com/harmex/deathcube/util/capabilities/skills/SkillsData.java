package com.harmex.deathcube.util.capabilities.skills;

import com.harmex.deathcube.world.skill.Skill;
import com.harmex.deathcube.world.skill.SkillData;
import com.harmex.deathcube.world.skill.Skills;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import java.util.EnumMap;
import java.util.Map;

@AutoRegisterCapability
public class SkillsData {
    private EnumMap<Skills, SkillData> skills;

    public SkillsData() {
        skills = new EnumMap<>(Skills.class);

        for (Skills skill : Skills.values()) {
            skills.put(skill, new SkillData(skill, 0, 0, 0));
        }
    }

    public void tick(Player pPlayer) {

    }

    public void copyFrom(SkillsData pSource) {
        skills = pSource.skills;
    }

    public void loadNBTData(CompoundTag pNBT) {
        ListTag skillListTag = pNBT.getList("Skills", Tag.TAG_COMPOUND);
        for (int i = 0; i < skillListTag.size(); i++) {
            CompoundTag skillLVLTag = skillListTag.getCompound(i);
            Skill savedSkill = Skills.valueOf(skillLVLTag.getString("Skill").toUpperCase());
            skills.get(savedSkill).setLevel(skillLVLTag.getInt("LVL"));
            skills.get(savedSkill).setExperience(skillLVLTag.getFloat("XP"));
            skills.get(savedSkill).setTotalExperience(skillLVLTag.getFloat("TotalXP"));
        }
    }

    public void saveNBTData(CompoundTag pNBT) {
        ListTag skillListTag = new ListTag();
        for (Map.Entry<Skills, SkillData> skill : skills.entrySet()) {
            CompoundTag skillLVLTag = new CompoundTag();
            skillLVLTag.putString("Skill", skill.getKey().getName());
            skillLVLTag.putInt("LVL", skill.getValue().getLevel());
            skillLVLTag.putFloat("XP", skill.getValue().getExperience());
            skillLVLTag.putFloat("TotalXP", skill.getValue().getTotalExperience());
            skillListTag.add(skillLVLTag);
        }
        pNBT.put("Skills", skillListTag);
    }

    public EnumMap<Skills, SkillData> getSkills() {
        return skills;
    }

    public void addExperience(Player pPlayer, Skills pSkill, Float pAmount) {
        skills.get(pSkill).earnExperience(pPlayer, pAmount);
    }

    public void addLevel(Player pPlayer, Skills pSkill, int pAmount) {
        skills.get(pSkill).addLevel(pPlayer, pAmount);
    }
    public void subLevel(Player pPlayer, Skills pSkill, int pAmount) {
        skills.get(pSkill).subLevel(pPlayer, pAmount);
    }
    public void setLevel(Player pPlayer, Skills pSkill, int pAmount) {
        skills.get(pSkill).setLevel(pPlayer, pAmount);
    }

    public void updateAttributes(Player pPlayer) {
        for (SkillData skillData : skills.values()) {
            skillData.updateAttributes(pPlayer);
            skillData.updateMaxLevelAttributes(pPlayer);
        }
    }
}
