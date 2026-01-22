package net.jolene.ninetofiveessentials.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;

public class AilmentEffect extends StatusEffect {
    public AilmentEffect(StatusEffectCategory category, int color) {
        super(category, color, ParticleTypes.ASH);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}
