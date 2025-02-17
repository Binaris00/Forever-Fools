package com.binaris.forever_fools.registry;

import com.binaris.forever_fools.FFCommonMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class FFFabricCreativeTabs {
    public static final ResourceKey<CreativeModeTab> POTATO_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB,
            FFCommonMod.id("potato_tab"));

    public static void registerTabs() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, POTATO_TAB, FabricItemGroup.builder()
                .title(Component.translatable("creativetab.forever_fools.potato_tab"))
                .icon(() -> new ItemStack(Items.POISONOUS_POTATO))
                .displayItems((displayContext, entries) -> {
                    entries.accept(new ItemStack(Items.POISONOUS_POTATO));
                    entries.accept(FFItemRegistry.DENT.getDefaultInstance());
                    entries.accept(FFItemRegistry.AMBER_GEM.getDefaultInstance());
                    entries.accept(FFItemRegistry.TOXIC_RESIN.getDefaultInstance());
                    entries.accept(FFItemRegistry.POISONOUS_POTATO_CHIPS.getDefaultInstance());
                    entries.accept(FFItemRegistry.POISONOUS_POTATO_FRIES.getDefaultInstance());
                    entries.accept(FFItemRegistry.POISONOUS_POTATO_STICKS.getDefaultInstance());
                    entries.accept(FFItemRegistry.POISONOUS_POTATO_SLICES.getDefaultInstance());
                    entries.accept(FFItemRegistry.HOT_POTATO.getDefaultInstance());
                    entries.accept(FFItemRegistry.HASH_BROWNS.getDefaultInstance());

                    FFItemRegistry.POTATOPEELS_ITEMS.forEach((color, item) -> {
                        entries.accept(item.getDefaultInstance());
                    });
                })        .build());
    }
}
