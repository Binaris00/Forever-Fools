package com.binaris.forever_fools.mixin.features.passive_mobs;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Predicate;

@Mixin(AbstractSkeleton.class)
public abstract class AbstractSkeletonMixin extends Mob {
    protected AbstractSkeletonMixin(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Overwrite
    public void registerGoals() {
        Predicate<LivingEntity> goodZombiePredicate = (target) ->
                !this.hasCustomName() || !this.getCustomName().getString().equals("boneless");

        AbstractSkeleton skeleton = (AbstractSkeleton) (Object) this;

        this.goalSelector.addGoal(2, new RestrictSunGoal(skeleton));
        this.goalSelector.addGoal(3, new FleeSunGoal(skeleton, 1.0));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(skeleton, Wolf.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(skeleton, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(skeleton, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(skeleton));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(skeleton, new Class[0]));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(skeleton, Player.class, true, goodZombiePredicate));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(skeleton, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(skeleton, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }
}
