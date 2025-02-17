package com.binaris.forever_fools.registry;

import com.binaris.forever_fools.FFCommonMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class FFForgeCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FFCommonMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> POTATO_TAB = CREATIVE_MODE_TABS.register("potato_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.POISONOUS_POTATO))
                    .title(Component.translatable("creativetab.forever_fools.potato_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(new ItemStack(Items.POISONOUS_POTATO));
                    }).build());
}
