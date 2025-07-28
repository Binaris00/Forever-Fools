package com.binaris.forever_fools.client.renderer.entity;

import com.binaris.forever_fools.client.renderer.entity.layer.DoubleSpyGlassLayer;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;

public class SpyglassSkeletonRenderer<T extends AbstractSkeleton> extends HumanoidMobRenderer<T, SkeletonModel<T>> {
    private static final ResourceLocation SKELETON_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/skeleton/skeleton.png");

    public SpyglassSkeletonRenderer(EntityRendererProvider.Context p_174380_) {
        this(p_174380_, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
    }

    public SpyglassSkeletonRenderer(EntityRendererProvider.Context pContext, ModelLayerLocation pSkeletonLayer, ModelLayerLocation pInnerModelLayer, ModelLayerLocation pOuterModelLayer) {
        // Mantenemos la estructura del constructor actual y añadimos nuestra capa
        this(pContext, pInnerModelLayer, pOuterModelLayer, new SkeletonModel<>(pContext.bakeLayer(pSkeletonLayer)));
    }

    public SpyglassSkeletonRenderer(EntityRendererProvider.Context pContext, ModelLayerLocation pSkeletonLayer, ModelLayerLocation pInnerModelLayer, SkeletonModel<T> pModel) {
        super(pContext, pModel, 0.5F);
        // Agregamos la capa de armadura existente
        this.addLayer(
                new HumanoidArmorLayer<>(
                        this, new SkeletonModel(pContext.bakeLayer(pSkeletonLayer)), new SkeletonModel(pContext.bakeLayer(pInnerModelLayer)), pContext.getModelManager()
                )
        );
        // ¡Aquí es donde añadimos la capa para los catalejos!
        this.addLayer(new DoubleSpyGlassLayer<>(this));
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(T pEntity) {
        return SKELETON_LOCATION;
    }

    protected boolean isShaking(T pEntity) {
        return pEntity.isShaking();
    }

}
