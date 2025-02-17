package com.binaris.forever_fools;

import com.binaris.forever_fools.client.FFCommonClientMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class FFFabricClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FFCommonClientMod.createRenderers(EntityRendererRegistry::register);
        FFCommonClientMod.createEntityLayers((layer, supplier) -> EntityModelLayerRegistry.registerModelLayer(layer, supplier::get));
    }
}
