package com.binaris.forever_fools;

import com.binaris.forever_fools.client.FFCommonClientMod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class FFNeoForgeClientEvents {
    @EventBusSubscriber(modid = FFCommonMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModBusEvents {
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            FFCommonClientMod.createRenderers(event::registerEntityRenderer);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            FFCommonClientMod.createEntityLayers(event::registerLayerDefinition);
        }
    }
}
