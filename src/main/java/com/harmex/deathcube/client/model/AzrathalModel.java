package com.harmex.deathcube.client.model;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.world.entity.boss.Azrathal;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AzrathalModel extends HumanoidModel<Azrathal> {
    public static ModelLayerLocation AZRATHAL_LAYER =
            new ModelLayerLocation(new ResourceLocation(DeathCube.MODID, "azrathal"), "body");
    private final ModelPart leftEar = this.head.getChild("left_ear");

    public AzrathalModel(ModelPart pRoot) {
        super(pRoot);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = PiglinModel.createMesh(CubeDeformation.NONE);
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    @Override
    public void setupAnim(Azrathal pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        float f1 = pAgeInTicks * 0.1F + pLimbSwing * 0.5F;
        float f2 = 0.08F + pLimbSwingAmount * 0.4F;
        this.leftEar.zRot = (-(float)Math.PI / 6F) - Mth.cos(f1 * 1.2F) * f2;
    }
}
