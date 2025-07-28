package com.binaris.forever_fools.mixin.features.pink_wither;

import com.binaris.forever_fools.registry.FFEntityRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitherSkull.class)
public abstract class WitherSkullMixin {
    @Unique
    WitherSkull skull = (WitherSkull) (Object) this;

    @Inject(method = "onHitEntity", at = @At("HEAD"), cancellable = true)
    public void fool$onHit(EntityHitResult pResult, CallbackInfo ci){
        if(skull.getOwner() == null) return;

        if(skull.getOwner().getType().equals(FFEntityRegistry.PINK_WITHER)){
            ci.cancel();
        }
    }

    @Inject(method = "onHit", at = @At("HEAD"), cancellable = true)
    public void fool$onHit(HitResult pResult, CallbackInfo ci){
        if(skull.getOwner() == null) return;

        if(skull.getOwner().getType().equals(FFEntityRegistry.PINK_WITHER)){
            if(pResult instanceof BlockHitResult blockHitResult){
                BlockState blockstate = skull.level().getBlockState(blockHitResult.getBlockPos());
                Block var5 = blockstate.getBlock();
                if (var5 instanceof BonemealableBlock bonemealableblock) {
                    if (bonemealableblock.isValidBonemealTarget(skull.level(), blockHitResult.getBlockPos(), blockstate)) {
                        if (skull.level() instanceof ServerLevel) {
                            if (bonemealableblock.isBonemealSuccess(skull.level(), skull.level().random, blockHitResult.getBlockPos(), blockstate)) {
                                bonemealableblock.performBonemeal((ServerLevel)skull.level(), skull.level().random, blockHitResult.getBlockPos(), blockstate);
                            }
                        }
                    }
                }
            }
            ci.cancel();
        }
    }
}
