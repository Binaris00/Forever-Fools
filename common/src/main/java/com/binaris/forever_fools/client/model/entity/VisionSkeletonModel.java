package com.binaris.forever_fools.client.model.entity;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.content.entity.VisionBaseEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class VisionSkeletonModel extends DefaultedEntityGeoModel<VisionBaseEntity> {
    public VisionSkeletonModel() {
        super(FFCommonMod.id("vision_skeleton"), true);
    }
}
