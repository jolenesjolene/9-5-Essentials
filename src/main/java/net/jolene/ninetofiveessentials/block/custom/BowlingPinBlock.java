package net.jolene.ninetofiveessentials.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class BowlingPinBlock extends Block {
    public BowlingPinBlock(Settings settings) {
        super(settings
        );
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(5, 0, 5, 11, 16, 11); // 8x12x8
    }
}
