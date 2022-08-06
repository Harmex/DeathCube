package com.harmex.deathcube.entity.renderer;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.entity.boss.Naervus;
import com.harmex.deathcube.entity.model.NaervusModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class NaervusRenderer extends HumanoidMobRenderer<Naervus, NaervusModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(DeathCube.MODID, "textures/entity/naervus.png");

    public NaervusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new NaervusModel(pContext.bakeLayer(NaervusModel.NAERVUS_LAYER)), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Naervus pEntity) {
        return TEXTURE;
    }
}
