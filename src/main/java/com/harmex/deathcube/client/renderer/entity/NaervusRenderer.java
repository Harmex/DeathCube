package com.harmex.deathcube.client.renderer.entity;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.client.model.NaervusModel;
import com.harmex.deathcube.world.entity.boss.Naervus;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
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
