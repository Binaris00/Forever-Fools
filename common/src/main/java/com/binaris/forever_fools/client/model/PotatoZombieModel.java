package com.binaris.forever_fools.client.model;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.Zombie;

public class PotatoZombieModel<T extends Zombie> extends ZombieModel<T> {
    public PotatoZombieModel(ModelPart modelPart) {
        super(modelPart);
    }
}
