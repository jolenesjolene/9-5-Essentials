package net.jolene.ninetofiveessentials.effect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

public class JittersEffect extends StatusEffect {
    public static final Identifier MODIFIER_ID =
            Identifier.of("ninetofiveessentials", "jitters");
    public JittersEffect(StatusEffectCategory category, int color) {
        super(category, color);

        this.addAttributeModifier(
                EntityAttributes.BLOCK_BREAK_SPEED,
                MODIFIER_ID,
                -1.0,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }
}