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

import java.util.Objects;

public class AppleItem extends Item {
    private final Apple apple;
    private final float maxHealthBoost;
    private final int[] boostRange;
    private final RandomSource randomSource = RandomSource.create();
    public AppleItem(Properties pProperties, Apple pApple) {
        super(pProperties);
        this.apple = pApple;
        maxHealthBoost = apple.getMaxHealthBoost();
        boostRange = apple.getBoostRange();
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pLivingEntity) {
        ItemStack itemStack = super.finishUsingItem(pStack, pLevel, pLivingEntity);
        float maxHealth = (float) pLivingEntity.getAttributes().getBaseValue(Attributes.MAX_HEALTH);

        if (maxHealth < maxHealthBoost) {
            float healthBoost = Mth.randomBetweenInclusive(randomSource, boostRange[0], boostRange[1]);
            if (healthBoost == 0) return itemStack;
            else {
                if (maxHealth + healthBoost > maxHealthBoost) {
                    healthBoost = maxHealthBoost - maxHealth;
                }
                Objects.requireNonNull(pLivingEntity.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(maxHealth + healthBoost);
            }
        }
        return itemStack;
    }
}
