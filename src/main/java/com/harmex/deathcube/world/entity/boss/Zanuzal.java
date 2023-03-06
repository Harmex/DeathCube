package com.harmex.deathcube.world.entity.boss;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class Zanuzal extends FlyingMob implements Enemy {
    private final ServerBossEvent bossEvent = new ServerBossEvent(this.getDisplayName(),
            BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS);
    Vec3 moveTargetPoint = Vec3.ZERO;
    BlockPos anchorPoint = BlockPos.ZERO;
    Zanuzal.AttackPhase attackPhase = Zanuzal.AttackPhase.CIRCLE;

    public Zanuzal(EntityType<? extends FlyingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ZanuzalAttackStrategyGoal());
        //this.goalSelector.addGoal(2, new Phantom.PhantomSweepAttackGoal());
        //this.goalSelector.addGoal(3, new Phantom.PhantomCircleAroundAnchorGoal());
        this.targetSelector.addGoal(1, new ZanuzalAttackPlayerTargetGoal());
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer pPlayer) {
        super.startSeenByPlayer(pPlayer);
        this.bossEvent.addPlayer(pPlayer);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer pPlayer) {
        super.stopSeenByPlayer(pPlayer);
        this.bossEvent.removePlayer(pPlayer);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (!this.isAlive()) {
            this.bossEvent.setProgress(0.0F);
        } else {
            this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        }
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.PHANTOM_AMBIENT;
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return SoundEvents.PHANTOM_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PHANTOM_DEATH;
    }

    enum AttackPhase {
        CIRCLE,
        SWOOP;
    }

    class ZanuzalAttackPlayerTargetGoal extends Goal {
        private final TargetingConditions attackTargeting = TargetingConditions.forCombat().range(64.0D);
        private int nextScanTick = reducedTickDelay(20);

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            if (this.nextScanTick > 0) {
                --this.nextScanTick;
                return false;
            } else {
                this.nextScanTick = reducedTickDelay(60);
                List<Player> list = Zanuzal.this.level.getNearbyPlayers(this.attackTargeting, Zanuzal.this, Zanuzal.this.getBoundingBox().inflate(16.0D, 64.0D, 16.0D));
                if (!list.isEmpty()) {
                    list.sort(Comparator.<Entity, Double>comparing(Entity::getY).reversed());

                    for(Player player : list) {
                        if (Zanuzal.this.canAttack(player, TargetingConditions.DEFAULT)) {
                            Zanuzal.this.setTarget(player);
                            return true;
                        }
                    }
                }

                return false;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            LivingEntity livingentity = Zanuzal.this.getTarget();
            return livingentity != null && Zanuzal.this.canAttack(livingentity, TargetingConditions.DEFAULT);
        }
    }
    class ZanuzalAttackStrategyGoal extends Goal {
        private int nextSweepTick;

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            LivingEntity livingentity = Zanuzal.this.getTarget();
            return livingentity != null ? Zanuzal.this.canAttack(livingentity, TargetingConditions.DEFAULT) : false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.nextSweepTick = this.adjustedTickDelay(10);
            Zanuzal.this.attackPhase = Zanuzal.AttackPhase.CIRCLE;
            this.setAnchorAboveTarget();
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            Zanuzal.this.anchorPoint = Zanuzal.this.level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, Zanuzal.this.anchorPoint).above(10 + Zanuzal.this.random.nextInt(20));
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (Zanuzal.this.attackPhase == Zanuzal.AttackPhase.CIRCLE) {
                --this.nextSweepTick;
                if (this.nextSweepTick <= 0) {
                    Zanuzal.this.attackPhase = Zanuzal.AttackPhase.SWOOP;
                    this.setAnchorAboveTarget();
                    this.nextSweepTick = this.adjustedTickDelay((8 + Zanuzal.this.random.nextInt(4)) * 20);
                    Zanuzal.this.playSound(SoundEvents.PHANTOM_SWOOP, 10.0F, 0.95F + Zanuzal.this.random.nextFloat() * 0.1F);
                }
            }

        }

        private void setAnchorAboveTarget() {
            Zanuzal.this.anchorPoint = Zanuzal.this.getTarget().blockPosition().above(20 + Zanuzal.this.random.nextInt(20));
            if (Zanuzal.this.anchorPoint.getY() < Zanuzal.this.level.getSeaLevel()) {
                Zanuzal.this.anchorPoint = new BlockPos(Zanuzal.this.anchorPoint.getX(), Zanuzal.this.level.getSeaLevel() + 1, Zanuzal.this.anchorPoint.getZ());
            }

        }
    }
}
