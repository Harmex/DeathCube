package com.harmex.deathcube.world.effect;

import com.harmex.deathcube.DeathCube;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DeathCube.MODID);



    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
