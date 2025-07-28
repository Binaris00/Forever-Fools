package com.binaris.forever_fools.mixin.features.passive_mobs;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Slime.class)
public abstract class SlimeMixin extends Mob {
    protected SlimeMixin(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(method = "dealDamage", at = @At("HEAD"), cancellable = true)
    public void fools$deal_damage(LivingEntity pLivingEntity, CallbackInfo ci){
        Slime mob = (Slime) (Object) this;

        if(mob instanceof Slime slime && slime.hasCustomName() && slime.getCustomName().getString().equals("cute slimo")) {
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, 0));
            ci.cancel();
        }

        if(mob instanceof MagmaCube magmaCube && magmaCube.hasCustomName() && magmaCube.getCustomName().getString().equals("cute")){
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, 0));
            ci.cancel();
        }
    }
}
