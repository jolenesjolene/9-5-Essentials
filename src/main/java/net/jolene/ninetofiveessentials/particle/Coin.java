package net.jolene.ninetofiveessentials.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class Coin extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    public Coin(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
        super(world, x, y, z);

        this.spriteProvider = spriteProvider;

        Random random = world.getRandom();

        double speed = 0.05 + random.nextDouble() * 0.075;
        double angle = random.nextDouble() * 2 * Math.PI;
        double vzAngle = (random.nextDouble() - 0.5) * Math.PI / 3;

        this.velocityX = speed * Math.cos(angle) * Math.cos(vzAngle);
        this.velocityY = speed * Math.sin(vzAngle);
        this.velocityZ = speed * Math.sin(angle) * Math.cos(vzAngle);

        this.gravityStrength = 0.48f;

        this.maxAge = 80;

        this.scale = 0.3f;  // much smaller size now
        this.setSprite(spriteProvider.getSprite(Random.create(0)));

        this.alpha = 1.0f;

        this.collidesWithWorld = true;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.onGround) {
            this.velocityY = Math.abs(this.velocityY);

            this.velocityX *= 0.7;
            this.velocityZ *= 0.7;

            if (Math.abs(this.velocityY) < 0.01) {
                this.velocityY = 0;
                this.velocityX = 0;
                this.velocityZ = 0;
            } else {
                this.velocityY *= 0.5;
            }
        }

        if (this.age > this.maxAge - 20) {
            float fadeProgress = (float)(this.age - (this.maxAge - 20)) / 20f;
            this.alpha = 1.0f - fadeProgress;
            if (this.alpha < 0) this.alpha = 0;
        } else {
            this.alpha = 1.0f;
        }

        this.scale = 0.3f;
    }

    public float getAlpha(float tickDelta) {
        return this.alpha;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;  // translucent for fading
    }

    @Override
    public int getBrightness(float tickDelta) {
        return 0xF000F0;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ) {
            return new Coin(world, x, y, z, this.spriteProvider);
        }
    }
}

