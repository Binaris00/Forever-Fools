package com.binaris.forever_fools;

import com.binaris.forever_fools.registry.EntityRegistry;
import com.binaris.forever_fools.registry.ItemRegistry;
import com.binaris.forever_fools.util.RegisterFunction;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.Consumer;

public class FFNeoForgeEvents {
    @EventBusSubscriber(modid = FFCommonMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class ModBusEvents {

        @SubscribeEvent
        public static void registerContent(RegisterEvent event) {
            if (event.getRegistryKey() == Registries.ITEM) register(event, ItemRegistry::registerItems);
            else if(event.getRegistryKey() == Registries.ENTITY_TYPE) register(event, EntityRegistry::registerEntityTypes);
//            if (event.getRegistryKey() == Registries.ENTITY_TYPE)
//                register(event, RockhoppersEntityTypes::registerEntityTypes);
//            else if (event.getRegistryKey() == Registries.SOUND_EVENT)
//                register(event, RockhoppersSoundEvents::registerSoundEvents);
//            else if (event.getRegistryKey() == Registries.ITEM)
//                register(event, RockhoppersItems::registerItems);
//            else if (event.getRegistryKey() == Registries.BLOCK)
//                register(event, RockhoppersBlocks::registerBlocks);
//            else if (event.getRegistryKey() == Registries.ACTIVITY)
//                register(event, RockhoppersActivities::registerActivities);
//            else if (event.getRegistryKey() == Registries.MEMORY_MODULE_TYPE)
//                register(event, RockhoppersMemoryModuleTypes::registerMemoryModuleTypes);
//            else if (event.getRegistryKey() == Registries.SENSOR_TYPE)
//                register(event, RockhoppersSensorTypes::registerSensorTypes);
//            else if (event.getRegistryKey() == Registries.BLOCK_ENTITY_TYPE)
//                register(event, RockhoppersBlockEntityTypes::registerBlockEntityTypes);
        }

        @SubscribeEvent
        public static void createEntityAttributes(EntityAttributeCreationEvent event) {
            EntityRegistry.registerAttributes(event::put);
        }

        private static <T> void register(RegisterEvent event, Consumer<RegisterFunction<T>> consumer) {
            consumer.accept((registry, id, value) -> event.register(registry.key(), id, () -> value));
        }
    }
}
