package com.binaris.forever_fools.content.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PotatoPeelerItem extends Item {
    public PotatoPeelerItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return player.isCreative();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }


//    public static AttributeModifiersComponent method_59060(int i, float f) {
//        return AttributeModifiersComponent.builder()
//                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", i,
//                        EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
//                .add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", f,
//                        EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).build();
//    }
//
//    private static float method_59062(World world) {
//        return world.random.method_58814(0.8f, 1.2f);
//    }
//
//    public static void method_59065(World world, @Nullable PlayerEntity playerEntity, BlockPos blockPos, SoundCategory soundCategory) {
//        world.playSound(playerEntity, blockPos, SoundEvents.ENTITY_POTATO_PEEL, soundCategory, 1.0f, class_9536.method_59062(world));
//    }
//
//    public static void method_59063(World world, Entity entity) {
//        entity.playSound(SoundEvents.ENTITY_POTATO_PEEL, 1.0f, class_9536.method_59062(world));
//    }
//
//    public static void method_59064(World world, Entity entity, SoundCategory soundCategory) {
//        world.playSoundFromEntity(null, entity, SoundEvents.ENTITY_POTATO_PEEL, soundCategory, 1.0f, class_9536.method_59062(world));
//    }

    private static InteractionResult interaction(UseOnContext itemUsageContext, ItemStack itemStack, BlockState blockState) {
        Level world = itemUsageContext.getLevel();
        BlockPos blockPos = itemUsageContext.getClickedPos();
        Player playerEntity = itemUsageContext.getPlayer();
        ItemStack itemStack2 = itemUsageContext.getItemInHand();
        //class_9536.method_59065(world, playerEntity, blockPos, SoundCategory.BLOCKS);
        world.setBlockAndUpdate(blockPos, blockState);
        if (world instanceof ServerLevel) {
            if (blockState.isAir()) {
                Block.popResource(world, blockPos, itemStack);
            } else {
                Block.popResourceFromFace(world, blockPos, itemUsageContext.getClickedFace(), itemStack);
            }
        }
        if (playerEntity != null) {
            itemStack2.hurtAndBreak(1, playerEntity, LivingEntity.getSlotForHand(itemUsageContext.getHand()));
        }
        if (playerEntity instanceof ServerPlayer) {
            ServerPlayer serverPlayerEntity = (ServerPlayer) playerEntity;
            //Criteria.field_50279.trigger(serverPlayerEntity);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos blockPos;
        Level world = context.getLevel();
        BlockState blockState = world.getBlockState(blockPos = context.getClickedPos());
        Block block = blockState.getBlock();
//        if (block instanceof PotatoPeelsBlock) {
//            //PotatoPeelsBlock potatoPeelsBlock = (PotatoPeelsBlock)block;
//            //return PotatoPeelerItem.interaction(context, new ItemStack(potatoPeelsBlock.getItem(), 9), Blocks.AIR.getDefaultState());
//        }
//        if (block == BlockRegistry.PEELGRASS_BLOCK && context.getSide() == Direction.UP) {
//            return PotatoPeelerItem.interaction(context, ((Item)Items.field_50594.get(class_9535.field_50703)).getDefaultStack(), Blocks.TERREDEPOMME.getDefaultState());
//        }
//        if (block == BlockRegistry.CORRUPTED_PEELGRASS_BLOCK && context.getSide() == Direction.UP) {
//            //return PotatoPeelerItem.interaction(context, Items.CORRUPTED_POTATO_PEELS.getDefaultStack(), Blocks.TERREDEPOMME.getDefaultState());
//        }
//        if (block == BlockRegistry.POISONOUS_POTATO_BLOCK) {
//            //return PotatoPeelerItem.interaction(context, new ItemStack(Items.POISONOUS_POTATO, 9), Blocks.POTATO_PEEL_BLOCKS.get(DyeColor.WHITE).getDefaultState());
//        }

        return super.useOn(context);
    }
}
