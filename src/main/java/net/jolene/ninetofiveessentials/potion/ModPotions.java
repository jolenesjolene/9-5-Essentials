package net.jolene.ninetofiveessentials.potion;

import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotions {
        public static final RegistryEntry<Potion> COFFEE = registerPotion(
                new Potion("coffee",
                        new StatusEffectInstance(ModEffects.OVERCAFFEINATED, 600, 0)
                        ));


        private static RegistryEntry<Potion> registerPotion(Potion potion) {
            return Registry.registerReference(Registries.POTION, Identifier.of(NineToFiveEssentials.MOD_ID, "coffee"), potion);
        }


    public static void registerModPotions() {
        NineToFiveEssentials.LOGGER.info("Registering Potions for " + NineToFiveEssentials.MOD_ID);
    }
}
