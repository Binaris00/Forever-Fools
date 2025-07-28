package com.binaris.forever_fools.client.model.entity;

import com.binaris.forever_fools.content.entity.FatSheepEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Sheep;

public class FatSheepModel<T extends FatSheepEntity> extends SheepModel<T> {

    public float bodyScale = 1.0F;
    private final ModelPart bodyPart;

    public FatSheepModel(ModelPart pRoot) {
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
}