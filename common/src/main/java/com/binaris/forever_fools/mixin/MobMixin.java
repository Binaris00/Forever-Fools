package com.binaris.forever_fools.mixin;

import com.binaris.forever_fools.FFCommonMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Slime;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class MobMixin {

    @Inject(method = "doHurtTarget", at = @At("HEAD"), cancellable = true)
    public void fools$hurtTarget(Entity pEntity, CallbackInfoReturnable<Boolean> cir){
        Mob mob = (Mob) (Object) this;
        if(mob instanceof Slime && mob.hasCustomName() && (mob.getCustomName().getString().equals("cute slimo"))) {
            if (pEntity instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, 0));
            }
            cir.cancel();
        }
    }
}
