package com.binaris.forever_fools.content.entity;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.client.FFCommonClientMod;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpyglassSkeleton extends Skeleton {
    private static final EntityDataAccessor<Integer> DATA_SPYGLASSES_IN_SOCKETS = SynchedEntityData.defineId(SpyglassSkeleton.class, EntityDataSerializers.INT);

    public SpyglassSkeleton(EntityType<? extends Skeleton> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DATA_SPYGLASSES_IN_SOCKETS, 2);
    }

    @Override
    public void tick() {
        super.tick();
        //FFCommonMod.LOG.info("a:" + getSpyglassesInSockets());
    }

    public int getSpyglassesInSockets() {
        return this.getEntityData().get(DATA_SPYGLASSES_IN_SOCKETS);
    }

    public void addSpyglassIntoEyeSocket() {
        this.getEntityData().set(DATA_SPYGLASSES_IN_SOCKETS, this.getSpyglassesInSockets() + 1);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {
        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
    }
}
