package com.harmex.deathcube.world.entity.boss;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

public class Zanuzal extends FlyingMob implements Enemy {
    private final ServerBossEvent bossEvent = (ServerBossEvent) new ServerBossEvent(this.getDisplayName(),
            BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS).setDarkenScreen(true);
    Vec3 moveTargetPoint = Vec3.ZERO;
    BlockPos anchorPoint = BlockPos.ZERO;
    Zanuzal.AttackPhase attackPhase = Zanuzal.AttackPhase.CIRCLE;

    public Zanuzal(EntityType<? extends FlyingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new ZanuzalMoveControl(this);
        this.lookControl = new ZanuzalLookControl(this);
    }

    protected BodyRotationControl createBodyControl() {
        return new ZanuzalBodyRotationControl(this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ZanuzalAttackStrategyGoal());
        this.goalSelector.addGoal(2, new ZanuzalSweepAttackGoal());
        this.goalSelector.addGoal(3, new ZanuzalCircleAroundAnchorGoal());
        this.targetSelector.addGoal(1, new ZanuzalAttackPlayerTargetGoal());
    }

    @Override
    public void startSeenByPlayer(ServerPlayer pPlayer) {
        super.startSeenByPlayer(pPlayer);
        this.bossEvent.addPlayer(pPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pPlayer) {
        super.stopSeenByPlayer(pPlayer);
        this.bossEvent.removePlayer(pPlayer);
    }

    public int getUniqueFlapTickOffset() {
        return this.getId() * 3;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            float f = Mth.cos((float)(this.getUniqueFlapTickOffset() + this.tickCount) * 7.448451F * ((float)Math.PI / 180F) + (float)Math.PI);
            float f1 = Mth.cos((float)(this.getUniqueFlapTickOffset() + this.tickCount + 1) * 7.448451F * ((float)Math.PI / 180F) + (float)Math.PI);
            if (f > 0.0F && f1 <= 0.0F) {
                this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.PHANTOM_FLAP, this.getSoundSource(), 0.95F + this.random.nextFloat() * 0.05F, 0.95F + this.random.nextFloat() * 0.05F, false);
            }

            float f2 = Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * (1.3F + 0.21F);
            float f3 = Mth.sin(this.getYRot() * ((float)Math.PI / 180F)) * (1.3F + 0.21F);
            float f4 = (0.3F + f * 0.45F) * (0.2F + 1.0F);
            this.level().addParticle(ParticleTypes.MYCELIUM, this.getX() + (double)f2, this.getY() + (double)f4, this.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
            this.level().addParticle(ParticleTypes.MYCELIUM, this.getX() - (double)f2, this.getY() + (double)f4, this.getZ() - (double)f3, 0.0D, 0.0D, 0.0D);
        }

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

    public static AttributeSupplier.Builder createAttributes() {
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

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.PHANTOM_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PHANTOM_DEATH;
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        EntityDimensions entitydimensions = super.getDimensions(pPose);
        float f = (entitydimensions.width + 0.2F) / entitydimensions.width;
        return entitydimensions.scale(f);
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
            } else {
                this.nextScanTick = reducedTickDelay(60);
                List<Player> list = Zanuzal.this.level().getNearbyPlayers(this.attackTargeting, Zanuzal.this, Zanuzal.this.getBoundingBox().inflate(16.0D, 64.0D, 16.0D));
                if (!list.isEmpty()) {
                    list.sort(Comparator.<Entity, Double>comparing(Entity::getY).reversed());

                    for(Player player : list) {
                        if (Zanuzal.this.canAttack(player, TargetingConditions.DEFAULT)) {
                            Zanuzal.this.setTarget(player);
                            return true;
                        }
                    }
                }

            }
            return false;
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
            return livingentity != null && Zanuzal.this.canAttack(livingentity, TargetingConditions.DEFAULT);
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
            Zanuzal.this.anchorPoint = Zanuzal.this.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, Zanuzal.this.anchorPoint).above(10 + Zanuzal.this.random.nextInt(20));
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
            Zanuzal.this.anchorPoint = Objects.requireNonNull(Zanuzal.this.getTarget()).blockPosition().above(20 + Zanuzal.this.random.nextInt(20));
            if (Zanuzal.this.anchorPoint.getY() < Zanuzal.this.level().getSeaLevel()) {
                Zanuzal.this.anchorPoint = new BlockPos(Zanuzal.this.anchorPoint.getX(), Zanuzal.this.level().getSeaLevel() + 1, Zanuzal.this.anchorPoint.getZ());
            }

        }
    }

    class ZanuzalBodyRotationControl extends BodyRotationControl {
        public ZanuzalBodyRotationControl(Mob pMob) {
            super(pMob);
        }

        /**
         * Update the Head and Body rendering angles
         */
        public void clientTick() {
            Zanuzal.this.yHeadRot = Zanuzal.this.yBodyRot;
            Zanuzal.this.yBodyRot = Zanuzal.this.getYRot();
        }
    }

    class ZanuzalCircleAroundAnchorGoal extends ZanuzalMoveTargetGoal {
        private float angle;
        private float distance;
        private float height;
        private float clockwise;

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return Zanuzal.this.getTarget() == null || Zanuzal.this.attackPhase == Zanuzal.AttackPhase.CIRCLE;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.distance = 5.0F + Zanuzal.this.random.nextFloat() * 10.0F;
            this.height = -4.0F + Zanuzal.this.random.nextFloat() * 9.0F;
            this.clockwise = Zanuzal.this.random.nextBoolean() ? 1.0F : -1.0F;
            this.selectNext();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (Zanuzal.this.random.nextInt(this.adjustedTickDelay(350)) == 0) {
                this.height = -4.0F + Zanuzal.this.random.nextFloat() * 9.0F;
            }

            if (Zanuzal.this.random.nextInt(this.adjustedTickDelay(250)) == 0) {
                ++this.distance;
                if (this.distance > 15.0F) {
                    this.distance = 5.0F;
                    this.clockwise = -this.clockwise;
                }
            }

            if (Zanuzal.this.random.nextInt(this.adjustedTickDelay(450)) == 0) {
                this.angle = Zanuzal.this.random.nextFloat() * 2.0F * (float)Math.PI;
                this.selectNext();
            }

            if (this.touchingTarget()) {
                this.selectNext();
            }

            if (Zanuzal.this.moveTargetPoint.y < Zanuzal.this.getY() && !Zanuzal.this.level().isEmptyBlock(Zanuzal.this.blockPosition().below(1))) {
                this.height = Math.max(1.0F, this.height);
                this.selectNext();
            }

            if (Zanuzal.this.moveTargetPoint.y > Zanuzal.this.getY() && !Zanuzal.this.level().isEmptyBlock(Zanuzal.this.blockPosition().above(1))) {
                this.height = Math.min(-1.0F, this.height);
                this.selectNext();
            }

        }

        private void selectNext() {
            if (BlockPos.ZERO.equals(Zanuzal.this.anchorPoint)) {
                Zanuzal.this.anchorPoint = Zanuzal.this.blockPosition();
            }

            this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
            Zanuzal.this.moveTargetPoint = Vec3.atLowerCornerOf(Zanuzal.this.anchorPoint).add((double)(this.distance * Mth.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * Mth.sin(this.angle)));
        }
    }

    static class ZanuzalLookControl extends LookControl {
        public ZanuzalLookControl(Mob pMob) {
            super(pMob);
        }

        /**
         * Updates look
         */
        public void tick() {
        }
    }

    class ZanuzalMoveControl extends MoveControl {
        private float speed = 0.1F;

        public ZanuzalMoveControl(Mob pMob) {
            super(pMob);
        }

        public void tick() {
            if (Zanuzal.this.horizontalCollision) {
                Zanuzal.this.setYRot(Zanuzal.this.getYRot() + 180.0F);
                this.speed = 0.1F;
            }

            double d0 = Zanuzal.this.moveTargetPoint.x - Zanuzal.this.getX();
            double d1 = Zanuzal.this.moveTargetPoint.y - Zanuzal.this.getY();
            double d2 = Zanuzal.this.moveTargetPoint.z - Zanuzal.this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            if (Math.abs(d3) > (double)1.0E-5F) {
                double d4 = 1.0D - Math.abs(d1 * (double)0.7F) / d3;
                d0 *= d4;
                d2 *= d4;
                d3 = Math.sqrt(d0 * d0 + d2 * d2);
                double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
                float f = Zanuzal.this.getYRot();
                float f1 = (float)Mth.atan2(d2, d0);
                float f2 = Mth.wrapDegrees(Zanuzal.this.getYRot() + 90.0F);
                float f3 = Mth.wrapDegrees(f1 * (180F / (float)Math.PI));
                Zanuzal.this.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
                Zanuzal.this.yBodyRot = Zanuzal.this.getYRot();
                if (Mth.degreesDifferenceAbs(f, Zanuzal.this.getYRot()) < 3.0F) {
                    this.speed = Mth.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
                } else {
                    this.speed = Mth.approach(this.speed, 0.2F, 0.025F);
                }

                float f4 = (float)(-(Mth.atan2(-d1, d3) * (double)(180F / (float)Math.PI)));
                Zanuzal.this.setXRot(f4);
                float f5 = Zanuzal.this.getYRot() + 90.0F;
                double d6 = (double)(this.speed * Mth.cos(f5 * ((float)Math.PI / 180F))) * Math.abs(d0 / d5);
                double d7 = (double)(this.speed * Mth.sin(f5 * ((float)Math.PI / 180F))) * Math.abs(d2 / d5);
                double d8 = (double)(this.speed * Mth.sin(f4 * ((float)Math.PI / 180F))) * Math.abs(d1 / d5);
                Vec3 vec3 = Zanuzal.this.getDeltaMovement();
                Zanuzal.this.setDeltaMovement(vec3.add((new Vec3(d6, d8, d7)).subtract(vec3).scale(0.2D)));
            }

        }
    }

    abstract class ZanuzalMoveTargetGoal extends Goal {
        public ZanuzalMoveTargetGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        protected boolean touchingTarget() {
            return Zanuzal.this.moveTargetPoint.distanceToSqr(Zanuzal.this.getX(), Zanuzal.this.getY(), Zanuzal.this.getZ()) < 4.0D;
        }
    }

    class ZanuzalSweepAttackGoal extends ZanuzalMoveTargetGoal {
        private static final int CAT_SEARCH_TICK_DELAY = 20;
        private boolean isScaredOfCat;
        private int catSearchTick;

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return Zanuzal.this.getTarget() != null && Zanuzal.this.attackPhase == Zanuzal.AttackPhase.SWOOP;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            LivingEntity livingentity = Zanuzal.this.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else {
                if (livingentity instanceof Player) {
                    Player player = (Player)livingentity;
                    if (livingentity.isSpectator() || player.isCreative()) {
                        return false;
                    }
                }

                if (!this.canUse()) {
                    return false;
                } else {
                    if (Zanuzal.this.tickCount > this.catSearchTick) {
                        this.catSearchTick = Zanuzal.this.tickCount + 20;
                        List<Cat> list = Zanuzal.this.level().getEntitiesOfClass(Cat.class, Zanuzal.this.getBoundingBox().inflate(16.0D), EntitySelector.ENTITY_STILL_ALIVE);

                        for(Cat cat : list) {
                            cat.hiss();
                        }

                        this.isScaredOfCat = !list.isEmpty();
                    }

                    return !this.isScaredOfCat;
                }
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            Zanuzal.this.setTarget((LivingEntity)null);
            Zanuzal.this.attackPhase = Zanuzal.AttackPhase.CIRCLE;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            LivingEntity livingentity = Zanuzal.this.getTarget();
            if (livingentity != null) {
                Zanuzal.this.moveTargetPoint = new Vec3(livingentity.getX(), livingentity.getY(0.5D), livingentity.getZ());
                if (Zanuzal.this.getBoundingBox().inflate((double)0.2F).intersects(livingentity.getBoundingBox())) {
                    Zanuzal.this.doHurtTarget(livingentity);
                    Zanuzal.this.attackPhase = Zanuzal.AttackPhase.CIRCLE;
                    if (!Zanuzal.this.isSilent()) {
                        Zanuzal.this.level().levelEvent(1039, Zanuzal.this.blockPosition(), 0);
                    }
                } else if (Zanuzal.this.horizontalCollision || Zanuzal.this.hurtTime > 0) {
                    Zanuzal.this.attackPhase = Zanuzal.AttackPhase.CIRCLE;
                }

            }
        }
    }
}
