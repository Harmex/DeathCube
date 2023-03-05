package com.harmex.deathcube.util.capabilities.equipment;

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

public class EquippedSetsDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<EquippedSetsData> EQUIPPED_SETS = CapabilityManager.get(new CapabilityToken<>() {});

    private EquippedSetsData equippedSets = null;
    private final LazyOptional<EquippedSetsData> optional = LazyOptional.of(this::createEquippedSets);

    private EquippedSetsData createEquippedSets() {
        if (equippedSets == null) {
            equippedSets = new EquippedSetsData();
        }
        return equippedSets;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == EQUIPPED_SETS) {
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
        createEquippedSets().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag pNBT) {
        createEquippedSets().loadNBTData(pNBT);
    }
}
