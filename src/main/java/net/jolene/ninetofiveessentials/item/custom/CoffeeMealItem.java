package net.jolene.ninetofiveessentials.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class CoffeeMealItem extends Item {
    public CoffeeMealItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        Block targetBlock = world.getBlockState(pos).getBlock();

        if (world.isClient) return ActionResult.SUCCESS;

        if (targetBlock instanceof Fertilizable fertilizable) {
            Fertilizable crop = fertilizable;
            Random random = world.getRandom();

            // Apply 3x growth
            for (int i = 0; i < 3; i++) {
                if (crop.isFertilizable(world, pos, world.getBlockState(pos))
                        && crop.canGrow(world, random, pos, world.getBlockState(pos))) {
                    crop.grow((ServerWorld) world, random, pos, world.getBlockState(pos));
                }
            }

            world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 0.9F);

            ServerWorld serverWorld = (ServerWorld) world;
            ParticleEffect coffeeEffect = ParticleTypes.EFFECT;

            for (int i = 0; i < 10; i++) {
                double offsetX = random.nextDouble() - 0.5;
                double offsetY = random.nextDouble() * 0.5;
                double offsetZ = random.nextDouble() - 0.5;
                serverWorld.spawnParticles(
                        coffeeEffect,
                        pos.getX() + 0.5 + offsetX,
                        pos.getY() + 0.5 + offsetY,
                        pos.getZ() + 0.5 + offsetZ,
                        1, 0, 0, 0, 0
                );
            }

            if (player != null && !player.isCreative()) {
                context.getStack().decrement(1);
            }

            player.incrementStat(Stats.USED.getOrCreateStat(this));
            return ActionResult.CONSUME;
        }

        return ActionResult.PASS;
    }
}
