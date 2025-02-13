package com.binaris.forever_fools;

import com.binaris.forever_fools.registry.EntityRegistry;
import com.binaris.forever_fools.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;

public class FFFabricMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        FFCommonMod.init();

        ItemRegistry.registerItems(Registry::register);
        EntityRegistry.registerEntityTypes(Registry::register);
        EntityRegistry.registerAttributes(FabricDefaultAttributeRegistry::register);
    }
}
