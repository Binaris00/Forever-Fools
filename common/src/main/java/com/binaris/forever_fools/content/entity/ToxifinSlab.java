package com.binaris.forever_fools.content.entity;

import com.binaris.forever_fools.registry.FFSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

// This is basically, a copy-paste from the Guardian code, just with the code cleaned to be used separately
public class ToxifinSlab extends Monster {
    private static final EntityDataAccessor<Boolean> DATA_ID_MOVING = SynchedEntityData.defineId(ToxifinSlab.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_ID_ATTACK_TARGET = SynchedEntityData.defineId(ToxifinSlab.class, EntityDataSerializers.INT);
    private float clientSideTailAnimation;
    private float clientSideTailAnimationO;
    private float clientSideTailAnimationSpeed;
    private float clientSideSpikesAnimation;
    private float clientSideSpikesAnimationO;
    @Nullable
    private LivingEntity clientSideCachedAttackTarget;
    private int clientSideAttackTime;
    private boolean clientSideTouchedGround;
    @Nullable
    protected RandomStrollGoal randomStrollGoal;
    private int tickOutOfWater;
    private int jumpTimer;

    public ToxifinSlab(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.moveControl = new SlabMoveControl(this);
        this.lookControl = new SlabLookControl();
        this.clientSideTailAnimation = this.random.nextFloat();
        this.clientSideTailAnimationO = this.clientSideTailAnimation;
    }

    @Override
    protected void registerGoals() {
        MoveTowardsRestrictionGoal moveTowardsRestrictionGoal = new MoveTowardsRestrictionGoal(this, 1.0);
        this.randomStrollGoal = new RandomStrollGoal(this, 1.0, 80) {
            @Override
            public boolean canUse() {
                return !ToxifinSlab.this.isPassenger() && super.canUse();
            }
        };
        this.goalSelector.addGoal(4, new SlabAttackGoal(this));
        this.goalSelector.addGoal(5, moveTowardsRestrictionGoal);
        this.goalSelector.addGoal(7, this.randomStrollGoal);
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F) {
            @Override
            public boolean canUse() {
                return !ToxifinSlab.this.isPassenger() && super.canUse();
            }
        });
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, ToxifinSlab.class, 12.0F, 0.01F) {
            @Override
            public boolean canUse() {
                return !ToxifinSlab.this.isPassenger() && super.canUse();
            }
        });
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this) {
            @Override
            public boolean canUse() {
                return !ToxifinSlab.this.isPassenger() && super.canUse();
            }
        });
        this.goalSelector
                .addGoal(
                        10,
                        new Goal() {
                            private final TargetingConditions RIDE_TARGET = TargetingConditions.forNonCombat()
                                    .range(3.0)
                                    .selector(livingEntity -> !livingEntity.isVehicle() && !livingEntity.isPassenger() && ToxifinSlab.this.isBuddy(livingEntity));

                            @Override
                            public boolean canUse() {
                                return ToxifinSlab.this.random.nextInt(100) == 0 && (!ToxifinSlab.this.isVehicle());
                            }

                            @Override
                            public void start() {
                                if (ToxifinSlab.this.getVehicle() == null) {
                                    AABB aABB = ToxifinSlab.this.getBoundingBox().inflate(2.0, 2.0, 2.0);
                                    ToxifinSlab Toxifin = ToxifinSlab.this.level()
                                            .getNearestEntity(ToxifinSlab.class, this.RIDE_TARGET,
                                                    ToxifinSlab.this, ToxifinSlab.this.getX(), ToxifinSlab.this.getY(), ToxifinSlab.this.getZ(), aABB);
                                    if (Toxifin != null) {
                                        ToxifinSlab.this.startRiding(Toxifin);
                                    }
                                } else if (ToxifinSlab.this.random.nextInt(10) == 0) {
                                    ToxifinSlab.this.stopRiding();
                                }
                            }
                        }
                );
        this.randomStrollGoal.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        moveTowardsRestrictionGoal.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, new SlabAttackSelector(this)));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.FOLLOW_RANGE, 16.0)
                .add(Attributes.MAX_HEALTH, 30.0);
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        LivingEntity livingEntity = super.getControllingPassenger();
        return this.isBuddy(livingEntity) ? null : livingEntity;
    }

    @Override
    protected void actuallyHurt(@NotNull DamageSource damageSource, float f) {
        super.actuallyHurt(damageSource, f);
        List<Entity> list = this.getPassengers();
        list.forEach(Entity::stopRiding);
        Entity entity = this.getVehicle();
        if (entity != null) {
            this.stopRiding();
            list.forEach(entity2 -> entity2.startRiding(entity, true));
        }
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
        return new WaterBoundPathNavigation(this, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_MOVING, false);
        builder.define(DATA_ID_ATTACK_TARGET, 0);
    }

    public boolean isMoving() {
        return this.entityData.get(DATA_ID_MOVING);
    }

    void setMoving(boolean bl) {
        this.entityData.set(DATA_ID_MOVING, bl);
    }

    public int getAttackDuration() {
        return 80;
    }

    void setActiveAttackTarget(int i) {
        this.entityData.set(DATA_ID_ATTACK_TARGET, i);
    }

    public boolean hasActiveAttackTarget() {
        return this.entityData.get(DATA_ID_ATTACK_TARGET) != 0;
    }

    @Nullable
    public LivingEntity getActiveAttackTarget() {
        if (!this.hasActiveAttackTarget()) {
            return null;
        } else if (this.level().isClientSide) {
            if (this.clientSideCachedAttackTarget != null) {
                return this.clientSideCachedAttackTarget;
            } else {
                Entity entity = this.level().getEntity(this.entityData.get(DATA_ID_ATTACK_TARGET));
                if (entity instanceof LivingEntity) {
                    this.clientSideCachedAttackTarget = (LivingEntity)entity;
                    return this.clientSideCachedAttackTarget;
                } else {
                    return null;
                }
            }
        } else {
            return this.getTarget();
        }
    }

    @Override
    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> entityDataAccessor) {
        super.onSyncedDataUpdated(entityDataAccessor);
        if (DATA_ID_ATTACK_TARGET.equals(entityDataAccessor)) {
            this.clientSideAttackTime = 0;
            this.clientSideCachedAttackTarget = null;
        }
    }

    @Override
    public int getAmbientSoundInterval() {
        return 160;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isInWaterOrBubble() ? FFSoundEvents.TOXIFIN_AMBIENT : FFSoundEvents.TOXIFIN_AMBIENT_LAND;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
        return this.isInWaterOrBubble() ? FFSoundEvents.TOXIFIN_HURT : FFSoundEvents.TOXIFIN_HURT_LAND;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return this.isInWaterOrBubble() ? FFSoundEvents.TOXIFIN_DEATH : FFSoundEvents.TOXIFIN_DEATH_LAND;
    }

    @Override
    protected Entity.@NotNull MovementEmission getMovementEmission() {
        return Entity.MovementEmission.EVENTS;
    }

    @Override
    public float getWalkTargetValue(@NotNull BlockPos blockPos, LevelReader levelReader) {
        return levelReader.getFluidState(blockPos).is(FluidTags.WATER)
                ? 10.0F + levelReader.getPathfindingCostFromLightLevels(blockPos)
                : super.getWalkTargetValue(blockPos, levelReader);
    }

    @Override
    public void aiStep() {
        if (!this.isInWaterOrBubble()) {
            this.tickOutOfWater++;
        } else {
            this.tickOutOfWater = 0;
        }

        if (this.isAlive()) {
            if (this.level().isClientSide) {
                this.clientSideTailAnimationO = this.clientSideTailAnimation;
                if (!this.isInWater()) {
                    Vec3 vec3 = this.getDeltaMovement();
                    this.clientSideTailAnimationSpeed = (float)vec3.length() / 0.5F + 0.1F;

                    if (vec3.y > 0.0 && this.clientSideTouchedGround && !this.isSilent()) {
                        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), this.getFlopSound(), this.getSoundSource(), 1.0F, 1.0F, false);
                    }

                    this.clientSideTouchedGround = vec3.y < 0.0 && this.level().loadedAndEntityCanStandOn(this.blockPosition().below(), this);
                } else if (this.isMoving()) {
                    if (this.clientSideTailAnimationSpeed < 0.5F) {
                        this.clientSideTailAnimationSpeed = 4.0F;
                    } else {
                        this.clientSideTailAnimationSpeed = this.clientSideTailAnimationSpeed + (0.5F - this.clientSideTailAnimationSpeed) * 0.1F;
                    }
                } else {
                    this.clientSideTailAnimationSpeed = this.clientSideTailAnimationSpeed + (0.125F - this.clientSideTailAnimationSpeed) * 0.2F;
                }

                this.clientSideTailAnimation = this.clientSideTailAnimation + this.clientSideTailAnimationSpeed;
                this.clientSideSpikesAnimationO = this.clientSideSpikesAnimation;
                if (!this.isInWaterOrBubble()) {
                    this.clientSideSpikesAnimation = 0.5F - (float)Math.cos((double)this.tickOutOfWater * 0.05 * Math.PI / 2.0) / 2.0F;
                } else if (this.isMoving()) {
                    this.clientSideSpikesAnimation = this.clientSideSpikesAnimation + (0.0F - this.clientSideSpikesAnimation) * 0.25F;
                } else {
                    this.clientSideSpikesAnimation = this.clientSideSpikesAnimation + (1.0F - this.clientSideSpikesAnimation) * 0.06F;
                }

                if (this.isPassenger() && this.getVehicle() instanceof ToxifinSlab slab && slab.getType() == this.getType()) {
                    this.clientSideSpikesAnimation = slab.clientSideSpikesAnimation;
                }

                if (this.isMoving() && this.isInWater()) {
                    Vec3 vec3x = this.getViewVector(0.0F);

                    for (int i = 0; i < 2; i++) {
                        this.level()
                                .addParticle(
                                        ParticleTypes.BUBBLE, this.getRandomX(0.5) - vec3x.x * 1.5, this.getRandomY() - vec3x.y * 1.5, this.getRandomZ(0.5) - vec3x.z * 1.5, 0.0, 0.0, 0.0
                                );
                    }
                }

                if (this.hasActiveAttackTarget()) {
                    if (this.clientSideAttackTime < this.getAttackDuration()) {
                        this.clientSideAttackTime++;
                    }

                    LivingEntity livingEntity = this.getActiveAttackTarget();
                    if (livingEntity != null) {
                        this.getLookControl().setLookAt(livingEntity, 90.0F, 90.0F);
                        this.getLookControl().tick();
                        double d = (double)this.getAttackAnimationScale(0.0F);
                        double e = livingEntity.getX() - this.getX();
                        double f = livingEntity.getY(0.5) - this.getEyeY();
                        double g = livingEntity.getZ() - this.getZ();
                        double h = Math.sqrt(e * e + f * f + g * g);
                        e /= h;
                        f /= h;
                        g /= h;
                        double j = this.random.nextDouble();

                        while (j < h) {
                            j += 1.8 - d + this.random.nextDouble() * (1.7 - d);
                            this.level().addParticle(ParticleTypes.BUBBLE, this.getX() + e * j, this.getEyeY() + f * j, this.getZ() + g * j, 0.0, 0.0, 0.0);
                        }
                    }
                }
            }

            if (!this.level().isClientSide && this.hasActiveAttackTarget()) {
                LivingEntity livingEntity = this.getActiveAttackTarget();
                if (livingEntity != null && livingEntity.getEffect(MobEffects.POISON) == null) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 0), this);
                }
            }

            if (this.isInWaterOrBubble()) {
                this.setAirSupply(300);
            } else if (this.canJump()) {
                this.setDeltaMovement(
                        this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.4F), 0.5, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.4F))
                );
                this.setYRot(this.random.nextFloat() * 360.0F);
                this.setOnGround(false);
                this.hasImpulse = true;
            }

            if (this.hasActiveAttackTarget()) {
                this.setYRot(this.yHeadRot);
            }
        }

        super.aiStep();
    }

    boolean isBuddy(@Nullable Entity entity) {
        return entity instanceof ToxifinSlab;
    }

    private boolean canJump() {
        if (!this.onGround()) {
            return false;
        } else {
            this.jumpTimer--;
            if (this.jumpTimer < 0) {
                this.jumpTimer = this.random.nextInt(40) + 20;
                return true;
            } else {
                return false;
            }
        }
    }

    protected SoundEvent getFlopSound() {
        return FFSoundEvents.TOXIFIN_FLOP;
    }

    public float getTailAnimation(float f) {
        return Mth.lerp(f, this.clientSideTailAnimationO, this.clientSideTailAnimation);
    }

    public float getSpikesAnimation(float f) {
        return Mth.lerp(f, this.clientSideSpikesAnimationO, this.clientSideSpikesAnimation);
    }

    public float getAttackAnimationScale(float f) {
        return ((float)this.clientSideAttackTime + f) / (float)this.getAttackDuration();
    }

    public float getClientSideAttackTime() {
        return (float)this.clientSideAttackTime;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader levelReader) {
        return levelReader.isUnobstructed(this);
    }


    public static boolean checkSlabSpawnRules(
            EntityType<? extends ToxifinSlab> entityType, LevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos,
            RandomSource randomSource
    ) {
        return (randomSource.nextInt(20) == 0 || !levelAccessor.canSeeSkyFromBelowWater(blockPos))
                && levelAccessor.getDifficulty() != Difficulty.PEACEFUL
                && (MobSpawnType.isSpawner(mobSpawnType) || levelAccessor.getFluidState(blockPos).is(FluidTags.WATER))
                && levelAccessor.getFluidState(blockPos.below()).is(FluidTags.WATER);
    }

    @Override
    public boolean hurt(@NotNull DamageSource damageSource, float f) {
        if (this.level().isClientSide) {
            return false;
        } else {
            if (!this.isMoving() && !damageSource.is(DamageTypeTags.AVOIDS_GUARDIAN_THORNS) && !damageSource.is(DamageTypes.THORNS)
                    && damageSource.getDirectEntity() instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 0), this);
            }

            if (this.randomStrollGoal != null) {
                this.randomStrollGoal.trigger();
            }

            return super.hurt(damageSource, f);
        }
    }

    @Override
    public int getMaxHeadXRot() {
        return 180;
    }

    @Override
    public void travel(@NotNull Vec3 vec3) {
        if (this.isControlledByLocalInstance() && this.isInWater()) {
            this.moveRelative(0.1F, vec3);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
            if (!this.isMoving() && this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(vec3);
        }
    }

    @Override
    protected @NotNull BodyRotationControl createBodyControl() {
        return new BodyRotationControl(this) {
            @Override
            public void clientTick() {
                if (ToxifinSlab.this.isPassenger() && ToxifinSlab.this.getVehicle() instanceof LivingEntity livingEntity
                        && livingEntity.getType() == ToxifinSlab.this.getType()) {
                    ToxifinSlab.this.yBodyRot = livingEntity.yBodyRot;
                }

                super.clientTick();
            }
        };
    }

    @Override
    protected @NotNull Vec3 getPassengerAttachmentPoint(@NotNull Entity entity, @NotNull EntityDimensions entityDimensions, float f) {
        Vec3 vec3 = super.getPassengerAttachmentPoint(entity, entityDimensions, f);
        if (entity.getType() == this.getType()) {
            vec3 = vec3.add(0.0, this.slabStackRidingOffset(), 0.0);
        }

        return vec3;
    }

    protected double slabStackRidingOffset() {
        return -0.112;
    }

    static class SlabAttackGoal extends Goal {
        private final ToxifinSlab slab;
        private int attackTime;
        private final boolean elder;

        public SlabAttackGoal(ToxifinSlab slab) {
            this.slab = slab;
            this.elder = false;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity livingEntity = this.slab.getTarget();
            return livingEntity != null && livingEntity.isAlive();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && (this.elder || this.slab.getTarget() != null && this.slab.distanceToSqr(this.slab.getTarget()) > 9.0);
        }

        @Override
        public void start() {
            this.attackTime = -10;
            this.slab.getNavigation().stop();
            LivingEntity livingEntity = this.slab.getTarget();
            if (livingEntity != null) {
                this.slab.getLookControl().setLookAt(livingEntity, 90.0F, 90.0F);
            }

            this.slab.hasImpulse = true;
        }

        @Override
        public void stop() {
            this.slab.setActiveAttackTarget(0);
            this.slab.setTarget(null);
            this.slab.randomStrollGoal.trigger();
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = this.slab.getTarget();
            if (livingEntity != null) {
                this.slab.getNavigation().stop();
                this.slab.getLookControl().setLookAt(livingEntity, 90.0F, 90.0F);
                if (!this.slab.hasLineOfSight(livingEntity)) {
                    this.slab.setTarget(null);
                } else {
                    this.attackTime++;
                    if (this.attackTime == 0) {
                        this.slab.setActiveAttackTarget(livingEntity.getId());
                        if (!this.slab.isSilent()) {
                            this.slab.level().broadcastEntityEvent(this.slab, (byte)19);
                        }
                    } else if (this.attackTime >= this.slab.getAttackDuration()) {
                        float f = 1.0F;
                        if (this.slab.level().getDifficulty() == Difficulty.HARD) {
                            f += 2.0F;
                        }

                        if (this.elder) {
                            f += 2.0F;
                        }

                        livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 40 + (int)f * 10, 0), this.slab);

                        this.slab.setTarget(null);
                    }

                    super.tick();
                }
            }
        }
    }

    static class SlabAttackSelector implements Predicate<LivingEntity> {
        private final ToxifinSlab slab;

        public SlabAttackSelector(ToxifinSlab slab) {
            this.slab = slab;
        }

        public boolean test(@Nullable LivingEntity livingEntity) {
            return (livingEntity instanceof Player || livingEntity instanceof Squid || livingEntity instanceof Axolotl)
                    && livingEntity.distanceToSqr(this.slab) > 9.0;
        }
    }

    class SlabLookControl extends LookControl {
        SlabLookControl() {
            super(ToxifinSlab.this);
        }

        @Override
        protected @NotNull Optional<Float> getXRotD() {
            return !ToxifinSlab.this.isPassenger() && !ToxifinSlab.this.isVehicle() ?
                    super.getXRotD() : Optional.empty();
        }

        @Override
        public void tick() {
            if (ToxifinSlab.this.getVehicle() instanceof ToxifinSlab slab && slab.getType() == ToxifinSlab.this.getType()) {
                ToxifinSlab.this.yHeadRot = slab.yHeadRot;
            }

            super.tick();
        }
    }

    static class SlabMoveControl extends MoveControl {
        private final ToxifinSlab slab;

        public SlabMoveControl(ToxifinSlab slab) {
            super(slab);
            this.slab = slab;
        }

        @Override
        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO && !this.slab.getNavigation().isDone()) {
                Vec3 vec3 = new Vec3(this.wantedX - this.slab.getX(),
                        this.wantedY - this.slab.getY(), this.wantedZ - this.slab.getZ());
                double d = vec3.length();
                double e = vec3.x / d;
                double f = vec3.y / d;
                double g = vec3.z / d;
                float h = (float)(Mth.atan2(vec3.z, vec3.x) * 180.0F / (float)Math.PI) - 90.0F;
                this.slab.setYRot(this.rotlerp(this.slab.getYRot(), h, 90.0F));
                this.slab.yBodyRot = this.slab.getYRot();
                float i = (float)(this.speedModifier * this.slab.getAttributeValue(Attributes.MOVEMENT_SPEED));
                float j = Mth.lerp(0.125F, this.slab.getSpeed(), i);
                this.slab.setSpeed(j);
                double k = Math.sin((double)(this.slab.tickCount + this.slab.getId()) * 0.5) * 0.05;
                double l = Math.cos((double)(this.slab.getYRot() * (float) (Math.PI / 180.0)));
                double m = Math.sin((double)(this.slab.getYRot() * (float) (Math.PI / 180.0)));
                double n = Math.sin((double)(this.slab.tickCount + this.slab.getId()) * 0.75) * 0.05;
                this.slab.setDeltaMovement(this.slab.getDeltaMovement().add(k * l, n * (m + l) * 0.25 + (double)j * f * 0.1, k * m));
                LookControl lookControl = this.slab.getLookControl();
                double o = this.slab.getX() + e * 2.0;
                double p = this.slab.getEyeY() + f / d;
                double q = this.slab.getZ() + g * 2.0;
                double r = lookControl.getWantedX();
                double s = lookControl.getWantedY();
                double t = lookControl.getWantedZ();
                if (!lookControl.isLookingAtTarget()) {
                    r = o;
                    s = p;
                    t = q;
                }

                this.slab.getLookControl().setLookAt(Mth.lerp(0.125, r, o), Mth.lerp(0.125, s, p), Mth.lerp(0.125, t, q), 10.0F, 40.0F);
                this.slab.setMoving(true);
            } else {
                this.slab.setSpeed(0.0F);
                this.slab.setMoving(false);
            }
        }
    }
}
