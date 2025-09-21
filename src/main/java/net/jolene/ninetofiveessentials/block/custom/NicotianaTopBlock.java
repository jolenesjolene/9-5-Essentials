package net.jolene.ninetofiveessentials.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class NicotianaTopBlock extends Block {
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }
    public static final IntProperty AGE = IntProperty.of("age", 0, 2);

    public NicotianaTopBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return false;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState below = world.getBlockState(pos.down());
        return below.getBlock() instanceof NicotianaPlantBlock &&
                below.get(NicotianaPlantBlock.AGE) >= 3;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state,
                           BlockEntity blockEntity, ItemStack stack) {
        BlockPos below = pos.down();
        BlockState belowState = world.getBlockState(below);

        if (belowState.getBlock() instanceof NicotianaPlantBlock) {
            world.breakBlock(below, true, player);
        }

        super.afterBreak(world, player, pos, state, blockEntity, stack);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        BlockPos below = pos.down();
        BlockState belowState = world.getBlockState(below);
        if (belowState.getBlock() instanceof NicotianaPlantBlock &&
                belowState.get(NicotianaPlantBlock.AGE) >= 3) {
            world.breakBlock(below, false);
        }

        super.onBroken(world, pos, state);
    }
}

