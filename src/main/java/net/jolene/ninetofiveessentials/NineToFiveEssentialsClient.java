package net.jolene.ninetofiveessentials;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.util.Identifier;

public class NineToFiveEssentialsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.NICOTIANA_PLANT, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.NICOTIANA_TOP, BlockRenderLayer.CUTOUT);
    }
}