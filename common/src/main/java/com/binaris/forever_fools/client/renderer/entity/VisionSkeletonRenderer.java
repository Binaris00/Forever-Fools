package com.binaris.forever_fools.client.renderer.entity;

import com.binaris.forever_fools.client.model.entity.VisionCreeperModel;
import com.binaris.forever_fools.client.model.entity.VisionSkeletonModel;
import com.binaris.forever_fools.content.entity.VisionBaseEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VisionSkeletonRenderer extends GeoEntityRenderer<VisionBaseEntity> {
    public VisionSkeletonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VisionSkeletonModel());
    }
}
