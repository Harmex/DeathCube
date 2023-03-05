package com.harmex.deathcube.client.model;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.world.entity.boss.Galterius;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GalteriusModel extends HumanoidModel<Galterius> {
    public static ModelLayerLocation GALTERIUS_LAYER =
            new ModelLayerLocation(new ResourceLocation(DeathCube.MODID, "galterius"), "body");

    public GalteriusModel(ModelPart pRoot) {
        super(pRoot);
    }

    @Override
    public void setupAnim(Galterius pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(pEntity), this.attackTime, pAgeInTicks);
    }

    public boolean isAggressive(Galterius pEntity) {
        return pEntity.isAggressive();
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = ZombieModel.createMesh(CubeDeformation.NONE, 0.0F);
        return LayerDefinition.create(meshDefinition, 64, 64);
    }
}
