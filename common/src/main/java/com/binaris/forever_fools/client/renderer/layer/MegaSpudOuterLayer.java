package com.binaris.forever_fools.client.renderer.layer;

import com.binaris.forever_fools.client.model.MegaSpudModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class MegaSpudOuterLayer<T extends LivingEntity> extends RenderLayer<T, MegaSpudModel<T>> {
    private final EntityModel<T> model;

    public MegaSpudOuterLayer(RenderLayerParent<T, MegaSpudModel<T>> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
        this.model = new MegaSpudModel<>(entityModelSet.bakeLayer(MegaSpudModel.LAYER_OUTER_LOCATION));
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i, @NotNull T livingEntity, float f, float g, float h, float j, float k, float l) {
        Minecraft minecraft = Minecraft.getInstance();
        boolean bl = minecraft.shouldEntityAppearGlowing(livingEntity) && livingEntity.isInvisible();
        if (!livingEntity.isInvisible() || bl) {
            VertexConsumer vertexConsumer;
            if (bl) {
                vertexConsumer = multiBufferSource.getBuffer(RenderType.outline(this.getTextureLocation(livingEntity)));
            } else {
                vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(livingEntity)));
            }

            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(livingEntity, f, g, h);
            this.model.setupAnim(livingEntity, f, g, j, k, l);
            this.model.renderToBuffer(poseStack, vertexConsumer, i, LivingEntityRenderer.getOverlayCoords(livingEntity, 0.0F));
        }
    }
}