package com.binaris.forever_fools;

import com.binaris.forever_fools.client.ForeverFoolsClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class FFFabricClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ForeverFoolsClient.createRenderers(EntityRendererRegistry::register);
        //RapscallionsAndRockhoppersClient.createEntityLayers((layer, supplier) -> EntityModelLayerRegistry.registerModelLayer(layer, supplier::get));
        //RockhoppersPackets.registerS2C();
    }
}
