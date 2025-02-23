package com.binaris.forever_fools.client.renderer.layer;

import com.binaris.forever_fools.client.model.MegaSpudModel;
import com.binaris.forever_fools.content.entity.MegaSpud;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class MegaSpudArmorLayer extends EnergySwirlLayer<MegaSpud, MegaSpudModel<MegaSpud>> {
    private final MegaSpudModel<MegaSpud> model;

    public MegaSpudArmorLayer(RenderLayerParent<MegaSpud, MegaSpudModel<MegaSpud>> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
        this.model = new MegaSpudModel<>(entityModelSet.bakeLayer(MegaSpudModel.LAYER_OUTER_LOCATION));
    }

    @Override
    protected float xOffset(float f) {
        return Mth.cos(f * 0.02F) * 3.0F;
    }

    @Override
    protected @NotNull ResourceLocation getTextureLocation() {
        return ResourceLocation.withDefaultNamespace("textures/entity/wither/wither_armor.png");
    }

    @Override
    protected @NotNull EntityModel<MegaSpud> model() {
        return this.model;
    }
}
