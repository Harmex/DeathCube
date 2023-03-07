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

public class EquipmentDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<EquipmentData> EQUIPMENT = CapabilityManager.get(new CapabilityToken<>() {});

    private EquipmentData equipmentData = null;
    private final LazyOptional<EquipmentData> optional = LazyOptional.of(this::createEquipmentData);

    private EquipmentData createEquipmentData() {
        if (equipmentData == null) {
            equipmentData = new EquipmentData();
        }
        return equipmentData;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == EQUIPMENT) {
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
        createEquipmentData().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag pNBT) {
        createEquipmentData().loadNBTData(pNBT);
    }
}
