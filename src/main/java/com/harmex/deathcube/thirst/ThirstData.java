package com.harmex.deathcube.thirst;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;

import static com.harmex.deathcube.thirst.ThirstConstants.*;

public class ThirstData {
    private int thirst;
    private int thirstSaturation;
    private int tickTimer;

    public int getThirst() {
        return thirst;
    }
    public int getThirstSaturation() {
        return thirstSaturation;
    }

    public ThirstData() {
        this.thirst = MAX_THIRST;
        this.thirstSaturation = 3;
    }

    public void addThirst(int add) {
        this.thirst = Math.min(this.thirst + add, MAX_THIRST);
        this.tickTimer = 0;
    }
    public void addThirstSaturation(int add) {
        this.thirstSaturation = Math.min(this.thirstSaturation + add, MAX_SATURATION);
        this.tickTimer = 0;
    }

    public void subThirst(int sub) {
        this.thirst = Math.max(this.thirst - sub, 0);
        this.tickTimer = 0;
    }
    public void subThirstSaturation(int sub) {
        this.thirstSaturation = Math.max(this.thirstSaturation - sub, 0);
        this.tickTimer = 0;
    }

    public void tick(Player player) {
        Difficulty difficulty = player.level.getDifficulty();
        if (difficulty != Difficulty.PEACEFUL) {
            this.tickTimer++;
            if (this.tickTimer >= DECAY_TICK_COUNT) {
                if (this.thirstSaturation > 0) {
                    this.subThirstSaturation(1);
                } else {
                    this.subThirst(1);
                }

                this.tickTimer = 0;
            }
        } else {
            // Fill the thirst if the world is in PEACEFUL
            this.tickTimer++;
            if (this.tickTimer >= 10) {
                this.addThirst(1);
                if (this.thirst % 2 == 0) {
                    this.addThirstSaturation(1);
                }

                this.tickTimer = 0;
            }
        }
    }

    public void copyFrom(ThirstData source) {
        this.thirst = source.thirst;
        this.thirstSaturation = source.thirstSaturation;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("thirst", thirst);
        nbt.putInt("thirstSaturation", thirstSaturation);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.thirst = nbt.getInt("thirst");
        this.thirstSaturation = nbt.getInt("thirstSaturation");
    }
}
