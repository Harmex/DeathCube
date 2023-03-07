package com.harmex.deathcube.world.entity.ai.goal;

import com.harmex.deathcube.world.entity.boss.Azrathal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class AzrathalAttackGoal extends MeleeAttackGoal {
    private final Azrathal azrathal;
    private int raiseArmTicks;

    public AzrathalAttackGoal(Azrathal pAzrathal, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pAzrathal, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        azrathal = pAzrathal;
    }

    @Override
    public void start() {
        super.start();
        raiseArmTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        azrathal.setAggressive(false);
    }

    @Override
    public void tick() {
        super.tick();
        raiseArmTicks++;
        azrathal.setAggressive(this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2);
    }
}
