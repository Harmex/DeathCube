package com.harmex.deathcube.client.renderer.entity;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.client.model.AzrathalModel;
import com.harmex.deathcube.world.entity.boss.Azrathal;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
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
