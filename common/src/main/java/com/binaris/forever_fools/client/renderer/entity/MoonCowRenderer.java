package com.binaris.forever_fools.client.renderer.entity;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.client.model.entity.MoonCowModel;
import com.binaris.forever_fools.client.renderer.entity.layer.MoonCowHeadLayer;
import com.binaris.forever_fools.content.entity.MoonCow;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MoonCowRenderer extends MobRenderer<MoonCow, MoonCowModel> {
    public MoonCowRenderer(EntityRendererProvider.Context context) {
        super(context, new MoonCowModel(context.bakeLayer(MoonCowModel.LAYER_LOCATION)), 0.7F);
        this.addLayer(new MoonCowHeadLayer<>(this, context.getItemInHandRenderer()));
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull MoonCow moonCow) {
        return FFCommonMod.id("textures/entity/moon_cow.png");
    }
}