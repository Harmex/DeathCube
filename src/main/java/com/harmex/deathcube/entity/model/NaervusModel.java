package com.harmex.deathcube.entity.model;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.entity.boss.Naervus;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;

public class NaervusModel extends HumanoidModel<Naervus> {
    public static ModelLayerLocation NAERVUS_LAYER =
            new ModelLayerLocation(new ResourceLocation(DeathCube.MODID, "naervus"), "body");
    public final ModelPart rightEar = this.head.getChild("right_ear");
    private final ModelPart leftEar = this.head.getChild("left_ear");
    private final PartPose bodyDefault = this.body.storePose();
    private final PartPose headDefault = this.head.storePose();
    private final PartPose leftArmDefault = this.leftArm.storePose();
    private final PartPose rightArmDefault = this.rightArm.storePose();

    public NaervusModel(ModelPart pRoot) {
        super(pRoot);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = PiglinModel.createMesh(CubeDeformation.NONE);
        return LayerDefinition.create(meshDefinition, 64, 64);
    }
}
