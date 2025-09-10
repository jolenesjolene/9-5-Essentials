package net.jolene.ninetofiveessentials.effect;

import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
        public static final RegistryEntry<StatusEffect> AILMENT = registerStatusEffect("ailment",
            new AilmentEffect(StatusEffectCategory.HARMFUL, 0x36ebab)
                    .addAttributeModifier(EntityAttributes.MAX_HEALTH, Identifier.of(NineToFiveEssentials.MOD_ID, "ailment"), -2f,
                            EntityAttributeModifier.Operation.ADD_VALUE));

    public static final RegistryEntry<StatusEffect> GUNPOWDER_RUSH = registerStatusEffect("gunpowder_rush",
            new GunpowderRushEffect(StatusEffectCategory.BENEFICIAL, 0x36ebab)
                    .addAttributeModifier(EntityAttributes.MOVEMENT_SPEED,
                            Identifier.of(NineToFiveEssentials.MOD_ID, "gunpowder_rush"),
                            0.1,
                            EntityAttributeModifier.Operation.ADD_VALUE)

                    .addAttributeModifier(EntityAttributes.ATTACK_SPEED,
                            Identifier.of(NineToFiveEssentials.MOD_ID, "gunpowder_rush"),
                            5,
                            EntityAttributeModifier.Operation.ADD_VALUE)
    );
    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
            return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(NineToFiveEssentials.MOD_ID, name), statusEffect);
        }

        public static void registerEffects() {
            NineToFiveEssentials.LOGGER.info("Registering Effects for" + NineToFiveEssentials.MOD_ID);
        }
    }