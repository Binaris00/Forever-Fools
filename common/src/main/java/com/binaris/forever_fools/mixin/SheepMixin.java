package com.binaris.forever_fools.mixin;

import com.binaris.forever_fools.registry.FFEntityRegistry;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Sheep.class)
public abstract class SheepMixin {

    @Shadow public abstract boolean readyForShearing();

    @Shadow public abstract void shear(SoundSource source);

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    public void fool$mobInteract(Player pPlayer, InteractionHand pHand, CallbackInfoReturnable<InteractionResult> cir){
        Sheep sheep = (Sheep) (Object) this;

        if(sheep.getType().equals(FFEntityRegistry.SHEARED_SHEEP)){
            if (!sheep.level().isClientSide && readyForShearing()) {
                shear(SoundSource.PLAYERS);
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }
}
