package net.jolene.ninetofiveessentials;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.jolene.ninetofiveessentials.particle.Coin;
import net.jolene.ninetofiveessentials.particle.ModParticles;
import net.jolene.ninetofiveessentials.particle.Puff;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.DefaultFramebufferSet;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import xyz.zetalasis.limelight.shader.api.ManagedPostShader;
import xyz.zetalasis.limelight.shader.api.ShaderType;
import xyz.zetalasis.limelight.shader.event.LimelightShaderEvents;

public class NineToFiveEssentialsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.PUFF, Puff.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.COIN, Coin.Factory::new);
        BlockRenderLayerMap.putBlock(ModBlocks.HEMP_PLANT, BlockRenderLayer.CUTOUT);

        LimelightShaderEvents.REGISTER_SHADERS.register(context -> {
            if (context.type().equals(ShaderType.POST))
            {
                context.register().accept(
                        null,
                        new ManagedPostShader.Definition(Identifier.of(NineToFiveEssentials.MOD_ID, "rainbow"), new Pair<>(null, DefaultFramebufferSet.MAIN_ONLY))
                );
            }
        });
    }
}