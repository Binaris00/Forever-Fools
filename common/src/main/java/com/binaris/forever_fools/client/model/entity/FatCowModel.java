package com.binaris.forever_fools.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;

public class FatCowModel<T extends Entity> extends CowModel<T> { // Hereda de CowModel

    // Necesitamos una referencia a la parte del cuerpo para escalarla
    public float bodyScale = 1.0F; // Hacerlo público para un acceso directo simple, o añadir un setter
    private final ModelPart bodyPart;

    public FatCowModel(ModelPart pRoot) {
        super(pRoot);
        this.bodyPart = pRoot.getChild("body"); // Obtenemos la parte "body"
    }


    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int pColor) {
        pPoseStack.pushPose();

        // Aplicamos el escalado solo a la parte del cuerpo
        // El pivote del cuerpo de la vaca es (0.0F, 5.0F, 2.0F) en su definición
        // Trasladamos para que el escalado sea desde el centro del cuerpo
        pPoseStack.translate(this.bodyPart.x / 16.0F, (this.bodyPart.y + 0.5F) / 16.0F, this.bodyPart.z / 16.0F); // Ajuste de Y para que crezca desde el suelo
        pPoseStack.scale(this.bodyScale, this.bodyScale, this.bodyScale);
        pPoseStack.translate(-this.bodyPart.x / 16.0F, -(this.bodyPart.y + 0.5F) / 16.0F, -this.bodyPart.z / 16.0F);

        this.bodyPart.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay);

        pPoseStack.popPose();

        // Renderizamos las otras partes del modelo normalmente
        this.head.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        this.rightHindLeg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        this.leftHindLeg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        this.rightFrontLeg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        this.leftFrontLeg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
    }
}