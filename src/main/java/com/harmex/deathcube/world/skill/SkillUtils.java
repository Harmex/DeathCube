package com.harmex.deathcube.world.skill;

import com.google.common.collect.ImmutableMap;
import com.harmex.deathcube.entity.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class SkillUtils {
    public static final ImmutableMap<EntityType<? extends LivingEntity>, Float> XP_AMOUNT_FOR_ENTITIES =
            new ImmutableMap.Builder<EntityType<? extends LivingEntity>, Float>()
                    .put(EntityType.ALLAY, 1.0F)
                    .put(EntityType.AXOLOTL, 5.0F)
                    .put(EntityType.BAT, 2.5F)
                    .put(EntityType.BEE, 2.5F)
                    .put(EntityType.BLAZE, 17.5F)
                    .put(EntityType.CAT, 1.0F)
                    .put(EntityType.CAMEL, 2.5F)
                    .put(EntityType.CAVE_SPIDER, 12.5F)
                    .put(EntityType.CHICKEN, 1.0F)
                    .put(EntityType.COD, 0.5F)
                    .put(EntityType.COW, 1.0F)
                    .put(EntityType.CREEPER, 10.0F)
                    .put(EntityType.DOLPHIN, 1.0F)
                    .put(EntityType.DONKEY, 1.0F)
                    .put(EntityType.DROWNED, 12.5F)
                    .put(EntityType.ELDER_GUARDIAN, 150.0F)
                    .put(EntityType.ENDER_DRAGON, 500.0F)
                    .put(EntityType.ENDERMAN, 20.0F)
                    .put(EntityType.ENDERMITE, 5.0F)
                    .put(EntityType.EVOKER, 50.0F)
                    .put(EntityType.FOX, 1.0F)
                    .put(EntityType.FROG, 1.0F)
                    .put(EntityType.GHAST, 25.0F)
                    .put(EntityType.GIANT, 150.0F)
                    .put(EntityType.GLOW_SQUID, 1.0F)
                    .put(EntityType.GOAT, 1.0F)
                    .put(EntityType.GUARDIAN, 20.0F)
                    .put(EntityType.HOGLIN, 17.5F)
                    .put(EntityType.HORSE, 2.5F)
                    .put(EntityType.HUSK, 12.5F)
                    .put(EntityType.ILLUSIONER, 50.0F)
                    .put(EntityType.IRON_GOLEM, 25.0F)
                    .put(EntityType.LLAMA, 1.0F)
                    .put(EntityType.MAGMA_CUBE, 5.0F)
                    .put(EntityType.MULE, 1.0F)
                    .put(EntityType.MOOSHROOM, 5.0F)
                    .put(EntityType.OCELOT, 1.0F)
                    .put(EntityType.PANDA, 1.0F)
                    .put(EntityType.PARROT, 1.0F)
                    .put(EntityType.PHANTOM, 25.0F)
                    .put(EntityType.PIG, 1.0F)
                    .put(EntityType.PIGLIN, 17.5F)
                    .put(EntityType.PIGLIN_BRUTE, 30.0F)
                    .put(EntityType.PILLAGER, 12.5F)
                    .put(EntityType.POLAR_BEAR, 5.0F)
                    .put(EntityType.PUFFERFISH, 1.0F)
                    .put(EntityType.RABBIT, 1.0F)
                    .put(EntityType.RAVAGER, 75.0F)
                    .put(EntityType.SALMON, 1.0F)
                    .put(EntityType.SHEEP, 1.0F)
                    .put(EntityType.SHULKER, 25.0F)
                    .put(EntityType.SILVERFISH, 12.5F)
                    .put(EntityType.SKELETON, 10.0F)
                    .put(EntityType.SKELETON_HORSE, 7.5F)
                    .put(EntityType.SLIME, 2.5F)
                    .put(EntityType.SNOW_GOLEM, 1.0F)
                    .put(EntityType.SPIDER, 7.5F)
                    .put(EntityType.SQUID, 1.0F)
                    .put(EntityType.STRAY, 17.5F)
                    .put(EntityType.STRIDER, 7.5F)
                    .put(EntityType.TADPOLE, 0.5F)
                    .put(EntityType.TRADER_LLAMA, 5.0F)
                    .put(EntityType.TROPICAL_FISH, 1.0F)
                    .put(EntityType.TURTLE, 2.5F)
                    .put(EntityType.VEX, 7.5F)
                    .put(EntityType.VILLAGER, 1.0F)
                    .put(EntityType.VINDICATOR, 25.0F)
                    .put(EntityType.WANDERING_TRADER, 1.0F)
                    .put(EntityType.WARDEN, 750.0F)
                    .put(EntityType.WITCH, 25.0F)
                    .put(EntityType.WITHER, 300.0F)
                    .put(EntityType.WITHER_SKELETON, 30.0F)
                    .put(EntityType.WOLF, 1.0F)
                    .put(EntityType.ZOGLIN, 20.0F)
                    .put(EntityType.ZOMBIE, 10.0F)
                    .put(EntityType.ZOMBIE_HORSE, 7.5F)
                    .put(EntityType.ZOMBIE_VILLAGER, 12.5F)
                    .put(EntityType.ZOMBIFIED_PIGLIN, 20.0F)
                    .put(EntityType.PLAYER, 1.0F)
                    .put(ModEntityTypes.AZRATHAL.get(), 500.0F)
                    .put(ModEntityTypes.BORZADON.get(), 400.0F)
                    .put(ModEntityTypes.GALTERIUS.get(), 250.0F)
                    .put(ModEntityTypes.NAERVUS.get(), 150.0F)
                    .put(ModEntityTypes.ZANUZAL.get(), 300.0F)
                    .build();

    public static final ImmutableMap<Item, Float> XP_FOR_ITEM_FISHED =
            ImmutableMap.<Item, Float>builder()
                    .put(Items.COD, 10.0F)
                    .put(Items.SALMON, 25.0F)
                    .put(Items.TROPICAL_FISH, 50.0F)
                    .put(Items.PUFFERFISH, 40.0F)
                    .put(Items.LILY_PAD, 5.0F)
                    .put(Items.LEATHER_BOOTS, 10.0F)
                    .put(Items.LEATHER, 5.0F)
                    .put(Items.BONE, 5.0F)
                    .put(Items.POTION, 5.0F)
                    .put(Items.STRING, 5.0F)
                    .put(Items.FISHING_ROD, 50.0F)
                    .put(Items.BOWL, 5.0F)
                    .put(Items.STICK, 5.0F)
                    .put(Items.INK_SAC, 5.0F)
                    .put(Items.TRIPWIRE_HOOK, 5.0F)
                    .put(Items.ROTTEN_FLESH, 5.0F)
                    .put(Items.BAMBOO, 5.0F)
                    .put(Items.NAME_TAG, 40.0F)
                    .put(Items.SADDLE, 40.0F)
                    .put(Items.BOW, 50.0F)
                    .put(Items.ENCHANTED_BOOK, 50.0F)
                    .put(Items.NAUTILUS_SHELL, 75.0F)
                    .build();

    public static final ImmutableMap<Block, Float> XP_FOR_BLOCK_BROKEN =
            ImmutableMap.<Block, Float>builder()
                    .put(Blocks.STONE, 2.5F)
                    .put(Blocks.COAL_ORE, 5.0F)
                    .build();
}
