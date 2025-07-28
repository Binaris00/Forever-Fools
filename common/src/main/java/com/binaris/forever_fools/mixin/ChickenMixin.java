package com.binaris.forever_fools.mixin;

import com.binaris.forever_fools.registry.FFEntityRegistry;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Chicken.class)
public abstract class ChickenMixin extends Animal {
    @Unique
    Chicken chicken = (Chicken) (Object) this;

    protected ChickenMixin(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void die(@NotNull DamageSource pDamageSource) {
        super.die(pDamageSource);

        if(this.random.nextBoolean()) this.spawnAtLocation(new ItemStack(Items.LAPIS_LAZULI, 3));
        else this.spawnAtLocation(new ItemStack(Items.DIAMOND, 3));
    }

    @Override
    protected boolean shouldDropLoot() {
        return !chicken.getType().equals(FFEntityRegistry.DIAMOND_CHICKEN);
    }

    @Redirect(method = "aiStep", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/animal/Chicken;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;"))
    public ItemEntity spawwingDiamond(Chicken instance, ItemLike itemLike){
        if(instance.getType().equals(FFEntityRegistry.DIAMOND_CHICKEN)){
            return instance.spawnAtLocation(Items.DIAMOND);
        }
        return instance.spawnAtLocation(Items.EGG);
    }
}
