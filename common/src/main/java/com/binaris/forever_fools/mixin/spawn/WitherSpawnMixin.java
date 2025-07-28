package com.binaris.forever_fools.mixin.spawn;

import com.binaris.forever_fools.content.entity.MegaSpud;
import com.binaris.forever_fools.registry.FFEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(BlockBehaviour.class)
public abstract class WitherSpawnMixin {
    @Unique private static BlockPattern ZOMBIE_PATTERN;
    @Unique private static final Predicate<BlockState> WOOL_BLOCK_PREDICATE = p_51396_ -> p_51396_ != null
            && p_51396_.is(Blocks.PINK_WOOL);
    @Unique private static final Predicate<BlockState> SKULL_BLOCK_PREDICATE = p_51396_ -> p_51396_ != null
            && p_51396_.is(Blocks.WITHER_SKELETON_SKULL);

    @Unique
    private static BlockPattern foreverFools$getOrCreateZombiePattern() {
        if (ZOMBIE_PATTERN == null) {
            ZOMBIE_PATTERN = BlockPatternBuilder.start()
                    .aisle("#", "~") // # = skull (top), ~ = pink wool (bottom)
                    .where('#', BlockInWorld.hasState(SKULL_BLOCK_PREDICATE))
                    .where('~', BlockInWorld.hasState(WOOL_BLOCK_PREDICATE))
                    .build();
        }
        return ZOMBIE_PATTERN;
    }

    @Inject(method = "onPlace", at = @At("RETURN"))
    private void onBlockPlaced(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving, CallbackInfo ci) {
        if (level.isClientSide) return;
        BlockPattern.BlockPatternMatch match = foreverFools$getOrCreateZombiePattern().find(level, pos.below());

        if (match != null) {
            LivingEntity wither = FFEntityRegistry.PINK_WITHER.create(level);
            if (wither != null) {
                BlockPos spawnPos = match.getBlock(0, 0, 0).getPos();
                wither.moveTo(spawnPos.getX() + 0.5, spawnPos.getY() + 0.05, spawnPos.getZ() + 0.5, 0.0F, 0.0F);
                level.addFreshEntity(wither);
                foreverFools$clearPatternBlocks(level, match);
            }
        }
    }

    @Unique
    private static void foreverFools$clearPatternBlocks(Level pLevel, BlockPattern.BlockPatternMatch pPatternMatch) {
        for (int i = 0; i < pPatternMatch.getWidth(); i++) {
            for (int j = 0; j < pPatternMatch.getHeight(); j++) {
                BlockInWorld blockinworld = pPatternMatch.getBlock(i, j, 0);
                pLevel.setBlock(blockinworld.getPos(), Blocks.AIR.defaultBlockState(), 2);
                pLevel.levelEvent(2001, blockinworld.getPos(), Block.getId(blockinworld.getState()));
            }
        }
    }
}