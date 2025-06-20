package com.binaris.forever_fools.mixin.client;

import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WolfModel.class)
public abstract class WolfModelMixin implements HeadedModel {
    @Shadow @Final private ModelPart head;

    @Override
    public @NotNull ModelPart getHead() {
        return head;
    }
}
