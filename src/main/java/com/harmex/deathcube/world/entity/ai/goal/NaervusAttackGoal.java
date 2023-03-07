package com.harmex.deathcube.world.entity.ai.goal;

import com.harmex.deathcube.world.entity.boss.Naervus;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class NaervusAttackGoal extends MeleeAttackGoal {
    private final Naervus naervus;
    private int raiseArmTicks;

    public NaervusAttackGoal(Naervus pNaervus, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pNaervus, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        naervus = pNaervus;
    }

    @Override
    public void start() {
        super.start();
        raiseArmTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        naervus.setAggressive(false);
    }

    @Override
    public void tick() {
        super.tick();
        raiseArmTicks++;
        naervus.setAggressive(this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2);
    }
}
