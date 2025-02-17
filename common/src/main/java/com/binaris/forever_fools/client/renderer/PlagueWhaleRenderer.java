package com.binaris.forever_fools.client.renderer;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.client.model.ToxifinModel;
import com.binaris.forever_fools.content.entity.PlaguewhaleSlab;
import com.binaris.forever_fools.content.entity.ToxifinSlab;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.ElderGuardian;
import org.jetbrains.annotations.NotNull;

public class PlagueWhaleRenderer extends ToxifinRenderer{
    public PlagueWhaleRenderer(EntityRendererProvider.Context pContext, ToxifinModel pModel, float pShadowRadius) {
        super(pContext, pModel, pShadowRadius);
    }

    public PlagueWhaleRenderer(EntityRendererProvider.Context context) {
        super(context, new ToxifinModel(context.bakeLayer(ToxifinModel.LAYER_LOCATION)), 1.2F);
    }

    @Override
    protected void scale(@NotNull ToxifinSlab plagueWhaleRenderer, PoseStack poseStack, float f) {
        poseStack.scale(ElderGuardian.ELDER_SIZE_SCALE, ElderGuardian.ELDER_SIZE_SCALE, ElderGuardian.ELDER_SIZE_SCALE);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ToxifinSlab plagueWhaleRenderer) {
        return FFCommonMod.id("textures/entity/plaguewhale.png");
    }
}
