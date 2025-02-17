package com.binaris.forever_fools.registry;

import com.binaris.forever_fools.FFCommonMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FFNeoForgeCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FFCommonMod.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> POTATO_TAB =
            CREATIVE_TABS.register("potato_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.forever_fools.potato_tab"))
                    .icon(Items.POISONOUS_POTATO::getDefaultInstance)
                    .displayItems((parameters, output) -> {
                        output.accept(Items.POISONOUS_POTATO.getDefaultInstance());
                    }).build());
}
