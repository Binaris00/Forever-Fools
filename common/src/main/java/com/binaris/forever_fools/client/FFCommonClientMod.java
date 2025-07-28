package com.binaris.forever_fools.client;

import com.binaris.forever_fools.FFCommonMod;
import com.binaris.forever_fools.client.model.CrownModel;
import com.binaris.forever_fools.client.model.entity.*;
import com.binaris.forever_fools.client.renderer.entity.*;
import com.binaris.forever_fools.content.entity.FatCowEntity;
import com.binaris.forever_fools.content.entity.FatSheepEntity;
import com.binaris.forever_fools.content.entity.LoveGolem;
import com.binaris.forever_fools.registry.FFEntityRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.Villager;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class FFCommonClientMod {
    public static void createEntityLayers(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> consumer) {
        consumer.accept(BatatoModel.LAYER_LOCATION, BatatoModel::createBodyLayer);
        consumer.accept(ToxifinModel.LAYER_LOCATION, ToxifinModel::createBodyLayer);
        consumer.accept(MegaSpudModel.LAYER_OUTER_LOCATION, MegaSpudModel::createOuterBodyLayer);
        consumer.accept(MegaSpudModel.LAYER_INNER_LOCATION, MegaSpudModel::createInnerBodyLayer);
        consumer.accept(MoonCowModel.LAYER_LOCATION, MoonCowModel::createBodyLayer);
        consumer.accept(CrownModel.LAYER_LOCATION, CrownModel::createLayer);
        consumer.accept(FatCowRenderer.LAYER_LOCATION, FatCowModel::createBodyLayer);
        consumer.accept(FatPigRenderer.LAYER_LOCATION, FatPigModel::createBodyLayer);
        consumer.accept(FatSheepRenderer.LAYER_LOCATION, FatSheepModel::createBodyLayer);
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

        consumer.accept(FFEntityRegistry.PINK_WITHER, (a) -> new WitherBossRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull WitherBoss pEntity) {
                return FFCommonMod.id("textures/entity/pink_wither.png");
            }
        });

        consumer.accept(FFEntityRegistry.DIAMOND_CHICKEN, (a) -> new ChickenRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Chicken pEntity) {
                return FFCommonMod.id("textures/entity/diamond_chicken.png");
            }
        });

        consumer.accept(FFEntityRegistry.PONY_PIG, (a) -> new PigRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Pig pEntity) {
                return FFCommonMod.id("textures/entity/pony_pig.png");
            }
        });

        consumer.accept(FFEntityRegistry.HORSE_COW, (a) -> new CowRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Cow pEntity) {
                return FFCommonMod.id("textures/entity/horse_cow.png");
            }
        });

        consumer.accept(FFEntityRegistry.REDSTONE_BUG, (a) -> new SilverfishRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Silverfish pEntity) {
                return FFCommonMod.id("textures/entity/redstone_bug.png");
            }
        });

        consumer.accept(FFEntityRegistry.LOVE_GOLEM, (a) -> new IronGolemRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull IronGolem pEntity) {
                return FFCommonMod.id("textures/entity/love_golem.png");
            }
        });

        consumer.accept(FFEntityRegistry.SMILE_CREEPER, (a) -> new CreeperRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Creeper pEntity) {
                return FFCommonMod.id("textures/entity/smile_creeper.png");
            }
        });

        consumer.accept(FFEntityRegistry.WOLF_MARS, (a) -> new WolfRenderer(a){
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull Wolf pEntity) {
                return FFCommonMod.id("textures/entity/wolf_mars.png");
            }
        });

        consumer.accept(FFEntityRegistry.SHEEP_POTATO, SheepPotatoRenderer::new);
        consumer.accept(FFEntityRegistry.BOGGED_POTATO, BoggedPotatoRenderer::new);
        consumer.accept(FFEntityRegistry.VISION_SKELETON, VisionSkeletonRenderer::new);
        consumer.accept(FFEntityRegistry.VISION_ENDERMAN, VisionEndermanRenderer::new);
        consumer.accept(FFEntityRegistry.NERD_CREEPER, NerdCreeperRenderer::new);
        consumer.accept(FFEntityRegistry.VISION_ZOMBIE, VisionZombieRenderer::new);
        consumer.accept(FFEntityRegistry.VISION_CREEPER, VisionCreeperRenderer::new);
        consumer.accept(FFEntityRegistry.SPYGLASS_SKELETON, SpyglassSkeletonRenderer::new);
        consumer.accept(FFEntityRegistry.SHEARED_SHEEP, SheepRenderer::new);
        consumer.accept(FFEntityRegistry.FAT_PIG, FatPigRenderer::new);
        consumer.accept(FFEntityRegistry.FAT_SHEEP, FatSheepRenderer::new);
        consumer.accept(FFEntityRegistry.FAT_COW, FatCowRenderer::new);
        consumer.accept(FFEntityRegistry.BATATO, BatatoRenderer::new);
        consumer.accept(FFEntityRegistry.POTATO_ZOMBIE, PotatoZombieRenderer::new);
        consumer.accept(FFEntityRegistry.TOXIFIN, ToxifinRenderer::new);
        consumer.accept(FFEntityRegistry.PLAGUEWHALE, PlagueWhaleRenderer::new);
        consumer.accept(FFEntityRegistry.MEGASPUD, MegaSpudRenderer::new);
        consumer.accept(FFEntityRegistry.MOON_COW, MoonCowRenderer::new);
        consumer.accept(FFEntityRegistry.RAY_TRACING, RayTracingRenderer::new);
        consumer.accept(FFEntityRegistry.SPIDERMAN_PIG, PigRenderer::new);
    }
}
