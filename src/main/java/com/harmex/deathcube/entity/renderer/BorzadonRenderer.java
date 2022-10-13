package com.harmex.deathcube.entity.renderer;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.entity.boss.Borzadon;
import com.harmex.deathcube.entity.model.BorzadonModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class BorzadonRenderer extends HumanoidMobRenderer<Borzadon, BorzadonModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(DeathCube.MODID, "textures/entity/borzadon.png");

    public BorzadonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BorzadonModel(pContext.bakeLayer(BorzadonModel.BORZADON_LAYER)), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Borzadon pEntity) {
        return TEXTURE;
    }
}
