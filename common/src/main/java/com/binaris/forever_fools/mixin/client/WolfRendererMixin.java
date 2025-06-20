package com.binaris.forever_fools.mixin.client;

import com.binaris.forever_fools.client.renderer.entity.layer.CrownLayer;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.world.entity.animal.Wolf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WolfRenderer.class)
public abstract class WolfRendererMixin extends MobRenderer<Wolf, WolfModel<Wolf>> implements HeadedModel {
    // ??
    public WolfRendererMixin(EntityRendererProvider.Context pContext, WolfModel<Wolf> pModel, float pShadowRadius) {
        super(pContext, pModel, pShadowRadius);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void FF$wolfinit(EntityRendererProvider.Context context, CallbackInfo ci){
        this.addLayer(new CrownLayer((WolfRenderer)(Object) this, context.getModelSet()));
    }
}
