package com.binaris.forever_fools.client.renderer.entity;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.content.entity.RayTracing;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RayTracingRenderer extends LivingEntityRenderer<RayTracing, PlayerModel<RayTracing>> {
    public RayTracingRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull RayTracing rayTracing) {
        return FFCommonMod.id("textures/entity/ray.png");
    }
}