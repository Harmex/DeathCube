package com.harmex.deathcube.capabilities.skills;

import com.harmex.deathcube.world.skill.Skill;
import com.harmex.deathcube.world.skill.SkillProperties;

import java.util.Map;

public class ClientSkillsData {
    private static Map<Skill, SkillProperties> skillsLVL;

    public static void set(Map<Skill, SkillProperties> pSkillsLVL) {
        skillsLVL = pSkillsLVL;
    }

    public static Map<Skill, SkillProperties> getSkillsLVL() {
        return skillsLVL;
    }
}
