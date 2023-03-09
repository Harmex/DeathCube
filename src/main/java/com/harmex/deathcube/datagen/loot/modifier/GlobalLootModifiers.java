package com.harmex.deathcube.datagen.loot.modifier;

import com.harmex.deathcube.DeathCube;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GlobalLootModifiers {
    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, DeathCube.MODID);

    public static final RegistryObject<Codec<SmelterEnchantmentModifier>> SMELTER = GLM.register("smelter",
            SmelterEnchantmentModifier.CODEC);

    public static void register(IEventBus eventBus) {
        GLM.register(eventBus);
    }
}
