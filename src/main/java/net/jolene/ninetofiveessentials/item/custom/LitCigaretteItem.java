package net.jolene.ninetofiveessentials.item.custom;


import net.jolene.ninetofiveessentials.effect.ModEffects;
import net.jolene.ninetofiveessentials.item.ModItems;
import net.jolene.ninetofiveessentials.sound.ModSounds;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LitCigaretteItem extends Item {
    private static final int MAX_AMPLIFIER = 4;
    private static final int DURATION = 12000; // 10 minutes in ticks

    public LitCigaretteItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            // Determine current amplifier
            int currentAmplifier = 0;
            StatusEffectInstance currentEffect = user.getStatusEffect(ModEffects.AILMENT);
            if (currentEffect != null) {
                currentAmplifier = currentEffect.getAmplifier() + 1;
                if (currentAmplifier > MAX_AMPLIFIER) {
                    currentAmplifier = MAX_AMPLIFIER;
                }
            }

            // Apply updated effects
            user.addStatusEffect(new StatusEffectInstance(
                    ModEffects.AILMENT,
                    DURATION,
                    currentAmplifier,
                    false,
                    true
            ));
            user.addStatusEffect(new StatusEffectInstance(
                    ModEffects.SERENTIY,
                    200,
                    0,
                    false,
                    true
            ));

            // Cooldown
            user.getItemCooldownManager().set(getDefaultStack(), 60);
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    ModSounds.PUFF, SoundCategory.MASTER,
                    0.5F, 1.9F / (world.getRandom().nextFloat() * 1.8F + 2F));

            // Particle in front of face
            Vec3d lookVec = user.getRotationVec(1.0F);
            double x = user.getX() + lookVec.x * 0.5;
            double y = user.getY() + 1.6;
            double z = user.getZ() + lookVec.z * 0.5;

            ((ServerWorld) world).spawnParticles(
                    ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    x, y, z,
                    3, 0.1, 0.1, 0.1, 0.01
            );

            // Damage item
            stack.damage(1, user);

            if (stack.getDamage() >= stack.getMaxDamage() - 1) {
                ItemStack newItem = new ItemStack(ModItems.CIGARETTE_BUTT);
                user.setStackInHand(hand, newItem);

                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        ModSounds.FIZZLE, SoundCategory.MASTER,
                        0.5F, 1.0F / (world.getRandom().nextFloat() * 0.9F + 1.1F));

                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.LAVA,
                        x, y, z,
                        5, 0, 0, 0, 0
                );
            }
        }

        return ActionResult.SUCCESS;
    }
}
