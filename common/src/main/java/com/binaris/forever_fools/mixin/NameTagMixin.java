package com.binaris.forever_fools.mixin;

import com.binaris.forever_fools.registry.FFEntityRegistry;
import com.binaris.forever_fools.registry.FFItemRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.NameTagItem;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NameTagItem.class)
public abstract class NameTagMixin {

    @Inject(method = "interactLivingEntity", at = @At("HEAD"))
    public void fool$interact(ItemStack pStack, Player pPlayer, LivingEntity pTarget, InteractionHand pHand, CallbackInfoReturnable<InteractionResult> cir){
        Component component = pStack.get(DataComponents.CUSTOM_NAME);
        if(component == null) return;

        if(pTarget.level().isClientSide) return;

        if (pTarget.getType() == EntityType.SQUID && component.getString().equals("flying squid")) {
            LivingEntity batato = FFEntityRegistry.BATATO.create(pTarget.level());
            batato.copyPosition(pTarget);
            batato.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 999999999, 0, false, false, false));
            batato.setInvulnerable(true);
            pTarget.level().addFreshEntity(batato);
            pTarget.setInvulnerable(true);
            pTarget.startRiding(batato);
        }

        if(pTarget.getType() == EntityType.STRAY && component.getString().equals("potatostray"))
            copySpawn(FFEntityRegistry.STRAY_POTATO, pTarget);
        if(pTarget.getType() == EntityType.ARMADILLO && component.getString().equals("potatoarmadillo"))
            copySpawn(FFEntityRegistry.POTATO_ARMADILLO, pTarget);
        if(pTarget.getType() == EntityType.BEE && component.getString().equals("potatobee"))
            copySpawn(FFEntityRegistry.BEE_POTATO, pTarget);
        if(pTarget.getType() == EntityType.CHICKEN && component.getString().equals("potatochicken"))
            copySpawn(FFEntityRegistry.POTATO_CHICKEN, pTarget);
        if(pTarget.getType() == EntityType.GIANT && component.getString().equals("potatogiantzombie"))
            copySpawn(FFEntityRegistry.GIANT_POTATO, pTarget);

        if(pTarget.getType() == EntityType.VILLAGER && component.getString().equals("potatovillager"))
            copySpawn(FFEntityRegistry.VILLAGER_POTATO, pTarget);

        if(pTarget.getType() == EntityType.ENDERMAN && component.getString().equals("potatoenderman"))
            copySpawn(FFEntityRegistry.ENDERMAN_POTATO, pTarget);
        if(pTarget.getType() == EntityType.HUSK && component.getString().equals("potatohusk"))
            copySpawn(FFEntityRegistry.HUSK_POTATO, pTarget);
        if(pTarget.getType() == EntityType.ZOMBIE && component.getString().equals("potatozombie"))
            copySpawn(FFEntityRegistry.POTATO_ZOMBIE, pTarget);
        if(pTarget.getType() == EntityType.SKELETON && component.getString().equals("potatoskeleton"))
            copySpawn(FFEntityRegistry.SKELETON_POTATO, pTarget);


        if(pTarget.getType() == EntityType.CREEPER && component.getString().equals("potatocreeper"))
            copySpawn(FFEntityRegistry.CREEPER_POTATO, pTarget);

        if(pTarget.getType() == EntityType.SPIDER && component.getString().equals("potatospider"))
            copySpawn(FFEntityRegistry.SPIDER_POTATO, pTarget);

        if(pTarget.getType() == EntityType.COW && component.getString().equals("potatocow"))
            copySpawn(FFEntityRegistry.COW_POTATO, pTarget);

        if(pTarget.getType() == EntityType.PIG && component.getString().equals("potatopig"))
            copySpawn(FFEntityRegistry.PIG_POTATO, pTarget);

        if(pTarget.getType() == EntityType.BAT && component.getString().equals("potatobat"))
            copySpawn(FFEntityRegistry.BATATO, pTarget);

        if(pTarget.getType() == EntityType.COW && component.getString().equals("mooncow"))
            copySpawn(FFEntityRegistry.MOON_COW, pTarget);

        if(pTarget.getType() == EntityType.CHICKEN && component.getString().equals("blue"))
            copySpawn(FFEntityRegistry.DIAMOND_CHICKEN, pTarget);

        if(pTarget.getType() == EntityType.PIG && component.getString().equals("pony pig"))
            copySpawn(FFEntityRegistry.PONY_PIG, pTarget);

        if(pTarget.getType() == EntityType.COW && component.getString().equals("horse cow"))
            copySpawn(FFEntityRegistry.HORSE_COW, pTarget);

        if(pTarget.getType() == EntityType.SILVERFISH && component.getString().equals("redstone bug"))
            copySpawn(FFEntityRegistry.REDSTONE_BUG, pTarget);


        if (pTarget.getType() == EntityType.ZOMBIFIED_PIGLIN && component.getString().equals("professor")) {
            ZombifiedPiglin piglin = (ZombifiedPiglin) pTarget;
            piglin.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.OAK_SIGN)); // Change to a sign
        }

        if(pTarget.getType() == EntityType.SHEEP && component.getString().equals("fat sheep")) {
            Sheep entity = FFEntityRegistry.FAT_SHEEP.create(pTarget.level());

            entity.setSheared(((Sheep) pTarget).isSheared());
            entity.copyPosition(pTarget);
            pTarget.discard();
            pTarget.level().addFreshEntity(entity);
        }

        if(pTarget.getType() == EntityType.COW && component.getString().equals("fat cow"))
            copySpawn(FFEntityRegistry.FAT_COW, pTarget);

        if(pTarget.getType() == EntityType.PIG && component.getString().equals("fat pig")){
            copySpawn(FFEntityRegistry.FAT_PIG, pTarget);
        }

        if(pTarget.getType() == EntityType.IRON_GOLEM && component.getString().equals("love")){
            copySpawn(FFEntityRegistry.LOVE_GOLEM, pTarget);
        }

        if(pTarget.getType() == EntityType.SHEEP && component.getString().equals("shears")){
            copySpawn(FFEntityRegistry.SHEARED_SHEEP, pTarget);
        }

        if(pTarget.getType() == EntityType.SKELETON && component.getString().equals("spy")){
            copySpawn(FFEntityRegistry.SPYGLASS_SKELETON, pTarget);
        }

        if(pTarget.getType() == EntityType.ZOMBIE && component.getString().equals("smart zombie")){
            copySpawn(FFEntityRegistry.VISION_ZOMBIE, pTarget);
        }

        if(pTarget.getType() == EntityType.ENDERMAN && component.getString().equals("smart enderman")){
            copySpawn(FFEntityRegistry.VISION_ENDERMAN, pTarget);
        }

        if(pTarget.getType() == EntityType.CREEPER && component.getString().equals("nerd creeper")){
            copySpawn(FFEntityRegistry.NERD_CREEPER, pTarget);
        }

        if(pTarget.getType() == EntityType.CREEPER && component.getString().equals("smart creeper")){
            copySpawn(FFEntityRegistry.VISION_CREEPER, pTarget);
        }

        if(pTarget.getType() == EntityType.CREEPER && component.getString().equals("smile")){
            copySpawn(FFEntityRegistry.SMILE_CREEPER, pTarget);
        }

        if(pTarget.getType() == EntityType.SKELETON && component.getString().equals("smart skeleton")){
            copySpawn(FFEntityRegistry.VISION_SKELETON, pTarget);
        }

        if(pTarget.getType() == EntityType.WOLF && component.getString().equals("mars")){
            copySpawn(FFEntityRegistry.WOLF_MARS, pTarget);
        }

//        if(pTarget.getType() == EntityType.HORSE && component.getString().equals("switcheroo")){
//            Entity playerButEntity = (Entity) pPlayer;
//            ((EntityInvoker) playerButEntity).addPassenger(pTarget);
//            pPlayer.sendSystemMessage(Component.literal("hecho con exito"));
//        }


        if(pTarget.getType() == EntityType.PIG && component.getString().equals("spiderman")){
            copySpawn(FFEntityRegistry.SPIDERMAN_PIG, pTarget);
        }

        if(pTarget.getType() == EntityType.ZOMBIFIED_PIGLIN && component.getString().equals("cupid")){
            pTarget.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            pTarget.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
            pTarget.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(FFItemRegistry.CUPID_SWORD));
        }

        if(pTarget.getType() == EntityType.STRAY && component.getString().equals("potatobogged")){
            copySpawn(FFEntityRegistry.BOGGED_POTATO, pTarget);
        }

        if(pTarget.getType() == EntityType.SHEEP && component.getString().equals("potatosheep")){
            copySpawn(FFEntityRegistry.SHEEP_POTATO, pTarget);
        }
    }

    @Unique
    public void copySpawn(EntityType<?> mob, LivingEntity target){
        LivingEntity entity = (LivingEntity) mob.create(target.level());
        entity.copyPosition(target);
        target.discard();
        target.level().addFreshEntity(entity);
    }
}
