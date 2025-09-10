package net.jolene.ninetofiveessentials.effect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;

public class GunpowderRushEffect extends StatusEffect {
    public GunpowderRushEffect(StatusEffectCategory category, int color) {
        super(category, color, ParticleTypes.WHITE_ASH);
    }

    // Run effect logic every tick
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
