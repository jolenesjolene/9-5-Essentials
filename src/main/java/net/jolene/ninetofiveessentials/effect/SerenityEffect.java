package net.jolene.ninetofiveessentials.effect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

public class SerenityEffect extends StatusEffect {
    public SerenityEffect(StatusEffectCategory category, int color) {
        super(category, color, ParticleTypes.EFFECT);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (world.isClient) return false;

        if (entity instanceof HostileEntity hostile) {
            hostile.setTarget(null);
            hostile.setAttacking(false);
        }

        return super.applyUpdateEffect(world, entity, amplifier);
    }
}

