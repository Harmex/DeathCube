package com.harmex.deathcube.client.renderer.entity;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.client.model.ZanuzalModel;
import com.harmex.deathcube.world.entity.boss.Zanuzal;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZanuzalRenderer extends MobRenderer<Zanuzal, ZanuzalModel<Zanuzal>> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(DeathCube.MODID, "textures/entity/zanuzal.png");

    public ZanuzalRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ZanuzalModel<>(pContext.bakeLayer(ZanuzalModel.ZANUZAL_LAYER)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Zanuzal pEntity) {
        return TEXTURE;
    }

    @Override
    protected void scale(Zanuzal pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        float f = 1.0F + 0.15F;
        pMatrixStack.scale(f, f, f);
        pMatrixStack.translate(0.0F, 1.3125F, 0.1875F);
    }

    @Override
    protected void setupRotations(Zanuzal pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pMatrixStack.mulPose(Axis.XP.rotationDegrees(pEntityLiving.getXRot()));
    }
}
