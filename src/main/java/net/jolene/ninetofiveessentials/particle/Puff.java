package net.jolene.ninetofiveessentials.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

public class Puff extends BillboardParticle {
    protected Puff(ClientWorld world, double x, double y, double z, Sprite sprite) {
        super(world, x, y, z, sprite);

        this.velocityMultiplier = 0.8f;
        this.maxAge = 40;

        this.scale(2.0f);

        //this.setSpriteForAge(spriteProvider);

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;

        this.alpha = 0.0f;

        this.velocityX = 0.0;
        this.velocityY = 0.01;
        this.velocityZ = 0.0;

        this.gravityStrength = 0.0f;
    }

    @Override
    public void tick() {
        super.tick();

        //this.setSpriteForAge(this.spriteProvider);

        int fadeDuration = 5;

        if (this.age < fadeDuration) {
            this.alpha = (float) this.age / fadeDuration;
        } else if (this.age > this.maxAge - fadeDuration) {
            this.alpha = (float) (this.maxAge - this.age) / fadeDuration;
        } else {
            this.alpha = 1.0f;
        }

        this.velocityY += 0.0005;
    }

    @Override
    protected RenderType getRenderType() {
        return RenderType.PARTICLE_ATLAS_TRANSLUCENT;
    }

    @Override
    public int getBrightness(float tickDelta) {
        return 0xF000F0;
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @org.jspecify.annotations.Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Random random) {
            return new Puff(world, x, y, z, this.spriteProvider.getFirst());
        }
    }
}
