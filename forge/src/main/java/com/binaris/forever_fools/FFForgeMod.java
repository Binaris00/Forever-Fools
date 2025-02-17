package com.binaris.forever_fools;

import com.binaris.forever_fools.registry.FFForgeCreativeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FFCommonMod.MOD_ID)
public class FFForgeMod {

    public FFForgeMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FFCommonMod.init();
        FFForgeCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
    }
}