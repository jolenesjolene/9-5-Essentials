package net.jolene.ninetofiveessentials.effect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;

public class OvercaffeinatedEffect extends StatusEffect {
    public static final Identifier MODIFIER_ID_1 =
            Identifier.of("ninetofiveessentials", "overcaffeinated");
    public static final Identifier MODIFIER_ID_2 =
            Identifier.of("ninetofiveessentials", "overcaffeinated");
    public OvercaffeinatedEffect(StatusEffectCategory category, int color) {
        super(category, color);

        this.addAttributeModifier(
                EntityAttributes.MOVEMENT_SPEED,
                MODIFIER_ID_1,
                0.25,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                EntityAttributes.ATTACK_SPEED,
                MODIFIER_ID_2,
                0.75,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }
}