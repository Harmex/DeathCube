package com.harmex.deathcube.world.skill;

public class SkillProperties {
    private int lvl;
    private float totalXp;
    private float xp;
    private float requiredXp;

    public SkillProperties(int pLvl, float pTotalXp, float pXp) {
        lvl = pLvl;
        totalXp = pTotalXp;
        xp = pXp;
        requiredXp = (float) (100.0F * Math.pow(1.5F, pLvl - 1));
    }

    public int getLvl() {
        return lvl;
    }
    public float getTotalXp() {
        return totalXp;
    }
    public float getXp() {
        return xp;
    }
    public float getRequiredXp() {
        return requiredXp;
    }

    public void setLvl(int pLvl) {
        lvl = pLvl;
    }
    public void setTotalXp(float pTotalXp) {
        totalXp = pTotalXp;
    }
    public void setXp(float pXp) {
        xp = pXp;
    }

    public void lvlUp() {
        lvl++;
        xp -= requiredXp;
        requiredXp *= 1.5F;
    }
    public void addXp(float pAmount) {
        xp += pAmount;
        totalXp += pAmount;
    }
}
