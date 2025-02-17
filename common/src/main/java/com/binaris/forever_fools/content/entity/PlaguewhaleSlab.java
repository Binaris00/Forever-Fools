package com.binaris.forever_fools.content.entity;

import com.binaris.forever_fools.registry.FFSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PlaguewhaleSlab extends ToxifinSlab {
    public PlaguewhaleSlab(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPersistenceRequired();
        if (this.randomStrollGoal != null) {
            this.randomStrollGoal.setInterval(400);
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return ToxifinSlab.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.ATTACK_DAMAGE, 8.0).add(Attributes.MAX_HEALTH, 80.0);
    }

    @Override
    public int getAttackDuration() {
        return 60;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isInWaterOrBubble() ? FFSoundEvents.PLAGUEWHALE_AMBIENT : FFSoundEvents.PLAGUEWHALE_AMBIENT_LAND;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
        return this.isInWaterOrBubble() ? FFSoundEvents.PLAGUEWHALE_HURT : FFSoundEvents.PLAGUEWHALE_HURT_LAND;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return this.isInWaterOrBubble() ? FFSoundEvents.PLAUGEWHALE_DEATH : FFSoundEvents.PLAGUEWHALE_DEATH_LAND;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return FFSoundEvents.PLAGUEWHALE_FLOP;
    }

    @Override
    protected double slabStackRidingOffset() {
        return -0.294;
    }
}
