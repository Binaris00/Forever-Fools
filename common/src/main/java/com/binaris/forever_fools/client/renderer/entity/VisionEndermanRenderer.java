package com.binaris.forever_fools.client.renderer.entity;

import com.binaris.forever_fools.client.model.entity.NerdCreeperModel;
import com.binaris.forever_fools.client.model.entity.VisionEndermanModel;
import com.binaris.forever_fools.content.entity.VisionBaseEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VisionEndermanRenderer extends GeoEntityRenderer<VisionBaseEntity> {
    public VisionEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VisionEndermanModel());
    }
}
