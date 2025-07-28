package com.binaris.forever_fools.mixin.features.passive_mobs;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends Monster {
    @Shadow private boolean canBreakDoors;

    protected ZombieMixin(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Overwrite
    public void addBehaviourGoals(){
        Predicate<LivingEntity> goodZombiePredicate = (target) ->
                !this.hasCustomName() || !this.getCustomName().getString().equals("steve");
        Zombie zombie = (Zombie) (Object) this;

        this.goalSelector.addGoal(2, new ZombieAttackGoal(zombie, 1.0, false));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(zombie, 1.0, true, 4, this::fool$canBreak));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(zombie, 1.0));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(zombie, new Class[0])).setAlertOthers(new Class[]{ZombifiedPiglin.class}));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(zombie, Player.class, true, goodZombiePredicate));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(zombie, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(zombie, IronGolem.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(zombie, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }

    @Inject(method = "isSunSensitive", at = @At("RETURN"), cancellable = true)
    public void FOOL$isSunSensitive(CallbackInfoReturnable<Boolean> cir) {
        Zombie zombie = (Zombie) (Object) this;
        if(zombie.hasCustomName() && zombie.getCustomName().getString().equals("steve")) cir.setReturnValue(false);
    }

    @Unique
    public boolean fool$canBreak() {
        return this.canBreakDoors;
    }
}
