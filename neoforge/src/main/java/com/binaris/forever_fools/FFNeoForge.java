package com.binaris.forever_fools;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(FFCommonMod.MOD_ID)
public class FFNeoForge {
    public FFNeoForge(IEventBus eventBus) {
        FFCommonMod.init();
    }
}