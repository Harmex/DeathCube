package com.harmex.deathcube.thirst;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;

import static com.harmex.deathcube.thirst.ThirstConstants.*;

public class ThirstData {
    private int thirstLevel = MAX_THIRST;
    private float saturationLevel;
    private float exhaustionLevel;
    private int tickTimer;


    public ThirstData() {
        this.saturationLevel = START_SATURATION;
    }

    public void tick(Player pPlayer) {
        Difficulty difficulty = pPlayer.level.getDifficulty();

        if (difficulty == Difficulty.PEACEFUL && pPlayer.level.getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION)) {
            if (tickTimer % 10 == 0) {
                if (needsWater()) setThirstLevel(thirstLevel + 1);
                if (needsSaturation()) setSaturationLevel(saturationLevel + 1.0F);
            }
        }

        if (difficulty != Difficulty.PEACEFUL) {
            //TODO : Le fonctionnement de la soif
            //Vec3 oldPlayerPos = new Vec3(pPlayer.xOld, pPlayer.yOld, pPlayer.zOld);
            //Vec3 playerPos = new Vec3(pPlayer.getX(), pPlayer.getY(), pPlayer.getZ());
            //Vec3 playerPosDelta = playerPos.subtract(oldPlayerPos);
            //double moveDist = oldPlayerPos.distanceToSqr(playerPos);
            //int moveDistRound = Math.round((float) moveDist * 100.0F);
            //int moveDistHorizontal = Math.round((float) playerPosDelta.horizontalDistanceSqr() * 100.0F);
//
            //if (!pPlayer.isPassenger()) {
            //    if (pPlayer.isSwimming() || pPlayer.isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
            //        if (moveDistRound > 0) {
            //            addExhaustion(EXHAUSTION_SWIM * (float) moveDistRound * 0.01F);
            //        }
            //    } else if (pPlayer.isInWater()) {
            //        if (moveDistHorizontal > 0) {
            //            addExhaustion(EXHAUSTION_SWIM * (float) moveDistHorizontal * 0.01F);
            //        }
            //    } else if (pPlayer.isOnGround()) {
            //        if (moveDistHorizontal > 0) {
            //            if (pPlayer.isSprinting()) {
            //                addExhaustion(EXHAUSTION_SPRINT * (float) moveDistHorizontal * 0.01F);
            //            }
            //        }
            //    }
            //}
        }

        if (exhaustionLevel > EXHAUSTION_DROP) {
            exhaustionLevel -= EXHAUSTION_DROP;
            if (saturationLevel > 0.0F) {
                saturationLevel = Math.max(saturationLevel - 1.0F, 0.0F);
            } else if (difficulty != Difficulty.PEACEFUL) {
                thirstLevel = Math.max( thirstLevel - 1, 0);
            }
        }

        if (thirstLevel <= 0) {
            tickTimer++;
            if (tickTimer >= 80) {
                if (pPlayer.getHealth() > 10.0F || difficulty == Difficulty.HARD || pPlayer.getHealth() > 1.0F && difficulty == Difficulty.NORMAL) {
                    pPlayer.hurt(DamageSource.STARVE, 1.0F);
                }

                tickTimer = 0;
            }
        } else {
            tickTimer = 0;
        }
    }

    public void copyFrom(ThirstData source) {
        thirstLevel = source.thirstLevel;
        saturationLevel = source.saturationLevel;
        exhaustionLevel = source.exhaustionLevel;
    }

    public void drink(int pThirstLevelModifier, float pSaturationLevelModifier) {
        thirstLevel = Math.min(thirstLevel + pThirstLevelModifier, MAX_THIRST);
        saturationLevel = Math.min(saturationLevel + pSaturationLevelModifier, MAX_SATURATION);
    }

    public boolean needsWater() {
        return thirstLevel < 20;
    }

    public boolean needsSaturation() {
        return saturationLevel < 20.0F;
    }

    public void addExhaustion(float pExhaustion) {
        exhaustionLevel = Math.min(exhaustionLevel + pExhaustion, MAX_EXHAUSTION);
    }

    public void loadNBTData(CompoundTag nbt) {
        thirstLevel = nbt.getInt("thirstLevel");
        saturationLevel = nbt.getFloat("thirstSaturationLevel");
        exhaustionLevel = nbt.getFloat("thirstExhaustionLevel");
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("thirstLevel", thirstLevel);
        nbt.putFloat("thirstSaturationLevel", saturationLevel);
        nbt.putFloat("thirstExhaustionLevel", exhaustionLevel);
    }

    public int getThirstLevel() {
        return thirstLevel;
    }

    public void setThirstLevel(int thirstLevel) {
        this.thirstLevel = thirstLevel;
    }

    public float getSaturationLevel() {
        return saturationLevel;
    }

    public void setSaturationLevel(float saturationLevel) {
        this.saturationLevel = saturationLevel;
    }

    public float getExhaustionLevel() {
        return exhaustionLevel;
    }

    public void setExhaustionLevel(float exhaustionLevel) {
        this.exhaustionLevel = exhaustionLevel;
    }
}
