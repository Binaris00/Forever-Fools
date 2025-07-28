package com.binaris.forever_fools.client.renderer.entity;

import com.binaris.forever_fools.FFCommonMod;
import net.minecraft.client.model.BoggedModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.layers.SkeletonClothingLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Bogged;
import org.jetbrains.annotations.NotNull;

public class BoggedPotatoRenderer extends SkeletonRenderer<Bogged> {
    private static final ResourceLocation BOGGED_SKELETON_LOCATION = FFCommonMod.id("textures/entity/skeleton/bogged.png");
    private static final ResourceLocation BOGGED_OUTER_LAYER_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/skeleton/bogged_overlay.png");

    public BoggedPotatoRenderer(EntityRendererProvider.Context p_174380_) {
        super(p_174380_, ModelLayers.BOGGED_INNER_ARMOR, ModelLayers.BOGGED_OUTER_ARMOR, new BoggedModel(p_174380_.bakeLayer(ModelLayers.BOGGED)));
        this.addLayer(new SkeletonClothingLayer<>(this, p_174380_.getModelSet(), ModelLayers.BOGGED_OUTER_LAYER, BOGGED_OUTER_LAYER_LOCATION));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Bogged pEntity) {
        return BOGGED_SKELETON_LOCATION;
    }
}
