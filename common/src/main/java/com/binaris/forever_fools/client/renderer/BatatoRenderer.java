package com.binaris.forever_fools.client.renderer;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.client.model.BatatoModel;
import com.binaris.forever_fools.content.entity.Batato;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BatatoRenderer extends MobRenderer<Batato, BatatoModel> {
    public BatatoRenderer(EntityRendererProvider.Context context) {
        super(context, new BatatoModel(context.bakeLayer(BatatoModel.LAYER_LOCATION)), 0.25F);
    }

    public ResourceLocation getTextureLocation(Batato batato) {
        return FFCommonMod.id("textures/entity/batato.png");
    }

}
