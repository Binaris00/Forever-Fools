package com.binaris.forever_fools.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DoubleSpyGlassLayer<T extends LivingEntity, M extends EntityModel<T> & HeadedModel> extends RenderLayer<T, M> {
    public DoubleSpyGlassLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
    }

    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        if (livingEntity instanceof Skeleton skeleton) {
//            int m = skeleton.getSpyglassesInSockets();
//            if (m == 0) {
//                return;
//            }

            ItemStack itemStack = new ItemStack(Items.SPYGLASS);
            double d = 0.15625;
            double e = -0.28125;
            double n = 0.5;
            float o = 0.75F;
            poseStack.pushPose();
            this.getParentModel().getHead().translateAndRotate(poseStack);
            poseStack.scale(0.75F, 0.75F, 0.75F);
//            if (m >= 1) {
//                poseStack.pushPose();
//                poseStack.translate(-0.15625, -0.28125, 0.5);
//                Minecraft.getInstance()
//                        .getItemRenderer()
//                        .renderStatic(livingEntity, itemStack, ItemDisplayContext.HEAD, false, poseStack,
//                                multiBufferSource, livingEntity.level(), i, 1, 1);
//                poseStack.popPose();
//            }
//
//            if (m >= 2) {
//                poseStack.pushPose();
//                poseStack.translate(0.15625, -0.28125, 0.5);
//                Minecraft.getInstance()
//                        .getItemRenderer()
//                        .renderStatic(livingEntity, itemStack, ItemDisplayContext.HEAD, false, poseStack,
//                                multiBufferSource, livingEntity.level(), i, 1, 1);
//                poseStack.popPose();
//            }

            poseStack.popPose();
        }
    }
}
