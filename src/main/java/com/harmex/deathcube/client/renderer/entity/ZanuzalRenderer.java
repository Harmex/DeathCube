package com.harmex.deathcube.client.renderer.entity;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.client.model.ZanuzalModel;
import com.harmex.deathcube.world.entity.boss.Zanuzal;
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
}
