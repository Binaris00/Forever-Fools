package com.binaris.forever_fools.client;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.registry.EntityRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.Villager;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ForeverFoolsClient {
    public static void createEntityLayers(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> consumer) {
        //consumer.accept(ChickenModel.LAYER_LOCATION, Bum::createLayer);
    }

    public interface EntityRendererCallback {
        <T extends Entity> void accept(EntityType<? extends T> entityType,
                                       EntityRendererProvider<T> factory);
    }

    public static void createRenderers(EntityRendererCallback consumer) {
        consumer.accept(EntityRegistry.POTATO_CHICKEN, (a) -> new ChickenRenderer(a){

            @Override
            public @NotNull ResourceLocation getTextureLocation(Chicken pEntity) {
                return FFCommonMod.id("textures/entity/chicken_potato.png");
            }
        });

        consumer.accept(EntityRegistry.POTATO_ARMADILLO, (a) -> new ArmadilloRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(Armadillo pEntity) {
                return FFCommonMod.id("textures/entity/armadillo_potato.png");
            }
        });

        consumer.accept(EntityRegistry.BEE_POTATO, (a) -> new BeeRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(Bee pEntity) {
                return FFCommonMod.id("textures/entity/bee_potato.png");
            }
        });

        consumer.accept(EntityRegistry.ENDERMAN_POTATO, (a) -> new EndermanRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(EnderMan pEntity) {
                return FFCommonMod.id("textures/entity/enderman_potato.png");
            }
        });

        consumer.accept(EntityRegistry.COW_POTATO, (a) -> new CowRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(Cow pEntity) {
                return FFCommonMod.id("textures/entity/cow_potato.png");
            }
        });

        consumer.accept(EntityRegistry.CREEPER_POTATO, (a) -> new CreeperRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(Creeper pEntity) {
                return FFCommonMod.id("textures/entity/creeper_potato.png");
            }
        });

        consumer.accept(EntityRegistry.GIANT_POTATO, (a) -> new GiantMobRenderer(a, 6.0F){
            @Override
            public @NotNull ResourceLocation getTextureLocation(Giant pEntity) {
                return FFCommonMod.id("textures/entity/giant_potato.png");
            }
        });

        consumer.accept(EntityRegistry.PIG_POTATO, (a) -> new PigRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(Pig pEntity) {
                return FFCommonMod.id("textures/entity/pig_potato.png");
            }
        });

        consumer.accept(EntityRegistry.SPIDER_POTATO, (a) -> new SpiderRenderer<>(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(Spider pEntity) {
                return FFCommonMod.id("textures/entity/spider_potato.png");
            }
        });

        consumer.accept(EntityRegistry.HUSK_POTATO, (a) -> new HuskRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(Zombie pEntity) {
                return FFCommonMod.id("textures/entity/husk_potato.png");
            }
        });

        consumer.accept(EntityRegistry.SKELETON_POTATO, (a) -> new SkeletonRenderer(a){

            @Override
            public @NotNull ResourceLocation getTextureLocation(AbstractSkeleton pEntity) {
                return FFCommonMod.id("textures/entity/skeleton_potato.png");
            }

        });

        consumer.accept(EntityRegistry.STRAY_POTATO, (a) -> new StrayRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(Stray pEntity) {
                return FFCommonMod.id("textures/entity/stray_potato.png");
            }
        });

        consumer.accept(EntityRegistry.VILLAGER_POTATO, (a) -> new VillagerRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(Villager pEntity) {
                return FFCommonMod.id("textures/entity/villager_potato.png");
            }
        });
    }
}
