package com.harmex.deathcube.client.renderer.entity;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.client.model.GalteriusModel;
import com.harmex.deathcube.world.entity.boss.Galterius;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
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
