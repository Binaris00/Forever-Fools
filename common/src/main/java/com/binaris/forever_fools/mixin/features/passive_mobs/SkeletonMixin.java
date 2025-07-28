package com.binaris.forever_fools.mixin.features.passive_mobs;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Skeleton.class)
public abstract class SkeletonMixin extends Monster {
    protected SkeletonMixin(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    @Override
    protected boolean isSunBurnTick() {
        Skeleton skeleton = (Skeleton) (Object) this;
        if(skeleton.hasCustomName() && skeleton.getCustomName().getString().equals("boneless")) return false;

        return super.isSunBurnTick();
    }
}
