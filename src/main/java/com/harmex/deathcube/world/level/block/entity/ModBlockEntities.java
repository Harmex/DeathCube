package com.harmex.deathcube.world.level.block.entity;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.world.level.block.ModBlocks;
import com.harmex.deathcube.world.level.block.entity.custom.MatterManipulatorBlockEntity;
import com.harmex.deathcube.world.level.block.entity.custom.ModSignBlockEntity;
import com.harmex.deathcube.world.level.block.entity.custom.ResurrectionAltarBlockEntity;
import com.harmex.deathcube.world.level.block.entity.custom.UpgradingStationBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DeathCube.MODID);

    public static final RegistryObject<BlockEntityType<MatterManipulatorBlockEntity>> MATTER_MANIPULATOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("matter_manipulator", () -> BlockEntityType.Builder.of(MatterManipulatorBlockEntity::new, ModBlocks.MATTER_MANIPULATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<UpgradingStationBlockEntity>> UPGRADING_STATION_BLOCK_ENTITY = BLOCK_ENTITIES.register("upgrading_station", () -> BlockEntityType.Builder.of(UpgradingStationBlockEntity::new, ModBlocks.UPGRADING_STATION.get()).build(null));
    public static final RegistryObject<BlockEntityType<ResurrectionAltarBlockEntity>> RESURRECTION_ALTAR_BLOCK_ENTITY = BLOCK_ENTITIES.register("resurrection_altar", () -> BlockEntityType.Builder.of(ResurrectionAltarBlockEntity::new, ModBlocks.RESURRECTION_ALTAR.get()).build(null));
    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> SIGN_BLOCK_ENTITIES = BLOCK_ENTITIES.register("sign", () -> BlockEntityType.Builder.of(ModSignBlockEntity::new, ModBlocks.CHERRY_SIGN.get(), ModBlocks.CHERRY_WALL_SIGN.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
