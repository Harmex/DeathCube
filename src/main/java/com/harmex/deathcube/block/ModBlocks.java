package com.harmex.deathcube.block;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.block.custom.*;
import com.harmex.deathcube.block.entity.ModWoodTypes;
import com.harmex.deathcube.item.ModItems;
import com.harmex.deathcube.item.ModRarities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, DeathCube.MODID);


    //region Misc
    public static final RegistryObject<Block> ECHO_AMETHYST_BLOCK = registerBlock("echo_amethyst_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_PURPLE)
                    .strength(5F, 1200F)
                    .requiresCorrectToolForDrops()
            ), ModRarities.EPIC);
    //endregion
    //region Crop
    public static final RegistryObject<Block> GOLDEN_CARROTS = registerBlockWithoutBlockItem("golden_carrots",
            () -> new GoldenCarrotBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
            ));
    //endregion
    //region Entity
    public static final RegistryObject<Block> UPGRADING_STATION = registerBlock("upgrading_station",
            () -> new UpgradingStationBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f)
                    .requiresCorrectToolForDrops()
            ), ModRarities.EPIC);
    public static final RegistryObject<Block> MATTER_MANIPULATOR = registerBlock("matter_manipulator",
            () -> new MatterManipulatorBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f)
                    .requiresCorrectToolForDrops()
            ), ModRarities.EPIC);
    public static final RegistryObject<Block> RESURRECTION_ALTAR = registerBlock("resurrection_altar",
            () -> new ResurrectionAltarBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f)
                    .requiresCorrectToolForDrops()
            ), Rarity.COMMON);
    //endregion
    //region Ores
    public static final RegistryObject<Block> ZANTHINE_ORE = registerBlock("zanthine_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops(),
                    UniformInt.of(5, 7)
            ), Rarity.COMMON);
    public static final RegistryObject<Block> DEEPSLATE_ZANTHINE_ORE = registerBlock("deepslate_zanthine_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(ZANTHINE_ORE.get())
                    .sound(SoundType.DEEPSLATE),
                    UniformInt.of(5, 7)
            ), Rarity.COMMON);
    //endregion
    //region Cherry Tree
    public static final RegistryObject<Block> CHERRY_PLANKS = registerBlock("cherry_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .color(MaterialColor.COLOR_PINK)
            ) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            }, ModRarities.COMMON);

    public static final RegistryObject<Block> CHERRY_SAPLING = registerBlock("cherry_sapling",
            () -> new SaplingBlock(new OakTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)
            ), ModRarities.COMMON);

    public static final RegistryObject<Block> POTTED_CHERRY_SAPLING = registerBlockWithoutBlockItem("potted_cherry_sapling",
            () -> new FlowerPotBlock(null, CHERRY_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)
            ));

    public static final RegistryObject<Block> CHERRY_LOG = registerBlock("cherry_log",
            () -> new ModLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
                    .color(MaterialColor.COLOR_PINK)
            ), ModRarities.COMMON);

    public static final RegistryObject<Block> STRIPPED_CHERRY_LOG = registerBlock("stripped_cherry_log",
            () -> new ModLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
                    .color(MaterialColor.COLOR_PINK)
            ), ModRarities.COMMON);

    public static final RegistryObject<Block> STRIPPED_CHERRY_WOOD = registerBlock("stripped_cherry_wood",
            () -> new ModLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)
                    .color(MaterialColor.COLOR_PINK)
            ), ModRarities.COMMON);

    public static final RegistryObject<Block> CHERRY_WOOD = registerBlock("cherry_wood",
            () -> new ModLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
                    .color(MaterialColor.COLOR_PINK)
            ), ModRarities.COMMON);

    public static final RegistryObject<Block> CHERRY_LEAVES = registerBlock("cherry_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
                    .color(MaterialColor.COLOR_PINK)
            ) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            }, ModRarities.COMMON);
    //endregion
    //region Cherry Furniture
    public static final RegistryObject<Block> CHERRY_SLAB = registerBlock("cherry_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)
                    .color(CHERRY_PLANKS.get().defaultMaterialColor())
            ) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            }, ModRarities.COMMON);

    public static final RegistryObject<Block> CHERRY_FENCE = registerBlock("cherry_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
                    .color(CHERRY_PLANKS.get().defaultMaterialColor())
            ) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            }, ModRarities.COMMON);

    public static final RegistryObject<Block> CHERRY_STAIRS = registerBlock("cherry_stairs",
            () -> new StairBlock(() -> ModBlocks.CHERRY_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(CHERRY_PLANKS.get())
            ) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            }, ModRarities.COMMON);

    public static final RegistryObject<Block> CHERRY_BUTTON = registerBlock("cherry_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON),
                    30, true,
                    SoundEvents.WOODEN_BUTTON_CLICK_OFF,
                    SoundEvents.WOODEN_BUTTON_CLICK_ON
            ), ModRarities.COMMON);

    public static final RegistryObject<Block> CHERRY_PRESSURE_PLATE = registerBlock("cherry_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
                            .color(CHERRY_PLANKS.get().defaultMaterialColor()),
                    SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF,
                    SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON
            ), ModRarities.COMMON);

    public static final RegistryObject<Block> CHERRY_DOOR = registerBlock("cherry_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)
                    .color(CHERRY_PLANKS.get().defaultMaterialColor()),
                    SoundEvents.WOODEN_DOOR_CLOSE,
                    SoundEvents.WOODEN_DOOR_OPEN
            ), ModRarities.COMMON);

    public static final RegistryObject<Block> CHERRY_TRAPDOOR = registerBlock("cherry_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)
                    .color(CHERRY_PLANKS.get().defaultMaterialColor()),
                    SoundEvents.WOODEN_TRAPDOOR_CLOSE,
                    SoundEvents.WOODEN_TRAPDOOR_OPEN
            ), ModRarities.COMMON);

    public static final RegistryObject<Block> CHERRY_FENCE_GATE = registerBlock("cherry_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)
                    .color(CHERRY_PLANKS.get().defaultMaterialColor()),
                    SoundEvents.FENCE_GATE_CLOSE,
                    SoundEvents.FENCE_GATE_OPEN
            ) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            }, ModRarities.COMMON);

    public static final RegistryObject<Block> CHERRY_SIGN = registerBlockWithoutBlockItem("cherry_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN)
                    .color(CHERRY_PLANKS.get().defaultMaterialColor()), ModWoodTypes.CHERRY));

    public static final RegistryObject<Block> CHERRY_WALL_SIGN = registerBlockWithoutBlockItem("cherry_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN)
                    .color(CHERRY_PLANKS.get().defaultMaterialColor()), ModWoodTypes.CHERRY));
    //endregion

    //region Registration
    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Rarity rarity) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, rarity);
        return toReturn;
    }

    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, Rarity rarity) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()
                .rarity(rarity)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
    //endregion
}
