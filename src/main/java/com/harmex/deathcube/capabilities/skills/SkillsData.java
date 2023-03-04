package com.harmex.deathcube.capabilities.skills;

import com.harmex.deathcube.world.skill.Skill;
import com.harmex.deathcube.world.skill.SkillProperties;
import com.harmex.deathcube.world.skill.Skills;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import java.util.HashMap;
import java.util.Map;

@AutoRegisterCapability
public class SkillsData {
    private Map<Skill, SkillProperties> skillsLVL;

    public SkillsData() {
        skillsLVL = new HashMap<>();
        for (Skill skill : Skills.values()) {
            skillsLVL.put(skill, new SkillProperties(1, 0.0F));
        }
    }

    public void tick(Player pPlayer) {
        for (Map.Entry<Skill, SkillProperties> entry : skillsLVL.entrySet()) {
            while (entry.getValue().getXp() >= entry.getValue().getRequiredXp()) {
                entry.getValue().lvlUp();
                pPlayer.sendSystemMessage(Component.literal(entry.getKey().getName() + " leveled up to " + entry.getValue().getLvl()));
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
        }
    }

    public void saveNBTData(CompoundTag pNBT) {
        ListTag skillListTag = new ListTag();
        for (Map.Entry<Skill, SkillProperties> entry : skillsLVL.entrySet()) {
            CompoundTag skillLVLTag = new CompoundTag();
            skillLVLTag.putString("Skill", entry.getKey().getName());
            skillLVLTag.putInt("LVL", entry.getValue().getLvl());
            skillLVLTag.putFloat("XP", entry.getValue().getXp());
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
