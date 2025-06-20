package com.binaris.forever_fools.client.renderer.entity.layer;

import com.binaris.forever_fools.content.entity.MoonCow;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.AbstractSkullBlock;
import org.jetbrains.annotations.NotNull;

public class MoonCowHeadLayer<T extends LivingEntity, M extends EntityModel<T> & HeadedModel> extends RenderLayer<T, M> {
    private final float scaleX;
    private final float scaleY;
    private final float scaleZ;
    private final ItemInHandRenderer itemInHandRenderer;

    public MoonCowHeadLayer(RenderLayerParent<T, M> renderLayerParent, ItemInHandRenderer itemInHandRenderer) {
        this(renderLayerParent, 1.0F, 1.0F, 1.0F, itemInHandRenderer);
    }

    public MoonCowHeadLayer(RenderLayerParent<T, M> renderLayerParent, float f, float g, float h, ItemInHandRenderer itemInHandRenderer) {
        super(renderLayerParent);
        this.scaleX = f;
        this.scaleY = g;
        this.scaleZ = h;
        this.itemInHandRenderer = itemInHandRenderer;
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        ItemStack itemStack = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
        if (!itemStack.isEmpty()) {
            Item item = itemStack.getItem();
            poseStack.pushPose();
            poseStack.scale(this.scaleX, this.scaleY, this.scaleZ);
            boolean bl = livingEntity instanceof Villager || livingEntity instanceof ZombieVillager;
            if (livingEntity.isBaby() && !(livingEntity instanceof Villager)) {
                poseStack.translate(0.0F, 0.03125F, 0.0F);
                poseStack.scale(0.7F, 0.7F, 0.7F);
                poseStack.translate(0.0F, 1.0F, 0.0F);
            }

            this.getParentModel().getHead().translateAndRotate(poseStack);
            if (livingEntity instanceof MoonCow) {
                if (livingEntity.isBaby()) poseStack.scale(1.5F, 1.5F, 1.5F);

                poseStack.translate(0.0F, 0.2F, -0.2F);
            }

            if (item instanceof BlockItem && ((BlockItem)item).getBlock() instanceof AbstractSkullBlock) {
                poseStack.scale(1.1875F, -1.1875F, -1.1875F);
                if (bl) {
                    poseStack.translate(0.0F, 0.0625F, 0.0F);
                }



                poseStack.translate(-0.5, 0.0, -0.5);
            } else if (!(item instanceof ArmorItem armorItem) || armorItem.getEquipmentSlot() != EquipmentSlot.HEAD) {
                translateToHead(poseStack, bl);
                this.itemInHandRenderer.renderItem(livingEntity, itemStack, ItemDisplayContext.HEAD, false, poseStack, multiBufferSource, i);
            }

            poseStack.popPose();
        }
    }

    public static void translateToHead(PoseStack poseStack, boolean bl) {
        poseStack.translate(0.0F, -0.25F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        poseStack.scale(0.625F, -0.625F, -0.625F);
        if (bl) {
            poseStack.translate(0.0F, 0.1875F, 0.0F);
        }
    }
}
