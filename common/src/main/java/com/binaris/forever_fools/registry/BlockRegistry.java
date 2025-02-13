package com.binaris.forever_fools.registry;

import com.binaris.forever_fools.util.RegisterFunction;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Map;

public class BlockRegistry {
    public static final Map<DyeColor, Block> POTATO_PEEL_BLOCKS = Map.of();

    public static final Block PEELGRASS_BLOCK = new Block(BlockBehaviour.Properties.of());
    public static final Block CORRUPTED_PEELGRASS_BLOCK = new Block(BlockBehaviour.Properties.of());
    public static final Block POISONOUS_POTATO_BLOCK = new Block(BlockBehaviour.Properties.of());

    public static void registerBlocks(RegisterFunction<Item> function) {

        ImmutableBiMap.Builder builder = ImmutableBiMap.builder();
        for(DyeColor dyeColor : DyeColor.values()){
            String string2 = dyeColor == DyeColor.WHITE ? "potato_peels" : dyeColor.getName() + "_potato_peels";
        }
    }

}
