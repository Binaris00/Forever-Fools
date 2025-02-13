package com.binaris.forever_fools.client.model;//package com.binaris.forever_fools.client.model;
//
//import com.binaris.forever_fools.content.entity.Batato;
//import net.minecraft.client.animation.definitions.BatAnimation;
//import net.minecraft.client.model.geom.ModelPart;
//import net.minecraft.client.model.geom.PartNames;
//import net.minecraft.client.model.geom.PartPose;
//import net.minecraft.client.model.geom.builders.CubeListBuilder;
//import net.minecraft.client.model.geom.builders.LayerDefinition;
//import net.minecraft.client.model.geom.builders.MeshDefinition;
//import net.minecraft.client.model.geom.builders.PartDefinition;
//import net.minecraft.core.Direction;
//
//import java.util.Set;
//
//public class BatatoModel extends HierarchicalModel<Batato> {
//    private final ModelPart field_51132;
//    private final ModelPart field_51133;
//    private final ModelPart field_51134;
//    private final ModelPart field_51135;
//    private final ModelPart field_51136;
//    private final ModelPart field_51137;
//    private final ModelPart field_51138;
//
//    public BatatoModel(ModelPart modelPart) {
//        super(modelPart);
//        this.field_51132 = modelPart;
//        this.field_51133 = modelPart.getChild(PartNames.BODY);
//        this.field_51134 = this.field_51133.getChild(PartNames.RIGHT_WING);
//        this.field_51136 = this.field_51134.getChild(PartNames.RIGHT_WING_TIP);
//        this.field_51135 = this.field_51133.getChild(PartNames.LEFT_WING);
//        this.field_51137 = this.field_51135.getChild(PartNames.LEFT_WING_TIP);
//        this.field_51138 = this.field_51133.getChild(PartNames.FEET);
//    }
//
//    // TODO: No correct name
//    public static LayerDefinition buildModel() {
//        MeshDefinition modelData = new MeshDefinition();
//        PartDefinition modelPartData = modelData.getRoot();
//        PartDefinition modelPartData2 = modelPartData.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(19, 20).addBox(-1.5f, -2.0f, 0.0f, 13.0f, 12.0f, 0.01f, Set.of(Direction.NORTH)).texOffs(6, 20).mirror().addBox(-1.5f, -2.0f, 0.0f, 13.0f, 12.0f, 0.01f, Set.of(Direction.SOUTH)), PartPose.offset(0.0f, 17.0f, 0.0f));
//        PartDefinition modelPartData3 = modelPartData2.addOrReplaceChild(PartNames.RIGHT_WING, CubeListBuilder.create().texOffs(12, 0).addBox(-2.0f, -2.0f, 0.0f, 2.0f, 7.0f, 0.0f), PartPose.offset(-1.5f, 0.0f, 0.0f));
//        modelPartData3.addOrReplaceChild(PartNames.RIGHT_WING_TIP, CubeListBuilder.create().texOffs(16, 0).addBox(-6.0f, -2.0f, 0.0f, 6.0f, 8.0f, 0.0f), PartPose.offset(-2.0f, 0.0f, 0.0f));
//        PartDefinition modelPartData4 = modelPartData2.addOrReplaceChild(PartNames.LEFT_WING, CubeListBuilder.create().texOffs(12, 7).addBox(0.0f, -2.0f, 0.0f, 2.0f, 7.0f, 0.0f), PartPose.offset(11.5f, 2.0f, 0.0f));
//        modelPartData4.addOrReplaceChild(PartNames.LEFT_WING_TIP, CubeListBuilder.create().texOffs(16, 8).addBox(0.0f, -2.0f, 0.0f, 6.0f, 8.0f, 0.0f), PartPose.offset(2.0f, 0.0f, 0.0f));
//        modelPartData2.addOrReplaceChild(PartNames.FEET, CubeListBuilder.create().texOffs(16, 16).addBox(-1.5f, 0.0f, 0.0f, 3.0f, 2.0f, 0.0f), PartPose.offset(3.0f, 10.0f, 0.0f));
//        return LayerDefinition.create(modelData, 32, 32);
//    }
//
//
//    @Override
//    public void setupAnim(Batato renderState) {
//        super.setupAnim(renderState);
//        this.animate(renderState.flyAnimationState, BatAnimation.BAT_FLYING, renderState.getAgeScale(), 1.0F);
//        this.animate(renderState.restAnimationState, BatAnimation.BAT_RESTING, renderState.getAgeScale(), 1.0F);
//    }
//}