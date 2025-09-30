package com.binaris.forever_fools.client.renderer.entity;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.client.renderer.entity.layer.SheepPotatoFurLayer;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Sheep;
import org.jetbrains.annotations.NotNull;

public class SheepPotatoRenderer extends MobRenderer<Sheep, SheepModel<Sheep>> {
    private static final ResourceLocation SHEEP_LOCATION = FFCommonMod.id("textures/entity/sheep/sheep.png");

    public SheepPotatoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SheepModel<>(pContext.bakeLayer(ModelLayers.SHEEP)), 0.7F);
        this.addLayer(new SheepPotatoFurLayer(this, pContext.getModelSet()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Sheep sheep) {
        return SHEEP_LOCATION;
    }
}
