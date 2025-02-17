package com.binaris.forever_fools.content.block;

import com.binaris.forever_fools.registry.FFItemRegistry;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class PotatoPeelsBlock
        extends Block {
    //public static final MapCodec<PotatoPeelsBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(PotatoPeelsBlock.propertiesCodec(), ((MapCodec)DyeColor.CODEC.fieldOf("color")).forGetter(potatoPeelsBlock -> potatoPeelsBlock.color)).apply((Applicative<PotatoPeelsBlock, ?>)instance, PotatoPeelsBlock::new));
    public final DyeColor color;

    public PotatoPeelsBlock(Properties settings, DyeColor color) {
        super(settings);
        this.color = color;
    }

//    protected MapCodec<PotatoPeelsBlock> getCodec() {
//        return CODEC;
//    }

    public DyeColor getColor() {
        return this.color;
    }

    public Item getItem() {
        return (Item) FFItemRegistry.POTATOPEELS_ITEMS.get(this.getColor());
    }
}


