package com.binaris.forever_fools.client.model;

import com.binaris.forever_fools.FFCommonMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class CrownModel<T extends LivingEntity> extends EntityModel<T> implements HeadedModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(FFCommonMod.id("crown"), "main");
    public final ModelPart root;

    public CrownModel(ModelPart modelPart) {
        this.root = modelPart;
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild(
                "hat", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -9.0F, -2.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.1F, 0.1F, 0.1F)), PartPose.ZERO
        );
        return LayerDefinition.create(meshDefinition, 32, 32);
    }

    public void setupAnim(@NotNull T livingEntity, float f, float g, float h, float i, float j) {
    }


    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int i, int i1, int i2) {
        this.root.render(poseStack, vertexConsumer, i, i1, i2);
    }

    @Override
    public @NotNull ModelPart getHead() {
        return this.root;
    }
}
