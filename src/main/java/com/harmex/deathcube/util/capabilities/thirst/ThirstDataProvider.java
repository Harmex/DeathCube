package com.harmex.deathcube.util.capabilities.thirst;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ThirstDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<ThirstData> PLAYER_THIRST = CapabilityManager.get(new CapabilityToken<>() { });

    private ThirstData thirst = null;
    private final LazyOptional<ThirstData> optional = LazyOptional.of(this::createPlayerThirst);

    private ThirstData createPlayerThirst() {
        if (this.thirst == null) {
            this.thirst = new ThirstData();
        }

        return this.thirst;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == PLAYER_THIRST) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerThirst().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag pNBT) {
        createPlayerThirst().loadNBTData(pNBT);
    }
}
