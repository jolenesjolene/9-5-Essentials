package net.jolene.ninetofiveessentials.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class CoffeeGumItem extends Item {
    public CoffeeGumItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        World world = player.getWorld();

        if (entity instanceof WolfEntity wolf) {
            if (wolf.isTamed() && wolf.getHealth() < wolf.getMaxHealth()) {
                wolf.heal(1.0F);

                world.playSound(null, wolf.getX(), wolf.getY(), wolf.getZ(),
                        SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.NEUTRAL,
                        1.0F, 1.0F);

                if (world instanceof ServerWorld serverWorld) {
                    ItemStack gumStack = new ItemStack(this);
                    ItemStackParticleEffect gumParticles = new ItemStackParticleEffect(ParticleTypes.ITEM, gumStack);

                    for (int i = 0; i < 8; i++) {
                        double offsetX = (world.getRandom().nextDouble() - 0.5) * 0.3;
                        double offsetY = world.getRandom().nextDouble() * 0.4 + 0.4;
                        double offsetZ = (world.getRandom().nextDouble() - 0.5) * 0.3;

                        serverWorld.spawnParticles(
                                gumParticles,
                                wolf.getX() + offsetX,
                                wolf.getY() + offsetY + 0.5,
                                wolf.getZ() + offsetZ,
                                1, 0, 0, 0, 0.01
                        );
                    }
                }

                // Consume item
                if (!player.isCreative()) {
                    stack.decrement(1);
                }

                player.incrementStat(Stats.USED.getOrCreateStat(this));
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }
}


