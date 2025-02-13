package com.binaris.forever_fools.registry;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.content.entity.Batato;
import com.binaris.forever_fools.util.RegisterFunction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import java.util.function.BiConsumer;

public class EntityRegistry {

    public static final EntityType<Chicken> POTATO_CHICKEN = EntityType.Builder.of(Chicken::new, MobCategory.CREATURE)
            .sized(0.4F, 0.7F).eyeHeight(0.644F).clientTrackingRange(10).passengerAttachments(new Vec3(0.0, 0.7, -0.1))
            .build(FFCommonMod.id("chicken_potato").toString());

    public static final EntityType<Armadillo> POTATO_ARMADILLO = EntityType.Builder.of(Armadillo::new, MobCategory.CREATURE)
            .sized(0.7F, 0.65F).eyeHeight(0.26F).clientTrackingRange(10)
            .build(FFCommonMod.id("armadillo_potato").toString());

    public static final EntityType<Bee> BEE_POTATO = EntityType.Builder.of(Bee::new, MobCategory.CREATURE)
            .sized(0.7F, 0.6F).eyeHeight(0.3F).clientTrackingRange(8)
            .build(FFCommonMod.id("bee_potato").toString());

    public static final EntityType<Cow> COW_POTATO = EntityType.Builder.of(Cow::new, MobCategory.CREATURE)
            .sized(0.9F, 1.4F).eyeHeight(1.3F).passengerAttachments(1.36875F).clientTrackingRange(10)
            .build(FFCommonMod.id("cow_potato").toString());

    public static final EntityType<Creeper> CREEPER_POTATO = EntityType.Builder.of(Creeper::new, MobCategory.MONSTER)
            .sized(0.6F, 1.7F).eyeHeight(1.3F).clientTrackingRange(8)
            .build(FFCommonMod.id("creeper_potato").toString());

    public static final EntityType<EnderMan> ENDERMAN_POTATO = EntityType.Builder.of(EnderMan::new, MobCategory.MONSTER)
            .sized(0.6F, 2.9F).eyeHeight(2.55F).passengerAttachments(2.80625F).clientTrackingRange(8)
            .build(FFCommonMod.id("enderman_potato").toString());

    public static final EntityType<Giant> GIANT_POTATO = EntityType.Builder.of(Giant::new, MobCategory.MONSTER)
            .sized(3.6F, 12.0F).eyeHeight(10.44F).passengerAttachments(-3.75F).clientTrackingRange(10)
            .build(FFCommonMod.id("giant_potato").toString());

    public static final EntityType<Pig> PIG_POTATO = EntityType.Builder.of(Pig::new, MobCategory.CREATURE)
            .sized(0.9F, 0.9F).passengerAttachments(0.86875F).clientTrackingRange(10)
            .build(FFCommonMod.id("pig_potato").toString());

    public static final EntityType<Skeleton> SKELETON_POTATO = EntityType.Builder.of(Skeleton::new, MobCategory.MONSTER)
            .sized(0.6F, 1.99F).eyeHeight(1.74F).ridingOffset(-0.7F).clientTrackingRange(8)
            .build(FFCommonMod.id("skeleton_potato").toString());

    public static final EntityType<Spider> SPIDER_POTATO = EntityType.Builder.of(Spider::new, MobCategory.MONSTER)
            .sized(1.4F, 0.9F).eyeHeight(0.65F).passengerAttachments(0.765F).clientTrackingRange(8)
            .build(FFCommonMod.id("spider_potato").toString());

    public static final EntityType<Husk> HUSK_POTATO = EntityType.Builder.of(Husk::new, MobCategory.MONSTER)
            .sized(0.6F, 1.95F).eyeHeight(1.74F).ridingOffset(-0.7F)
            .passengerAttachments(2.075F).clientTrackingRange(8)
            .build(FFCommonMod.id("husk_potato").toString());

    public static final EntityType<Stray> STRAY_POTATO = EntityType.Builder.of(Stray::new, MobCategory.MONSTER)
            .sized(0.6F, 1.99F).eyeHeight(1.74F).ridingOffset(-0.7F)
            .immuneTo(Blocks.POWDER_SNOW).clientTrackingRange(8)
            .build(FFCommonMod.id("stray_potato").toString());

    public static final EntityType<Villager> VILLAGER_POTATO = EntityType.Builder.<Villager>of(Villager::new, MobCategory.MISC)
            .sized(0.6F, 1.95F).eyeHeight(1.62F).clientTrackingRange(10)
            .build(FFCommonMod.id("villager_potato").toString());

    public static final EntityType<Batato> BATATO = EntityType.Builder.<Batato>of(Batato::new, MobCategory.AMBIENT)
            .sized(0.5f, 0.9f).eyeHeight(0.45f).clientTrackingRange(5)
            .build(FFCommonMod.id("batato").toString());

    public static void registerEntityTypes(RegisterFunction<EntityType<?>> function) {
        function.register(BuiltInRegistries.ENTITY_TYPE, FFCommonMod.id("chicken_potato"), POTATO_CHICKEN);
        function.register(BuiltInRegistries.ENTITY_TYPE, FFCommonMod.id("armadillo_potato"), POTATO_ARMADILLO);
        function.register(BuiltInRegistries.ENTITY_TYPE, FFCommonMod.id("bee_potato"), BEE_POTATO);
        function.register(BuiltInRegistries.ENTITY_TYPE, FFCommonMod.id("cow_potato"), COW_POTATO);
        function.register(BuiltInRegistries.ENTITY_TYPE, FFCommonMod.id("creeper_potato"), CREEPER_POTATO);
        function.register(BuiltInRegistries.ENTITY_TYPE, FFCommonMod.id("enderman_potato"), ENDERMAN_POTATO);
        function.register(BuiltInRegistries.ENTITY_TYPE, FFCommonMod.id("giant_potato"), GIANT_POTATO);
        function.register(BuiltInRegistries.ENTITY_TYPE, FFCommonMod.id("pig_potato"), PIG_POTATO);
        function.register(BuiltInRegistries.ENTITY_TYPE, FFCommonMod.id("skeleton_potato"), SKELETON_POTATO);
        function.register(BuiltInRegistries.ENTITY_TYPE, FFCommonMod.id("spider_potato"), SPIDER_POTATO);
        function.register(BuiltInRegistries.ENTITY_TYPE, FFCommonMod.id("husk_potato"), HUSK_POTATO);
        function.register(BuiltInRegistries.ENTITY_TYPE, FFCommonMod.id("stray_potato"), STRAY_POTATO);
        function.register(BuiltInRegistries.ENTITY_TYPE, FFCommonMod.id("villager_potato"), VILLAGER_POTATO);
        function.register(BuiltInRegistries.ENTITY_TYPE, FFCommonMod.id("batato"), BATATO);
    }

    public static void registerAttributes(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier> consumer) {
        consumer.accept(POTATO_CHICKEN, Chicken.createAttributes().build());
        consumer.accept(POTATO_ARMADILLO, Armadillo.createAttributes().build());
        consumer.accept(BEE_POTATO, Bee.createAttributes().build());
        consumer.accept(COW_POTATO, Cow.createAttributes().build());
        consumer.accept(CREEPER_POTATO, Creeper.createAttributes().build());
        consumer.accept(ENDERMAN_POTATO, EnderMan.createAttributes().build());
        consumer.accept(GIANT_POTATO, Giant.createAttributes().build());
        consumer.accept(PIG_POTATO, Pig.createAttributes().build());
        consumer.accept(SKELETON_POTATO, Skeleton.createAttributes().build());
        consumer.accept(SPIDER_POTATO, Spider.createAttributes().build());
        consumer.accept(STRAY_POTATO, Stray.createAttributes().build());
        consumer.accept(VILLAGER_POTATO, Villager.createAttributes().build());
        consumer.accept(HUSK_POTATO, Husk.createAttributes().build());
        consumer.accept(BATATO, Bat.createAttributes().build());
    }
}