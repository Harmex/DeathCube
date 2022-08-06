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

    public NaervusModel(ModelPart pRoot) {
        super(pRoot);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = PiglinModel.createMesh(CubeDeformation.NONE);
        return LayerDefinition.create(meshDefinition, 64, 64);
    }
}
