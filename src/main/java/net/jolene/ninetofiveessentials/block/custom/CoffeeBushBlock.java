package net.jolene.ninetofiveessentials.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;


public class CoffeeBushBlock extends PlantBlock implements Fertilizable {
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }
    public static final int MAX_AGE = 5;
    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);
    public static final BooleanProperty TOP = BooleanProperty.of("top");



    public CoffeeBushBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(AGE, 0)
                .with(TOP, false));
    }

    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return createCodec(CoffeeBushBlock::new);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(TOP);
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int age = state.get(AGE);
        if (age >= 3) {
            return VoxelShapes.fullCube();
        } else {
            return VoxelShapes.fullCube();
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return !state.get(TOP) && state.get(AGE) < MAX_AGE;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return !state.get(TOP) && state.get(AGE) < MAX_AGE;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (state.get(AGE) < MAX_AGE)
        {
            world.setBlockState(pos, state.with(AGE, state.get(AGE) + 1));
        }
    }
}
