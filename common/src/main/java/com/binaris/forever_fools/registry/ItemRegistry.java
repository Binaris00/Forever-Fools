package com.binaris.forever_fools.registry;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.content.item.PotatoPeelItem;
import com.binaris.forever_fools.util.RegisterFunction;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

import java.util.Map;

public final class ItemRegistry {
    public static final Item AMBER_GEM = new Item(new Item.Properties());
    public static final Item TOXIC_RESIN = new Item(new Item.Properties());
    public static final Item DENT = new Item(new Item.Properties());

    public static ImmutableBiMap<DyeColor, Item> POTATOPEELS_ITEMS;
    public static Map<DyeColor, Item> POTATOPEELS_BLOCKS;

    public static void registerItems(RegisterFunction<Item> function) {
        function.register(BuiltInRegistries.ITEM, FFCommonMod.id("amber_gem"), AMBER_GEM);
        function.register(BuiltInRegistries.ITEM, FFCommonMod.id("toxic_resin"), TOXIC_RESIN);
        function.register(BuiltInRegistries.ITEM, FFCommonMod.id("dent"), DENT);

        ImmutableBiMap.Builder builder = ImmutableBiMap.builder();
        for (DyeColor dyeColor : DyeColor.values()) {
            //String string = "item.minecraft.potato_peels." + dyeColor.getName();
            String string2 = dyeColor == DyeColor.WHITE ? "potato_peels" : dyeColor.getName() + "_potato_peels";
            Item potatoPeel = new PotatoPeelItem(new Item.Properties(), dyeColor);
            function.register(BuiltInRegistries.ITEM, FFCommonMod.id(string2), potatoPeel);
            builder.put(dyeColor, potatoPeel);
        }

//        ImmutableMap.Builder<DyeColor, Item> builder2 = ImmutableMap.builder();
//        for (Map.Entry entry : BlockRegistry.POTATO_PEEL_BLOCKS.entrySet()) {
//            builder2.put((DyeColor) entry.getKey(), Items.register((Block) entry.getValue()));
//        }
//        POTATOPEELS_BLOCKS = builder2.build();
    }
}
