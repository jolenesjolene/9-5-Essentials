package net.jolene.ninetofiveessentials.item.custom;


import net.jolene.ninetofiveessentials.effect.ModEffects;
import net.jolene.ninetofiveessentials.item.ModItems;
import net.jolene.ninetofiveessentials.particle.ModParticles;
import net.jolene.ninetofiveessentials.sound.ModSounds;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.random.RandomSplitter;
import net.minecraft.world.World;

public class LitFunkyCigaretteItem extends Item {
    private static final int MAX_AMPLIFIER = 4;
    private static final int DURATION = 600; // 10 minutes in ticks
    private static final double PUFF_VELOCITY = 0.05; // how fast the smoke moves outward
    private static final double RETURN_PAPER_CHANCE = 0.25;

    private static final Random RANDOM = new Random() {
        @Override
        public Random split() {
            return null;
        }

        @Override
        public RandomSplitter nextSplitter() {
            return null;
        }

        @Override
        public void setSeed(long seed) {

        }

        @Override
        public int nextInt() {
            return 0;
        }

        @Override
        public int nextInt(int bound) {
            return 0;
        }

        @Override
        public long nextLong() {
            return 0;
        }

        @Override
        public boolean nextBoolean() {
            return false;
        }

        @Override
        public float nextFloat() {
            return 0;
        }

        @Override
        public double nextDouble() {
            return 0;
        }

        @Override
        public double nextGaussian() {
            return 0;
        }
    };

    public LitFunkyCigaretteItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            // Determine amplifier
            int currentAmplifier = 0;
            StatusEffectInstance currentEffect = user.getStatusEffect(ModEffects.SERENTIY);
            if (currentEffect != null) {
                currentAmplifier = currentEffect.getAmplifier() + 1;
                if (currentAmplifier > MAX_AMPLIFIER) {
                    currentAmplifier = MAX_AMPLIFIER;
                }
            }

            // Apply serenity effect
            user.addStatusEffect(new StatusEffectInstance(
                    ModEffects.SERENTIY,
                    DURATION,
                    currentAmplifier,
                    false,
                    true
            ));

            // Cooldown
            user.getItemCooldownManager().set(getDefaultStack(), 60);

            // Sound puff
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    ModSounds.PUFF, SoundCategory.MASTER,
                    0.5F, 1.9F / (world.getRandom().nextFloat() * 1.8F + 2F));

            // Particle
            Vec3d lookVec = user.getRotationVec(1.0F);
            double x = user.getX() + lookVec.x * 0.5;
            double y = user.getY() + 1.6;
            double z = user.getZ() + lookVec.z * 0.5;

            // give the puff a small velocity in the look direction so it drifts
            double velocityX = lookVec.x * PUFF_VELOCITY;
            double velocityY = lookVec.y * PUFF_VELOCITY;
            double velocityZ = lookVec.z * PUFF_VELOCITY;

            ((ServerWorld) world).spawnParticles(
                    ModParticles.PUFF,
                    x, y, z,
                    1, // one particle
                    0, 0, 0,
                    0
            );

            // set the velocity manually
            ((ServerWorld) world).spawnParticles(
                    ModParticles.PUFF,
                    x, y, z,
                    0, // no random spread
                    velocityX, velocityY, velocityZ,
                    0
            );

            // Damage item
            stack.damage(1, user);


            // When fully used up
            if (stack.getDamage() >= stack.getMaxDamage() - 1) {
                if (RANDOM.nextDouble() < RETURN_PAPER_CHANCE) {
                    // 25% chance to return paper
                    user.setStackInHand(hand, new ItemStack(net.minecraft.item.Items.PAPER));
                } else {
                    user.setStackInHand(hand, ItemStack.EMPTY);
                }

                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        ModSounds.FIZZLE, SoundCategory.MASTER,
                        0.5F, 1.0F / (world.getRandom().nextFloat() * 0.9F + 1.1F));

                ((ServerWorld) world).spawnParticles(
                        net.minecraft.particle.ParticleTypes.LAVA,
                        x, y, z,
                        5, 0, 0, 0, 0
                );
            }
        }

        return ActionResult.SUCCESS;
    }
}