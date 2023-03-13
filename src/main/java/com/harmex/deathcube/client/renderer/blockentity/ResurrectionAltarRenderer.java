package com.harmex.deathcube.client.renderer.blockentity;

import com.harmex.deathcube.world.level.block.entity.custom.ResurrectionAltarBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;

public class ResurrectionAltarRenderer implements BlockEntityRenderer<ResurrectionAltarBlockEntity> {
    public ResurrectionAltarRenderer(BlockEntityRendererProvider.Context pContext) {

    }

    @Override
    public void render(ResurrectionAltarBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        ItemStack totem = pBlockEntity.inventory.getStackInSlot(0);
        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.75625F, 0.5F);
        pPoseStack.scale(0.5F, 0.25F, 0.5F);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(-90));

        itemRenderer.renderStatic(totem, ItemTransforms.TransformType.GUI, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);
        pPoseStack.popPose();
    }
}
