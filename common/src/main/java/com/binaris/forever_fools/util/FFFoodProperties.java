package com.binaris.forever_fools.util;

import net.minecraft.world.food.FoodProperties;

public class FFFoodProperties {

    public static final FoodProperties HOT_POTATO = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6f).alwaysEdible().build();
    public static final FoodProperties STICKS = new FoodProperties.Builder().nutrition(1).saturationModifier(0.6f).build();
    public static final FoodProperties SLICES = new FoodProperties.Builder().nutrition(1).saturationModifier(0.6f).build();
    public static final FoodProperties FRIES = new FoodProperties.Builder().nutrition(10).saturationModifier(0.6f).build();
    public static final FoodProperties CHIPS = new FoodProperties.Builder().nutrition(8).saturationModifier(0.6f).build();
    public static final FoodProperties HASH_BROWNS = new FoodProperties.Builder().nutrition(2).saturationModifier(0.6f).build();
}
