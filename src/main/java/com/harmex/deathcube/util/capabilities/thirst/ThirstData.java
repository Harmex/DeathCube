package com.harmex.deathcube.util.capabilities.thirst;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import java.util.Objects;

import static com.harmex.deathcube.util.capabilities.thirst.ThirstConstants.*;

@AutoRegisterCapability
public class ThirstData {
    private int thirstLevel = MAX_THIRST;
    private float saturationLevel;
    private float exhaustionLevel;
    private int tickTimer;
    private Vec3 lastPlayerPos;


    public ThirstData() {
        this.saturationLevel = START_SATURATION;
    }

    public void tick(Player pPlayer) {
        Difficulty difficulty = pPlayer.level().getDifficulty();

        if (difficulty == Difficulty.PEACEFUL && pPlayer.level().getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION)) {
            if (tickTimer % 10 == 0) {
                if (needsWater()) setThirstLevel(thirstLevel + 1);
                if (needsSaturation()) setSaturationLevel(saturationLevel + 1.0F);
            }
        }

        if (difficulty != Difficulty.PEACEFUL) {
            Vec3 playerPos = new Vec3(pPlayer.getX(), pPlayer.getY(), pPlayer.getZ());
            if (lastPlayerPos != null && !Objects.equals(lastPlayerPos, playerPos)) {
                Vec3 playerPosDelta = playerPos.subtract(lastPlayerPos);
                int moveDist = Math.round((float) lastPlayerPos.distanceTo(playerPos) * 100.0F);
                int moveDistHorizontal = Math.round((float) playerPosDelta.horizontalDistance() * 100.0F);
                if (!pPlayer.isPassenger()) {
                    if (pPlayer.isSwimming() || pPlayer.isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
                        if (moveDist > 0) {
                            addExhaustion(EXHAUSTION_SWIM * (float) moveDist * 0.01F);
                        }
                    } else if (pPlayer.isInWater()) {
                        if (moveDistHorizontal > 0) {
                            addExhaustion(EXHAUSTION_SWIM * (float) moveDistHorizontal * 0.01F);
                        }
                    } else if (pPlayer.onGround()) {
                        if (moveDistHorizontal > 0) {
                            if (pPlayer.isSprinting()) {
                                addExhaustion(EXHAUSTION_SPRINT * (float) moveDistHorizontal * 0.01F);
                            }
                        }
                    }
                }
            }
            lastPlayerPos = playerPos;
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
                    pPlayer.hurt(pPlayer.damageSources().starve(), 1.0F);
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

    public void loadNBTData(CompoundTag pNBT) {
        thirstLevel = pNBT.getInt("thirstLevel");
        saturationLevel = pNBT.getFloat("thirstSaturationLevel");
        exhaustionLevel = pNBT.getFloat("thirstExhaustionLevel");
    }

    public void saveNBTData(CompoundTag pNBT) {
        pNBT.putInt("thirstLevel", thirstLevel);
        pNBT.putFloat("thirstSaturationLevel", saturationLevel);
        pNBT.putFloat("thirstExhaustionLevel", exhaustionLevel);
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
