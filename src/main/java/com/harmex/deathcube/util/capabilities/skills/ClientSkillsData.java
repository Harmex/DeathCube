package com.harmex.deathcube.util.capabilities.skills;

import com.harmex.deathcube.world.skill.SkillData;
import com.harmex.deathcube.world.skill.Skills;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.EnumMap;

@OnlyIn(Dist.CLIENT)
public class ClientSkillsData {
    private static EnumMap<Skills, SkillData> skills;

    public static void set(EnumMap<Skills, SkillData> pSkillsLVL) {
        skills = pSkillsLVL;
    }

    public static EnumMap<Skills, SkillData> getSkills() {
        return skills;
    }
}
