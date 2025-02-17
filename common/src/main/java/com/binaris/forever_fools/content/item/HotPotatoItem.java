package com.binaris.forever_fools.content.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class HotPotatoItem extends Item {
    public HotPotatoItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pLivingEntity) {
        pLivingEntity.setRemainingFireTicks(pLivingEntity.getRemainingFireTicks() + 1000001);
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        // TODO: Original Hot potato mechanic
        if(pLevel.getGameTime() % 40 == 0 && pEntity instanceof LivingEntity livingEntity){
            livingEntity.setRemainingFireTicks(40);
        }
    }
}
