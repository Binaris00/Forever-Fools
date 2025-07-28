package com.binaris.forever_fools.client.renderer.entity;

import com.binaris.forever_fools.client.model.entity.NerdCreeperModel;
import com.binaris.forever_fools.client.model.entity.VisionCreeperModel;
import com.binaris.forever_fools.content.entity.VisionBaseEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NerdCreeperRenderer extends GeoEntityRenderer<VisionBaseEntity> {
    public NerdCreeperRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NerdCreeperModel());
    }
}
