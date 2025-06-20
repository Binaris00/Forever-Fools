package com.binaris.forever_fools.content.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.NodeEvaluator;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MoonCow extends Cow {
    public MoonCow(EntityType<? extends MoonCow> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new MoonCow.MoonWalkControl(this);
    }

//    public static boolean checkMoonCowSpawnRules(EntityType<? extends Animal> entityType, LevelAccessor levelAccessor,
//                                                 MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
//        return levelAccessor.getBlockState(blockPos.below()).is(Blocks.CHEESE);
//    }

    @Override
    public float getWalkTargetValue(BlockPos blockPos, LevelReader levelReader) {
        // TODO CHEESE
        return levelReader.getBlockState(blockPos.below()).is(Blocks.END_STONE) ? 10.0F : super.getWalkTargetValue(blockPos, levelReader);
    }


    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor pLevel, @NotNull DifficultyInstance pDifficulty, @NotNull
    MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Blocks.GLASS));
        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
    }

    @Nullable
    @Override
    public Cow getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        Cow cow = (Cow)this.getType().create(serverLevel);
        cow.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Blocks.GLASS));
        return cow;
    }

    static class MoonWalkControl extends MoveControl {
        public MoonWalkControl(Mob mob) {
            super(mob);
        }

        @Override
        public void tick() {
            MoveControl.Operation operation = this.operation;
            anotherTick();
            if (operation == MoveControl.Operation.MOVE_TO || operation == MoveControl.Operation.JUMPING) {
                this.mob.setZza(-this.mob.getSpeed());
            }
        }

        // Original MoveControl just with the MoonWalk fix
        public void anotherTick(){
            float f9;
            if (this.operation == MoveControl.Operation.STRAFE) {
                float f = (float)this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED);
                float f1 = (float)this.speedModifier * f;
                float f2 = this.strafeForwards;
                float f3 = this.strafeRight;
                float f4 = Mth.sqrt(f2 * f2 + f3 * f3);
                if (f4 < 1.0F) {
                    f4 = 1.0F;
                }

                f4 = f1 / f4;
                f2 *= f4;
                f3 *= f4;
                float f5 = Mth.sin(this.mob.getYRot() * 0.017453292F);
                float f6 = Mth.cos(this.mob.getYRot() * 0.017453292F);
                float f7 = f2 * f6 - f3 * f5;
                f9 = f3 * f6 + f2 * f5;
                if (!this.isWalkable(f7, f9)) {
                    this.strafeForwards = 1.0F;
                    this.strafeRight = 0.0F;
                }

                this.mob.setSpeed(f1);
                this.mob.setZza(this.strafeForwards);
                this.mob.setXxa(this.strafeRight);
                this.operation = MoveControl.Operation.WAIT;
            } else if (this.operation == MoveControl.Operation.MOVE_TO) {
                this.operation = MoveControl.Operation.WAIT;
                double d0 = this.wantedX - this.mob.getX();
                double d1 = this.wantedZ - this.mob.getZ();
                double d2 = this.wantedY - this.mob.getY();
                double d3 = d0 * d0 + d2 * d2 + d1 * d1;
                if (d3 < 2.500000277905201E-7) {
                    this.mob.setZza(0.0F);
                    return;
                }


                f9 = (float)(((Mth.atan2(d1, d0) * 180.0 / 3.1415927410125732) - 90.0F)) - 180;
                this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f9, 90.0F));
                this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                BlockPos blockpos = this.mob.blockPosition();
                BlockState blockstate = this.mob.level().getBlockState(blockpos);
                VoxelShape voxelshape = blockstate.getCollisionShape(this.mob.level(), blockpos);
                if (d2 > (double)this.mob.maxUpStep() && d0 * d0 + d1 * d1 < (double)Math.max(1.0F, this.mob.getBbWidth()) || !voxelshape.isEmpty() && this.mob.getY() < voxelshape.max(Direction.Axis.Y) + (double)blockpos.getY() && !blockstate.is(BlockTags.DOORS) && !blockstate.is(BlockTags.FENCES)) {
                    this.mob.getJumpControl().jump();
                    this.operation = MoveControl.Operation.JUMPING;
                }
            } else if (this.operation == MoveControl.Operation.JUMPING) {
                this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                if (this.mob.onGround()) {
                    this.operation = MoveControl.Operation.WAIT;
                }
            } else {
                this.mob.setZza(0.0F);
            }
        }

        public boolean isWalkable(float pRelativeX, float pRelativeZ) {
            PathNavigation pathnavigation = this.mob.getNavigation();
            NodeEvaluator nodeevaluator = pathnavigation.getNodeEvaluator();
            return nodeevaluator.getPathType(this.mob, BlockPos.containing(this.mob.getX() + (double) pRelativeX, (double) this.mob.getBlockY(), this.mob.getZ() + (double) pRelativeZ)) == PathType.WALKABLE;
        }
    }
}