package net.jolene.ninetofiveessentials;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.jolene.ninetofiveessentials.particle.Coin;
import net.jolene.ninetofiveessentials.particle.ModParticles;
import net.minecraft.client.render.BlockRenderLayer;

public class NineToFiveEssentialsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.COIN, Coin.Factory::new);
        BlockRenderLayerMap.putBlock(ModBlocks.NICOTIANA_PLANT, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.NICOTIANA_TOP, BlockRenderLayer.CUTOUT);
    }
}