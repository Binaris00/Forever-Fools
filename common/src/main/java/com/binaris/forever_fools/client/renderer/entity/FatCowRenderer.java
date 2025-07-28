package com.binaris.forever_fools.client.renderer.entity;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.client.model.entity.FatCowModel;
import com.binaris.forever_fools.content.entity.FatCowEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class FatCowRenderer extends MobRenderer<FatCowEntity, FatCowModel<FatCowEntity>> { // Usa FatCowModel
    private static final ResourceLocation FAT_COW_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/cow/cow.png");
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(FFCommonMod.id("fat_cow"), "main");

    public FatCowRenderer(EntityRendererProvider.Context p_173956_) {
        super(p_173956_, new FatCowModel<>(p_173956_.bakeLayer(LAYER_LOCATION)), 0.7F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FatCowEntity pEntity) {
        return FAT_COW_LOCATION;
    }

    @Override
    public void render(FatCowEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        // Pasa el valor de escalado del cuerpo al modelo antes de renderizarlo
        this.model.bodyScale = pEntity.getCurrentBodyScale();


        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected void scale(FatCowEntity pEntity, PoseStack pPoseStack, float pPartialTick) {
        // No hacer nada aquí, el escalado es manejado por el modelo
    }
}