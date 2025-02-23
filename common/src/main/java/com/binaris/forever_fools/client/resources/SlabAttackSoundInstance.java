package com.binaris.forever_fools.client.resources;

import com.binaris.forever_fools.content.entity.ToxifinSlab;
import com.binaris.forever_fools.registry.FFSoundEvents;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;

public class SlabAttackSoundInstance extends AbstractTickableSoundInstance {
    private final ToxifinSlab slab;

    public SlabAttackSoundInstance(ToxifinSlab slab) {
        super(FFSoundEvents.TOXIFIN_ATTACK, SoundSource.HOSTILE, SoundInstance.createUnseededRandom());
        this.slab = slab;
        this.attenuation = Attenuation.NONE;
        this.looping = true;
        this.delay = 0;
    }

    public boolean canPlaySound() {
        return !this.slab.isSilent();
    }

    public void tick() {
        if (!this.slab.isRemoved() && this.slab.getTarget() == null) {
            this.x = (float)this.slab.getX();
            this.y = (float)this.slab.getY();
            this.z = (float)this.slab.getZ();
            float $$0 = this.slab.getAttackAnimationScale(0.0F);
            this.volume = 0.0F + 1.0F * $$0 * $$0;
            this.pitch = 0.7F + 0.5F * $$0;
        } else {
            this.stop();
        }
    }
}