package com.binaris.forever_fools.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Animal.class)
public class AnimalMixin {

    @Inject(method = "mobInteract", at = @At(value = "HEAD"), cancellable = true)
    public void fool$mobInteract(Player pPlayer, InteractionHand pHand, CallbackInfoReturnable<InteractionResult> cir) {
        Animal animal = (Animal) (Object) this;

        if(animal instanceof Sheep){
            if(pPlayer.getItemInHand(pHand).getItem() == Items.GOLDEN_APPLE){
                pPlayer.getItemInHand(pHand).shrink(1);
                animal.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 99999, 1, false, false, false));
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }
}
