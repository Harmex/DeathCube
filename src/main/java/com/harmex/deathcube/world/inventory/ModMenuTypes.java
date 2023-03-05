package com.harmex.deathcube.world.inventory;

import com.harmex.deathcube.DeathCube;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, DeathCube.MODID);


    public static final RegistryObject<MenuType<UpgradingStationMenu>> UPGRADING_STATION_MENU =
            registerMenuType(UpgradingStationMenu::new, "upgrading_menu_menu");

    public static final RegistryObject<MenuType<MatterManipulatorMenu>> MATTER_MANIPULATOR_MENU =
            registerMenuType(MatterManipulatorMenu::new, "matter_manipulator_menu");

    public static final RegistryObject<MenuType<ResurrectionAltarMenu>> RESURRECTION_ALTAR_MENU =
            registerMenuType(ResurrectionAltarMenu::new, "resurrection_altar_menu");


    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                 String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
