package com.harmex.deathcube.world.item.custom;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;

public class ToolSetItem extends DiggerItem {
    public ToolSetItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, TagKey<Block> pBlocks, Properties pProperties) {
        super(pAttackDamageModifier, pAttackSpeedModifier, pTier, pBlocks, pProperties);
    }
}
