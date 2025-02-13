package com.binaris.forever_fools;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FFCommonMod {

    public static final String MOD_ID = "forever_fools";
    public static final String MOD_NAME = "Forever Fools";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {

    }

    public static ResourceLocation id(String name){
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}