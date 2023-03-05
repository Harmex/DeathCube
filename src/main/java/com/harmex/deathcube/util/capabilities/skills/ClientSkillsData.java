package com.harmex.deathcube.util.capabilities.skills;

import com.harmex.deathcube.world.skill.Skill;
import com.harmex.deathcube.world.skill.SkillProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ClientSkillsData {
    private static Map<Skill, SkillProperties> skillsLVL;

    public static void set(Map<Skill, SkillProperties> pSkillsLVL) {
        skillsLVL = pSkillsLVL;
    }

    public static Map<Skill, SkillProperties> getSkillsLVL() {
        return skillsLVL;
    }
}
