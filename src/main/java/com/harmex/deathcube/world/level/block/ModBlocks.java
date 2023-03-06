package com.harmex.deathcube.world.level.block;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.world.item.ModItems;
import com.harmex.deathcube.world.item.ModRarities;
import com.harmex.deathcube.world.level.block.custom.GoldenCarrotBlock;
import com.harmex.deathcube.world.level.block.custom.MatterManipulatorBlock;
import com.harmex.deathcube.world.level.block.custom.ResurrectionAltarBlock;
import com.harmex.deathcube.world.level.block.custom.UpgradingStationBlock;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DeathCube.MODID);

    public static final RegistryObject<Block> ECHO_AMETHYST_BLOCK = registerBlock("echo_amethyst_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_PURPLE)
                    .strength(5F, 1200F)
                    .requiresCorrectToolForDrops()), ModRarities.EPIC
    );
    public static final RegistryObject<Block> GOLDEN_CARROTS = registerBlockWithoutBlockItem("golden_carrots",
            () -> new GoldenCarrotBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .noCollission().randomTicks().instabreak().sound(SoundType.CROP))
    );
    public static final RegistryObject<Block> UPGRADING_STATION = registerBlock("upgrading_station",
            () -> new UpgradingStationBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f).requiresCorrectToolForDrops()), ModRarities.EPIC
    );
    public static final RegistryObject<Block> MATTER_MANIPULATOR = registerBlock("matter_manipulator",
            () -> new MatterManipulatorBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f).requiresCorrectToolForDrops()), ModRarities.EPIC
    );
    public static final RegistryObject<Block> RESURRECTION_ALTAR = registerBlock("resurrection_altar",
            () -> new ResurrectionAltarBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f).requiresCorrectToolForDrops()), Rarity.COMMON
    );
    public static final RegistryObject<Block> ZANTHINE_ORE = registerBlock("zanthine_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3F, 3F).requiresCorrectToolForDrops(),
                    UniformInt.of(5, 7)), Rarity.COMMON
    );
    public static final RegistryObject<Block> DEEPSLATE_ZANTHINE_ORE = registerBlock("deepslate_zanthine_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(ZANTHINE_ORE.get())
                    .sound(SoundType.DEEPSLATE), UniformInt.of(5, 7)), Rarity.COMMON
    );

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String pName, Supplier<T> pBlock) {
        return BLOCKS.register(pName, pBlock);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String pName, Supplier<T> pBlock, Rarity pRarity) {
        RegistryObject<T> toReturn = BLOCKS.register(pName, pBlock);
        registerBlockItem(pName, toReturn, pRarity);
        return toReturn;
    }

    public static <T extends Block> void registerBlockItem(String pName, RegistryObject<T> pBlock, Rarity pRarity) {
        ModItems.ITEMS.register(pName, () -> new BlockItem(pBlock.get(), new Item.Properties().rarity(pRarity)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
