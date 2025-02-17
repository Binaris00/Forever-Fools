package com.binaris.forever_fools.client;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.client.model.BatatoModel;
import com.binaris.forever_fools.client.model.ToxifinModel;
import com.binaris.forever_fools.client.renderer.BatatoRenderer;
import com.binaris.forever_fools.client.renderer.PlagueWhaleRenderer;
import com.binaris.forever_fools.client.renderer.PotatoZombieRenderer;
import com.binaris.forever_fools.client.renderer.ToxifinRenderer;
import com.binaris.forever_fools.content.entity.ToxifinSlab;
import com.binaris.forever_fools.registry.FFEntityRegistry;
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

public class FFCommonClientMod {
    public static void createEntityLayers(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> consumer) {
        consumer.accept(BatatoModel.LAYER_LOCATION, BatatoModel::createBodyLayer);
        consumer.accept(ToxifinModel.LAYER_LOCATION, ToxifinModel::createBodyLayer);
    }

    public interface EntityRendererCallback { <T extends Entity> void accept(EntityType<? extends T> entityType,
                                       EntityRendererProvider<T> factory);
    }

    public static void createRenderers(EntityRendererCallback consumer) {
        consumer.accept(FFEntityRegistry.POTATO_CHICKEN, (a) -> new ChickenRenderer(a){

            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Chicken pEntity) {
                return FFCommonMod.id("textures/entity/chicken_potato.png");
            }
        });

        consumer.accept(FFEntityRegistry.POTATO_ARMADILLO, (a) -> new ArmadilloRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Armadillo pEntity) {
                return FFCommonMod.id("textures/entity/armadillo_potato.png");
            }
        });

        consumer.accept(FFEntityRegistry.BEE_POTATO, (a) -> new BeeRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Bee pEntity) {
                return FFCommonMod.id("textures/entity/bee_potato.png");
            }
        });

        consumer.accept(FFEntityRegistry.ENDERMAN_POTATO, (a) -> new EndermanRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull EnderMan pEntity) {
                return FFCommonMod.id("textures/entity/enderman_potato.png");
            }
        });

        consumer.accept(FFEntityRegistry.COW_POTATO, (a) -> new CowRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Cow pEntity) {
                return FFCommonMod.id("textures/entity/cow_potato.png");
            }
        });

        consumer.accept(FFEntityRegistry.CREEPER_POTATO, (a) -> new CreeperRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Creeper pEntity) {
                return FFCommonMod.id("textures/entity/creeper_potato.png");
            }
        });

        consumer.accept(FFEntityRegistry.GIANT_POTATO, (a) -> new GiantMobRenderer(a, 6.0F){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Giant pEntity) {
                return FFCommonMod.id("textures/entity/giant_potato.png");
            }
        });

        consumer.accept(FFEntityRegistry.PIG_POTATO, (a) -> new PigRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Pig pEntity) {
                return FFCommonMod.id("textures/entity/pig_potato.png");
            }
        });

        consumer.accept(FFEntityRegistry.SPIDER_POTATO, (a) -> new SpiderRenderer<>(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Spider pEntity) {
                return FFCommonMod.id("textures/entity/spider_potato.png");
            }
        });

        consumer.accept(FFEntityRegistry.HUSK_POTATO, (a) -> new HuskRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Zombie pEntity) {
                return FFCommonMod.id("textures/entity/husk_potato.png");
            }
        });

        consumer.accept(FFEntityRegistry.SKELETON_POTATO, (a) -> new SkeletonRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull AbstractSkeleton pEntity) {
                return FFCommonMod.id("textures/entity/skeleton_potato.png");
            }

        });

        consumer.accept(FFEntityRegistry.STRAY_POTATO, (a) -> new StrayRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Stray pEntity) {
                return FFCommonMod.id("textures/entity/stray_potato.png");
            }
        });

        consumer.accept(FFEntityRegistry.VILLAGER_POTATO, (a) -> new VillagerRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Villager pEntity) {
                return FFCommonMod.id("textures/entity/villager_potato.png");
            }
        });

        consumer.accept(FFEntityRegistry.BATATO, BatatoRenderer::new);
        consumer.accept(FFEntityRegistry.POTATO_ZOMBIE, PotatoZombieRenderer::new);
        consumer.accept(FFEntityRegistry.TOXIFIN, ToxifinRenderer::new);
        consumer.accept(FFEntityRegistry.PLAGUEWHALE, PlagueWhaleRenderer::new);
    }
}
