package com.binaris.forever_fools.mixin.features.passive_mobs;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Witch.class)
public abstract class WitchMixin extends Raider implements RangedAttackMob {
    protected WitchMixin(EntityType<? extends Raider> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(method = "performRangedAttack", at = @At("HEAD"), cancellable = true)
    public void FOOLSperformRangedAttack(LivingEntity pTarget, float pDistanceFactor, CallbackInfo ci) {
        Witch witch = (Witch) (Object) this;

        if(witch.hasCustomName() && witch.getCustomName().getString().equals("beauty") && !witch.isDrinkingPotion()){
            Vec3 vec3 = pTarget.getDeltaMovement();
            double d0 = pTarget.getX() + vec3.x - this.getX();
            double d1 = pTarget.getEyeY() - 1.100000023841858 - this.getY();
            double d2 = pTarget.getZ() + vec3.z - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            Holder<Potion> holder = Potions.HEALING;
            if (pTarget instanceof Raider) {
                if (pTarget.getHealth() <= 4.0F) {
                    holder = Potions.HEALING;
                } else {
                    holder = Potions.REGENERATION;
                }

                this.setTarget((LivingEntity)null);
            } else if (d3 >= 8.0 && !pTarget.hasEffect(MobEffects.MOVEMENT_SPEED)) {
                holder = Potions.STRENGTH;
            } else if (pTarget.getHealth() >= 8.0F && !pTarget.hasEffect(MobEffects.REGENERATION)) {
                holder = Potions.REGENERATION;
            } else if (d3 <= 3.0 && !pTarget.hasEffect(MobEffects.DIG_SPEED) && this.random.nextFloat() < 0.25F) {
                holder = Potions.STRENGTH;
            }

            ThrownPotion thrownpotion = new ThrownPotion(this.level(), this);
            thrownpotion.setItem(PotionContents.createItemStack(Items.SPLASH_POTION, holder));
            thrownpotion.setXRot(thrownpotion.getXRot() - -20.0F);
            thrownpotion.shoot(d0, d1 + d3 * 0.2, d2, 0.75F, 8.0F);
            if (!this.isSilent()) {
                this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITCH_THROW, this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
            }

            this.level().addFreshEntity(thrownpotion);

            ci.cancel();
        }
    }
}
