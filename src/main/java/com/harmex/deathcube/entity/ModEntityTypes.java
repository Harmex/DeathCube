package com.harmex.deathcube.entity;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.entity.boss.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DeathCube.MODID);

    public static final RegistryObject<EntityType<Azrathal>> AZRATHAL =
            ENTITY_TYPES.register("azrathal",
                    () -> EntityType.Builder.of(Azrathal::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F)
                            .clientTrackingRange(8)
                            .fireImmune()
                            .build("azrathal")
            );
    public static final RegistryObject<EntityType<Borzadon>> BORZADON =
            ENTITY_TYPES.register("borzadon",
                    () -> EntityType.Builder.of(Borzadon::new, MobCategory.MONSTER)
                            .sized(0.7F, 2.4F)
                            .clientTrackingRange(8)
                            .fireImmune()
                            .build("borzadon")
            );
    public static final RegistryObject<EntityType<Galterius>> GALTERIUS =
            ENTITY_TYPES.register("galterius",
                    () -> EntityType.Builder.of(Galterius::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F)
                            .clientTrackingRange(8)
                            .fireImmune()
                            .build("galterius")
            );
    public static final RegistryObject<EntityType<Naervus>> NAERVUS =
            ENTITY_TYPES.register("naervus",
                    () -> EntityType.Builder.of(Naervus::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F)
                            .clientTrackingRange(8)
                            .fireImmune()
                            .build("naervus")
            );
    public static final RegistryObject<EntityType<Zanuzal>> ZANUZAL =
            ENTITY_TYPES.register("zanuzal",
                    () -> EntityType.Builder.of(Zanuzal::new, MobCategory.MONSTER)
                            .sized(0.9F, 0.5F)
                            .clientTrackingRange(8)
                            .fireImmune()
                            .build("zanuzal")
            );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
