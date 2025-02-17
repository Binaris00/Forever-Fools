package com.binaris.forever_fools;

import com.binaris.forever_fools.registry.FFNeoForgeCreativeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(FFCommonMod.MOD_ID)
public class FFNeoForge {
    public FFNeoForge(IEventBus eventBus) {
        FFCommonMod.init();

        FFNeoForgeCreativeTabs.CREATIVE_TABS.register(eventBus);
    }
}