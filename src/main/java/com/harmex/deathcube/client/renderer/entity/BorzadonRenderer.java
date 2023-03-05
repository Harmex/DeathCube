package com.harmex.deathcube.client.renderer.entity;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.client.model.BorzadonModel;
import com.harmex.deathcube.world.entity.boss.Borzadon;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
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
