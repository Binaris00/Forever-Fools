package com.binaris.forever_fools.client.renderer.entity.layer;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.client.model.CrownModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.npc.Villager;
import org.jetbrains.annotations.NotNull;

public class CrownLayer<T extends LivingEntity, M extends EntityModel<T> & HeadedModel> extends RenderLayer<T, M> {
    private final CrownModel<T> model;

    public CrownLayer(RenderLayerParent<T, M> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
        this.model = new CrownModel<>(entityModelSet.bakeLayer(CrownModel.LAYER_LOCATION));
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        String string = ChatFormatting.stripFormatting(livingEntity.getName().getString());
        if (string.equals("kingbdogz")) {
            poseStack.pushPose();
            if (livingEntity.isBaby() && !(livingEntity instanceof Villager)) {
                poseStack.translate(0.0F, 0.03125F, 0.0F);
                poseStack.scale(0.7F, 0.7F, 0.7F);
                poseStack.translate(0.0F, 1.0F, 0.0F);
            }

            this.getParentModel().getHead().translateAndRotate(poseStack);
            if (livingEntity instanceof Piglin) {
                poseStack.translate(0.0F, 0.0F, -0.03125F);
            }

            this.model.setupAnim(livingEntity, f, g, j, k, l);
            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(this.model.renderType(FFCommonMod.id("textures/effect/crown.png")));
            this.model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        }
    }
}
