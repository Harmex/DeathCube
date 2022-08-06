package com.harmex.deathcube.entity.renderer;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.entity.boss.Galterius;
import com.harmex.deathcube.entity.model.GalteriusModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GalteriusRenderer extends HumanoidMobRenderer<Galterius, GalteriusModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(DeathCube.MODID, "textures/entity/galterius.png");

    public GalteriusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GalteriusModel(pContext.bakeLayer(GalteriusModel.GALTERIUS_LAYER)), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Galterius pEntity) {
        return TEXTURE;
    }
}
