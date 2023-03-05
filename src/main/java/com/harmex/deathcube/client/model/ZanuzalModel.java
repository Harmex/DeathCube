package com.harmex.deathcube.client.model;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.world.entity.boss.Zanuzal;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZanuzalModel<T extends Zanuzal> extends HierarchicalModel<T> {
    public static ModelLayerLocation ZANUZAL_LAYER =
            new ModelLayerLocation(new ResourceLocation(DeathCube.MODID, "zanuzal"), "body");
    private final ModelPart root;
    private final ModelPart leftWingBase;
    private final ModelPart leftWingTip;
    private final ModelPart rightWingBase;
    private final ModelPart rightWingTip;
    private final ModelPart tailBase;
    private final ModelPart tailTip;

    public ZanuzalModel(ModelPart pRoot) {
        this.root = pRoot;
        ModelPart modelpart = pRoot.getChild("body");
        this.tailBase = modelpart.getChild("tail_base");
        this.tailTip = this.tailBase.getChild("tail_tip");
        this.leftWingBase = modelpart.getChild("left_wing_base");
        this.leftWingTip = this.leftWingBase.getChild("left_wing_tip");
        this.rightWingBase = modelpart.getChild("right_wing_base");
        this.rightWingTip = this.rightWingBase.getChild("right_wing_tip");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition rootPart = meshDefinition.getRoot();
        PartDefinition bodyPart = rootPart.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 8).addBox(-3.0F, -2.0F, -8.0F, 5.0F, 3.0F, 9.0F), PartPose.rotation(-0.1F, 0.0F, 0.0F));
        PartDefinition tailBasePart = bodyPart.addOrReplaceChild("tail_base", CubeListBuilder.create().texOffs(3, 20).addBox(-2.0F, 0.0F, 0.0F, 3.0F, 2.0F, 6.0F), PartPose.offset(0.0F, -2.0F, 1.0F));
        tailBasePart.addOrReplaceChild("tail_tip", CubeListBuilder.create().texOffs(4, 29).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 6.0F), PartPose.offset(0.0F, 0.5F, 6.0F));
        PartDefinition leftWingBasePart = bodyPart.addOrReplaceChild("left_wing_base", CubeListBuilder.create().texOffs(23, 12).addBox(0.0F, 0.0F, 0.0F, 6.0F, 2.0F, 9.0F), PartPose.offsetAndRotation(2.0F, -2.0F, -8.0F, 0.0F, 0.0F, 0.1F));
        leftWingBasePart.addOrReplaceChild("left_wing_tip", CubeListBuilder.create().texOffs(16, 24).addBox(0.0F, 0.0F, 0.0F, 13.0F, 1.0F, 9.0F), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F));
        PartDefinition rightWingBasePart = bodyPart.addOrReplaceChild("right_wing_base", CubeListBuilder.create().texOffs(23, 12).mirror().addBox(-6.0F, 0.0F, 0.0F, 6.0F, 2.0F, 9.0F), PartPose.offsetAndRotation(-3.0F, -2.0F, -8.0F, 0.0F, 0.0F, -0.1F));
        rightWingBasePart.addOrReplaceChild("right_wing_tip", CubeListBuilder.create().texOffs(16, 24).mirror().addBox(-13.0F, 0.0F, 0.0F, 13.0F, 1.0F, 9.0F), PartPose.offsetAndRotation(-6.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1F));
        bodyPart.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.0F, -5.0F, 7.0F, 3.0F, 5.0F), PartPose.offsetAndRotation(0.0F, 1.0F, -7.0F, 0.2F, 0.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float f = pAgeInTicks * 7.448451F * ((float)Math.PI / 180F);
        float f1 = 16.0F;
        this.leftWingBase.zRot = Mth.cos(f) * 16.0F * ((float)Math.PI / 180F);
        this.leftWingTip.zRot = Mth.cos(f) * 16.0F * ((float)Math.PI / 180F);
        this.rightWingBase.zRot = -this.leftWingBase.zRot;
        this.rightWingTip.zRot = -this.leftWingTip.zRot;
        this.tailBase.xRot = -(5.0F + Mth.cos(f * 2.0F) * 5.0F) * ((float)Math.PI / 180F);
        this.tailTip.xRot = -(5.0F + Mth.cos(f * 2.0F) * 5.0F) * ((float)Math.PI / 180F);
    }

}
