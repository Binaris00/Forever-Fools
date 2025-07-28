package com.binaris.forever_fools.mixin;

import com.binaris.forever_fools.FFCommonMod;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class GhastMixin extends LivingEntity{

    protected GhastMixin(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Shadow @Nullable public abstract LivingEntity getControllingPassenger();


    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void makeGhastRideable(Player pPlayer, InteractionHand pHand, CallbackInfoReturnable<InteractionResult> cir) {
        Mob mob = (Mob) (Object) this;
        if(!(mob instanceof Ghast)) return;

        if (!pPlayer.isCrouching() && !this.isPassenger()) {
            if(this.hasCustomName() && mob.getCustomName().getString().equals("rideable ghast")){
                if (this.canAddPassenger(pPlayer)) {
                    pPlayer.startRiding(mob);
                    cir.setReturnValue(InteractionResult.SUCCESS);
                }
            }
        }
    }

    @Inject(method = "getTarget", at = @At("HEAD"), cancellable = true)
    private void makeGhastTargetable(CallbackInfoReturnable<LivingEntity> cir) {
        Mob mob = (Mob) (Object) this;

        if (mob instanceof Ghast && mob.hasCustomName() && mob.getCustomName().getString().equals("rideable ghast")) {
            cir.setReturnValue(null);
        }
    }
}
