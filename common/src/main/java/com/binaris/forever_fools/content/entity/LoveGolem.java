package com.binaris.forever_fools.content.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.DefendVillageTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class LoveGolem extends IronGolem {
    public LoveGolem(EntityType<? extends IronGolem> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        //this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, true));
        //this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9, 32.0F));
        this.goalSelector.addGoal(2, new MoveBackToVillageGoal(this, 0.6, false));
        this.goalSelector.addGoal(4, new GolemRandomStrollInVillageGoal(this, 0.6));
        this.goalSelector.addGoal(5, new OfferFlowerGoal(this));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        //this.targetSelector.addGoal(1, new DefendVillageTargetGoal(this));
        //this.targetSelector.addGoal(2, new HurtByTargetGoal(this, new Class[0]));
        //this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class, 10, true, false, this::isAngryAt));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Mob.class, 5, false, false, (p_28879_) -> {
//            return p_28879_ instanceof Enemy && !(p_28879_ instanceof Creeper);
//        }));
        //this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal(this, false));
    }
}
