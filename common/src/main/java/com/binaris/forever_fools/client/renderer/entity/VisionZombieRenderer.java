package com.binaris.forever_fools.client.renderer.entity;

import com.binaris.forever_fools.client.model.entity.VisionCreeperModel;
import com.binaris.forever_fools.client.model.entity.VisionZombieModel;
import com.binaris.forever_fools.content.entity.VisionBaseEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VisionZombieRenderer extends GeoEntityRenderer<VisionBaseEntity> {
    public VisionZombieRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VisionZombieModel());
    }
}
