package com.harmex.deathcube.world.entity.ai.goal;

import com.harmex.deathcube.world.entity.boss.Galterius;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class GalteriusAttackGoal extends MeleeAttackGoal {
    private final Galterius galterius;
    private int raiseArmTicks;

    public GalteriusAttackGoal(Galterius pGalterius, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pGalterius, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        galterius = pGalterius;
    }

    @Override
    public void start() {
        super.start();
        raiseArmTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        galterius.setAggressive(false);
    }

    @Override
    public void tick() {
        super.tick();
        raiseArmTicks++;
        galterius.setAggressive(this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2);
    }
}
