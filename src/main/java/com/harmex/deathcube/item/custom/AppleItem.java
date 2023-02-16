package com.harmex.deathcube.item.custom;

import com.harmex.deathcube.config.DeathCubeCommonConfigs;
import com.harmex.deathcube.item.ModItems;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AppleItem extends Item {
    public AppleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pLivingEntity) {
        ItemStack itemStack = super.finishUsingItem(pStack, pLevel, pLivingEntity);
        AttributeInstance maxHealthAttribute = pLivingEntity.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttribute == null) {
            return itemStack;
        }

        if (pStack.getItem() == ModItems.DIAMOND_APPLE.get()) {
            if (pLivingEntity.getMaxHealth() >= DeathCubeCommonConfigs.APPLES_DIAMOND_MAX_HEALTH.get()) {
                return itemStack;
            } else {
                setMaxHealth(maxHealthAttribute,
                        new int[] {
                                DeathCubeCommonConfigs.APPLES_DIAMOND_BOOST_CHANCE.get()
                        }, new int[] {
                                DeathCubeCommonConfigs.APPLES_DIAMOND_BOOST_AMOUNT.get()
                        },
                        DeathCubeCommonConfigs.APPLES_DIAMOND_MAX_HEALTH.get()
                );
            }
        }

        if (pStack.getItem() == ModItems.NETHERITE_APPLE.get()) {
            if (pLivingEntity.getMaxHealth() >= DeathCubeCommonConfigs.APPLES_NETHERITE_MAX_HEALTH.get()) {
                return itemStack;
            } else {
                setMaxHealth(maxHealthAttribute,
                        new int[] {
                                DeathCubeCommonConfigs.APPLES_NETHERITE_FIRST_BOOST_CHANCE.get(),
                                DeathCubeCommonConfigs.APPLES_NETHERITE_LAST_BOOST_CHANCE.get()
                        }, new int[] {
                                DeathCubeCommonConfigs.APPLES_NETHERITE_FIRST_BOOST_AMOUNT.get(),
                                DeathCubeCommonConfigs.APPLES_NETHERITE_LAST_BOOST_AMOUNT.get()
                        },
                        DeathCubeCommonConfigs.APPLES_NETHERITE_MAX_HEALTH.get()
                );
            }
        }

        if (pStack.getItem() == ModItems.BEDROCK_APPLE.get()) {
            if (pLivingEntity.getMaxHealth() >= DeathCubeCommonConfigs.APPLES_BEDROCK_MAX_HEALTH.get()) {
                return itemStack;
            } else {
                setMaxHealth(maxHealthAttribute,
                        new int[] {
                                DeathCubeCommonConfigs.APPLES_BEDROCK_FIRST_BOOST_CHANCE.get(),
                                DeathCubeCommonConfigs.APPLES_BEDROCK_SECOND_BOOST_CHANCE.get(),
                                DeathCubeCommonConfigs.APPLES_BEDROCK_LAST_BOOST_CHANCE.get()
                        }, new int[] {
                                DeathCubeCommonConfigs.APPLES_BEDROCK_FIRST_BOOST_AMOUNT.get(),
                                DeathCubeCommonConfigs.APPLES_BEDROCK_SECOND_BOOST_CHANCE.get(),
                                DeathCubeCommonConfigs.APPLES_BEDROCK_LAST_BOOST_AMOUNT.get()
                        },
                        DeathCubeCommonConfigs.APPLES_BEDROCK_MAX_HEALTH.get()
                );
            }
        }
        return itemStack;
    }

    private void setMaxHealth(AttributeInstance maxHealthAttribute, int[] chances, int[] boosts, int maxBoost) {
        for (int i = 0; i < chances.length && i < boosts.length; i++) {
            if (chance(chances[i]) &&
                    maxHealthAttribute.getBaseValue() <= maxBoost - boosts[i]) {
                maxHealthAttribute.setBaseValue(maxHealthAttribute.getBaseValue() + boosts[i]);
                return;
            }
        }
    }

    private boolean chance(int percent) {
        int random = Mth.randomBetweenInclusive(RandomSource.create(), 0, 100);
        return random <= percent;
    }
}
