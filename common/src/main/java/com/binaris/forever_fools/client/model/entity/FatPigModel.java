package com.binaris.forever_fools.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.Entity;

public class FatPigModel<T extends Entity> extends PigModel<T> {

    public float bodyScale = 1.0F;
    private final ModelPart bodyPart;

    public FatPigModel(ModelPart pRoot) {
        super(pRoot);
        this.bodyPart = pRoot.getChild("body");
    }


    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int pColor) {
        pPoseStack.pushPose();

        pPoseStack.translate(this.bodyPart.x / 16.0F, (this.bodyPart.y + 0.5F) / 16.0F, this.bodyPart.z / 16.0F); // Ajuste de Y para que crezca desde el suelo
        pPoseStack.scale(this.bodyScale, this.bodyScale, this.bodyScale);
        pPoseStack.translate(-this.bodyPart.x / 16.0F, -(this.bodyPart.y + 0.5F) / 16.0F, -this.bodyPart.z / 16.0F);

        this.bodyPart.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay);

        pPoseStack.popPose();

        this.head.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        this.rightHindLeg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        this.leftHindLeg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        this.rightFrontLeg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        this.leftFrontLeg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
    }

    public static LayerDefinition createBodyLayer() {
        return PigModel.createBodyLayer(CubeDeformation.NONE);
    }
}