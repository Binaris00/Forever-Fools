package com.binaris.forever_fools.mixin;

import com.binaris.forever_fools.client.resources.SlabAttackSoundInstance;
import com.binaris.forever_fools.content.entity.ToxifinSlab;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.CommonListenerCookie;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin extends ClientCommonPacketListenerImpl {
    protected ClientPacketListenerMixin(Minecraft pMinecraft, Connection pConnection, CommonListenerCookie pCommonListenerCookie) {
        super(pMinecraft, pConnection, pCommonListenerCookie);
    }

    @Inject(method = "handleEntityEvent", at = @At("TAIL"))
    public void handleEntityEvent(ClientboundEntityEventPacket pPacket, CallbackInfo ci, @Local Entity entity) {
        if (entity != null) {
            if(pPacket.getEventId() == 19){
                minecraft.getSoundManager().play(new SlabAttackSoundInstance((ToxifinSlab) entity));
            }
        }

    }
}
