package com.binaris.forever_fools.mixin.spawn;


import com.binaris.forever_fools.content.entity.ToxifinSlab;
import com.binaris.forever_fools.registry.FFEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
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
public class ToxifinSpawnMixin {
    @Unique
    private static BlockPattern ZOMBIE_PATTERN;
    @Unique private static final Predicate<BlockState> SPONGE_BLOCK_PREDICATE = state -> state != null && state.is(Blocks.SPONGE);
    @Unique private static final Predicate<BlockState> PRISMARINE_PREDICATE = state -> state != null && state.is(Blocks.PRISMARINE_BRICK_SLAB);

    @Unique
    private static BlockPattern foreverFools$getOrCreateZombiePattern() {
        if (ZOMBIE_PATTERN == null) {
            ZOMBIE_PATTERN = BlockPatternBuilder.start()
                    .aisle("#", "~")
                    .where('#', BlockInWorld.hasState(PRISMARINE_PREDICATE))
                    .where('~', BlockInWorld.hasState(SPONGE_BLOCK_PREDICATE))
                    .build();
        }
        return ZOMBIE_PATTERN;
    }

    @Inject(method = "onPlace", at = @At("RETURN"))
    private void onBlockPlaced(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving, CallbackInfo ci) {
        if (level.isClientSide) return;
        BlockPattern.BlockPatternMatch match = foreverFools$getOrCreateZombiePattern().find(level, pos.below());

        if (match != null) {
            ToxifinSlab toxifinSlab = FFEntityRegistry.TOXIFIN.create(level);
            if (toxifinSlab != null) {
                BlockPos spawnPos = match.getBlock(0, 0, 0).getPos();
                toxifinSlab.moveTo(spawnPos.getX() + 0.5, spawnPos.getY() + 0.05, spawnPos.getZ() + 0.5, 0.0F, 0.0F);
                level.addFreshEntity(toxifinSlab);
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
