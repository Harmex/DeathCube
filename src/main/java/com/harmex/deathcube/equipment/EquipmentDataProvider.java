package com.harmex.deathcube.equipment;

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

public class EquipmentDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<EquipmentData> EQUIPPED_SETS = CapabilityManager.get(new CapabilityToken<>() {});

    private EquipmentData equippedSets = null;
    private final LazyOptional<EquipmentData> optional = LazyOptional.of(this::createPlayerThirst);

    private EquipmentData createPlayerThirst() {
        if (equippedSets == null) {
            equippedSets = new EquipmentData();
        }

        return equippedSets;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == EQUIPPED_SETS) {
            return optional.cast();
        }
        return LazyOptional.empty();
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
