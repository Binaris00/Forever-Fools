package com.binaris.forever_fools.content.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class PotatoPeelItem extends Item {
    public static final DyeColor field_50703 = DyeColor.LIME;
    private final DyeColor field_50704;

    public PotatoPeelItem(Properties properties, DyeColor dyeColor) {
        super(properties);
        this.field_50704 = dyeColor;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos blockPos;
        Level level;
        BlockState state;

        // TODO
        // && (blockState = (world = context.getWorld()).getBlockState(blockPos = context.getBlockPos())).isOf(Blocks.TERREDEPOMME) && context.getSide() == Direction.UP
        if(this.field_50704 == field_50703){
            Player player = context.getPlayer();
            context.getItemInHand().shrink(1);
//            world.playSound(playerEntity, blockPos, SoundEvents.BLOCK_PEELGRASS_BLOCK_PLACE, SoundCategory.BLOCKS, 1.0f, world.random.method_58814(0.9f, 1.1f));
//            world.setBlockState(blockPos, Blocks.PEELGRASS_BLOCK.getDefaultState());
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
