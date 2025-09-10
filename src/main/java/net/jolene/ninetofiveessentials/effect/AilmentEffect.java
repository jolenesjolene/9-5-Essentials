package net.jolene.ninetofiveessentials.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class AilmentEffect extends StatusEffect {
    public AilmentEffect(StatusEffectCategory category, int color) {
        super(category, color, ParticleTypes.ASH);
    }

    // Run effect logic every tick
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    // Called every tick while effect is active
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!(entity instanceof PlayerEntity player)) return;

        // Only on server side
        if (player.getWorld().isClient()) return;

        // Get the player's total age in ticks
        long ticks = player.age;

        // Every ~200 ticks (10 seconds), with randomness
        if (ticks % 200 == 0 && Math.random() < 0.5) {
            player.getWorld().playSound(
                    null,                              // Player (null means everyone hears it)
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.ENTITY_PLAYER_BREATH,  // Choose your custom or vanilla sound
                    SoundCategory.PLAYERS,
                    1.0f,                              // Volume
                    1.0f + (float)(Math.random() * 0.4 - 0.2) // Pitch variation ±0.2
            );
        }
    }
}
