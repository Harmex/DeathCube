package com.harmex.deathcube.entity.renderer;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.entity.boss.Azrathal;
import com.harmex.deathcube.entity.model.AzrathalModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class AzrathalRenderer extends HumanoidMobRenderer<Azrathal, AzrathalModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(DeathCube.MODID, "textures/entity/azrathal.png");

    public AzrathalRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AzrathalModel(pContext.bakeLayer(AzrathalModel.AZRATHAL_LAYER)), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Azrathal pEntity) {
        return TEXTURE;
    }
}
