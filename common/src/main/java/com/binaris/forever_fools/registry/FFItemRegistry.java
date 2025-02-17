package com.binaris.forever_fools.registry;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.content.item.HotPotatoItem;
import com.binaris.forever_fools.content.item.PotatoPeelItem;
import com.binaris.forever_fools.util.FFFoodProperties;
import com.binaris.forever_fools.util.RegisterFunction;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

import java.util.Map;

public final class FFItemRegistry {
    public static final Item AMBER_GEM = new Item(new Item.Properties());
    public static final Item TOXIC_RESIN = new Item(new Item.Properties());
    public static final Item DENT = new Item(new Item.Properties());

    public static final Item POISONOUS_POTATO_FRIES = new Item(new Item.Properties().food(FFFoodProperties.FRIES));
    public static final Item POISONOUS_POTATO_STICKS = new Item(new Item.Properties().food(FFFoodProperties.STICKS));
    public static final Item POISONOUS_POTATO_SLICES = new Item(new Item.Properties().food(FFFoodProperties.SLICES));
    public static final Item POISONOUS_POTATO_CHIPS = new Item(new Item.Properties().food(FFFoodProperties.CHIPS));
    public static final Item HOT_POTATO = new HotPotatoItem(new Item.Properties().stacksTo(1).fireResistant().food(FFFoodProperties.HOT_POTATO));
    public static final Item HASH_BROWNS = new Item(new Item.Properties().food(FFFoodProperties.HASH_BROWNS));

    public static ImmutableBiMap<DyeColor, Item> POTATOPEELS_ITEMS;
    public static Map<DyeColor, Item> POTATOPEELS_BLOCKS;

    public static void registerItems(RegisterFunction<Item> function) {
        function.register(BuiltInRegistries.ITEM, FFCommonMod.id("amber_gem"), AMBER_GEM);
        function.register(BuiltInRegistries.ITEM, FFCommonMod.id("toxic_resin"), TOXIC_RESIN);
        function.register(BuiltInRegistries.ITEM, FFCommonMod.id("dent"), DENT);
        function.register(BuiltInRegistries.ITEM, FFCommonMod.id("poisonous_potato_fries"), POISONOUS_POTATO_FRIES);
        function.register(BuiltInRegistries.ITEM, FFCommonMod.id("poisonous_potato_slices"), POISONOUS_POTATO_SLICES);
        function.register(BuiltInRegistries.ITEM, FFCommonMod.id("poisonous_potato_sticks"), POISONOUS_POTATO_STICKS);
        function.register(BuiltInRegistries.ITEM, FFCommonMod.id("poisonous_potato_chips"), POISONOUS_POTATO_CHIPS);
        function.register(BuiltInRegistries.ITEM, FFCommonMod.id("hot_potato"), HOT_POTATO);
        function.register(BuiltInRegistries.ITEM, FFCommonMod.id("hash_browns"), HASH_BROWNS);

        ImmutableBiMap.Builder builder = ImmutableBiMap.builder();
        for (DyeColor dyeColor : DyeColor.values()) {
            String string2 = dyeColor == DyeColor.WHITE ? "potato_peels" : dyeColor.getName() + "_potato_peels";
            Item potatoPeel = new PotatoPeelItem(new Item.Properties(), dyeColor);
            function.register(BuiltInRegistries.ITEM, FFCommonMod.id(string2), potatoPeel);
            builder.put(dyeColor, potatoPeel);
        }
        POTATOPEELS_ITEMS = builder.build();
    }
}
