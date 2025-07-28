package com.binaris.forever_fools.content.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class FatCowEntity extends Cow {

    private int feedingCounter = 0;
    private float currentBodyScale = 1.0F;
    private static final int MAX_FEEDINGS = 5;

    public FatCowEntity(EntityType<? extends Cow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.WHEAT);
    }

    @Override
    public net.minecraft.world.@NotNull InteractionResult mobInteract(Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (this.isFood(itemstack)) {
            this.growFat();
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            return net.minecraft.world.InteractionResult.SUCCESS;
        }
        return super.mobInteract(pPlayer, pHand);
    }

    private void growFat() {
        this.feedingCounter++;
        this.currentBodyScale = Math.min(this.currentBodyScale + 0.10F, 2.5F);

        if (this.feedingCounter >= MAX_FEEDINGS) {
            this.explode();
        }
    }

    private void explode() {
        if (!this.level().isClientSide) {
            this.level().explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 0.0F, Level.ExplosionInteraction.NONE);
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.NEUTRAL, 4.0F, (1.0F + (this.level().random.nextFloat() - this.level().random.nextFloat()) * 0.2F) * 0.7F);

            if (this.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, this.getX(), this.getY() + 0.5D, this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
            }

            this.dropFatCowLoot();
            this.discard();
        }
    }

    private void dropFatCowLoot() {
        for (int i = 0; i < 3; i++) {
            this.spawnAtLocation(new ItemStack(Items.LEATHER, this.random.nextInt(3)));
            this.spawnAtLocation(new ItemStack(Items.BEEF, this.random.nextInt(3) + 1));
        }
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.addFreshEntity(new ExperienceOrb(serverLevel, this.getX(), this.getY(), this.getZ(), this.random.nextInt(5) + 3));
        }
    }

    // Nuevo getter para el escalado del cuerpo
    public float getCurrentBodyScale() {
        return this.currentBodyScale;
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("FeedingCounter", this.feedingCounter);
        pCompound.putFloat("CurrentBodyScale", this.currentBodyScale);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.feedingCounter = pCompound.getInt("FeedingCounter");
        this.currentBodyScale = pCompound.getFloat("CurrentBodyScale");
    }

    @Override
    public float getScale() {
        return 1.0F;
    }
}
