package com.harmex.deathcube.world.item.enchantment;

import com.harmex.deathcube.DeathCube;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, DeathCube.MODID);

    public static final RegistryObject<Enchantment> SMELTER =
            ENCHANTMENTS.register("smelter",
                    () -> new SmeltEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
