package com.harmex.deathcube.entity.renderer;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.entity.boss.Zanuzal;
import com.harmex.deathcube.entity.model.ZanuzalModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

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
}
