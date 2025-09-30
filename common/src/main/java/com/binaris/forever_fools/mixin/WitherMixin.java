package com.binaris.forever_fools.mixin;


import com.binaris.forever_fools.registry.FFEntityRegistry;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitherBoss.class)
public class WitherMixin {
    @Shadow @Final private ServerBossEvent bossEvent;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void fool$constructwither(EntityType pEntityType, Level pLevel, CallbackInfo ci){
        if(pEntityType.equals(FFEntityRegistry.PINK_WITHER))
            this.bossEvent.setVisible(false);
    }
}
