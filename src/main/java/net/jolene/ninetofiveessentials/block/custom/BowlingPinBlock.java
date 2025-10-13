package net.jolene.ninetofiveessentials.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;


public class BowlingPinBlock extends Block {

    public static final IntProperty PIN_COUNT = IntProperty.of("pin_count", 1, 4);

    public BowlingPinBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(PIN_COUNT, 1));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PIN_COUNT);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            int currentCount = state.get(PIN_COUNT);
            if (currentCount < 4) {
                System.out.println("Pin count: " + currentCount + " → " + (currentCount + 1));
                world.setBlockState(pos, state.with(PIN_COUNT, currentCount + 1), Block.NOTIFY_ALL);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, net.minecraft.block.ShapeContext context) {
        int count = state.get(PIN_COUNT);

        VoxelShape shape = Block.createCuboidShape(5, 0, 5, 11, 16, 11); // center
        if (count >= 2) shape = VoxelShapes.union(shape, Block.createCuboidShape(2, 0, 5, 8, 16, 11));
        if (count >= 3) shape = VoxelShapes.union(shape, Block.createCuboidShape(8, 0, 2, 14, 16, 8));
        if (count >= 4) shape = VoxelShapes.union(shape, Block.createCuboidShape(2, 0, 2, 8, 16, 8));

        return shape;
    }
}

