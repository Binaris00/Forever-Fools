package com.binaris.forever_fools.client.renderer;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.client.model.PotatoZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import org.jetbrains.annotations.NotNull;

public class PotatoZombieRenderer extends AbstractZombieRenderer<Zombie, PotatoZombieModel<Zombie>> {
    public PotatoZombieRenderer(EntityRendererProvider.Context context) {
        this(context, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
    }

    public PotatoZombieRenderer(EntityRendererProvider.Context context, ModelLayerLocation modelLayerLocation, ModelLayerLocation modelLayerLocation2, ModelLayerLocation modelLayerLocation3) {
        super(context, new PotatoZombieModel<>(context.bakeLayer(modelLayerLocation)), new PotatoZombieModel<>(context.bakeLayer(modelLayerLocation2)),
                new PotatoZombieModel<>(context.bakeLayer(modelLayerLocation3)));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Zombie zombie) {
        return FFCommonMod.id("textures/entity/poisonous_potato_zombie.png");
    }
}
