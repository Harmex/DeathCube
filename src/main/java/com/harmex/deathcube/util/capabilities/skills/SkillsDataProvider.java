package com.harmex.deathcube.util.capabilities.skills;

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

public class SkillsDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<SkillsData> SKILLS = CapabilityManager.get(new CapabilityToken<>() {});

    private SkillsData skills = null;
    private final LazyOptional<SkillsData> optional = LazyOptional.of(this::createSkills);

    private SkillsData createSkills() {
        if (skills == null) {
            skills = new SkillsData();
        }
        return skills;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == SKILLS) {
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
        createSkills().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag pNBT) {
        createSkills().loadNBTData(pNBT);
    }
}
