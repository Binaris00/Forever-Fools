package com.binaris.forever_fools.client.model.entity;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.content.entity.VisionBaseEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class NerdCreeperModel extends DefaultedEntityGeoModel<VisionBaseEntity> {
    public NerdCreeperModel() {
        super(FFCommonMod.id("nerd_creeper"), true);
    }
}
