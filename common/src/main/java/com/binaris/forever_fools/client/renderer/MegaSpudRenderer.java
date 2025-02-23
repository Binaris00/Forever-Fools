package com.binaris.forever_fools.client.renderer;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.client.model.MegaSpudModel;
import com.binaris.forever_fools.client.renderer.layer.MegaSpudArmorLayer;
import com.binaris.forever_fools.client.renderer.layer.MegaSpudOuterLayer;
import com.binaris.forever_fools.content.entity.MegaSpud;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class MegaSpudRenderer extends MobRenderer<MegaSpud, MegaSpudModel<MegaSpud>> {

    public MegaSpudRenderer(EntityRendererProvider.Context context) {
        super(context, new MegaSpudModel<>(context.bakeLayer(MegaSpudModel.LAYER_INNER_LOCATION)), 0.25F);
        this.addLayer(new MegaSpudOuterLayer<>(this, context.getModelSet()));
        this.addLayer(new MegaSpudArmorLayer(this, context.getModelSet()));
    }

    @Override
    public void render(MegaSpud megaSpud, float f, float g, @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i) {
        this.shadowRadius = 0.25F * (float)megaSpud.getSize();
        super.render(megaSpud, f, g, poseStack, multiBufferSource, i);
    }

    @Override
    protected void scale(MegaSpud megaSpud, PoseStack poseStack, float f) {
        poseStack.scale(0.999F, 0.999F, 0.999F);
        poseStack.translate(0.0F, 0.001F, 0.0F);
        float h = (float)megaSpud.getSize();
        float i = Mth.lerp(f, megaSpud.oSquish, megaSpud.squish) / (h * 0.5F + 1.0F);
        float j = 1.0F / (i + 1.0F);
        poseStack.scale(j * h, 1.5F / j * h, j * h);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull MegaSpud megaSpud) {
        return FFCommonMod.id("textures/entity/mega_spud.png");
    }
}