package com.binaris.forever_fools.registry;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.util.RegisterFunction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

public final class FFSoundEvents {
    public static final SoundEvent TOXIFIN_AMBIENT = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.toxifin.ambient"));
    public static final SoundEvent TOXIFIN_AMBIENT_LAND = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.toxifin.ambient_land"));
    public static final SoundEvent TOXIFIN_ATTACK = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.toxifin.attack"));
    public static final SoundEvent TOXIFIN_DEATH = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.toxifin.death"));
    public static final SoundEvent TOXIFIN_DEATH_LAND = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.toxifin.death_land"));
    public static final SoundEvent TOXIFIN_FLOP = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.toxifin.flop"));
    public static final SoundEvent TOXIFIN_HURT = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.toxifin.hurt"));
    public static final SoundEvent TOXIFIN_HURT_LAND = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.toxifin.hurt_land"));

    public static final SoundEvent PLAGUEWHALE_AMBIENT = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.plaguewhale.ambient"));
    public static final SoundEvent PLAGUEWHALE_AMBIENT_LAND = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.plaguewhale.ambient_land"));
    public static final SoundEvent PLAUGEWHALE_DEATH = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.plaguewhale.death"));
    public static final SoundEvent PLAGUEWHALE_DEATH_LAND = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.plaguewhale.death_land"));
    public static final SoundEvent PLAGUEWHALE_FLOP = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.plaguewhale.flop"));
    public static final SoundEvent PLAGUEWHALE_HURT = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.plaguewhale.hurt"));
    public static final SoundEvent PLAGUEWHALE_HURT_LAND = SoundEvent.createVariableRangeEvent(FFCommonMod.id("entity.plaguewhale.hurt_land"));

    public static void registerSoundEvents(RegisterFunction<SoundEvent> function) {
        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.toxifin.ambient"), TOXIFIN_AMBIENT);
        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.toxifin.ambient_land"), TOXIFIN_AMBIENT_LAND);
        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.toxifin.attack"), TOXIFIN_ATTACK);
        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.toxifin.death"), TOXIFIN_DEATH);
        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.toxifin.death_land"), TOXIFIN_DEATH_LAND);
        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.toxifin.flop"), TOXIFIN_FLOP);
        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.toxifin.hurt"), TOXIFIN_HURT);
        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.toxifin.hurt_land"), TOXIFIN_HURT_LAND);

        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.plaguewhale.ambient"), PLAGUEWHALE_AMBIENT);
        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.plaguewhale.ambient_land"), PLAGUEWHALE_AMBIENT_LAND);
        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.plaguewhale.death"), PLAUGEWHALE_DEATH);
        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.plaguewhale.death_land"), PLAGUEWHALE_DEATH_LAND);
        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.plaguewhale.flop"), PLAGUEWHALE_FLOP);
        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.plaguewhale.hurt"), PLAGUEWHALE_HURT);
        function.register(BuiltInRegistries.SOUND_EVENT, FFCommonMod.id("entity.plaguewhale.hurt_land"), PLAGUEWHALE_HURT_LAND);
    };
}
