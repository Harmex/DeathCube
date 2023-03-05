package com.harmex.deathcube.util.capabilities.mana;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class ManaData {
    private float manaLevel;

    public ManaData() {
        manaLevel = 20.0F;
    }

    public void tick(Player pPlayer) {

    }

    public void copyFrom(ManaData pSource) {
        manaLevel = pSource.manaLevel;
    }

    public void loadNBTData(CompoundTag pNBT) {
        manaLevel = pNBT.getFloat("manaLevel");
    }

    public void saveNBTData(CompoundTag pNBT) {
        pNBT.putFloat("manaLevel", manaLevel);
    }

    public float getManaLevel() {
        return manaLevel;
    }
}
