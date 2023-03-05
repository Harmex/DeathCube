package com.harmex.deathcube.world.entity.ai.attribute;

import com.harmex.deathcube.DeathCube;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(ForgeRegistries.ATTRIBUTES, DeathCube.MODID);

    public static final RegistryObject<Attribute> MAX_MANA =
            ATTRIBUTES.register("max_mana",
                    () -> new RangedAttribute("attribute.name.deathcube.max_mana",
                            20.0D, 0.0D, 1024.0D).setSyncable(true));

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }
}
