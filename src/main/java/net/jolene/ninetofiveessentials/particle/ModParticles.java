package net.jolene.ninetofiveessentials.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final SimpleParticleType PUFF =
            registerParticle("puff", FabricParticleTypes.simple());
    public static final SimpleParticleType COIN =
            registerParticle("coin", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(NineToFiveEssentials.MOD_ID, name), particleType);
    }

    public static void registerModParticles() {
        NineToFiveEssentials.LOGGER.info("Registering Particles for " + NineToFiveEssentials.MOD_ID);
    }
}