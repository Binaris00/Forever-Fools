package com.binaris.forever_fools;

import com.binaris.forever_fools.registry.FFEntityRegistry;
import com.binaris.forever_fools.registry.FFFabricCreativeTabs;
import com.binaris.forever_fools.registry.FFItemRegistry;
import com.binaris.forever_fools.registry.FFSoundEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;

public class FFFabricMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        FFCommonMod.init();

        FFItemRegistry.registerItems(Registry::register);
        FFEntityRegistry.registerEntityTypes(Registry::register);
        FFEntityRegistry.registerAttributes(FabricDefaultAttributeRegistry::register);
        FFSoundEvents.registerSoundEvents(Registry::register);
        FFFabricCreativeTabs.registerTabs();
    }
}
