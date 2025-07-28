package com.binaris.forever_fools.client.renderer.entity;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.client.model.entity.FatPigModel;
import com.binaris.forever_fools.content.entity.FatPigEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class FatPigRenderer extends MobRenderer<FatPigEntity, FatPigModel<FatPigEntity>> { // Usa FatCowModel
    private static final ResourceLocation FAT_PIG_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/pig/pig.png");
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(FFCommonMod.id("fat_pig"), "main");

    public FatPigRenderer(EntityRendererProvider.Context p_173956_) {
        super(p_173956_, new FatPigModel<>(p_173956_.bakeLayer(LAYER_LOCATION)), 0.7F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FatPigEntity pEntity) {
        return FAT_PIG_LOCATION;
    }

    @Override
    public void render(FatPigEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        // Pasa el valor de escalado del cuerpo al modelo antes de renderizarlo
        this.model.bodyScale = pEntity.getCurrentBodyScale();


        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected void scale(FatPigEntity pEntity, PoseStack pPoseStack, float pPartialTick) {
        // No hacer nada aquí, el escalado es manejado por el modelo
    }
}